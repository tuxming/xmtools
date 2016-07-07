package com.xm2013.tools.img;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageTools {
	
	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream(new File("d:/aaa.jpg"));
		//BufferedImage b = autoResize(in, 1024, 768);
		BufferedImage b = compress(in, 1024, 768, false);
		ImageIO.write(b, "jpg", new File("D:/new11.jpg"));
	}
	
	public static BufferedImage autoResize(InputStream inputStream, float maxWidth, float maxHeight) {
        try {
            BufferedImage image = ImageIO.read(inputStream);
            // 关闭流
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            // 获得缩放的比例
            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight() > maxHeight || image.getWidth() > maxWidth) {
                double ratio1 = maxHeight / image.getHeight();
                double ratio2 = maxWidth / image.getWidth();

                ratio = ratio1 < ratio2 ? ratio1 : ratio2;
            }
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);

            //BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //bufferedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            
            BufferedImage to = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB); 
            Graphics2D graphics2D = to.createGraphics();
            graphics2D.setComposite(AlphaComposite.Src);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            graphics2D.dispose();
            
//            Graphics2D g2d = to.createGraphics();       
//            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.OPAQUE);       
//            g2d.dispose();             
//            g2d = to.createGraphics();   
//            
//            Image from = image.getScaledInstance(newWidth, newWidth, Image.SCALE_AREA_AVERAGING);       
//            g2d.drawImage(from, 0, 0, null);     
//            g2d.dispose();             
            
            //ImageIO.write(to, suffix, new File(newPath));
            
            return to;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
	
	/**
     * 图片压缩
     *
     * @param inputStream 文件流
     * @param maxWidth    最大宽度
     * @param maxHeight   最大高度
     * @return
     */
    public static BufferedImage compress(InputStream inputStream, float maxWidth, float maxHeight, boolean highQuality) {
        try {
            BufferedImage image = ImageIO.read(inputStream);
            // 关闭流
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            
            int newWidth = image.getWidth();
            int newHeight = image.getHeight();
            if(!highQuality){
            	// 获得缩放的比例
                double ratio = 1.0;
                // 判断如果高、宽都不大于设定值，则不处理
                if (image.getHeight() > maxHeight || image.getWidth() > maxWidth) {
                    double ratio1 = maxHeight / image.getHeight();
                    double ratio2 = maxWidth / image.getWidth();

                    ratio = ratio1 < ratio2 ? ratio1 : ratio2;
                }
                // 计算新的图面宽度和高度
                newWidth = (int) (image.getWidth() * ratio);
                newHeight = (int) (image.getHeight() * ratio);
            }
            

            BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

            return bufferedImage;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
	
}
