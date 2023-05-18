package com.vo;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
public class FPWoT extends Thread {

	private final FP fp;

	public FPWoT(final FP fp) {
		this.fp = fp;
	}

	@Override
	public void run() {
		System.out.println(
				java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "FPWoT.run()");

		while (true) {
//			final N wo = this.fp.getWo();

			this.fp.repaint();

			try {
				Thread.sleep(1);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
