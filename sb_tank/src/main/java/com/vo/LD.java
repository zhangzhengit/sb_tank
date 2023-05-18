package com.vo;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Data;

/**
 * 二维数组的地图
 *
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
@Data
public class LD {

	/**
	 * 我军坦克所在的坐标
	 */
	public static final int WO_Y = N.Y_NUM - 1;
	public static final int WO_X = N.X_NUM - 1;

	private final N[][] d = new N[N.Y_NUM][N.X_NUM];

	SecureRandom random = new SecureRandom();

	private final AtomicBoolean chonghui = new AtomicBoolean(true);

	public LD() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "LD.LD()");

		final int n = 300;
		int g = 0;
		final Set<String> set = new HashSet<>();
		final NRE[] v = NRE.values();
		while (true) {
			final int x = this.random.nextInt(N.X_NUM);
			final int y = this.random.nextInt(N.Y_NUM);
			if (y == LD.WO_Y && x == LD.WO_X) {
				continue;
			}
			final boolean add = set.add(x + "_" + y);

			if (!add) {
				while (true) {
					final NRE nnn = v[NG.random.nextInt(v.length)];
					if (nnn == NRE.WO || nnn == NRE.DI_REN) {
						continue;
					}
					final N nn = NG.g(nnn, FE.U);
					nn.setX(x * N.WIDTH);
					nn.setY(y * N.HEIGHT);
					this.d[y][x] = nn;
					g++;
					System.out.println("生成一个n，x = " +x + "\t" + "y = " + y + "g = " + g);
					break;
				}
			}

			if (g >=n) {
				break;
			}

		}

		System.out.println("生成完成 g = " + g);

		for(final N[] ns : this.d) {
			for (final N n2 : ns) {
				if(n2!=null) {
					System.out.print(n2.getName() + " " + n2.getX() +" " + n2.getY());
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}
}
