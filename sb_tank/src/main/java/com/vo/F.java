package com.vo;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * 启动类
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
public class F extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int STEP = 1;

	private static final String TITLE = "tank游戏 WASD控制方向，J攻击";

	public static final int G_HEIGHT = N.HEIGHT * N.Y_NUM;
	public static final int G_WIDTH = N.WIDTH * N.X_NUM;

	public static void main(final String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(() -> {
			final F f = new F();
			f.setVisible(true);
		});

	}

	public F() {
		this.setTitle(F.TITLE);
		this.setSize(F.G_WIDTH + N.WIDTH, F.G_HEIGHT + N.HEIGHT);
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final FP fp = new FP();
		final Container p = this.getContentPane();

		fp.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(final KeyEvent e) {

				if (fp.getWo().getFae() == FAE.L_A || fp.getWo().getFae() == FAE.U_A || fp.getWo().getFae() == FAE.R_A
						|| fp.getWo().getFae() == FAE.D_A) {
					return;
				}

				final int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_W:
					if (fp.getWo().getFae() != FAE.U) {
						return;
					}

					fp.getWo().setFae(FAE.U_A);

					final int y = fp.getWo().getY();

					final int yM = y % N.HEIGHT;
					// y %N.HEIGHT > 0，说明已进入上面一个格子了，那就一直移动到正好放进上面那个格子里
					if (yM == 0) {
						break;
					}
					if (yM > 0) {
						final Thread threadW = new Thread(() -> {
							for (int y1 = y; fp.getWo().getFae() == FAE.U_A && y1 >= y / N.HEIGHT * N.HEIGHT; y1 -= 1) {
								fp.getWo().setY(y1);
								F.sleep();
								fp.repaint();
							}
							fp.getWo().setFae(FAE.NULL);
						});
						threadW.start();
					}

					break;
				case KeyEvent.VK_A:
					if (fp.getWo().getFae() != FAE.L) {
						return;
					}

					fp.getWo().setFae(FAE.L_A);
					final int x = fp.getWo().getX();
					if (x <= 0) {
						break;
					}
					final int xL = x % N.WIDTH;
					if (xL > 0) {
						final Thread threadA = new Thread(() -> {
							for (int x1 = x; fp.getWo().getFae() == FAE.L_A && x1 >= x / N.WIDTH * N.WIDTH; x1 -= 1) {
								fp.getWo().setX(x1);
								F.sleep();
								fp.repaint();
							}
							fp.getWo().setFae(FAE.NULL);
						});
						threadA.start();
					}

					break;

				case KeyEvent.VK_S:
					if (fp.getWo().getFae() != FAE.D) {
						return;
					}

					fp.getWo().setFae(FAE.D_A);

					final int yS = fp.getWo().getY();
					System.out.println("wo.y = " + yS);

					final int yMS = yS % N.HEIGHT;
					System.out.println("y % N.HEIGHT = " + yMS);
					System.out.println("y / N.HEIGHT = " + yS / N.HEIGHT);
					// y %N.HEIGHT > 0，说明已进入上面一个格子了，那就一直移动到正好放进上面那个格子里
					if (yMS == 0) {
						break;
					}
					if (yMS > 0) {
						final Thread threadW = new Thread(() -> {
							for (int y1 = yS; fp.getWo().getFae() == FAE.D_A
									&& y1 <= yS / N.HEIGHT * N.HEIGHT + N.HEIGHT; y1 += 1) {
								fp.getWo().setY(y1);
								F.sleep();
								fp.repaint();
							}
							fp.getWo().setFae(FAE.NULL);
						});
						threadW.start();
					}
					break;
				case KeyEvent.VK_D:
					if (fp.getWo().getFae() != FAE.R) {
						return;
					}

					fp.getWo().setFae(FAE.R_A);

					final int x2 = fp.getWo().getX();
					if (x2 <= 0) {
						break;
					}
					final int xR = x2 % N.WIDTH;
					if (xR > 0) {
						final Thread threadA = new Thread(() -> {
							for (int x1 = x2; fp.getWo().getFae() == FAE.R_A
									&& x1 <= x2 / N.WIDTH * N.WIDTH + N.WIDTH; x1++) {
								fp.getWo().setX(x1);
								F.sleep();
								fp.repaint();
							}
							fp.getWo().setFae(FAE.NULL);
						});
						threadA.start();
					}

					break;

				default:
					break;
				}
			}

			@Override
			public void keyPressed(final KeyEvent e) {
				final int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_W:
					if (fp.getWo().getFae() == FAE.U || fp.getWo().getFae() == null) {
						return;
					}
					fp.getWo().setFe(FE.U);
					fp.getWo().setFae(FAE.U);
					final Thread thread = new Thread(() -> {
						boolean jixu = true;
						while (fp.getWo().getFae() == FAE.U) {

							final N wo = fp.getWo();
							final int newY = wo.getY() - F.STEP;
							final List<N> qita = fp.getOther();
							for (final N q : qita) {
								if (!q.getNre().isKeTankChuanGuo() && Math.abs(newY - q.getY()) < N.HEIGHT
										&& Math.abs(wo.getX() - q.getX()) < N.WIDTH) {
									System.out
											.println("不能向上移动，碰到了 " + q.getName() + "\t" + " x = " + q.getX() + "\t y = "
													+ q.getY() + "\t wo.x = " + wo.getX() + "\two.y = " + wo.getY());
									fp.repaint();
									jixu = false;
								}
							}
							if (newY < N.HEIGHT - N.HEIGHT) {
								System.out.println("到最上边了,return;");
								fp.repaint();
								jixu = false;
							}
							if (!jixu) {
								fp.getWo().setFae(FAE.U);
								break;
							}
							wo.setY(newY);
							F.sleep();
							fp.repaint();
						}
					});
					thread.start();
					break;

				case KeyEvent.VK_A:
					if (fp.getWo().getFae() == FAE.L) {
						return;
					}
					fp.getWo().setFe(FE.L);
					fp.getWo().setFae(FAE.L);
					final Thread threadA = new Thread(() -> {
						boolean jixu = true;
						while (fp.getWo().getFae() == FAE.L) {
							final N wo3 = fp.getWo();
							final int newX = wo3.getX() - F.STEP;

							final List<N> qitaA = fp.getOther();
							for (final N q : qitaA) {
								if (!q.getNre().isKeTankChuanGuo() && Math.abs(newX - q.getX()) < N.WIDTH
										&& Math.abs(wo3.getY() - q.getY()) < N.HEIGHT) {
									System.out
											.println("不能向左移动，碰到了 " + q.getName() + "\t" + " x = " + q.getX() + "\t y = "
													+ q.getY() + "\t wo.x = " + wo3.getX() + "\two.y = " + wo3.getY());
									fp.repaint();
									jixu = false;
								}

								if (newX < N.WIDTH - N.WIDTH) {
									System.out.println("到最左边了,return;");
									fp.repaint();
									jixu = false;
								}
							}

							if (!jixu) {
								fp.getWo().setFae(FAE.L);
								break;
							}
							wo3.setX(newX);
							F.sleep();
							fp.repaint();
						}
					});
					threadA.start();

					break;

				case KeyEvent.VK_S:
					if (fp.getWo().getFae() == FAE.D) {
						return;
					}
					fp.getWo().setFe(FE.D);
					fp.getWo().setFae(FAE.D);
					final Thread threadS = new Thread(() -> {
						boolean jixu = true;
						while (fp.getWo().getFae() == FAE.D) {

							final N wo1 = fp.getWo();
							final int newYS = wo1.getY() + F.STEP;

							final List<N> qita2 = fp.getOther();
							for (final N q : qita2) {
								if (!q.getNre().isKeTankChuanGuo() && Math.abs(newYS - q.getY()) < N.HEIGHT
										&& Math.abs(wo1.getX() - q.getX()) < N.WIDTH) {
									System.out
											.println("不能向下移动，碰到了 " + q.getName() + "\t" + " x = " + q.getX() + "\t y = "
													+ q.getY() + "\t wo.x = " + wo1.getX() + "\two.y = " + wo1.getY());
									fp.repaint();
									jixu = false;
								}

								if (newYS > N.Y_NUM * N.HEIGHT - N.HEIGHT) {
									System.out.println("下到底了,return;");
									fp.repaint();
									jixu = false;
								}

							}

							if (!jixu) {
								fp.getWo().setFae(FAE.D);
								break;
							}
							wo1.setY(newYS);
							F.sleep();
							fp.repaint();
						}
					});
					threadS.start();

					break;

				case KeyEvent.VK_D:
					if (fp.getWo().getFae() == FAE.R) {
						return;
					}
					fp.getWo().setFe(FE.R);
					fp.getWo().setFae(FAE.R);
					final Thread threadD = new Thread(() -> {

						boolean jixu = true;
						while (fp.getWo().getFae() == FAE.R) {

							final N wo4 = fp.getWo();
							final int newXD = wo4.getX() + F.STEP;

							final List<N> qitaD = fp.getOther();
							for (final N q : qitaD) {
								if (!q.getNre().isKeTankChuanGuo() && Math.abs(newXD - q.getX()) < N.WIDTH
										&& Math.abs(wo4.getY() - q.getY()) < N.HEIGHT) {
									System.out
											.println("不能向右移动，碰到了 " + q.getName() + "\t" + " x = " + q.getX() + "\t y = "
													+ q.getY() + "\t wo.x = " + wo4.getX() + "\two.y = " + wo4.getY());
									fp.repaint();
									jixu = false;
								}

								if (newXD > N.X_NUM * N.WIDTH - N.WIDTH) {
									System.out.println("到最右边了,return;");
									fp.repaint();
									jixu = false;
								}
							}

							if (!jixu) {
								fp.getWo().setFae(FAE.R);
								break;
							}
							wo4.setX(newXD);
							F.sleep();
							fp.repaint();
						}
					});
					threadD.start();

					break;

				case KeyEvent.VK_J:
					final N wwwww = fp.getWo();
					if (!wwwww.getPd().isKefashe()) {
						return;
					}

					final FE fe = wwwww.getFe();
					switch (fe) {
					case U:
						final int y = wwwww.getY();
						final Thread thread2 = new Thread(() -> {
							final int x = wwwww.getX();
							for (int y1 = y; y1 > 0; y1 -= PD.PX) {
								final int fromX = Math.abs(x / N.WIDTH) * N.WIDTH + N.WIDTH / 2 - PD.WIDTH / 2;
								final PD pd = new PD(PD.COLOR, FE.U, fromX, fromX, y1, 0, false, false);
								wwwww.setPd(pd);

								final boolean jixu = F.this.jiance_pd_pengzhuang_JIXU(pd, fp.getLd(), fp.getDi());

								try {
									Thread.sleep(1);
								} catch (final InterruptedException e1) {
									e1.printStackTrace();
								}
								if (!jixu) {
									break;
								}
								fp.repaint();
							}
							wwwww.getPd().setKefashe(true);
						});
						thread2.start();

						break;

					case D:
						final int yD = wwwww.getY();
						final Thread threadD1 = new Thread(() -> {
							final int x = wwwww.getX();
							for (int y1 = yD; y1 < F.G_HEIGHT; y1 += PD.PX) {
								final int fromX = Math.abs(x / N.WIDTH) * N.WIDTH + N.WIDTH / 2 - PD.WIDTH / 2;
								final int fromY = y1 + N.HEIGHT;
								final PD pd = new PD(PD.COLOR, FE.D, fromX, fromX, fromY, 0, false, false);
								wwwww.setPd(pd);

								final boolean jixu = F.this.jiance_pd_pengzhuang_JIXU(pd, fp.getLd(), fp.getDi());

								fp.repaint();
								try {
									Thread.sleep(1);
								} catch (final InterruptedException e1) {
									e1.printStackTrace();
								}
								if (!jixu) {
									break;
								}

							}
							wwwww.getPd().setKefashe(true);
						});
						threadD1.start();

						break;

					case R:
						final int yR = wwwww.getY();
						final Thread threadR = new Thread(() -> {
							final int x = wwwww.getX();
							final int y1 = wwwww.getY();
							for (int x1 = x; x1 <= F.G_WIDTH; x1 += PD.PX) {
								final int fromY = Math.abs(y1 / N.HEIGHT) * N.HEIGHT + N.HEIGHT / 2 - PD.HEIGHT / 2;
								final int fromX = x1 + N.WIDTH;
								final PD pd = new PD(PD.COLOR, FE.R, fromX, F.G_WIDTH, fromY, fromY, false, false);
								wwwww.setPd(pd);

								final boolean jixu = F.this.jiance_pd_pengzhuang_JIXU(pd, fp.getLd(), fp.getDi());

								fp.repaint();
								try {
									Thread.sleep(1);
								} catch (final InterruptedException e1) {
									e1.printStackTrace();
								}

								if (!jixu) {
									break;
								}
							}
							wwwww.getPd().setKefashe(true);
						});
						threadR.start();

						break;

					case L:
						final Thread threadL = new Thread(() -> {
							final int x = wwwww.getX();
							final int y1 = wwwww.getY();
							for (int x1 = x; x1 > 0; x1 -= PD.PX) {
								final int fromY = Math.abs(y1 / N.HEIGHT) * N.HEIGHT + N.HEIGHT / 2 - PD.HEIGHT / 2;
								final int fromX = x1 - N.WIDTH;
								final PD pd = new PD(PD.COLOR, FE.L, fromX, 0, fromY, fromY, false, false);
								wwwww.setPd(pd);

								final boolean jixu = F.this.jiance_pd_pengzhuang_JIXU(pd, fp.getLd(), fp.getDi());

								fp.repaint();
								try {
									Thread.sleep(1);
								} catch (final InterruptedException e1) {
									e1.printStackTrace();
								}
								if (!jixu) {
									break;
								}
							}
							wwwww.getPd().setKefashe(true);
						});
						threadL.start();

						break;

					default:
						break;
					}

					break;

				default:
					break;
				}
			}

		});

		fp.setFocusable(true);
		p.add(fp);

		final List<N> di = fp.getDi();
		for (final N n : di) {
			final DT dtr = new DT(fp, n);
			dtr.start();
		}
	}

	/**
	 * 判断我的炮弹的x y坐标是否碰到了其他可被击破的对象（敌军、墙壁 等等）
	 *
	 *
	 * @param pd
	 * @param ld
	 * @param di
	 * @return true表示炮弹继续飞行，false炮弹停止飞行
	 *
	 */
	boolean jiance_pd_pengzhuang_JIXU(final PD pd, final LD ld, final List<N> di) {
		final int fromX = pd.getFromX();
		final int fromY = pd.getFromY();

		final FE fe = pd.getFe();
		switch (fe) {
		case U:
			// 碰到敌军
			N diJ = null;
			if (!di.isEmpty()) {
				for (final N n : di) {
					if (n == null) {
						continue;
					}
					if (pd.getFromY() - n.getY() <= N.HEIGHT && pd.getFromX() > n.getX()
							&& pd.getFromX() - n.getX() <= N.WIDTH) {
						final NRE nre = n.getNre();
						final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();
						// 不可被炮弹穿过
						if (!kePaodanChuanGuo) {
							// 不可被炮弹穿过，但是可被击破
							if (nre.isKeJiPo()) {
								pd.setJipole(true);
								diJ = n;
								di.remove(diJ);
								this.yingle(di);
								return false;
							}

							// 不可被炮弹穿过，并且不可被击破
							if (!nre.isKeJiPo()) {
								pd.setJipole(true);
								return false;
							}

							return false;
						}
						return true;
					}
				}
			}

			// 碰到地图
			final int xHU = fromX / N.WIDTH;
			final N[][] dU = ld.getD();
			for (int yHU = fromY / N.HEIGHT; yHU < F.G_HEIGHT / N.HEIGHT; yHU++) {
				final N n = dU[yHU][xHU];
				if (n == null) {
					continue;
				}
				// pd > U pdY >= nY &&
				if (pd.getFromY() >= n.getY() && Math.abs(pd.getFromX() - n.getX()) <= N.WIDTH) {
					final NRE nre = n.getNre();
					final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();

					// 不可被炮弹穿过
					if (!kePaodanChuanGuo) {

						// 不可被炮弹穿过，但是可被击破
						if (nre.isKeJiPo()) {
							pd.setJipole(true);
							dU[yHU][xHU] = null;
							return false;
						}

						// 不可被炮弹穿过，并且不可被击破
						if (!nre.isKeJiPo()) {
							pd.setJipole(true);
							return false;
						}

						return false;
					}
					return true;
				}
			}

			break;

		case D:
			// 碰到敌军
			N diJD = null;
			if (!di.isEmpty()) {
				for (final N n : di) {
					if (n == null) {
						continue;
					}
					if (n.getY() - pd.getFromY() <= N.HEIGHT && pd.getFromX() > n.getX()
							&& pd.getFromX() - n.getX() <= N.WIDTH) {
						System.out.println("碰到碰到敌军了,di.name = " + n.getName());
						final NRE nre = n.getNre();
						final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();
						// 不可被炮弹穿过
						if (!kePaodanChuanGuo) {
							// 不可被炮弹穿过，但是可被击破
							if (nre.isKeJiPo()) {
								pd.setJipole(true);
								diJD = n;
								di.remove(diJD);
								this.yingle(di);
								return false;
							}

							// 不可被炮弹穿过，并且不可被击破
							if (!nre.isKeJiPo()) {
								pd.setJipole(true);
								return false;
							}

							return false;
						}
						return true;
					}
				}
			}

			// 碰到地图
			final int xHD = fromX / N.WIDTH;
			final N[][] dD = ld.getD();
			for (int yHD = fromY / N.HEIGHT >= N.Y_NUM ? N.Y_NUM - 1 : fromY / N.HEIGHT; yHD > 0; yHD--) {
				final N n = dD[yHD][xHD];
				if (n == null) {
					continue;
				}
				// pd > U pdY <= nY &&
				if (pd.getFromY() <= n.getY() && Math.abs(pd.getFromX() - n.getX()) <= N.WIDTH) {
					final NRE nre = n.getNre();
					final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();

					// 不可被炮弹穿过
					if (!kePaodanChuanGuo) {

						// 不可被炮弹穿过，但是可被击破
						if (nre.isKeJiPo()) {
							pd.setJipole(true);
							dD[yHD][xHD] = null;
							return false;
						}

						// 不可被炮弹穿过，并且不可被击破
						if (!nre.isKeJiPo()) {
							pd.setJipole(true);
							return false;
						}

						return false;
					}
					return true;
				}
			}

			break;

		case R:

			// 碰到敌军
			N diJR = null;
			if (!di.isEmpty()) {
				for (final N n : di) {
					if (n == null) {
						continue;
					}
					if (n.getX() - pd.getFromX() <= N.WIDTH && pd.getFromY() > n.getY()
							&& pd.getFromY() - n.getY() <= N.HEIGHT) {
						final NRE nre = n.getNre();
						final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();
						// 不可被炮弹穿过
						if (!kePaodanChuanGuo) {
							// 不可被炮弹穿过，但是可被击破
							if (nre.isKeJiPo()) {
								pd.setJipole(true);
								diJR = n;
								di.remove(diJR);
								this.yingle(di);
								return false;
							}

							// 不可被炮弹穿过，并且不可被击破
							if (!nre.isKeJiPo()) {
								pd.setJipole(true);
								return false;
							}

							return false;
						}
						return true;
					}
				}
			}

			// 碰到地图
			final int yHR = fromY / N.HEIGHT;
			final N[][] dR = ld.getD();
			for (int xHR = fromX / N.WIDTH; xHR < F.G_WIDTH / N.WIDTH; xHR++) {
				final N n = dR[yHR][xHR];
				if (n == null) {
					continue;
				}
				// pd > R pdX >= nX &&
				if (pd.getFromX() >= n.getX() && Math.abs(pd.getFromY() - n.getY()) <= N.HEIGHT) {
					final NRE nre = n.getNre();
					final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();

					// 不可被炮弹穿过
					if (!kePaodanChuanGuo) {

						// 不可被炮弹穿过，但是可被击破
						if (nre.isKeJiPo()) {
							pd.setJipole(true);
							dR[yHR][xHR] = null;
							return false;
						}

						// 不可被炮弹穿过，并且不可被击破
						if (!nre.isKeJiPo()) {
							pd.setJipole(true);
							return false;
						}

						return false;
					}
					return true;
				}
			}

			break;

		case L:
			// 碰到敌军
			N diJL = null;
			if (!di.isEmpty()) {
				for (final N n : di) {
					if (n == null) {
						continue;
					}
					if (pd.getFromX() - n.getX() <= N.WIDTH && pd.getFromY() > n.getY()
							&& pd.getFromY() - n.getY() <= N.HEIGHT) {
						final NRE nre = n.getNre();
						final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();
						// 不可被炮弹穿过
						if (!kePaodanChuanGuo) {
							// 不可被炮弹穿过，但是可被击破
							if (nre.isKeJiPo()) {
								pd.setJipole(true);
								diJL = n;
								di.remove(diJL);
								this.yingle(di);
								return false;
							}

							// 不可被炮弹穿过，并且不可被击破
							if (!nre.isKeJiPo()) {
								pd.setJipole(true);
								return false;
							}

							return false;
						}
						return true;
					}
				}
			}

			// 碰到地图
			final int yHL = fromY / N.HEIGHT;
			final N[][] dL = ld.getD();
			for (int xHL = fromX / N.WIDTH; xHL >= 0; xHL--) {
				final N n = dL[yHL][xHL];
				if (n == null) {
					continue;
				}
				// pd > R pdX >= nX &&
				if (pd.getFromX() <= n.getX() && Math.abs(pd.getFromY() - n.getY()) <= N.HEIGHT) {
					final NRE nre = n.getNre();
					final boolean kePaodanChuanGuo = nre.isKePaodanChuanGuo();

					// 不可被炮弹穿过
					if (!kePaodanChuanGuo) {

						// 不可被炮弹穿过，但是可被击破
						if (nre.isKeJiPo()) {
							pd.setJipole(true);
							dL[yHL][xHL] = null;
							return false;
						}

						// 不可被炮弹穿过，并且不可被击破
						if (!nre.isKeJiPo()) {
							pd.setJipole(true);
							return false;
						}

						return false;
					}
					return true;
				}
			}

			break;

		default:
			break;
		}

		return true;
	}

	private void yingle(final List<N> di) {
		if (di.size() <= 0) {
			JOptionPane.showMessageDialog(this, "恭喜过关！");
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(4);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
