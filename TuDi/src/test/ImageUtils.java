package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public  class ImageUtils {
		private Image	srcImage	= null;
		private File	srcFile  = null;
		private File	destFile	= null;
		private String	fileSuffix	= null;
        
		private int imageWidth = 0;
		private int imageHeight = 0;

		public ImageUtils(String fileName) throws IOException {
			this(new File(fileName));
		}

		public ImageUtils(File fileName) throws IOException {
			File _file = fileName;
			_file.setReadOnly();
			this.srcFile = _file;
			this.fileSuffix = _file.getName().substring(
					(_file.getName().indexOf(".") + 1),
					(_file.getName().length()));
			this.destFile = new File("D:\\b.jpg");
			srcImage = javax.imageio.ImageIO.read(_file);
			//得到图片的原始大小， 以便按比例压缩。
			imageWidth = srcImage.getWidth(null);
			imageHeight = srcImage.getHeight(null);
			System.out.println("width: " + imageWidth);
			System.out.println("height: " + imageHeight);		
		}

		/**
		 * 强制压缩/放大图片到固定的大小
		 * @param w int 新宽度
		 * @param h int 新高度
		 * @throws IOException
		 */
		public void resize(int w, int h) throws IOException {
			//得到合适的压缩大小，按比例。
			if ( imageWidth >= imageHeight)
			{
				h = (int)Math.round((imageHeight * w * 1.0 / imageWidth));
			}
			else 
			{
				w = (int)Math.round((imageWidth * h * 1.0 / imageHeight));
			}

			//构建图片对象
			BufferedImage _image = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			//绘制缩小后的图
			_image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);
			//输出到文件流
			FileOutputStream out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(_image);
			out.flush();
			out.close();
			
		}
		
		public static void main(String[] args) {
			try {
				ImageUtils im = new ImageUtils("C:\\Documents and Settings\\Administrator\\桌面\\000001.TIF");
				im.resize(100, 700);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}