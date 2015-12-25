package com.land.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ParseZipUtil {
	private static final int buffer = 2048;
	
	public static void unzip(String name, String savepath) throws Exception {
		int count = -1;
		String[] names;
		File file = null;
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		Logger log = Logger.getLogger(ParseZipUtil.class);
		ZipFile zipFile = new ZipFile(name);
		Enumeration<?> entries = zipFile.getEntries();
		while (entries.hasMoreElements()) {
			byte buf[] = new byte[buffer];
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String tifName = entry.getName();
			names = tifName.split("/");
			String dirname = savepath + "\\";
			String filename = "";

			if (!tifName.toLowerCase().contains("jpg")) {
				for (int i = 0; i < names.length; i++) {
					dirname = dirname + names[i] + "\\";
					File f = new File(dirname);
					if (!f.exists()) {
						f.mkdir();
					}
				}
				continue;
			}

			if (names[names.length - 1].trim().length() != 0) {
				for (int i = 0; i < names.length - 1; i++) {
					dirname = dirname + names[i] + "\\";
					File f = new File(dirname);
					if (!f.exists()) {
						f.mkdir();
					}
					filename = filename + names[i] + "\\";
				}
				filename = savepath + "\\" + filename + names[names.length - 1];
				file = new File(filename);
				file.createNewFile();
				is = zipFile.getInputStream(entry);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, buffer);

				while ((count = is.read(buf)) > -1) {
					bos.write(buf, 0, count);
				}
				fos.close();
				is.close();
				file = null;
				log.info("添加文件--"+filename);
			}
		}
		zipFile.close();

	}

}
