package com.land.actions.htgl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.util.Constant;
import com.land.util.OCR;

public class OCRAction extends LandSupport{

	private File yxFile;
	private String yxFileContentType;
	private String yxFileFileName;
	private String errorMessage;
	private String parseResult;
	private String yx;
	Logger log = Logger.getLogger(OCRAction.class.getName());
	private Map<String, String> prePathMap = Constant.PREPATHMAP;
	public String execute() {
		if (this.getYxFileFileName() == null
				|| this.getYxFileFileName().length() == 0) {
			errorMessage="请指定TIF文件";
			return "input";
		}
		String end = this.getYxFileFileName().substring(this.getYxFileFileName().length()-4, this.getYxFileFileName().length());
		if(!".jpg".equalsIgnoreCase(end)) {
			errorMessage="文件格式不正确，请上传TIF文件。";
			return "input";
		}
		String processPath = prePathMap.get("djyw")+"/tmp";
		File f = new File(processPath);
		if(!f.exists()) {
			f.mkdir();
		}
		f=null;
		try {
			System.out.println(yxFile.getParentFile()+"-----");
			InputStream isYx = new FileInputStream(yxFile);
			File toFile = new File(processPath, this.getYxFileFileName());
			OutputStream os = new FileOutputStream(toFile);
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = isYx.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			isYx.close();
			os.close();
			yx = processPath+"/"+this.getYxFileFileName();
			parseResult = new OCR().recognizeText(new File(yx), "jpg");
		} catch (Exception e) {
			errorMessage="文件解析出错.";
			log.error(errorMessage);
			log.error(e);
			return "input";
		} 
		
		return "success";
	}

	public File getYxFile() {
		return yxFile;
	}

	public void setYxFile(File yxFile) {
		this.yxFile = yxFile;
	}

	public String getYxFileContentType() {
		return yxFileContentType;
	}

	public void setYxFileContentType(String yxFileContentType) {
		this.yxFileContentType = yxFileContentType;
	}

	public String getYxFileFileName() {
		return yxFileFileName;
	}

	public void setYxFileFileName(String yxFileFileName) {
		this.yxFileFileName = yxFileFileName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getParseResult() {
		return parseResult;
	}

	public void setParseResult(String parseResult) {
		this.parseResult = parseResult;
	}

	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}
	
	
}
