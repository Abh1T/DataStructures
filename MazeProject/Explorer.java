package mazeproject;
import java.awt.*;

public class Explorer {
	int direction;
	Color colo;
	Location l;
	int size;
	Rectangle r1;
	//String strlol;
	int x = 25;
	int y = 25;
	public Explorer(Location loc, int dir, int sizer, Color color) {
		this.colo = color;
		this.direction = dir;
		this.l = loc;
		this.size = sizer;
		//strlol = "none";
	}
	
	public int getDirection() {
		return direction;
	}
	public Location getLocation() {
		return l;
	}
	public Boolean shouldFinishEasy() {
		return((getLocation().getR()==7)&&(getLocation().getC()==43));
	}
	public Boolean shouldFinishHard() {
		return((getLocation().getR()==10)&&(getLocation().getC()==43));
	}
	public Color getColor() {
		return colo;
	}
	
	public void move(int key, char[][]maze) {
		Location locations = getLocation();
		int r = locations.getR();
		int c = locations.getC();
		System.out.println(r+" "+c);
		
		if(key == 38) { //forward
			//strlol = "North";
			if(direction == 0 ) { //up
				if(r>0 && maze[r-1][c] == 'O') {
					getLocation().setR(-1);
					System.out.println("Direction: "+direction);
				}
			}
			if(direction == 1 ) { //right
				if(c<maze[0].length - 1 && maze[r][c+1] == 'O') {
					getLocation().setC(+1);
					System.out.println("Direction: "+direction);
				}
			}
			if(direction == 2 ) { //down
				if(r<maze.length-1 && maze[r+1][c] == 'O') {
					getLocation().setR(+1);
					System.out.println("Direction: "+direction);
				}
			}
			if(direction == 3 ) { //left
				if(c>0 && maze[r][c-1] == 'O') {
					getLocation().setC(-1);
					System.out.println("Direction: "+direction);
				}
			}
		}
		if(key == 37) { //rotate left
			direction--;
			if(direction<0) {
				direction = 3;
			}
			//strlol = "West";
			System.out.println("Direction Shaft: "+direction);
		}
		if(key == 39) { //rotate right
			direction++;
			if(direction >3) {
				direction = 0;
			}
			System.out.println("Direction Shift: "+direction);
		}
	}
	public Rectangle getRect(){
		int r = getLocation().getR();
		int c = getLocation().getC();
		int size = 25;
		return new Rectangle(c*size + size,r*size + size,size,size);
	}
}
