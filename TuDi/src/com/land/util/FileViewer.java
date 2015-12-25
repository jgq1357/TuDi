package com.land.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
public abstract class FileViewer {
	protected List<String> fileList = new ArrayList<String>();
	private String file;
	private String suffix;
	private boolean isdepth;
	
	
	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public boolean isIsdepth() {
		return isdepth;
	}

	public void setIsdepth(boolean isdepth) {
		this.isdepth = isdepth;
	}

	public FileViewer(String file, String suffix, boolean isdepth) {
		this.file = file;
		this.suffix = suffix;
		this.isdepth = isdepth;
	}
	
	public FileViewer(String file, String suffix, boolean isdepth, String outputFile) {
		this.file = file;
		this.suffix = suffix;
		this.isdepth = isdepth;
	}
	
	public void start() {
		File f = new File(file);
		listFile(f, suffix, isdepth);
		if(fileList.isEmpty()){
			Logger log = Logger.getLogger(FileViewer.class);
			log.warn("No match file found.");
			return;
		}
		
		//logger.info(message);
	}

	public abstract String process(String temp);

	private void listFile(File f, String s, boolean depth) {

		if (f.isDirectory()) {
			File[] t = f.listFiles();
			if (depth) {
				for (int i = 0; i < t.length; i++) {
					listFile(t[i], s, depth);
				}
			} else {
				for (int i = 0; i < t.length; i++) {
					if(t[i].isFile()) {
						addFileNameToList(f, s);
					}
				}
			}
		} else {
			addFileNameToList(f, s);

		}
	}

	private void addFileNameToList(File f, String s) {
		String filePath = f.getAbsolutePath();

		if (s == "" || s == null) {
			fileList.add(filePath);
		} else {
			int begIndex = filePath.lastIndexOf(".");
			String tempsuffix = "";

			if (begIndex != -1) 
			{
				tempsuffix = filePath
						.substring(begIndex + 1, filePath.length());
			}

			if (tempsuffix.equalsIgnoreCase(s)) {
				fileList.add(process(filePath));
			}
		} 
	}

}
