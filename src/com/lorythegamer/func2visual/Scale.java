package com.lorythegamer.func2visual;

import java.awt.Color;

public class Scale {

	public Color logScale(float result) {
		float logHueValue = result > 1 ? Math.min((float) (Math.log10(result) / 10), 
				1.0f) : 0.0f;

		try {
			return Util.hslColor(logHueValue / 2 - 0.5f, 0.5f, 0.5f);
			
		} catch(IllegalArgumentException e) {
			System.out.println(logHueValue);
		}
		return new Color(0,0,0);
	}
	
	/*public Color linearScale(float result, int max) {
		
	}*/
}
