package com.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * 元素类型
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
@Getter
@AllArgsConstructor
public enum NRE {

	WO("wo", false, false, true),

	DI_REN("di", false, false, true),

	SHU("shu", false, true, false),

	QIANG("qiang", false, false, true),

	BAOLEI("baolei", false, false, false),

	HE("he", true, true, false),

	;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 此元素能否被坦克穿过，如河流、树林等不能穿过，空地、道路等等可以被穿过
	 */
	private boolean keTankChuanGuo;

	/**
	 * 此元素能否被坦克的炮弹穿过，如河流、树林、空地、道路等能穿过，墙壁、堡垒等不能穿过
	 */
	private boolean kePaodanChuanGuo;

	/**
	 * 此元素能否被炮弹击破，如：墙壁可被击破，堡垒无法被击破（阻挡坦克前进）等等
	 */
	private boolean keJiPo;

}
