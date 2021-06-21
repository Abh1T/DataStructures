package mazeproject;
import java.awt.color.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Polygon;

public class Wall {
	int[] rows;
	int[] cols;
	int rValue;
	int gValue;
	int bValue;
	Color startColor;
	Color endColor;
	String typeValue;
	int size;
	
	public Wall(int[] ro, int[] co, int rval, int gvalue, int bvalue, String type, int siz) {
		this.rows = ro;
		this.cols = co;
		this.rValue = rval;
		this.gValue = gvalue;
		this.bValue = bvalue;
		this.typeValue = type;
		this.size = siz;
		
		startColor = new Color(rValue,gValue,bValue);
		try {
			endColor = new Color(rValue-size, gValue - size, bValue - size);
		}catch(Exception e) {
			endColor = startColor;
		}
	}	
	
	public int[] getRows() {
		return rows;
	}
	public int[] getCols() {
		return cols;
	}
	public int rVal() {
		return rValue;
	}
	public int gVal() {
		return gValue;
	}
	public int bVal() {
		return bValue;
	}
	public String getType() {
		return typeValue;
	}
	public int getSize() {
		return size;
	}
	public GradientPaint getGradient() {
		int sR =0;
		int sC = 0;
		int eR = 0;
		int eC = 0;
		if(typeValue.contains("Trap")) {
			sR = (rows[0]+rows[3])/2;
			sC = cols[0];
			eR = sR;
			if(typeValue.equals("LeftTrap")) {
				eC = sC+size;
			}else if(typeValue.equals("RightTrap")) {
				eC = sC+size;
			}
		}
		
		else if(typeValue.contains("EXT")) {
			sC = (cols[0]+cols[1])/2;
			sR = rows[0];
			eC = sC;
			
			if(typeValue.equals("EXTTop")) {
				eR = sR + size;
			}else if(typeValue.equals("EXTBottom")) {
				eR = sR - size;
			}
			
		}
		else if(typeValue.equals("TopTri")) {
			sC = (cols[0] +cols[1])/2;
			eR = sR + size;
			eC = sC;
			sR = rows[0];
		}
		else if(typeValue.equals("BottomTri")) {
			sR = rows[2];
			sC = (cols[0] +cols[1])/2;
			eR = sR+ size;
			eC = sC;
		}
		
		return new GradientPaint(sC, sR, startColor, eC, eR, endColor);
	}
	
	public Polygon getPoly() {
		Polygon p = new Polygon(cols, rows, cols.length);
		return p;
	}
}
