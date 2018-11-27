package demo.executor;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ThreadPoolExecutorTest {

	@Test
	public void testNewFix() throws IOException {
		AtomicInteger count = new AtomicInteger(0);
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, workQueue,
				new ThreadPoolExecutor.AbortPolicy(){
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
				System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHH");
			}
		});
//		 ExecutorService pool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 20; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					System.out.println("�߳� [" + Thread.currentThread().getId() + "] ��ʼ�ɻ����");
					try {
						if (workQueue.size() > 0) {
							System.out.println("----------------�������������߳���" + workQueue.size());
						}
//						Thread.sleep(100);
						count.incrementAndGet();
						System.out.println("�߳� [" + Thread.currentThread().getId() + "] ������������������===================" + count.get());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			pool.execute(runnable);
		}
		System.in.read();
	}
}
