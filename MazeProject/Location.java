package mazeproject;

public class Location {
	int r;
	int c;
	public Location() {
		this.r = 200;
		this.c = 200;
	}
	public Location(int t, int l) {
		this.r = t;
		this.c = l;
	}	
	public void setR(int t){
		this.r += t;
	}
	public void setC(int t){
		this.c += t;
	}
	public int getR() {
		return r;
	}
	public int getC() {
		return c;
	}
	
}
