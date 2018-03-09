package com.xm2013.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;import javax.xml.stream.events.EndDocument;

public class TestApp {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String qrcode = QRCodeTool.getQRCodeImgBase64("sgfddasfgdfsgsd", 100, 100);
//		System.out.println(qrcode);
		String param = "HashKey=VuMmZi17WHPyS4gB&AllPayLogisticsID=3127801&CVSPaymentNo=08967262227&CVSValidationNo=&MerchantID=3021881&HashIV=SeZ5vJO2FFB4wgud";
		String encoded = URLEncoder.encode(param, "utf-8");
		System.out.println(encoded);
		System.out.println(encoded.toLowerCase());
		String encrypt = EncryptTools.MD5(encoded.toLowerCase()).toUpperCase();
		System.out.println(encrypt);
		//1B4877B14E3AFD6BEDE7D7C5FD246B49
		//3C4FE8B3ACD8D8F6B677309D26BCCCB8
		//FCB09BCF00A62D0F7A0F3F11A425D7AA
		
	}
}
