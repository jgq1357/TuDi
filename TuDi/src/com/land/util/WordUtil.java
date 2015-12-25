package com.land.util;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordUtil {
	
	public static void wordTohtml(String word, String savepaths) {
		File f = new File(word);
		  String filename = f.getName();
		  String filetype = filename.substring((filename.length() - 3), filename.length());// 取得文件类型
		  if (filetype.equals("doc")) {// 判断是否为doc文件
		   System.out.println("当前正在转换......");
		   // 打印当前目录路径
		   System.out.println(word);
		   ActiveXComponent app = new ActiveXComponent("Word.Application");// 启动word
		   String docpath = word;
		   String htmlpath = savepaths + filename.substring(0, (filename.length() - 6));
		   String inFile = docpath;
		   // 要转换的word文件
		  String tpFile = htmlpath;
		   // HTML文件
		   try {
			   app.setProperty("Visible", new Variant(false));// 设置word不可见
			    Dispatch docs = app.getProperty("Documents").toDispatch();
			    Dispatch doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] { inFile, new Variant(false),new Variant(true) },
			      new int[1]).toDispatch();// 打开word文件
			    /*
			     * new Variant(10)筛选过的网页
			     * new Variant(9) 单个文件网页
			     * new Variant(8) 另存为网页
			     * new Variant(7) 另存为txt格式
			     * new Variant(6) 另存为rtf格式
			     * new Variant(0) 另存为doc格式
			     */

			    Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {tpFile, new Variant(10) }, new int[1]);// 作为html格式保存到临时文件
			    Variant fl = new Variant(false);
			    Dispatch.call(doc, "Close", fl);
			   } catch (Exception e) {
			    e.printStackTrace();
			   } finally {
			    app.invoke("Quit", new Variant[] {});
			   }
			   System.out.println("转化完毕！");
			  }
	}
	
	public static void main (String[] args) {
		wordTohtml("C:\\log\\档案管理系统.doc", "C:\\log\\");
	}
}
