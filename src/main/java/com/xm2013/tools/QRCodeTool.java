package com.xm2013.tools;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * http://blog.csdn.net/zgliang88/article/details/54617795
 * @author tuxming
 *
 */
public class QRCodeTool {
	/**
	 * 生成二维码图片
	 * @param code	 	字符串
	 * @param format	图片格式 ：png/jpeg
	 * @param width		图片宽度 ：
	 * @param height	图片高度：
	 * @param stream	文件流：如果是response则是：response.getOutputStream()
	 * @return 返回outputStream
	 * @throws Exception 
	 */
	public static OutputStream getQRCode(String code, String format, int width, int height, OutputStream stream) throws Exception{
		
		/**
		 * 生成的二维码会有20px的边框
		 */
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//hints.put(EncodeHintType.MARGIN, 1);
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(code, BarcodeFormat.QR_CODE, width+40, height+40);
		
		//去掉边框，原理：把生成的二维码部分放大
		/*
		BufferedImage bi =  MatrixToImageWriter.toBufferedImage(bitMatrix);

		BufferedImage newImage = new BufferedImage(width,height,bi.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(bi, 0, 0, width,height,null);
        g.dispose();
		
		ImageIO.write(newImage, format, stream); //生成二维码图片
		*/
		
		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
		return stream;
	}
	
	/**
	 * 得到图片的base64编码
	 * @param bs
	 * @param format 图片类型：png/jpeg
	 * @return
	 */
	public static String getImageBase64(byte[] bs, String format){
		String imgDataHead = "data:image/"+format+";base64,";
		byte[] encode = Base64.encodeBase64(bs);
		return imgDataHead+new String(encode);
	}
	
	/**
	 * 得到base64编码的二维码图片
	 * @param code 
	 * @param width
	 * @param height
	 * @param format png/jpeg
	 * @return
	 */
	public static String getQRCodeImgBase64(String code, int width, int height, String format){
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getQRCode(code, format, width, height, out);
			byte[] bs = out.toByteArray();
			return getImageBase64(bs, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 得到base64编码的二维码图片
	 * @param code 
	 * @param width
	 * @param height
	 * @param format png/jpeg
	 * @return
	 */
	public static String getQRCodeImgBase64(String code, int width, int height){
		return getQRCodeImgBase64(code, width, height, "jpeg");
	}
}
