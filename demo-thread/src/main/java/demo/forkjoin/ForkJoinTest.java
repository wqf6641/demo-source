package demo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.junit.Test;

public class ForkJoinTest {
	@Test
	public void test() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask task = new ForkJoinTask(1, 100000000000000L);
//		BigDecimal sum = forkJoinPool.invoke(task);
		Future<Long> sum = forkJoinPool.submit(task);
		System.out.println(sum.get());

		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	@Test
	public void test1() {
		long start = System.currentTimeMillis();

		long sum = 0;
		for (long i = 1; i <= 100000000000000L; i++) {
			sum += i;
		}
		System.out.println(sum);

		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
