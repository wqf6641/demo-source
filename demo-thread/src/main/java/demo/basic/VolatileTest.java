package demo.basic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class VolatileTest {

	static volatile int num = 0;
	static SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.sss");

	public static void main(String[] args) throws InterruptedException, IOException {
		for (int i = 1; i <= 2; i++) {
			int m = i;
			new Thread() {
				public void run() {
					while (true) {
						num = new Random().nextInt(1000) * m;
						System.out.println("�߳�" + Thread.currentThread().getId() + "д--ʱ�䣺" + fmt.format(new Date())
								+ ",���ݣ�" + num);
						try {
							Thread.sleep(1000 * (m + 1));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
			}.start();
		}
		new Thread() {
			public void run() {
				while (true) {
					System.out.println(
							"�߳�" + Thread.currentThread().getId() + "��--ʱ�䣺" + fmt.format(new Date()) + ",���ݣ�" + num);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
}
