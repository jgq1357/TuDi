package com.land.actions.main;

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
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.domain.User;
import com.land.util.Constant;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterAction extends LandSupport {
	private InputStream imageStream;
	private String dangAnHao;
	private String pageNum;
	private String tmId;
	private String errorMessage;
	private int total;
	private String dalb;
	private String hbFlag = "N";
	private String type = "S";

	Logger log = Logger.getLogger(WaterAction.class);
	Map<String, String> prePathMap = Constant.PREPATHMAP;

	public String execute() {
		String prePath = "";
		prePath = prePathMap.get(dalb);
		if (prePath.trim().length() == 0) {
			errorMessage = "The prePath is null.";
			log.error("系统出错"+"数据库中没有配置相关影像根路径。");
			return "error";
		}

		String tmp = dangAnHao.substring(dangAnHao.indexOf("-") + 1, dangAnHao
				.length() - 1);
		String mlh = tmp.substring(0, tmp.indexOf("-"));
		tmp = tmp.substring(tmp.indexOf("-") + 1, tmp.length() - 1);
		String flh = tmp.substring(0, tmp.indexOf("-"));

		String path = prePath + flh + "\\" + mlh + "\\" + dangAnHao;

		System.out.println("WaterAction :: path = " + path);

		pageNum = this.countPageNum(pageNum, path);
		if ("error".equals(pageNum)) {
			errorMessage = "未找到影像。";
			return "error";
		}

		User user = (User) session.get("user");

		imageStream = pressText(user.getSywz(), path + "\\" + pageNum + ".jpg",
				"", 1, Color.ORANGE.getRGB(), 100);
		log.info(user.getUserName() + "查看影像：" + path + "\\" + pageNum + ".jpg");
		return "success";
	}

	private InputStream pressText(String pressText, String targetImg,
			String fontName, int fontStyle, int color, int fontSize) {
		try {

			File _file = new File(targetImg);

			BufferedImage src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			int newW = 0;
			int newH = 0;
			if ("S".equals(type)) {
				newW = Constant.SMALLPIC_WIDTH;
				fontSize = 15;
			} else if ("B".equals(type)) {
				newW = Constant.BIGPIC_WIDTH;
				fontSize = 35;
			} else if ("P".equals(type)) {
				newW = Constant.PRINTPIC_WIDTH;
				fontSize = 25;
			}
			newH = (int) ((double) height / (double) wideth * newW);
			BufferedImage grayimage = new BufferedImage(wideth, height,
					BufferedImage.TYPE_BYTE_BINARY);
			if ("Y".equalsIgnoreCase(hbFlag)) {
				for (int i = 0; i < wideth; i++) {
					for (int j = 0; j < height; j++) {
						int rgb = src.getRGB(i, j);
						grayimage.setRGB(i, j, rgb);
					}
				}
			}
			BufferedImage image = new BufferedImage(newW, newH,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D g = image.createGraphics();
			if ("Y".equalsIgnoreCase(hbFlag)) {
				g.drawImage(grayimage.getScaledInstance(newW, newH,
						Image.SCALE_SMOOTH), 0, 0, newW, newH, null);
			} else {
				g.drawImage(src, 0, 0, newW, newH, null);
			}

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					0.5f));
			g.rotate(-Math.PI / 180 * 30, (double) image.getWidth() / 2,
					(double) image.getHeight() / 2);
			g.setColor(new Color(168, 168, 168));
			g.setFont(new Font(fontName, fontStyle, fontSize));
			int t_width = pressText.length() * fontSize;
			int t_height = fontSize;
			int gCount = 0;
			for (int x = 0 - t_width; x < wideth + t_width; x += (t_width + fontSize * 4)) {
				for (int y = 0 - t_height; y < height + 2 * t_height; y += (3 * t_height)) {
					if (gCount % 3 == 0)
						g.drawString(pressText, x, y);
					gCount++;
				}
			}
			/*
			 * g.drawString(pressText, wideth/2 - fontSize -
			 * pressText.length()/2, height/2 - fontSize / 2 );
			 */
			g.dispose();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			// FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			// FileInputStream in = new FileInputStream(out.toString());

			InputStream is = new ByteArrayInputStream(bos.toByteArray());
			src = null;
			grayimage = null;
			image = null;
			bos.flush();
			bos.close();
			bos = null;
			is.close();

			return is;
			// bos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String countFileNum() {
		String prepath = "";
		prepath = prePathMap.get(dalb);

		String path = prepath + dangAnHao + "\\" + dangAnHao + "-" + tmId;
		int num = 0;
		File f = new File(path);
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].isFile()) {
					num += 1;
				}
			}
		} else {
			log.error("系统出错"+"该目录下（" + path + "）未找到影像。");
			log.error("该目录下（ " + path + " ）没有文件");
		}
		total = num;
		return "success";
	}

	private String countPageNum(String Num, String path) {
		String id = "";
		int pn = Integer.parseInt(Num);
		for (int i = 0; i < 6 - ("" + pn).length(); i++) {
			id = id + "0";
		}
		id = id + pn;
		String file = path + "\\" + id + ".jpg";
		if (!new File(file).isFile()) {
			log.error("系统出错"+"影像不存在>>>" + file);
			id = "error";
		}
		return id;
	}

	@JSON(serialize = false)
	public String getDalb() {
		return dalb;
	}

	public void setDalb(String dalb) {
		this.dalb = dalb;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(serialize = false)
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JSON(serialize = false)
	public String getDangAnHao() {
		return dangAnHao;
	}

	public void setDangAnHao(String dangAnHao) {
		this.dangAnHao = dangAnHao;
	}

	@JSON(serialize = false)
	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	@JSON(serialize = false)
	public String getTmId() {
		return tmId;
	}

	public void setTmId(String tmId) {
		this.tmId = tmId;
	}

	@JSON(serialize = false)
	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

	@JSON(serialize = false)
	public String getHbFlag() {
		return hbFlag;
	}

	public void setHbFlag(String hbFlag) {
		this.hbFlag = hbFlag;
	}

	@JSON(serialize = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
