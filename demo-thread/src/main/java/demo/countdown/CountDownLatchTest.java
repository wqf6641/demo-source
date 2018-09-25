package demo.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ģ����̲߳�������
 * 
 * @author wengqf
 *
 */
public class CountDownLatchTest {

	static int threads = 200;// �̲߳�����

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(threads);
		for (int i = 0; i < threads; i++) {
			Thread.currentThread().setName("" + i);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("��ǰ�߳�:" + Thread.currentThread().getName() + "׼������, �ȴ�latchΪ0��ʼ����....");
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println("��ǰ�߳�:" + Thread.currentThread().getName() + "�ȴ�����, ��ʼģ��web�û����� ��ʼʱ��:"
								+ System.currentTimeMillis());
					}
				}
			}).start();
		}

		// �ȴ����룬�ȴ������̳߳�ʼ����ɡ�
		Thread.sleep(2000);
		System.out.println("���߳�:" + Thread.currentThread().getName() + "countdown�Լ���ʼ....");
		for (int i = 0; i < threads; i++) {
			latch.countDown();// ȫ��ͬʱִ��
		}
		System.out.println("���߳�:" + Thread.currentThread().getName() + "countdown�Լ����....");
	}
}
