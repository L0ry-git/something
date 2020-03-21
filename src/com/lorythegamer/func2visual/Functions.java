package com.lorythegamer.func2visual;

public class Functions {

	public static float testFunc(float x, float y, float... parameters) {
		float k = parameters[0];
		
		float result = k * 3 * ((int)x * 2 | (int)y * 2);	
		return result;//round(result / k) * k;
	}
	
	public static float round(float v) {
		return (float) Math.round(v);
	}
	
	public static float pow(float b, float exp) {
		return (float) Math.pow(b, exp);
	}
	
	public static float rad(float deg) {
		return (float) Math.toRadians(deg);
	}
	
	public static float cos(float v) {
		return (float) Math.cos(v);
	}
	
	public static float abs(float v) {
		return Math.abs(v);
	}
	
	public static float sin(float v) {
		return (float) Math.sin(v);
	}
	
}
