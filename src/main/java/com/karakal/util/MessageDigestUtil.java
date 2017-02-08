package com.karakal.util;

import java.security.MessageDigest;
/**
 * MD5加密方法
 * ClassName: MessageDigestUtil <br/>
 * Package Name:com.karakal.util
 * @author xt(di.xiao@karakal.com.cn)
 * @version 1.0
 * Date:2016年7月28日上午10:51:32
 * Copyright (c) 2016, manzz.com All Rights Reserved.
 */
public class MessageDigestUtil {
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception localException) {
		}
		return resultString;
	}

	public static String SHAEncode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception localException) {
		}
		return resultString;
	}
}
