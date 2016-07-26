package com.example.refreshlistmodule.util;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsynImageLoader {
	private static final String TAG = "AsynImageLoader";

	public static final String CACHE_DIR = "/your_dir";

	private Map<String, SoftReference<Bitmap>> caches;

	private List<Task> taskQueue;
	private boolean isRunning = false;

	public AsynImageLoader() {
		super();
		caches = new HashMap<String, SoftReference<Bitmap>>();
		taskQueue = new ArrayList<Task>();
		isRunning = true;
		new Thread(runnable).start();
	}

	public void showImageAsyn(ImageView imageView, String url, int resId) {
		imageView.setTag(url);
		Bitmap bitmap = loadImageAsyn(url, getImageCallback(imageView, resId));

		if (bitmap == null) {
			imageView.setImageResource(resId);
		} else {
			imageView.setImageBitmap(bitmap);
		}
	}

	public Bitmap loadImageAsyn(String path, ImageCallback callback) {
		// 判断缓存中是否已经存在该图片
		if (caches.containsKey(path)) {
			// 取出软引用
			SoftReference<Bitmap> rf = caches.get(path);
			// 通过软引用，获取图片
			Bitmap bitmap = rf.get();
			// 如果该图片已经被释放，则将该path对应的键从Map中移除掉
			if (bitmap == null) {
				caches.remove(path);
			} else {
				// 如果图片未被释放，直接返回该图片
				Log.i(TAG, "return image in cache" + path);
				return bitmap;
			}
		} else {
			// 如果缓存中不常在该图片，则创建图片下载任务
			Task task = new Task();
			task.path = path;
			task.callback = callback;
			Log.i(TAG, "new Task ," + path);
			if (!taskQueue.contains(task)) {
				taskQueue.add(task);
				// 唤醒任务下载队列
				synchronized (runnable) {
					runnable.notify();
				}
			}
		}

		// 缓存中没有图片则返回null
		return null;
	}

	private ImageCallback getImageCallback(final ImageView imageView,
			final int resId) {
		return new ImageCallback() {

			@Override
			public void loadImage(String path, Bitmap bitmap) {
				if (path.equals(imageView.getTag().toString())) {
					imageView.setImageBitmap(bitmap);
				} else {
					imageView.setImageResource(resId);
				}
			}
		};
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Task task = (Task) msg.obj;
			task.callback.loadImage(task.path, task.bitmap);
		};

	};
	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				while (taskQueue.size() > 0) {
					Task task = taskQueue.remove(0);
					try {
						task.bitmap = PicUtil.getbitmapAndwrite1(task.path);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.i("AsynImageLoader", task.bitmap.toString());
					caches.put(task.path,
							new SoftReference<Bitmap>(task.bitmap));
					if (handler != null) {
						Message msg = handler.obtainMessage();
						msg.obj = task;
						handler.sendMessage(msg);

					}

				}
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	};

	public interface ImageCallback {
		void loadImage(String path, Bitmap bitmap);

	}

	class Task {
		String path;
		Bitmap bitmap;
		ImageCallback callback;

		@Override
		public boolean equals(Object o) {
			// TODO Auto-generated method stub
			Task task = (Task) o;
			return task.path.equals(path);
		}

	}

}
