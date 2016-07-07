package com.xm2013.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) throws IOException {
		
		short s = 1;
		
		Object value = s;
		
		short cc = (short) value;
		System.out.println(cc);
	}
	
	public static List<List<String>> readFileByLines(String fileName) {
        File file = new File(fileName);
        
        List<List<String>> groupByWords = new ArrayList<List<String>>();
        List<String> words3 = new ArrayList<String>();
        List<String> words4 = new ArrayList<String>();
        List<String> words5 = new ArrayList<String>();
        List<String> words6 = new ArrayList<String>();
        List<String> wordothers = new ArrayList<String>();
        
        groupByWords.add(words3);
        groupByWords.add(words4);
        groupByWords.add(words5);
        groupByWords.add(words6);
        groupByWords.add(wordothers);
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                //line++;
            	String[] ss= tempString.split("\\s*=\\s*");
            	if(ss[0].length()==3){
            		words3.add(ss[0]);
            	}else if(ss[0].length() == 4){
            		words4.add(ss[0]);
            	}else if(ss[0].length() == 5){
            		words5.add(ss[0]);
            	}else if(ss[0].length() == 6){
            		words6.add(ss[0]);
            	}else{
            		wordothers.add(ss[0]);
            	}
            	
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return groupByWords;
    }
	
}
