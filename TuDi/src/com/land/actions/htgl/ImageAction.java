package com.land.actions.htgl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageAction extends LandSupport{
	
	private String yx;
	private InputStream imageStream;
	Logger log = Logger.getLogger(ImageAction.class.getName()); 
	public String execute() {
		try {
			File _file = new File(yx);
			BufferedImage src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			int newW=400;
			int newH=(int) ((double) height / (double) wideth * newW);
			System.out.println("newH==="+newH);
			BufferedImage image = new BufferedImage(newW, newH,
					BufferedImage.TYPE_INT_RGB);
		
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, newW, newH, null);
			g.dispose();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			// FileInputStream in = new FileInputStream(out.toString());

			imageStream = new ByteArrayInputStream(bos.toByteArray());
			src=null;
			image = null;
			bos.flush();
			bos.close();
			bos=null;
			
			_file.delete();
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return "success";
	}
	
	public String getYx() {
		return yx;
	}
	public void setYx(String yx) {
		this.yx = yx;
	}
	public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	

}
