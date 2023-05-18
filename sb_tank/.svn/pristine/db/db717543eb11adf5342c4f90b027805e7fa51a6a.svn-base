package com.vo;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;

import javax.swing.SwingUtilities;

/**
 *	敌军坦克的行进策略——胡乱走、胡乱发射
 *
 * @author zhangzhen
 * @date 2022年10月4日
 *
 */
public class DT extends Thread{

	public static final int SLEEP_MS = 10;

	private final FP fp;

	private final N nnnn;

	public DT(final FP fp, final N diN) {
		this.fp = fp;
		this.nnnn = diN;
	}

	@Override
	public void run() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "DT.run()");

		final LD ld = this.fp.getLd();

		while (true) {

			final FE fe = DT.this.randomFE();
			this.nnnn.setFe(fe);
			System.out.println(fe);

			final int step = F.STEP;
			final int w = N.WIDTH / step;
			System.out.println("nk = " + w);
			for (int k = 1; k <= w; k++) {
				DT.this.sleep();
				this.repaint();

				if (fe == FE.L) {
					if (this.nnnn.getX() >= 0) {
						final int xH = (this.nnnn.getX() / N.WIDTH)  - 1;
						final int xH2 = xH < 0 ? 0 : xH;
						final int yH = (this.nnnn.getY() / N.HEIGHT);


						final N nX = ld.getD()[yH][xH2];
						if (this.nX_bukeTankchuanuo(nX)) {
							continue;
						}

						final int newXXX = this.nnnn.getX() - step;
						this.nnnn.setX(newXXX);
					}
				} else if (fe == FE.R) {

					if (this.nnnn.getX() + N.WIDTH <= F.G_WIDTH) {
						final int xH = (this.nnnn.getX() / N.WIDTH) + 1;
						final int xH2 = xH >= N.X_NUM ? N.X_NUM - 1 : xH;
						final int yH = (this.nnnn.getY() / N.HEIGHT);


						final N nX = ld.getD()[yH][xH2];
						if (this.nX_bukeTankchuanuo(nX)) {
							continue;
						}

						this.nnnn.setX(this.nnnn.getX() + step);
					}
				} else if (fe == FE.U) {
					if (this.nnnn.getY() >= 0) {

						final int xH = (this.nnnn.getX() / N.WIDTH) ;

						final int yUM = this.nnnn.getY() % N.HEIGHT;
						final int yUT1 = this.nnnn.getY();
						if (yUM > N.HEIGHT / 2) {
						}

						final int yH = (yUT1 / N.HEIGHT)  - 1;
						final int yH2 = yH < 0 ? 0 : yH;


						final N nX = ld.getD()[yH2][xH];
						if (this.nX_bukeTankchuanuo(nX)) {
							continue;
						}

						this.nnnn.setY(this.nnnn.getY() - step);
					}
				} else if ((fe == FE.D) && (this.nnnn.getY() + N.HEIGHT + N.HEIGHT / 2 <= F.G_HEIGHT)) {
					final int xH = (this.nnnn.getX() / N.WIDTH) ;
					final int yH = (this.nnnn.getY() / N.HEIGHT)  + 1;
					final int yH2 = yH >= N.Y_NUM ? N.Y_NUM -1 : yH;

					System.out.println("xh = " + xH + "\t" + "yH = " + yH);

					final N nX = ld.getD()[yH2][xH];
					if (this.nX_bukeTankchuanuo(nX)) {
						continue;
					}

					this.nnnn.setY(this.nnnn.getY() + step);
				}
			}

		}

	}

	private void repaint() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					DT.this.fp.repaint();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}


	private boolean nX_bukeTankchuanuo(final N nX) {
		return nX != null && !nX.getNre().isKeTankChuanGuo();
	}

	private FE randomFE() {
		final FE[] fev = FE.values();
		final int i = this.random.nextInt(fev.length);
		final FE fe = fev[i];
		return fe;
	}
	final SecureRandom random =new SecureRandom();

	private void sleep() {
		try {
			Thread.sleep(DT.SLEEP_MS);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
