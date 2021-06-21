package mazeproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class runner extends JPanel implements KeyListener
{
	
	//Abhiram Tamvada Maze Project
	runner apper;
	JFrame frame;
	char[][] maze;
	boolean drawin3d;
	ArrayList<Wall> wallList;
	Explorer explore;
	int size = 20;
	int shrinkBy = 50;
	int moves = 0;
	JButton moveForward;
	JButton moveBackward;
	JButton leftTurn;
	JButton rightTurn;
	long start;
	long lastMoveTime;
	String keepTime;
	int min;
	int sec;
	boolean easy;
	String str;
	boolean gameFinish;
	static boolean easyorhard;
	public runner() 
	{
		
		System.out.println("It is easy or hard: "+easyorhard);
		drawin3d = false;
		str = "";
		gameFinish = false;
		reader();
		frame = new JFrame("Maze");
		frame.add(this);
		start = System.currentTimeMillis();
		lastMoveTime = start;
		frame.setSize(1200, 800);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawin3d = false;
		Location loc = new Location(1,0);
		explore = new Explorer(loc, 1, 20, Color.RED);
		size = 8;
		frame.addKeyListener(this);
		frame.setVisible(true);
	}
	
	
	public void reader() {
		if(easyorhard) {
		File fileName = new File("/Users/abhiram/Desktop/maze.txt");
		maze = new char[12][44];
		try {
			int row = 0;
			int col = 0;
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while((text = input.readLine())!= null) {						
				String[] the = text.split(" ");
				for(String s : the) {
					maze[row][col] = s.charAt(0);
					col++;
				}
			col = 0;
			row++;
		}
		
			
	}catch(IOException e) {
		e.printStackTrace();
		System.out.println("File not found.");
	}
	}else {
		File fileName = new File("/Users/abhiram/Desktop/maze2.txt");
		maze = new char[20][44];
		try {
			int row = 0;
			int col = 0;
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while((text = input.readLine())!= null) {						
				String[] the = text.split(" ");
				for(String s : the) {
					maze[row][col] = s.charAt(0);
					col++;
				}
			col = 0;
			row++;
		}
		
			
	}catch(IOException e) {
		e.printStackTrace();
		System.out.println("File not found.");
	}
	}
}	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //giant eraser 
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1200, 800);
				
		
		if(easyorhard) {
		if(!explore.shouldFinishEasy()) {
		
			if(min<1) {
		if(!drawin3d) {
			g2.setColor(Color.GRAY);
			for(int c = 0; c<maze[0].length; c++) {
				for(int r = 0; r<maze.length; r++) {
					if(maze[r][c]=='O') {
						g2.fillRect(c*25 +25, r*25 +25, 25, 25);
					}else {
						g2.drawRect(c*25 +25, r*25 +25, 25, 25);
					}
				}
			}
			g2.setColor(explore.getColor());
			Rectangle r1 = explore.getRect();
			g2.fill(explore.getRect());
			moves++;
			g2.setColor(Color.WHITE);
			long end = System.currentTimeMillis();
			double second = (end-start)/1000;
			int totalSec = (int)second;
			min = totalSec/60;
			sec = totalSec%60;
			if(sec>9)
			keepTime= min+":"+sec;
			else keepTime = min+":0"+sec;
			System.out.println((end-lastMoveTime)/1000+" SECONDS");
			double lastTime = (end-lastMoveTime)/1000;
			if(lastTime<1) {
				System.out.println("\n"+(int)lastTime+"\n");
				str = "Last Move Time: "+(int)lastTime+" Seconds";
				if(str.equals("Last Move Time: 0 Seconds"))
					g2.drawString("Last Move Time: <0 SECONDS", 600, 550);
			}
				else {
					g2.drawString("Last Move Time: ~"+(int)lastTime+" Seconds", 600, 550);
				}
				str = "Last Move Time: "+(int)lastTime+" Seconds";
			
			g2.drawString("Elapsed Movement Time- "+keepTime, 600, 500);
			g2.drawString("Total Moves Taken: "+moves, 600, 450);
			lastMoveTime = end;
			int i = explore.getDirection();
			g2.drawString("You are moving", 385, 425);
			if(i == 0) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/up.png");
				icon.paintIcon(this, g2, 400, 450);
				System.out.println("ran");
			}else if(i ==1) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/right.png");
				icon.paintIcon(this, g2, 400, 450);
				System.out.println("ran");
			}else if(i ==2) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/down.png");
				icon.paintIcon(this, g2, 400, 450);
				System.out.println("ran");
			}else if(i ==3) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/left.png");
				icon.paintIcon(this, g2, 400, 450);
				System.out.println("ran");
			}
		}else {
			createWalls();
			for(Wall w : wallList) {
				g2.setPaint(w.getGradient());
				g2.fillPolygon(w.getPoly());
				g2.setColor(Color.BLACK);
				g2.drawPolygon(w.getPoly());
			}
			g2.setColor(Color.WHITE);
			long end = System.currentTimeMillis();
			double second = (end-start)/1000;
			int totalSec = (int)second;
			min = totalSec/60;
			sec = totalSec%60;
			if(sec>9)
			keepTime= min+":"+sec;
			else keepTime = min+":0"+sec;
			System.out.println((end-lastMoveTime)/1000+" SECONDS");
			double lastTime = (end-lastMoveTime)/1000;
			if(lastTime<1) {
				System.out.println("\n"+(int)lastTime+"\n");
				str = "Last Move Time: "+(int)lastTime+" Seconds";
				if(str.equals("Last Move Time: 0 Seconds"))
					g2.drawString("Last Move Time: <0 SECONDS", 800, 350);
			}
				else {
					g2.drawString("Last Move Time: ~"+(int)lastTime+" Seconds", 800, 350);
				}
				str = "Last Move Time: "+(int)lastTime+" Seconds";
			
			g2.drawString("Elapsed Movement Time- "+keepTime, 800, 300);
			g2.drawString("Total Moves Taken: "+moves, 800, 250);
			lastMoveTime = end;
			g2.drawString("You are moving", 785, 600);
			int i = explore.getDirection();
			if(i == 0) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/up.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}else if(i ==1) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/right.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}else if(i ==2) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/down.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}else if(i ==3) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/left.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}
		}
		}else {
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 1200, 800);
			g2.setColor(Color.RED);
			Font font = new Font("Serif", Font.PLAIN, 48);
		    g2.setFont(font);
		    
		    g2.drawString("OUT OF TIME. YOU LOSE.", 300, 350);
		    AggressiveEndMenu app = new AggressiveEndMenu();
		}
			
	}else {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1200, 800);
		g2.setColor(Color.GREEN);
		Font font = new Font("Serif", Font.PLAIN, 48);
	    g2.setFont(font);
	    g2.drawString("YOU WIN. A WINNER IS YOU.", 300, 350);
	    g2.drawString("IT TOOK "+moves+" MOVES", 300, 400);
	    EndMenu app = new EndMenu();
	}
	}else {
		if(!explore.shouldFinishHard()) {
			
			if(min<3) {
		if(!drawin3d) {
			g2.setColor(Color.GRAY);
			for(int c = 0; c<maze[0].length; c++) {
				for(int r = 0; r<maze.length; r++) {
					if(maze[r][c]=='O') {
						g2.fillRect(c*25 +25, r*25 +25, 25, 25);
					}else {
						g2.drawRect(c*25 +25, r*25 +25, 25, 25);
					}
				}
			}
			g2.setColor(explore.getColor());
			Rectangle r1 = explore.getRect();
			g2.fill(explore.getRect());
			moves++;
			g2.setColor(Color.WHITE);
			long end = System.currentTimeMillis();
			double second = (end-start)/1000;
			int totalSec = (int)second;
			min = totalSec/60;
			sec = totalSec%60;
			if(sec>9)
			keepTime= min+":"+sec;
			else keepTime = min+":0"+sec;
			System.out.println((end-lastMoveTime)/1000+" SECONDS");
			double lastTime = (end-lastMoveTime)/1000;
			if(lastTime<1) {
				System.out.println("\n"+(int)lastTime+"\n");
				str = "Last Move Time: "+(int)lastTime+" Seconds";
				if(str.equals("Last Move Time: 0 Seconds"))
					g2.drawString("Last Move Time: <0 SECONDS", 100, 650);
			}
				else {
					g2.drawString("Last Move Time: ~"+(int)lastTime+" Seconds", 100, 650);
				}
				str = "Last Move Time: "+(int)lastTime+" Seconds";
			
			g2.drawString("Elapsed Movement Time- "+keepTime, 500, 650);
			g2.drawString("Total Moves Taken: "+moves, 900, 650);
			lastMoveTime = end;
			int i = explore.getDirection();
			g2.drawString("You are moving", 385, 700);
			if(i == 0) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/up.png");
				icon.paintIcon(this, g2, 400, 715);
				System.out.println("ran");
			}else if(i ==1) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/right.png");
				icon.paintIcon(this, g2, 400, 715);
				System.out.println("ran");
			}else if(i ==2) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/down.png");
				icon.paintIcon(this, g2, 400, 715);
				System.out.println("ran");
			}else if(i ==3) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/left.png");
				icon.paintIcon(this, g2, 400, 715);
				System.out.println("ran");
			}
			
		}else {
			createWalls();
			for(Wall w : wallList) {
				g2.setPaint(w.getGradient());
				g2.fillPolygon(w.getPoly());
				g2.setColor(Color.BLACK);
				g2.drawPolygon(w.getPoly());
			}
			g2.setColor(Color.WHITE);
			long end = System.currentTimeMillis();
			double second = (end-start)/1000;
			int totalSec = (int)second;
			min = totalSec/60;
			sec = totalSec%60;
			if(sec>9)
			keepTime= min+":"+sec;
			else keepTime = min+":0"+sec;
			System.out.println((end-lastMoveTime)/1000+" SECONDS");
			double lastTime = (end-lastMoveTime)/1000;
			if(lastTime<1) {
				System.out.println("\n"+(int)lastTime+"\n");
				str = "Last Move Time: "+(int)lastTime+" Seconds";
				if(str.equals("Last Move Time: 0 Seconds"))
					g2.drawString("Last Move Time: <0 SECONDS", 800, 350);
			}
				else {
					g2.drawString("Last Move Time: ~"+(int)lastTime+" Seconds", 800, 350);
				}
				str = "Last Move Time: "+(int)lastTime+" Seconds";
			
			g2.drawString("Elapsed Movement Time- "+keepTime, 800, 300);
			g2.drawString("Total Moves Taken: "+moves, 800, 250);
			lastMoveTime = end;
			g2.drawString("You are moving", 785, 600);
			int i = explore.getDirection();
			if(i == 0) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/up.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}else if(i ==1) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/right.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}else if(i ==2) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/down.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}else if(i ==3) {
				ImageIcon icon = new ImageIcon("/Users/abhiram/Desktop/left.png");
				icon.paintIcon(this, g2, 800, 615);
				System.out.println("ran");
			}
		}
		}else {
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 1200, 800);
			g2.setColor(Color.RED);
			Font font = new Font("Serif", Font.PLAIN, 48);
		    g2.setFont(font);
		    g2.drawString("OUT OF TIME. YOU LOSE.", 300, 350);
		    AggressiveEndMenu app = new AggressiveEndMenu();
		}
		
	}else {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 1200, 800);
		g2.setColor(Color.GREEN);
		Font font = new Font("Serif", Font.PLAIN, 48);
	    g2.setFont(font);
	    g2.drawString("YOU WIN. A WINNER IS YOU.", 300, 350);
	    g2.drawString("IT TOOK "+moves+" MOVES", 300, 400);
	    EndMenu app = new EndMenu();
		
	}
		
	}
	
		
		
	}
	
	public static void main(String [] args) {
		StartMenu apper = new StartMenu();
		//runner app = new runner();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyChar());
		explore.move(e.getKeyCode(), maze);
		if(e.getKeyCode()==32) {
			if(drawin3d) {
				drawin3d = false;
				System.out.println("Drawing in 3d");
			}else {
				drawin3d = true;
				System.out.println("Not drawing in 3d");
			}
		}
		repaint();
	}
	
	public void createWalls() 
	{
		wallList = new ArrayList<Wall>();
		triangleMaker();
		for(int x = 0; x< 5; x++)
			wallList.add(findLeftPath(x));
		for(int x = 0; x< 5; x++)
			wallList.add(findRightPath(x));
		
		int heroR = explore.getLocation().getR();
		int heroC = explore.getLocation().getC();
		if(heroR == 7 && heroC == 43) {
			gameFinish = true;
		}
		int heroDir = explore.getDirection();
		if(easyorhard) {
		if(heroR == 1 && heroC == 0 && heroDir == 3) {
			wallList.add(getLeft(0));
			wallList.add(getRight(0));
			wallList.add(findFront(1));
			wallList.add(findTop(0));
			wallList.add(findFloor(0));
		}else if(heroR == 1 && heroC == 1 && heroDir == 3){
			wallList.add(getLeft(0));
			wallList.add(getLeft(1));
			wallList.add(getRight(0));
			wallList.add(getRight(1));
			wallList.add(findFront(2));
			wallList.add(findTop(0));
			wallList.add(findTop(1));
			wallList.add(findFloor(0));
			wallList.add(findFloor(1));
			wallList.add(findLeftPath(0));
		}else if(heroR == 7 && heroC == 42 && heroDir == 1){
			wallList.add(getLeft(0));
			wallList.add(getLeft(1));
			wallList.add(getRight(0));
			wallList.add(getRight(1));
			wallList.add(goalWall(1));
			wallList.add(findTop(0));
			wallList.add(findFloor(0));
			wallList.add(findRightPath(0));
		
		}else {
		switch(heroDir) {
		case 0: 
			moves++;
			for(int x = 0; x< 5; x++) {
				try {
					
					if(maze[heroR-x][heroC-1]=='F')
						wallList.add(getLeft(x));
						else wallList.add(findLeftPath(x));
				
					if(maze[heroR-x][heroC+1]=='F')
						wallList.add(getRight(x));
					else wallList.add(findRightPath(x));
					
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}wallList.add(findTop(x));
				wallList.add(findFloor(x));
			}
			for(int x = 0; x< 5; x++) {
				try {
				if(maze[heroR-x-1][heroC]=='F') 
				{
					wallList.add(findFront(x+1));
					x=5;
				}
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
			break;	
		 
		
		case 1: 
			moves++;
			for(int x = 0; x< 5; x++) {
				try {
					
					if(maze[heroR-1][heroC+x]=='F')
						wallList.add(getLeft(x));
						else wallList.add(findLeftPath(x));
				
					if(maze[heroR+1][heroC+x]=='F')
						wallList.add(getRight(x));
					else wallList.add(findRightPath(x));
					
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}wallList.add(findTop(x));
				wallList.add(findFloor(x));
			}
			for(int x = 0; x< 5; x++) {
				try {
				if(maze[heroR][heroC+x+1]=='F') 
				{
					wallList.add(findFront(x+1));
					x=5;
				}
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
			break;		
			
			
		case 2: 
			moves++;
			for(int x = 0; x< 5; x++) {
				try {
					
					if(maze[heroR+x][heroC+1]=='F')
						wallList.add(getLeft(x));
						else wallList.add(findLeftPath(x));
				
					if(maze[heroR+x][heroC-1]=='F')
						wallList.add(getRight(x));
					else wallList.add(findRightPath(x));
					
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}wallList.add(findTop(x));
				wallList.add(findFloor(x));
			}
			for(int x = 0; x< 5; x++) {
				try {
				if(maze[heroR+x+1][heroC]=='F') 
				{
					wallList.add(findFront(x+1));
					x=5;
				}
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
			break;		
		
		case 3: 
			moves++;
			for(int x = 0; x< 5; x++) {
				try {
					
					if(maze[heroR+1][heroC-x]=='F')
						wallList.add(getLeft(x));
						else wallList.add(findLeftPath(x));
				
					if(maze[heroR-1][heroC-x]=='F')
						wallList.add(getRight(x));
					else wallList.add(findRightPath(x));
					
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}wallList.add(findTop(x));
				wallList.add(findFloor(x));
			}
			for(int x = 0; x< 5; x++) {
				try {
				if(maze[heroR][heroC-x-1]=='F') 
				{
					wallList.add(findFront(x+1));
					x=5;
				}
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
			break;		
		}
		}
		}else {
			
			if(heroR == 1 && heroC == 0 && heroDir == 3) {
				wallList.add(getLeft(0));
				wallList.add(getRight(0));
				wallList.add(findFront(1));
				wallList.add(findTop(0));
				wallList.add(findFloor(0));
			}else if(heroR == 1 && heroC == 1 && heroDir == 3){
				wallList.add(getLeft(0));
				wallList.add(getLeft(1));
				wallList.add(getRight(0));
				wallList.add(getRight(1));
				wallList.add(findFront(2));
				wallList.add(findTop(0));
				wallList.add(findTop(1));
				wallList.add(findFloor(0));
				wallList.add(findFloor(1));
				wallList.add(findLeftPath(0));
			}else if(heroR == 10 && heroC == 42 && heroDir == 1){
				wallList.add(getLeft(0));
				wallList.add(getLeft(1));
				wallList.add(getRight(0));
				wallList.add(getRight(1));
				wallList.add(goalWall(1));
				wallList.add(findTop(0));
				wallList.add(findFloor(0));
			}else if(heroR == 10 && heroC == 41 && heroDir == 1){
				wallList.add(getLeft(1));
				wallList.add(getRight(0));
				wallList.add(getRight(1));
				wallList.add(goalWall(2));
				wallList.add(findTop(0));
				wallList.add(findTop(1));
				wallList.add(findFloor(0));
				wallList.add(findFloor(1));
				wallList.add(findRightPath(0));
				wallList.add(getTopTri(0));
			
			}else {
			switch(heroDir) {
			case 0: 
				moves++;
				for(int x = 0; x< 5; x++) {
					try {
						
						if(maze[heroR-x][heroC-1]=='F')
							wallList.add(getLeft(x));
							else wallList.add(findLeftPath(x));
					
						if(maze[heroR-x][heroC+1]=='F')
							wallList.add(getRight(x));
						else wallList.add(findRightPath(x));
						
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}wallList.add(findTop(x));
					wallList.add(findFloor(x));
				}
				for(int x = 0; x< 5; x++) {
					try {
					if(maze[heroR-x-1][heroC]=='F') 
					{
						wallList.add(findFront(x+1));
						x=5;
					}
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}
				}
				break;	
			 
			
			case 1: 
				moves++;
				for(int x = 0; x< 5; x++) {
					try {
						
						if(maze[heroR-1][heroC+x]=='F')
							wallList.add(getLeft(x));
							else wallList.add(findLeftPath(x));
					
						if(maze[heroR+1][heroC+x]=='F')
							wallList.add(getRight(x));
						else wallList.add(findRightPath(x));
						
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}wallList.add(findTop(x));
					wallList.add(findFloor(x));
				}
				for(int x = 0; x< 5; x++) {
					try {
					if(maze[heroR][heroC+x+1]=='F') 
					{
						wallList.add(findFront(x+1));
						x=5;
					}
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}
				}
				break;		
				
				
			case 2: 
				moves++;
				for(int x = 0; x< 5; x++) {
					try {
						
						if(maze[heroR+x][heroC+1]=='F')
							wallList.add(getLeft(x));
							else wallList.add(findLeftPath(x));
					
						if(maze[heroR+x][heroC-1]=='F')
							wallList.add(getRight(x));
						else wallList.add(findRightPath(x));
						
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}wallList.add(findTop(x));
					wallList.add(findFloor(x));
				}
				for(int x = 0; x< 5; x++) {
					try {
					if(maze[heroR+x+1][heroC]=='F') 
					{
						wallList.add(findFront(x+1));
						x=5;
					}
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}
				}
				break;		
			
			case 3: 
				moves++;
				for(int x = 0; x< 5; x++) {
					try {
						
						if(maze[heroR+1][heroC-x]=='F')
							wallList.add(getLeft(x));
							else wallList.add(findLeftPath(x));
					
						if(maze[heroR-1][heroC-x]=='F')
							wallList.add(getRight(x));
						else wallList.add(findRightPath(x));
						
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}wallList.add(findTop(x));
					wallList.add(findFloor(x));
				}
				for(int x = 0; x< 5; x++) {
					try {
					if(maze[heroR][heroC-x-1]=='F') 
					{
						wallList.add(findFront(x+1));
						x=5;
					}
					}catch(ArrayIndexOutOfBoundsException e) {
						
					}
				}
				break;		
			}
			}	
			
			
		}
		
		
		
		
					
		}		

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
	public Wall findLeftPath(int n) { 
		int [] rLocs = new int[] {100+shrinkBy*n, 100+shrinkBy*n, 700-shrinkBy*n, 700-shrinkBy*n};
		int [] cLocs = new int[] {50+shrinkBy*n, 100+shrinkBy*n, 100+shrinkBy*n, 50 + shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n,14, 255-shrinkBy*n, "LeftPath", size);
		
	}
	public Wall getLeft(int n) {//trapezoid
		int [] rLocs = new int[] {50+shrinkBy*n, 100+shrinkBy*n, 700-shrinkBy*n, 750-shrinkBy*n};
		int [] cLocs = new int[] {50+shrinkBy*n, 100+shrinkBy*n, 100+shrinkBy*n, 50 + shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255-shrinkBy*n, 255-shrinkBy*n, "LeftTrap", size);
	}

	public Wall findRightPath(int n) {
		int [] rLocs = new int[] {100+shrinkBy*n, 100+shrinkBy*n, 700-shrinkBy*n, 700- shrinkBy*n};
		int [] cLocs = new int[] {700-shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n, 700 - shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 14, 255-shrinkBy*n, "RightPath", size);
		
	}
	public Wall getRight(int n) {//trapezoid
		int [] rLocs = new int[] {100+shrinkBy*n, 50+shrinkBy*n, 750-shrinkBy*n, 700-shrinkBy*n};
		int [] cLocs = new int[] {700-shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n, 700- shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255-shrinkBy*n, 255-shrinkBy*n, "RightTrap", size);
	}
	public Wall findFront(int n) {
		int [] rLocs = new int[] {50+shrinkBy*n, 50+shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n};
		int [] cLocs = new int[] {50+shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n, 50+ shrinkBy*n};
		return new Wall(rLocs, cLocs, 70, 255-shrinkBy*n, 255-shrinkBy*n, "Forward", size);
	}
	
	public Wall findTop(int n) {
		int[] rLocs = {50+shrinkBy*n, shrinkBy+shrinkBy*n, 100+shrinkBy*n, 100+shrinkBy*n};
		int[] cLocs = {50+shrinkBy*n, 750-shrinkBy*n, 700-shrinkBy*n, 100+shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255-shrinkBy*n, 255-shrinkBy*n,"EXTTop", size);	
	}
	
	public Wall findFloor(int n) { //trapezoid
		int[] rLocs = {700-shrinkBy*n, 700-shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n};
		int[] cLocs = {100+shrinkBy*n, 700-shrinkBy*n, 750-shrinkBy*n, shrinkBy+shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255-shrinkBy*n, 255-shrinkBy*n,"EXTBottom", size);
	}
	public Wall goalWall(int n) {
		int [] rLocs = new int[] {50+shrinkBy*n, 50+shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n};
		int [] cLocs = new int[] {50+shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n, 50+ shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255, 255-shrinkBy*n, "Forward", size);

	}
	
	public void triangleMaker() {
		for(int x = 0; x < 5; x++) {
			wallList.add(getTopTri(x));
		}
		for(int x = 0; x< 5; x++) {
			wallList.add(getBotTri(x));
		}
	}
	
	public Wall getBotTri(int n) {
		int[] rLocs = {100+shrinkBy*n, 50+shrinkBy*n, 750-shrinkBy*n, 700-shrinkBy*n};
		int[] cLocs = {700-shrinkBy*n, 750-shrinkBy*n, 750-shrinkBy*n, 700-shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255-shrinkBy*n, 255-shrinkBy*n,"BottomTri", size);
	}
	public Wall getTopTri(int n) {
		int[] rLocs = {50+shrinkBy*n, 100+shrinkBy*n, 700-shrinkBy*n, 750-shrinkBy*n};
		int[] cLocs = {50+shrinkBy*n, 100+shrinkBy*n, 100+shrinkBy*n, 50+shrinkBy*n};
		return new Wall(rLocs, cLocs, 255-shrinkBy*n, 255-shrinkBy*n, 255-shrinkBy*n,"TopTri", size);
	}
	
	
	
	static class StartMenu{
		public StartMenu() {
			JFrame starter = new JFrame();
			JPanel panel = new JPanel();
			panel.setBackground(Color.ORANGE);
			JLabel jlabel = new JLabel("Welcome to the Abhiram Tamvada Maze. Would you like to play the harder or easier maze?");
			JLabel jlabel2 = new JLabel("To start, please select either a hard or easy mode.");
			JLabel jlabel3 = new JLabel("Hard mode has a 3 minute time limit and easy mode has a 1 minute time limit to navigate the maze.");
			JLabel jlabel4 = new JLabel("Once time runs out you will lose.");
			JLabel jlabel5 = new JLabel("Good luck making it out before then.");
			JButton easier = new JButton("Easy Maze Start");
			easier.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						easyorhard = true;
						runner easy = new runner();
						starter.setVisible(false);
					}
				}
			
			);
			JButton harder = new JButton("Hard Maze Start");
			harder.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					easyorhard = false;
					runner hard = new runner();
					starter.setVisible(false);
				}
			}
			);
			panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
			panel.setLayout(new GridLayout(0,1));
			panel.add(jlabel);
			panel.add(easier);
			panel.add(harder);
			panel.add(jlabel2);
			panel.add(jlabel3);
			panel.add(jlabel4);
			panel.add(jlabel5);
			starter.add(panel, BorderLayout.CENTER);
			starter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			starter.setTitle("Start Menu");
			starter.pack();
			starter.setResizable(false);
			starter.setVisible(true);
			
			
		}
		
		
	}
	static class EndMenu{
		JFrame ender;
		public EndMenu() {
			ender = new JFrame();
			JPanel panel = new JPanel();
			panel.setBackground(Color.GREEN);
			JLabel jlabel = new JLabel("Thanks for playing! You Won! Would you like to play again?");
			JButton easier = new JButton("Easy Maze Start");
			easier.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						easyorhard = true;
						runner easy = new runner();
						ender.setVisible(false);
					}
				}
			
			);
			JButton harder = new JButton("Hard Maze Start");
			harder.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					easyorhard = false;
					runner hard = new runner();
					ender.setVisible(false);
				}
			}
			);
			panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
			panel.setLayout(new GridLayout(0,1));
			panel.add(jlabel);
			panel.add(easier);
			panel.add(harder);
			ender.add(panel, BorderLayout.CENTER);
			ender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ender.setTitle("End Menu");
			ender.pack();
			ender.setResizable(false);
			ender.setVisible(true);
			
			
		}

	
	}
	static class AggressiveEndMenu{
		JFrame ender;
		public AggressiveEndMenu() {
			ender = new JFrame();
			JPanel panel = new JPanel();
			panel.setBackground(Color.RED);
			JLabel jlabel = new JLabel("You lost! Try again, foolish one?");
			JButton easier = new JButton("Easy Maze Start");
			easier.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						easyorhard = true;
						runner easy = new runner();
						ender.setVisible(false);
					}
				}
			
			);
			JButton harder = new JButton("Hard Maze Start");
			harder.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					easyorhard = false;
					runner hard = new runner();
					ender.setVisible(false);
				}
			}
			);
			panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
			panel.setLayout(new GridLayout(0,1));
			panel.add(jlabel);
			panel.add(easier);
			panel.add(harder);
			ender.add(panel, BorderLayout.CENTER);
			ender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ender.setTitle("End Menu");
			ender.pack();
			ender.setResizable(false);
			ender.setVisible(true);			
		}
	
	}
	
}

