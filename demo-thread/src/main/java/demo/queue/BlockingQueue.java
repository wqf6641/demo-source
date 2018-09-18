package demo.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 手写带阻塞的线程安全队列
 * @author wengqf
 *
 */
public class BlockingQueue {

	private List<String> queueList = new ArrayList<>();

	private int maxSize;

	private Object lock = new Object();

	public BlockingQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	public void put(String ele) {
		synchronized (lock) {
			try {
				if (queueList.size() == maxSize) {
					System.out.println("队列已满，put等待中...");
					lock.wait();// 等待后 释放锁
				}
				queueList.add(ele);
				System.out.println("元素:" + ele + " 已进入队列！！！");
				lock.notifyAll();// 唤醒其他线程，不释放锁
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void get() {
		synchronized (lock) {
			try {
				if (queueList.size() == 0) {
					System.out.println("队列已空，get等待中...");
					lock.wait();// 等待后 释放锁
				}
				String res = queueList.get(0);
				System.out.println("取出元素：" + res);
				queueList.remove(res);
				lock.notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final BlockingQueue queue = new BlockingQueue(5);
		new Thread(new Runnable() {

			@Override
			public void run() {
				queue.put("1");
				queue.put("2");
				queue.put("3");
				queue.put("4");
				queue.put("5");
				queue.put("6");
			}
		}, "PUT THREAD").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				queue.get();
				queue.get();
				queue.get();
				queue.get();
				queue.get();
				queue.get();
				queue.get();
				queue.get();
			}
		}, "GET THREAD").start();
	}
}
