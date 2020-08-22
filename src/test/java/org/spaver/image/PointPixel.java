package org.spaver.image;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PointPixel {
	public static void getData(String path) {
		try {
			BufferedImage bimg = ImageIO.read(new File(path));
			int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
			// ��ʽһ��ͨ��getRGB()��ʽ������ؾ���
			// �˷�ʽΪ��Height����ɨ��
			for (int i = 0; i < bimg.getWidth(); i++) {
				for (int j = 0; j < bimg.getHeight(); j++) {
					data[i][j] = bimg.getRGB(i, j);
					// ���һ�����ݱȶ�
					if (i == 0)
						System.out.printf("%x\t", data[i][j]);
				}
			}
			Raster raster = bimg.getData();
			System.out.println("This is second way to achieve the pixels");
			int[] temp = new int[raster.getWidth() * raster.getHeight() * raster.getNumBands()];
			// ��ʽ����ͨ��getPixels()��ʽ������ؾ���
			// �˷�ʽΪ��Width����ɨ��
			int[] pixels = raster.getPixels(0, 0, raster.getWidth(), raster.getHeight(), temp);
			for (int i = 0; i < pixels.length;) {
				// ���һ�����ݱȶ�
				if ((i % raster.getWidth() * raster.getNumBands()) == 0)
					System.out.printf("ff%x%x%x\t", pixels[i], pixels[i + 1], pixels[i + 2]);
				i += 3;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getData("./src/main/resources/painting.png");
	}
}
