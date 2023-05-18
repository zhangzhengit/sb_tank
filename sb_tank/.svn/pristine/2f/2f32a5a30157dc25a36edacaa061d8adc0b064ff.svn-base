package com.vo;

import java.awt.image.BufferedImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *	表示一个元素，如：我军坦克、敌军坦克、墙壁、河流等等
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// FIXME 2022年10月5日 下午9:50:44 zhanghen: 给敌军加入不同的类型特性，如：有可以冲破墙壁的、
//	有的可以自爆（碰到我方坦克就爆破）、有的发射的炮弹可以击破任何障碍的 等等
public class N {

	public static final int X_NUM = 18;
	public static final int Y_NUM = 18;
	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;

	/**
	 * 此元素的图片
	 */
	private BufferedImage image;

	private String name;

	private NRE nre;

	private PD pd = new PD();

	private FE fe = FE.U;

	private FAE fae = FAE.NULL;

	private int x;
	private int y;

	public BufferedImage getImage() {
		return NG.g_i(this.getNre(), this.getFe());
	}

}
