package com.land.util;

import java.util.ArrayList;
import java.util.List;

public class DangAnFileViewer extends FileViewer{

	public DangAnFileViewer(String file, String suffix, boolean isdepth) {
		super(file, suffix, isdepth);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String temp) {
		// TODO Auto-generated method stub
		String str = temp;
		int last = str.lastIndexOf("\\");
		temp = str.substring(last+1, str.length()).split("\\.")[0];
		temp = temp+",";
		str = str.substring(0,last);
		last = str.lastIndexOf("\\");
		str = str.substring(last+1,str.length());
		last = str.lastIndexOf("-");
		temp = temp + str.substring(last+1,str.length());
		return temp;
	}
	
	public void sort() {
		List<String> tmps = new ArrayList<String>();
		for(int i=0;i<fileList.size();i++) {
			String str = fileList.get(i);
			if(str.contains("ds10")||str.contains("ds11")) {
				tmps.add(str);
			}
		}
		for(int i=0;i<fileList.size();i++) {
			String str = fileList.get(i);
			if(str.contains("ds10")||str.contains("ds11")) {
				
			}else {
				tmps.add(str);
			}
		}
		fileList = tmps;
	}

	
	public static void main(String args[]) {
		DangAnFileViewer d = new DangAnFileViewer("C:\\yxk\\dj\\J888-194-C4-279", "jpg", true);
		d.start();
		d.sort();
		System.out.println(d.getFileList().get(2));
		
	}
}
