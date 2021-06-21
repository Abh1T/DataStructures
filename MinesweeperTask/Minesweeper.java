import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.Image.*;
import java.util.Timer;
import java.util.TimerTask;
public class Minesweeper extends JFrame implements ActionListener, MouseListener
{
	JToggleButton[][] board;
	JPanel boardPanel;
	boolean firstClick;
	int numMines;
	ImageIcon mineIcon, flag, face;
	GraphicsEnvironment ge;
	Font mineFont, timeFont;
	Timer timer;
	boolean waiting;
	int timePassed;
	JTextField timeField;
	int boo = 9;
	ImageIcon[] numbers;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem beginner, intermediate, expert;
	JButton reset;
	int hoo = 9;
	
	public Minesweeper()
	{
		timeField = new JTextField();
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		beginner = new JMenuItem("Beginner");
		intermediate = new JMenuItem("Intermediate");
		expert = new JMenuItem("Expert");
		reset = new JButton();
		beginner.addActionListener(this);
		expert.addActionListener(this);
		intermediate.addActionListener(this);
		reset.addActionListener(this);
		menu.add(beginner);
		menu.add(intermediate);
		menu.add(expert);
//		boardPanel.add(menuBar);
	//	boardPanel.add(menu);
		
		
		flag = new ImageIcon("/Users/abhiram/Desktop/flag.png");
		flag = new ImageIcon(flag.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
		
		face = new ImageIcon("/Users/abhiram/Desktop/smile0.png");
		face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
		reset.setIcon(face);
		reset.setDisabledIcon(face);
		reset.setEnabled(true);
		
		numbers = new ImageIcon[8];
		for(int x = 0; x<8; x++) {
			numbers[x] = new ImageIcon("/Users/abhiram/Desktop/"+(x+1)+".png");
			numbers[x] = new ImageIcon(numbers[x].getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
		}
		
		firstClick = true;
		numMines = 10;
		createBoard(10,20);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void createBoard(int row, int col) 
	{
		boo = row;
		hoo = col;
		if(boardPanel != null)
		this.remove(boardPanel);
		boardPanel = new JPanel();
		
		
		board = new JToggleButton[row][col];
		boardPanel.setLayout(new GridLayout(row, col));
		menuBar.setLayout(new GridLayout(1, 3));
		menuBar.add(menu);
		menuBar.add(reset);
		this.add(menuBar, BorderLayout.NORTH);
		
		try {
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			mineFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/abhiram/Desktop/mine-sweeper.ttf"));
			ge.registerFont(mineFont);
			timeFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/abhiram/Desktop/digital-7.ttf"));
			ge.registerFont(timeFont);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mineIcon = new ImageIcon("/Users/abhiram/Desktop/mine.png");
		mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
		timeField.setFont(timeFont.deriveFont(18f));
		timeField.setForeground(Color.GREEN);
		timeField.setBackground(Color.black);
		timeField.setText("0");
		menuBar.add(timeField);
		for(int r = 0; r<row; r++) {
			for(int c = 0; c< col; c++) {
				board[r][c] = new JToggleButton();
				board[r][c].putClientProperty("row", r);
				board[r][c].putClientProperty("column", c);
				board[r][c].putClientProperty("count", 0);
				board[r][c].setBorder(BorderFactory.createBevelBorder(0));
				board[r][c].setFont(mineFont.deriveFont(12f));
				board[r][c].setFocusPainted(false);				
				board[r][c].addMouseListener(this);
				boardPanel.add(board[r][c]);
			}
		}
		this.setSize(col*40, row*40);
		this.add(boardPanel);
		this.revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reset) {
			firstClick = true;
			createBoard(boo,hoo);
			face = new ImageIcon("/Users/abhiram/Desktop/smile0.png");
			face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
			reset.setIcon(face);
			reset.setDisabledIcon(face);
			reset.setEnabled(true);
			timePassed = 0;
			timeField.setText(timePassed+"");
		}
		if(e.getSource() == beginner) {
			numMines = 10;
			boo = 9;
			hoo = 9;
			firstClick = true;
			createBoard(9,9);
			face = new ImageIcon("/Users/abhiram/Desktop/smile0.png");
			face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
			reset.setIcon(face);
			reset.setDisabledIcon(face);
			reset.setEnabled(true);
		}
		if(e.getSource() == intermediate) {
			numMines = 40;
			boo= 16;
			hoo = 16;
			firstClick = true;
			createBoard(16,16);
			face = new ImageIcon("/Users/abhiram/Desktop/smile0.png");
			face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
			reset.setIcon(face);
			reset.setDisabledIcon(face);
			reset.setEnabled(true);
		}
		if(e.getSource()== expert) {
			numMines = 99;
			boo = 16;
			hoo = 40;
			firstClick = true;
			createBoard(16,40);
			face = new ImageIcon("/Users/abhiram/Desktop/smile0.png");
			face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
			reset.setIcon(face);
			reset.setDisabledIcon(face);
			reset.setEnabled(true);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(waiting) {
		face = new ImageIcon("/Users/abhiram/Desktop/wait0.png");
		face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
		reset.setIcon(face);
		reset.setDisabledIcon(face);
		reset.setEnabled(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int row  = (int)((JToggleButton)e.getComponent()).getClientProperty("row");
		int col = (int)((JToggleButton)e.getComponent()).getClientProperty("column");
		if(e.getButton() == MouseEvent.BUTTON1 && board[row][col].isEnabled()) {
			
			face = new ImageIcon("/Users/abhiram/Desktop/smile0.png");
			face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
			reset.setIcon(face);
			reset.setDisabledIcon(face);
			reset.setEnabled(true);
			board[row][col].setBackground(Color.LIGHT_GRAY);
			board[row][col].setOpaque(true);
			if(firstClick) {
				timer = new Timer();
				timer.schedule(new UpdateTimer(), 0,1000);
				setMinesAndCounts(row, col);
				firstClick = false;	
			}
			waiting = true;
			int state =  (int)((JToggleButton)board[row][col]).getClientProperty("count");
			if(state == -1) {
				board[row][col].setIcon(mineIcon);
				board[row][col].setContentAreaFilled(false);
				board[row][col].setOpaque(true);
				board[row][col].setBackground(Color.RED);
				revealMines(boo, hoo);		
				face = new ImageIcon("/Users/abhiram/Desktop/lose0.png");
				face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
				reset.setIcon(face);
				reset.setDisabledIcon(face);
				reset.setEnabled(true);
				timer.cancel();
				JOptionPane.showMessageDialog(null, "You lose!");
				
				//board[row][col].setBackground(Color.RED);
				//flip over mine locations
			}else {
				expand(row,col);
				checkWin();
			}
		}
		if(e.getButton()== MouseEvent.BUTTON3) 
		{
			if(!board[row][col].isSelected()) 
			{
				if(board[row][col].getIcon() == null) 
				{
					board[row][col].setIcon(flag);
					board[row][col].setDisabledIcon(flag);
					board[row][col].setEnabled(false);
				}else {
					board[row][col].setIcon(null);
					board[row][col].setDisabledIcon(null);
					board[row][col].setEnabled(true);
				}
			}
		}
	}
	public void revealMines(int r, int c) {
		for(int rr = 0; rr<r; rr++) {
			for(int cc = 0; cc<c; cc++) {
				int state =  (int)((JToggleButton)board[rr][cc]).getClientProperty("count");
				if(state==-1) {
					board[rr][cc].setIcon(mineIcon);
					board[rr][cc].setDisabledIcon(mineIcon);
				}
				board[rr][cc].setEnabled(false);
			}
		}
	}
	public void write(int row, int col, int state) {
		Color c = Color.BLUE;
		switch(state) {
			case 2: c = Color.GREEN; break;
			
			case 3: c = Color.RED; break;
			
			case 4: c = new Color(128,0,128); break;
			
			case 5: c = new Color(128,0,0); break;
			
			case 6: c = Color.CYAN; break;
			
			case 7: c = Color.MAGENTA; break;
			
			case 8: c = Color.GRAY; break;
		}
		
		if(state>0) {
		//	board[row][col].setForeground(c);
			board[row][col].setBackground(Color.LIGHT_GRAY);
			board[row][col].setIcon(numbers[state-1]);
			board[row][col].setDisabledIcon(numbers[state-1]);
			board[row][col].setOpaque(true);
			//board[row][col].setText(""+state);
		}
	}
	public void expand(int row, int col) 
	{
		if(!board[row][col].isSelected()) {
			board[row][col].setSelected(true);
			board[row][col].setBackground(Color.LIGHT_GRAY);
			board[row][col].setOpaque(true);
		}
		int state =  (int)((JToggleButton)board[row][col]).getClientProperty("count");
		if(state > 0)
		{
			write(row,col,state);
			
		}
		else 
		{
			for(int rr = row-1; rr<=row+1; rr++) 
			{
				for(int cc = col-1;cc<=col+1; cc++) 
				{
					try {
						if(!board[rr][cc].isSelected()) 
						{
							expand(rr,cc);
							
						}
					}catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
		
		
	}
	public void setMinesAndCounts(int currRow, int currCol) {
		int count = numMines;
		int dimR = board.length;
		int dimC = board[0].length;
		while(count>0) {
			int randR = (int)(Math.random()*dimR);
			int randC = (int)(Math.random()*dimC);
			int state =  (int)(((JToggleButton)board[randR][randC]).getClientProperty("count"));
			if(state == 0 && (Math.abs(currRow-randR)>1 || Math.abs(currCol-randC)>1)) {
				board[randR][randC].putClientProperty("count", -1); // -1 is a mine
				count--;
			}
			//currRow-randR>1 || currCol-randC>1
		}
		
		//button counts
		
		for(int r = 0; r<dimR; r++) {
			for(int c = 0;c< dimC; c++) {
				int state =  (int)((JToggleButton)board[r][c]).getClientProperty("count");
				//board[r][c].setText(""+state);
				//if(state==-1)
				//board[r][c].setForeground(Color.BLUE);
			}
		}
		for(int r = 0; r<dimR; r++) {
			for(int c = 0;c< dimC; c++) {
				int counter = 0;
				if((int)((JToggleButton)board[r][c]).getClientProperty("count") != -1) {
				for(int rr = r-1; rr<=r+1; rr++) {
					for(int cc = c-1;cc<=c+1; cc++) {
						try {
							int state =  (int)((JToggleButton)board[rr][cc]).getClientProperty("count");
							if(state==-1) {
								counter++;
							}
						}catch(Exception e) {
							
						}
					}
				}
				board[r][c].putClientProperty("count", counter);
				//board[r][c].setText(""+counter);
				}
			}
		}
	
	}
	
	
	
	public void checkWin() 
	{
		int dimR = board.length;
		int dimC = board[0].length;
		int totalSpaces = dimR * dimC;
		int count = 0;
		for(int r = 0; r<dimR; r++) {
			for(int c = 0; c<dimC; c++) {
				int state = (int)board[r][c].getClientProperty("count");
				
				if(board[r][c].isSelected() && state!= -1) {
					count++;
					
				}
			}
		}
		if(numMines== totalSpaces-count) {
			face = new ImageIcon("/Users/abhiram/Desktop/win0.png");
			face = new ImageIcon(face.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
			reset.setIcon(face);
			reset.setDisabledIcon(face);
			reset.setEnabled(true);
			timer.cancel();
			JOptionPane.showMessageDialog(null, "A winner is you!");
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args)
	{
		Minesweeper app=new Minesweeper();
	}
	public class UpdateTimer extends TimerTask{

		@Override
		public void run() {
			timePassed++;
			timeField.setText(timePassed+"");
			
		}
		
	}

}
