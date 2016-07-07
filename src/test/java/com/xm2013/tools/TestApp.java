package com.xm2013.tools;

public class TestApp {
	public static void main(String[] args) {
		String qrcode = QRCodeTool.getQRCodeImgBase64("sgfddasfgdfsgsd", 100, 100);
		System.out.println(qrcode);
	}
}
