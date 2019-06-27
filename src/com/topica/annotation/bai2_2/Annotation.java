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
		Class<?> nameClass = obj.getClass();
		String nameTable = nameClass.getAnnotation(dataDB.class).name();
		Field[] field = nameClass.getDeclaredFields();
		for (Field fd : field) {
			dataDB data = fd.getAnnotation(dataDB.class);
			if (data != null) {
				fd.setAccessible(true);
			}
		}
		insert(field, obj, nameTable);
		select(field, nameTable);
	}

	public static void insert(Field[] field, Object obj, String nameTable)
			throws IllegalArgumentException, IllegalAccessException {
		String column = "";
		String value = "";
		StringBuilder builder = new StringBuilder();
		for (Field fd : field) {
			column = column + fd.getName() + ",";
			if (fd.get(obj) instanceof String) {
				value = value + "'" + fd.get(obj) + "'" + ",";
			} else {
				value = value + fd.get(obj) + ",";
			}
		}
		column = column.substring(0, column.length() - 1);
		value = value.substring(0, value.length() - 1);
		builder.append("INSERT INTO ").append(nameTable).append(" (").append(column).append(") VALUES (").append(value)
				.append(")").toString();
		System.out.println(builder);
	}

	public static void select(Field[] field, String nameTable) {
		String column = "";
		StringBuilder builder = new StringBuilder();
		for (Field fd : field) {
			column = column + fd.getName() + ",";
		}
		column = column.substring(0, column.length() - 1) + " ";
		builder.append("SELECT ").append(column).append("FROM ").append(nameTable).toString();
		System.out.println(builder);
	}

}
