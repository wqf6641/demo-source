package demo.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * ��д���������̰߳�ȫ����
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
					System.out.println("����������put�ȴ���...");
					lock.wait();// �ȴ��� �ͷ���
				}
				queueList.add(ele);
				System.out.println("Ԫ��:" + ele + " �ѽ�����У�����");
				lock.notifyAll();// ���������̣߳����ͷ���
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void get() {
		synchronized (lock) {
			try {
				if (queueList.size() == 0) {
					System.out.println("�����ѿգ�get�ȴ���...");
					lock.wait();// �ȴ��� �ͷ���
				}
				String res = queueList.get(0);
				System.out.println("ȡ��Ԫ�أ�" + res);
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
