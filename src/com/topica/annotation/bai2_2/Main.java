package com.topica.annotation.bai2_2;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Student st = new Student(1, "duy", 12, "soc soc");
		Annotation.dataDB(st);
	}

}
