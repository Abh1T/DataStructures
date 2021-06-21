import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class aclass extends JPanel implements ActionListener, MouseMotionListener, MouseListener, AdjustmentListener, ChangeListener, KeyListener{
	
	JFrame frame;
	ArrayList<Point> points;
	Color currentColor, backgroundColor, oldColor;;
	JMenuBar bar;
	Stack<Object> shapes, undoRedoStack;
	Shape currShape;
	JMenu colorMenu, fileMenu;
	JMenuItem save,load,clear,exit;
	JMenuItem[] colorOptions;
	Color[] colors;
	Stack<ArrayList<Point>> freeLines;
	JButton freeLineButton, rectangleButton, ovalButton, undoButton, redoButton, eraser;
	int penWidth, currX, currY, currWidth, currHeight;
	JScrollBar penWidthBar;
	JColorChooser colorChooser;
	BufferedImage loadedImage;
	boolean firstClick = true, drawingFreeLine = true, 
			drawingRectangle = false, drawingOval = false, eraserOn = false, shiftPressed;
	JFileChooser fileChooser;
	ImageIcon freeLineImg, rectImg, ovalImg, loadImg, saveImg, undoImg, redoImg, eraserImg;

	public aclass() {
		frame = new JFrame("Bootleg Paint");
		frame.add(this);
		
		colors = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
		
		colorOptions = new JMenuItem[colors.length];
		bar = new JMenuBar();
		colorMenu = new JMenu("Color Options");
		colorMenu.setLayout(new GridLayout(7,1));
		
		for(int x = 0; x<colorOptions.length; x++) {
			colorOptions[x] = new JMenuItem();
			colorOptions[x].putClientProperty("colorIndex", x);
			colorOptions[x].setBackground(colors[x]);
			//colorOptions[x].setOpaque(true);
			//colorOptions[x].setBorderPainted(false);
		    colorOptions[x].addActionListener(this);
		    //colorOptions[x].setPreferredSize(new Dimension(540, 30));
		    colorOptions[x].setFocusable(false);
		    colorMenu.add(colorOptions[x]);
		}
		
		points = new ArrayList<Point>();
		freeLines = new Stack<ArrayList<Point>>();
		shapes = new Stack<>();
		undoRedoStack = new Stack<>();
		currentColor = colors[0];
		drawingFreeLine = true;
		penWidthBar = new JScrollBar(JScrollBar.HORIZONTAL,1,0,1,40);
		penWidthBar.addAdjustmentListener(this);
		penWidthBar.setFocusable(false);
		penWidth = penWidthBar.getValue();
		
		saveImg = new ImageIcon("saveImg.png");
		saveImg = new ImageIcon(saveImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		loadImg = new ImageIcon("loadImg.png");
		loadImg = new ImageIcon(loadImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		freeLineImg = new ImageIcon("freeLineImg.png");
		freeLineImg = new ImageIcon(freeLineImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		rectImg = new ImageIcon("rectImg.png");
		rectImg = new ImageIcon(rectImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		ovalImg = new ImageIcon("ovalImg.png");
		ovalImg = new ImageIcon(ovalImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		eraserImg = new ImageIcon("eraserImg.png");
		eraserImg = new ImageIcon(eraserImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		undoImg = new ImageIcon("undoImg.png");
		undoImg = new ImageIcon(undoImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		redoImg = new ImageIcon("redoImg.png");
		redoImg = new ImageIcon(redoImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		
		
		
		freeLineButton = new JButton();
		freeLineButton.setIcon(freeLineImg);
		freeLineButton.setFocusPainted(false);
		freeLineButton.setBackground(Color.LIGHT_GRAY);
		freeLineButton.setFocusable(false);
		freeLineButton.addActionListener(this);

		rectangleButton = new JButton();
		rectangleButton.setIcon(rectImg);
		rectangleButton.setFocusPainted(false);
		rectangleButton.setFocusable(false);
		rectangleButton.addActionListener(this);

		ovalButton = new JButton();
		ovalButton.setIcon(ovalImg);
		ovalButton.setFocusPainted(false);
		ovalButton.setFocusable(false);
		ovalButton.addActionListener(this);

		eraser = new JButton();
		eraser.setIcon(eraserImg);
		eraser.setFocusPainted(false);
		eraser.setFocusable(false);
		eraser.addActionListener(this);

		undoButton = new JButton();
		undoButton.setIcon(undoImg);
		undoButton.setFocusPainted(false);
		undoButton.setFocusable(false);
		undoButton.addActionListener(this);

		redoButton = new JButton();
		redoButton.setIcon(redoImg);
		redoButton.setFocusPainted(false);
		redoButton.setFocusable(false);
		redoButton.addActionListener(this);
		
		
		fileMenu = new JMenu("File");
		save = new JMenuItem("Save", KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.setIcon(saveImg);
		load = new JMenuItem("Load", KeyEvent.VK_L);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		load.setIcon(loadImg);
		clear = new JMenuItem("Clear");
		exit = new JMenuItem("Exit");
		save.addActionListener(this);
		load.addActionListener(this);
		clear.addActionListener(this);
		exit.addActionListener(this);
		fileMenu.add(clear);
		fileMenu.add(load);
		fileMenu.add(save);
		fileMenu.add(exit);
		String currDir = System.getProperty("user.dir");
		fileChooser = new JFileChooser(currDir);
		


		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		colorMenu.add(colorChooser);
		bar.add(fileMenu);
		bar.add(colorMenu);
		bar.add(freeLineButton);
		bar.add(rectangleButton);
		bar.add(ovalButton);
		bar.add(undoButton);
		bar.add(redoButton);
		bar.add(eraser);
		bar.add(penWidthBar);
		shiftPressed = false;
		backgroundColor = Color.WHITE;
		frame.addKeyListener((KeyListener) this);
		frame.add(bar, BorderLayout.NORTH);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		frame.setSize(1000,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(backgroundColor);
		g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		if (loadedImage != null)
			g2.drawImage(loadedImage, 0, 0, null);
		Iterator it = shapes.iterator();
		while(it.hasNext()) {
			Object next = it.next();
			 if (next instanceof Oval) {
				Oval temp = (Oval) next;
				g2.setColor(temp.getColor());
				g2.setStroke(new BasicStroke(temp.getPenWidth()));
				g2.draw(temp.getShape());
			}else if (next instanceof Rectangle) {
				Rectangle temp = (Rectangle) next;
				g2.setColor(temp.getColor());
				g2.setStroke(new BasicStroke(temp.getPenWidth()));
				g2.draw(temp.getShape());
			}else{
				ArrayList<?> temp = (ArrayList<?>)next; 
				if (temp.size() > 0)  {
					g2.setStroke(new BasicStroke(((Point)temp.get(0)).getPenWidth()));
					for (int x = 0; x<temp.size()-1; x++) {
						Point p1 = (Point)(temp.get(x));
						Point p2 = (Point)(temp.get(x+1));
						g2.setColor(p1.getColor());
						g2.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
					}
				}
			}
		}
		if (drawingFreeLine && points.size() > 0) {
			g2.setColor(points.get(0).getColor());
			g2.setStroke(new BasicStroke(points.get(0).getPenWidth()));
			for (int x = 0; x<points.size()-1; x++) {
				Point p1 = points.get(x);
				Point p2 = points.get(x+1);
				g2.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if (drawingRectangle || drawingOval) {
			if (firstClick) {
				currX = e.getX();
				currY = e.getY();
				if (drawingRectangle)
					currShape = new Rectangle(currX, currY, currentColor, penWidth, 0, 0);
				else currShape = new Oval(currX, currY, currentColor, penWidth, 0, 0);
				firstClick = false;
				shapes.push(currShape);
			}else {
				currWidth = Math.abs(e.getX() - currX);
				currHeight = Math.abs(e.getY() - currY);
				currShape.setWidth(currWidth);
				currShape.setHeight(currHeight);
				if (currX <= e.getX() && currY >= e.getY())
					currShape.setY(e.getY());
				else if (currX >= e.getX() && currY <= e.getY())
					currShape.setX(e.getX());
				else if (currX >= e.getX() && currY >= e.getY()) {
					currShape.setX(e.getX());
					currShape.setY(e.getY());
				}
			}
		}
		else points.add(new Point(e.getX(), e.getY(), currentColor, penWidth));
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//this is da saving
		if (e.getSource() == save) {
			FileFilter filter = new FileNameExtensionFilter("*.png", "png");
			fileChooser.setFileFilter(filter);
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					String str = file.getAbsolutePath();
					if (str.indexOf(".png") >= 0)
						str = str.substring(0, str.length()-4);
					ImageIO.write(createImage(), "png", new File(str + ".png"));
				}catch(IOException ioe) {}
			}
		}

		//this is da loading
		else if (e.getSource() == load) {
			fileChooser.showOpenDialog(null);
			File imgFile = fileChooser.getSelectedFile();
			if (imgFile != null && imgFile.toString().indexOf(".png") >= 0) {
				try {
					loadedImage = ImageIO.read(imgFile);
				} catch(IOException ioe) {}
				shapes = new Stack<>();
				repaint();
			}else {
				if (imgFile != null) JOptionPane.showMessageDialog(null, "You messed up lol.");
			}
		}
		
		else if (e.getSource() == ovalButton) {
			drawingFreeLine = false;
			drawingRectangle = false;
			drawingOval = true;
			eraserOn = false;
			freeLineButton.setBackground(null);
			rectangleButton.setBackground(null);
			ovalButton.setBackground(Color.LIGHT_GRAY);
			eraser.setBackground(null);
			currentColor = oldColor;
		}
		
		else if (e.getSource() == rectangleButton) {
			drawingFreeLine = false;
			drawingRectangle = true;
			drawingOval = false;
			eraserOn = false;
			freeLineButton.setBackground(null);
			rectangleButton.setBackground(Color.LIGHT_GRAY);
			ovalButton.setBackground(null);
			eraser.setBackground(null);
			currentColor = oldColor;
		}

		else if (e.getSource() == eraser) {
			drawingFreeLine = true;
			drawingRectangle = false;
			drawingOval = false;
			eraserOn = true;
			freeLineButton.setBackground(null);
			rectangleButton.setBackground(null);
			ovalButton.setBackground(null);
			eraser.setBackground(Color.LIGHT_GRAY);
			oldColor = currentColor;
			currentColor = backgroundColor;
		}
		
		else if (e.getSource() == freeLineButton) {
			drawingFreeLine = true;
			drawingRectangle = false;
			drawingOval = false;
			eraserOn = false;
			freeLineButton.setBackground(Color.LIGHT_GRAY);
			rectangleButton.setBackground(null);
			ovalButton.setBackground(null);
			eraser.setBackground(null);
			currentColor = oldColor;
		}

		else if (e.getSource() == undoButton) { undo(); }
		else if (e.getSource() == redoButton) { redo(); }
		else if (e.getSource() == clear) { shapes = new Stack<>();  loadedImage = null;   repaint(); }
		else if (e.getSource() == exit) {System.exit(0);}
		else { //colorBar
			if (eraserOn) {
				drawingFreeLine = true;
				drawingRectangle = false;
				drawingOval = false;
				eraserOn = false;
				freeLineButton.setBackground(Color.LIGHT_GRAY);
				rectangleButton.setBackground(null);
				ovalButton.setBackground(null);
				eraser.setBackground(null);
			}
			int index = (int)((JMenuItem)e.getSource()).getClientProperty("colorIndex");
			currentColor = colors[index];
		}
	}
	
	public void undo() {
		if (!shapes.isEmpty()) { undoRedoStack.push(shapes.pop());  repaint(); }
	}
	public void redo() {
		if (!undoRedoStack.isEmpty()) { shapes.push(undoRedoStack.pop()); repaint(); }
	}

	public static void main(String[] args) {
		aclass app = new aclass();
	}
	public class Point
	{
		int x,y, penWidth;
		Color color;
		public Point(int x, int y, Color color, int penWidth) 
		{
			this.x = x;
			this.y = y;
			this.color = color;
			this.penWidth = penWidth;
		}
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getPenWidth() {
			return penWidth;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public Color getColor() {
			return color;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (drawingRectangle || drawingOval) {
			firstClick = true;
		}else {
			shapes.push(points);
			points = new ArrayList<Point>();
		}
		undoRedoStack = new Stack<>(); 
		repaint();
	}
	
	public BufferedImage createImage()
	{
		int width=this.getWidth();
		int height=this.getHeight();
		BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=img.createGraphics();
		this.paint(g2);
		g2.dispose();
		return img;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		penWidth = penWidthBar.getValue();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		currentColor = colorChooser.getColor(); 
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if (e.isControlDown()) {
			if (e.getKeyCode() == KeyEvent.VK_Z)
				undo();
			if (e.getKeyCode() == KeyEvent.VK_Y)
				redo();
		}
	}
	
	public class Shape extends Point {
		private int width, height;
		public Shape(int x, int y, Color c, int penWidth, int width, int height) {
			super(x, y, c, penWidth);
			this.width = width;
			this.height = height;
		}
		public void setWidth(int w) {
			width = w;
		}
		public void setHeight(int h) {
			height = h;
		}
		public int getHeight() {
			return height;
		}
		public int getWidth() {
			return width;
		}
	}

	
	public class Rectangle extends Shape {
		public Rectangle(int x, int y, Color c, int penWidth, int width, int height) {
			super(x, y, c, penWidth, width, height);
		}
		public Rectangle2D getShape() {
			return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
		}
	}

	
	
	public class Oval extends Shape {
		public Oval(int x, int y, Color c, int penWidth, int width, int height) {
			super(x, y, c, penWidth, width, height);
		}
		public Ellipse2D getShape() {
			return new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
		}
	}
	
	
	
}
