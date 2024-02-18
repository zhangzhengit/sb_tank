package com.vo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lombok.Getter;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
@Getter
public class FP extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 敌人数量 ，保持与N.X一致
	 */
	public static final int diC = 18;

	private final N wo;
	private final List<N> di;
	private final LD ld = new LD();

	public List<N> getOther() {
		final N[][] d = this.ld.getD();

		final List<N> m = new ArrayList<>(this.di);
		for (final N[] ns : d) {
			for (final N xxx : ns) {
				if (xxx != null) {
					m.add(xxx);
				}
			}
		}

		return m;
	}

	public FP() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "FP.FP()");
		this.wo = this.g_wo();

		this.di = new ArrayList<>();
		for (int k1 = 1; k1 <= FP.diC; k1++) {
			this.di.add(this.g_di((N.X_NUM - k1) * N.WIDTH, 0));
		}

	}

	private N g_wo() {
		final N w = NG.g(NRE.WO, FE.U);
		w.setX(LD.WO_X * N.WIDTH);
		w.setY(LD.WO_Y * N.HEIGHT);
		return w;
	}

	private N g_di(final int x, final int y) {
		final N w = NG.g(NRE.DI_REN, FE.D);
		w.setX(x);
		w.setY(y);
		return w;
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);

		// 绘制格子
		g.setColor(Color.PINK);
		for (int x = 1; x <= N.X_NUM; x++) {
			for (int y = 1; y <= N.Y_NUM; y++) {
				g.drawRect((x - 1) * N.WIDTH, (y - 1) * N.HEIGHT, N.WIDTH, N.HEIGHT);
			}
		}

		// 绘制地图
		final N[][] d = this.ld.getD();
		for (final N[] ns : d) {
			for (final N n : ns) {
				if (n == null) {
					continue;
				}
				g.drawImage(n.getImage(), n.getX(), n.getY(), N.WIDTH, N.HEIGHT, null);
			}
		}

		// 绘制敌军
		for (final N n : this.di) {
			g.drawImage(n.getImage(), n.getX(), n.getY(), N.WIDTH, N.HEIGHT, null);
		}

		// 绘制我（玩家的坦克）
		g.drawImage(this.wo.getImage(), this.wo.getX(), this.wo.getY(), N.WIDTH, N.HEIGHT, null);

		// 绘制我的炮弹
		if (this.wo.getPd() != null) {
			g.setColor(this.wo.getPd().getColor());
			g.fillOval(this.wo.getPd().getFromX(), this.wo.getPd().getFromY(), PD.WIDTH, PD.HEIGHT);
		}

	}

}
