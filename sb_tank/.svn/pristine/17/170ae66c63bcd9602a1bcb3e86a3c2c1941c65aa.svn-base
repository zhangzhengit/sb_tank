package com.vo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import javax.imageio.ImageIO;

/**
 *	元素生成器
 *
 * @author zhangzhen
 * @date 2022年10月1日
 *
 */
// FIXME 2022年10月1日 下午10:54:29 zhanghen: 判断生成一个合理的地图，比如：我军和敌军不能被元素相隔永远不能相见
// 或者手动制作地图数据
public class NG {
	static SecureRandom random = new SecureRandom();

	public static List<N> g_ditu() {

		final int maxX = F.G_WIDTH / N.WIDTH;
		final int maxY = F.G_HEIGHT / N.HEIGHT;
		System.out.println("maxX = " + maxX);
		System.out.println("maxY = " + maxY);

		final int n = maxX * maxY / 3;
//		final int n = 0;
//		final int n = 10000 ;
		final List<N> l = new ArrayList<>();
		for (int i = 1; i <= n;) {
			final NRE[] v = NRE.values();
			final int k = NG.random.nextInt(v.length);
			final NRE nnn = v[k];
			if (nnn == NRE.WO || nnn == NRE.DI_REN) {
				continue;
			}

			final N n2 = g(nnn, FE.U);

			n2.setX((NG.random.nextInt(maxX)) * N.WIDTH);
			n2.setY((NG.random.nextInt(maxY)) * N.HEIGHT);

			l.add(n2);

			i++;
		}

		return l;
	}

	public static N g(final NRE nre, final FE fe) {
		final N n = new N();
		n.setName(nre.getName());
		n.setImage(g_i(nre, fe));
		n.setNre(nre);

		return n;
	}

	public static BufferedImage g_i(final NRE nre, final FE fe) {

		final String name = (nre == NRE.WO || nre == NRE.DI_REN) ? nre.getName()
				+ '_' + fe.name().toLowerCase()
				: nre.getName();

		final BufferedImage bufferedImage = NG.m.get(name);
		if (bufferedImage != null) {
			return bufferedImage;
		}

		final URL url = N.class.getClassLoader().getResource("icon/" + name + ".jpg");
		final File file = new File(url.getFile());
		try {
			final BufferedImage image = ImageIO.read(file);
			NG.m.put(name, image);
			return image;
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	static
	WeakHashMap<String, BufferedImage> m = new WeakHashMap<>();
}
