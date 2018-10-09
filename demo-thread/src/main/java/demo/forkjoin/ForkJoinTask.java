package demo.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTask extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long THRESHOLD = 10000;// 临界值

	public ForkJoinTask(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) {
			// 不大于临界值直接计算和
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		} else {
			// 大于临界值继续进行拆分子任务
			long mid = (start + end) / 2;

			// 拆分子任务
			ForkJoinTask calculator1 = new ForkJoinTask(start, mid);
			calculator1.fork();

			// 拆分子任务
			ForkJoinTask calculator2 = new ForkJoinTask(mid + 1, end);
			calculator2.fork();

			// 合并子任务结果
			return calculator1.join() + calculator2.join();
		}
	}

}
