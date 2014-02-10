package alekuba.util;

import android.graphics.Rect;

public class DPIConverter {
	public static float logicalDensity;
	
	public static void setDensity(float d){
		logicalDensity = d;
	}
	
	public static Rect getRect(int left, int top, int right, int bottom){
		return new Rect((int) (left/logicalDensity), (int) (top/logicalDensity), (int) (right/logicalDensity), (int) (bottom/logicalDensity));
	}
	
	public static int conv(int i){
		return (int) (i/logicalDensity);
	}
}
