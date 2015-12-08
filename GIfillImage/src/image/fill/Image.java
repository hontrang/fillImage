package image.fill;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes.Name;

import javax.imageio.ImageIO;

public class Image {
	String ab_path, fullName, name, ext;
	int x, y, height, width;

	Image(String t_ab_path) {
		this.ab_path = t_ab_path;
		this.fullName = t_ab_path.substring(t_ab_path.lastIndexOf("\\") + 1,
				t_ab_path.length());
		this.name = fullName.substring(0, fullName.lastIndexOf("."));
		this.ext = fullName.substring(fullName.lastIndexOf("."),
				fullName.length());
		String array[] = name.split(" ");
		this.x = Integer.parseInt(array[0]);
		this.y = Integer.parseInt(array[1]);
		this.width = Integer.parseInt(array[2]);
		this.height = Integer.parseInt(array[3]);
	}

	public void setImageCor() {

	}

	public void executeImage() {
		try {
			File file = new File(ab_path);
			BufferedImage img = ImageIO.read(file);
			Color maskColor = new Color(0, 0, 255);
			int ptiX = this.x + this.width / 2;
			int ptiY = img.getHeight() - (this.y + this.height / 2);
			for (int t_y = 0; t_y < img.getHeight(); t_y++) {
				for (int t_x = 0; t_x < img.getWidth(); t_x++) {
					if (t_x <= this.x || t_x >= this.x + this.width) {
						img.setRGB(t_x, t_y, maskColor.getRGB());
						continue;
					}
					if ((this.x <= t_x && t_x <= this.x + this.width)
							&& (t_y <= this.y || t_y >= this.y + this.height)) {
						img.setRGB(t_x, t_y, maskColor.getRGB());
						continue;
					}
				}
			}

			/* images output path */

			File out = new File("D:\\images out\\" + this.name + "(" + ptiX
					+ "," + ptiY + ")" + ext);
			ImageIO.write(img, "bmp", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/* image input path */
		// for (int i = 0; i < 100; i++) {
		// System.out.println("pti -i 100.20.57.10 -S " + i + ".bmp");
		// }
		String path = "D:\\images in\\";

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String ab_path = path + file.getName();
				// Image image = new Image(ab_path);
				//
				// image.executeImage();
				System.out.println(ab_path);
			}
		}
	}
}
