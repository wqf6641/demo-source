package demo.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTask extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long THRESHOLD = 10000;// �ٽ�ֵ

	public ForkJoinTask(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) {
			// �������ٽ�ֱֵ�Ӽ����
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		} else {
			// �����ٽ�ֵ�������в��������
			long mid = (start + end) / 2;

			// ���������
			ForkJoinTask calculator1 = new ForkJoinTask(start, mid);
			calculator1.fork();

			// ���������
			ForkJoinTask calculator2 = new ForkJoinTask(mid + 1, end);
			calculator2.fork();

			// �ϲ���������
			return calculator1.join() + calculator2.join();
		}
	}

}
