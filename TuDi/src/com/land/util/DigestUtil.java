package com.land.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

public class DigestUtil {
	
	public static String digestMd5(String s){
			try{
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] bt = md.digest(s.getBytes());
				//使用base64算法将md5加密之后的字节变成字符串
				BASE64Encoder base64Encoder = new BASE64Encoder();
				return base64Encoder.encode(bt);
			}catch(Exception e){
				Logger log = Logger.getLogger(DigestUtil.class);
				log.error("MD5加密出错");
				return "";
			}
		}
}
