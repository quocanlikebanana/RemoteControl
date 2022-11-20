package ScreenCapture;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class ScreenCapture {
	public ObjectOutputStream oos;
	byte[] data;

	public ScreenCapture(ObjectOutputStream oos) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
	}

	public boolean get_Screenshot() throws IOException, AWTException {
		String path = "D:\\sc.jpg";
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(dimension);
		Robot robot = new Robot();
		BufferedImage screen = robot.createScreenCapture(rectangle);

		try {
			ImageIO.write(screen, "jpg", new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}
		BufferedImage bImage = ImageIO.read(new File("D:\\sc.jpg"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos);

		data = bos.toByteArray();
		if (data == null) {
			return false;
		}
		return true;
	}

	public void send_ScreenShot() throws IOException {
		oos.writeObject(data);
	}
}
