package com.xm2013.tools;

import java.lang.reflect.Field;

public class BaseTypeTest {
	
	public static void main(String[] args) {
		BaseTypeTest b = new BaseTypeTest();
		Person p = b.new Person();
		
		for(Field field : Person.class.getDeclaredFields()){
			
			System.out.println(field.getName()+", "+
					field.getType().getName()+", "+
					field.getType().getTypeName()+", "+
					field.getType().getComponentType()
				);
			
//			if(field.getType().getName().startsWith("[") && field.getType().getComponentType()==Integer.TYPE)
//				System.out.println("1："+field.getType());
//			else
//				System.out.println(field.getType());
			
			
		}
	
		System.out.println(int.class == Integer.TYPE);
		System.out.println(boolean.class == Boolean.TYPE);
	}
	
	class Person{
		private int age;
		private double score;
		private boolean sex;
		private String name;
		
		private int[] ages;
		private String[] names;
		private String[][] arrays;
		private boolean sexs[];
		private double scores;
		
		public int[] getAges() {
			return ages;
		}
		public void setAges(int[] ages) {
			this.ages = ages;
		}
		public String[] getNames() {
			return names;
		}
		public void setNames(String[] names) {
			this.names = names;
		}
		public boolean[] getSexs() {
			return sexs;
		}
		public void setSexs(boolean[] sexs) {
			this.sexs = sexs;
		}
		public double getScores() {
			return scores;
		}
		public void setScores(double scores) {
			this.scores = scores;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public boolean isSex() {
			return sex;
		}
		public void setSex(boolean sex) {
			this.sex = sex;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String[][] getArrays() {
			return arrays;
		}
		public void setArrays(String[][] arrays) {
			this.arrays = arrays;
		}
		
		
	}
	
}
