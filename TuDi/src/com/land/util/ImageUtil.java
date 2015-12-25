package com.land.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	
	public static InputStream pressText(String pressText, String targetImg,
			int fontSize,String hbFlag,String pic_type) {
		try {
		System.out.println(targetImg);
			File _file = new File(targetImg);
			
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			System.out.println(wideth);
			int height = src.getHeight(null);
			System.out.println(height);
			BufferedImage image;
			if("Y".equalsIgnoreCase(hbFlag)) {
				image = new BufferedImage(wideth, height, BufferedImage.TYPE_BYTE_BINARY);  
			    for(int i= 0 ; i < wideth ; i++){  
			        for(int j = 0 ; j < height; j++){  
			        int rgb = image.getRGB(i, j);  
			        image.setRGB(i, j, rgb);  
			        }  
			    }  
				//image = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter(image,null); 
			}else {
				image = new BufferedImage(wideth, height,
						BufferedImage.TYPE_INT_RGB);
			}
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// String s="www.qhd.com.cn";
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					0.5f));
			g.rotate(-Math.PI / 180 * 30, (double) image.getWidth() / 2,
					(double) image.getHeight() / 2);
			g.setColor(new Color(168,168,168));
			g.setFont(new Font("", 1, fontSize));
			int t_width = pressText.length()*fontSize;
			int t_height = fontSize;
			for (int x=0-t_width; x <wideth+t_width; x+=t_width+150) {
				for(int y=0-t_height; y<height+2*t_height; y+=3*t_height) {
					g.drawString(pressText, x, y);
				}
			}
			g.dispose();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);

			InputStream is = new ByteArrayInputStream(bos.toByteArray());
			bos.flush();
			bos.close();
			is.close();
			return is;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
