package demo.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 模拟多线程并发访问
 * 
 * @author wengqf
 *
 */
public class CountDownLatchTest {

	static int threads = 200;// 线程并发数

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(threads);
		for (int i = 0; i < threads; i++) {
			Thread.currentThread().setName("" + i);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("当前线程:" + Thread.currentThread().getName() + "准备就绪, 等待latch为0后开始运行....");
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println("当前线程:" + Thread.currentThread().getName() + "等待结束, 开始模拟web用户请求， 开始时间:"
								+ System.currentTimeMillis());
					}
				}
			}).start();
		}

		// 等待两秒，等待并发线程初始化完成。
		Thread.sleep(2000);
		System.out.println("主线程:" + Thread.currentThread().getName() + "countdown自减开始....");
		for (int i = 0; i < threads; i++) {
			latch.countDown();// 全部同时执行
		}
		System.out.println("主线程:" + Thread.currentThread().getName() + "countdown自减完成....");
	}
}
