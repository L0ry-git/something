package com.lorythegamer.func2visual;

import java.awt.Color;

public class Util {

	public static int transfom(int value, int oldFrom, int oldTo, int newFrom, int newTo) {
		float oldRange = oldTo - oldFrom;
		float newRange = newTo - newFrom;
		
		float ratio = newRange / oldRange;
		//System.out.println(ratio);
		return (int) (((value - oldFrom) * ratio) + newFrom);
	}
	
	public static float transfom(float value, float oldFrom, float oldTo, float newFrom, float newTo) {
		float oldRange = oldTo - oldFrom;
		float newRange = newTo - newFrom;
		
		float ratio = newRange / oldRange;
		//System.out.println(ratio);
		return (((value - oldFrom) * ratio) + newFrom);
	}
	
	public static Color hslColor(float h, float s, float l) {
	    float q, p, r, g, b;

	    if (s == 0) {
	        r = g = b = l; // achromatic
	    } else {
	        q = l < 0.5 ? (l * (1 + s)) : (l + s - l * s);
	        p = 2 * l - q;
	        r = hue2rgb(p, q, h + 1.0f / 3);
	        g = hue2rgb(p, q, h);
	        b = hue2rgb(p, q, h - 1.0f / 3);
	    }
	    return new Color(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255));
	}
	
	private static float hue2rgb(float p, float q, float h) {
	    if (h < 0) {
	        h += 1;
	    }

	    if (h > 1) {
	        h -= 1;
	    }

	    if (6 * h < 1) {
	        return p + ((q - p) * 6 * h);
	    }

	    if (2 * h < 1) {
	        return q;
	    }

	    if (3 * h < 2) {
	        return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));
	    }

	    return p;
	}
	
	
	
}
