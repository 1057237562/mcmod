package com.scirev.vanilla;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflect {

	private static Field modifiersField;

	static {
		try {
			modifiersField = Field.class.getDeclaredField("modifiers");
			// Makes the 'modifiers' field of the class Field accessible
			modifiersField.setAccessible(true);
		} catch (ReflectiveOperationException ignored) {
		}
	}

	@SuppressWarnings("unchecked")
	public static <T, R> R invoke(Method method, Object instance, Object[] args) {
		try {
			method.setAccessible(true);
			return (R) method.invoke(instance, args);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getMethod(Class clazz, String methodName, Class[] parameterTypes) {
		try {
			Method m = clazz.getDeclaredMethod(methodName, parameterTypes);
			if (m != null) {
				return m;
			}
		} catch (NoSuchMethodException ex) {
		} catch (SecurityException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Field getField(Class clazz, int fieldID) {
		return clazz.getDeclaredFields()[fieldID];
	}

	public static <T, V> void setField(Field field, T instance, V value) {
		try {
			field.setAccessible(true);
			field.set(instance, value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void setModifier(Field field, int mod, boolean flag) {
		try {
			field.setAccessible(true);
			int modifiers = modifiersField.getInt(field);
			if (flag) {
				modifiers |= mod;
			} else {
				modifiers &= ~mod;
			}
			modifiersField.setInt(field, modifiers);
		} catch (ReflectiveOperationException ex) {
			ex.printStackTrace();
		}
	}
}
