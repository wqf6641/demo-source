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
						System.out.println("线程" + Thread.currentThread().getId() + "写--时间：" + fmt.format(new Date())
								+ ",内容：" + num);
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
							"线程" + Thread.currentThread().getId() + "读--时间：" + fmt.format(new Date()) + ",内容：" + num);
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
