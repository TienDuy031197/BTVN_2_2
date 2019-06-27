package com.topica.annotation.bai2_2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(value = { ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface dataDB {
	String name() default "";
}

public class Annotation {
	public static void dataDB(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clas = obj.getClass();
		// String nametable = clas.getSimpleName();
		String nameTable = clas.getAnnotation(dataDB.class).name();
		Field[] field = clas.getDeclaredFields();
		for (Field fd : field) {
			dataDB data = fd.getAnnotation(dataDB.class);
			if (data != null) {
				fd.setAccessible(true);
			}
		}

		insert_1(field, obj, nameTable);
		select(field, nameTable);
	}

	public static void insert_1(Field[] field, Object obj, String nameTable)
			throws IllegalArgumentException, IllegalAccessException {
		String column = "";
		String value = "";
		for (Field fd : field) {
			column = column + fd.getName() + ",";
		}
		for (Field fd : field) {
			value = value + fd.get(obj) + ",";
		}
		column = column.substring(0, column.length() - 1);
		value = value.substring(0, value.length() - 1);
		System.out.println("INSERT INTO " + nameTable + " (" + column + ") VALUES (" + value + ");");
	}

	public static void select(Field[] field, String nameTable) {
		String column = "";
		for (Field fd : field) {
			column = column + fd.getName() + " ,";
		}
		column = column.substring(0, column.length() - 1);
		System.out.println("SELECT " + column + "FROM " + nameTable);
	}

}
