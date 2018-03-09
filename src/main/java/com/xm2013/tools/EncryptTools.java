package com.xm2013.tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类，ASC,MD5加密
 * @author Administrator
 *
 */
public class EncryptTools {

	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @param  length 加密次数: 128,268, 512, 1024
	 * @return 
	 */  
	public static byte[] encrypt(String content, String password, int length) {  
		try {             
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			kgen.init(length, new SecureRandom(password.getBytes()));  
			SecretKey secretKey = kgen.generateKey();  
			byte[] enCodeFormat = secretKey.getEncoded();  
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
			byte[] byteContent = content.getBytes("utf-8");  
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
			byte[] result = cipher.doFinal(byteContent);  
			return result; // 加密  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		} catch (NoSuchPaddingException e) {  
			e.printStackTrace();  
		} catch (InvalidKeyException e) {  
			e.printStackTrace();  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
		} catch (IllegalBlockSizeException e) {  
			e.printStackTrace();  
		} catch (BadPaddingException e) {  
			e.printStackTrace();  
		}  
		return null;  
	}  

	/**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @param  length 加密次数: 128,268, 512, 1024
	 * @return 
	 */  
	public static byte[] decrypt(byte[] content, String password, int length) {  
		try {  
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			kgen.init(length, new SecureRandom(password.getBytes()));  
			SecretKey secretKey = kgen.generateKey();  
			byte[] enCodeFormat = secretKey.getEncoded();  
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
			byte[] result = cipher.doFinal(content);  
			return result; // 加密  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		} catch (NoSuchPaddingException e) {  
			e.printStackTrace();  
		} catch (InvalidKeyException e) {  
			e.printStackTrace();  
		} catch (IllegalBlockSizeException e) {  
			e.printStackTrace();  
		} catch (BadPaddingException e) {  
			e.printStackTrace();  
		}  
		return null;  
	}  

	
	/**
	 * 获取MD5值
	 * @param pwd
	 * @return
	 */
	public static String MD5(String pwd) {  
		//用于加密的字符  
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
				'A', 'B', 'C', 'D', 'E', 'F' };  
		try {  
			//使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中  
			byte[] btInput = pwd.getBytes();  

			//信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。  
			MessageDigest mdInst = MessageDigest.getInstance("MD5");  

			//MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要  
			mdInst.update(btInput);  

			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文  
			byte[] md = mdInst.digest();  

			// 把密文转换成十六进制的字符串形式  
			int j = md.length;  
			char str[] = new char[j * 2];  
			int k = 0;  
			for (int i = 0; i < j; i++) {   //  i = 0  
				byte byte0 = md[i];  //95  
				str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5   
				str[k++] = md5String[byte0 & 0xf];   //   F  
			}  

			//返回经过加密后的字符串  
			return new String(str).toLowerCase();  

		} catch (Exception e) {  
			return null;  
		}  
	}  
	
	
	/**
	 * byte[] 转16进制的字符串
	 * @param hash
	 * @return
	 */
	public static String byteToHex(byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] hexStr2Byte(String hexStr) {  
		if (hexStr.length() < 1)  
			return null;  
		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	}  

}
