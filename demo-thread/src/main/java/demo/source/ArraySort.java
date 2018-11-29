package demo.source;

import java.util.Arrays;

public class ArraySort implements Runnable{
	private int num;

	public ArraySort(int num) {
		super();
		this.num = num;
	}

	public static void main(String[] args) {
		int[] arrs = {3,88,77,65,34,29,5874,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,8};
		Arrays.sort(arrs);
		for (int i : arrs) {
			System.out.println(i);
		}
		System.out.println();
		for (int i = 0,len = arrs.length; i < len; i++) {
			new Thread(new ArraySort(arrs[i])).start();
		}
	}
	

	@Override
	public void run() {
		try {
			Thread.sleep(num);
			System.out.println(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
