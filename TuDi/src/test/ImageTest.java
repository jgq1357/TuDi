package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageTest {
	public void jpgTset() throws Exception {
        File _file = new File("C:\\yxk\\dj\\J888-194-C4-279\\J888-194-C4-279-001\\000001.TIF"); //读入文件
        Image src = javax.imageio.ImageIO.read(_file); //构造Image对象
        int wideth=src.getWidth(null); //得到源图宽
        int height=src.getHeight(null); //得到源图长

        /**//*
         * //绘制缩小后的图
        BufferedImage tag = new BufferedImage(wideth/2,height/2,BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(src,0,0,wideth/2,height/2,null); //绘制缩小后的图
        */

        BufferedImage tag = new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(src,0,0,wideth,height,null); 
        FileOutputStream out=new FileOutputStream("c:\\youfilename.jpg"); //输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag); //JPEG编码
        out.close();
    }

    public static void main(String[] args){
        try{
            new ImageTest().jpgTset();
        }catch(Exception e){
            e.printStackTrace();
    }
    }

}
