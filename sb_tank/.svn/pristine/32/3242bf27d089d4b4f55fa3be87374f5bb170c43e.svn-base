package com.vo;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 坦克的炮弹
 *
 * @author zhangzhen
 * @date 2022年10月2日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PD {

	public static final Color COLOR = Color.RED;

	public static final int PX = 2;

	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;

	private Color color = Color.RED;
	private FE fe = FE.U;
	private int fromX;
	private int toX;
	private int fromY;
	private int toY;

	/**
	 * 	此炮弹是否击破了什么东西（敌军、墙壁等等）
	 */
	private boolean jipole;

	private boolean kefashe = true;

}
