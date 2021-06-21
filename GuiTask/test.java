
GUI Task
Stephen Dentler
•
Jan 26 (Edited Feb 3)
30 points
Due Feb 8

GUI Instructions.pdf
PDF

A little walkthrough of the GUI.pdf
PDF
Class comments
Your work
Turned in

test.java
Java
Private comments
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class test extends JPanel implements ActionListener{
	JFrame frame;
	JMenuBar menu;
	JPanel buttonPanel, bigPanel;
	GridLayout buttonLayout, bigLayout;
	JButton north,east,reset, west, south;
	JMenu fontOptions, fontSizes, fontColors, textBackgrounds, outlines;
	JMenuItem[] fontOption, fontSize, fontColor, textBackground, outline;
	String[] fontNames, fNames, tNames, oNames;
	JTextArea textArea;
	Font currentFont, resetFont;
	int currentFontSize;
	Font[] fonts, fonts2, fonts3;
	int[] fSizes;
	Color[] bColor,tColor,oColor,tbColor, fColors, tColors, oColors;
	
	
	
	public test() {
		frame = new JFrame("GUI Thing");
		frame.setLayout(new BorderLayout());
		menu = new JMenuBar();
		
		fontOptions = new JMenu("Font");
		fontSizes = new JMenu("Sizes");
		fontColors = new JMenu("Text");
		textBackgrounds = new JMenu("B Color");
		outlines = new JMenu("Outline");
		fontOption = new JMenuItem[3];
		fontSize = new JMenuItem[4];
		fontColor = new JMenuItem[4];
		textBackground = new JMenuItem[4];
		outline = new JMenuItem[4];
		
		menu.setLayout(new GridLayout(1,2));
		
		
		resetFont = new Font("Lucida Grande", Font.PLAIN, 13);
		
		menu.add(fontColors);
		menu.add(fontOptions);
		menu.add(fontSizes);
		menu.add(textBackgrounds);
		menu.add(outlines);
		
		fontNames = new String[]{"Times New Roman", "Arial", "Consolas"};
		fonts = new Font[fontNames.length];
		fSizes = new int[] {18, 24, 36, 48};
		fonts2 = new Font[fSizes.length];
		fColors = new Color[] {Color.BLACK, Color.RED, Color.BLUE, Color.CYAN};
		fNames = new String[] {"Black", "Red", "Blue", "Random"};
		fonts3 = new Font[fColors.length];
		tColors = new Color[] {Color.WHITE, Color.PINK, Color.BLACK, Color.CYAN};
		tNames = new String[] {"White", "Pink", "Black", "Random"};
		oColors = new Color[] {Color.WHITE, Color.RED, Color.BLUE, Color.CYAN};
		oNames = new String[] {"None", "Red", "Blue", "Random"};
		
		
		for(int x = 0; x<fontNames.length;x++) {
			fonts[x] = new Font(fontNames[x], Font.PLAIN, fSizes[0]);
			fontOption[x] = new JMenuItem(fontNames[x]);
			fontOption[x].setFont(fonts[x]);
			fontOption[x].addActionListener(this);
			fontOptions.add(fontOption[x]);
		}
		
		for(int x = 0; x<fSizes.length;x++) {
			fonts2[x] = new Font("Arial", Font.PLAIN, fSizes[0]);
			fontSize[x] = new JMenuItem(fSizes[x]+"");
			fontSize[x].setFont(resetFont);
			fontSize[x].addActionListener(this);
			fontSizes.add(fontSize[x]);
		}
		for(int x = 0; x<fColors.length;x++) {
			fonts3[x] = new Font("Arial", Font.PLAIN, fSizes[0]);
			fontColor[x] = new JMenuItem(fNames[x]+"");
			fontColor[x].setFont(resetFont);
			fontColor[x].addActionListener(this);
			fontColors.add(fontColor[x]);
		}
		Font fonte = new Font("Arial", Font.PLAIN, fSizes[0]);
		for(int x = 0; x<fColors.length;x++) {
			textBackground[x] = new JMenuItem(tNames[x]+"");
			textBackground[x].setFont(resetFont);
			textBackground[x].addActionListener(this);
			textBackgrounds.add(textBackground[x]);
		}
		for(int x = 0; x<oColors.length;x++) {
			outline[x] = new JMenuItem(oNames[x]+"");
			outline[x].setFont(resetFont);
			outline[x].addActionListener(this);
			outlines.add(outline[x]);
		}
		
		
		currentFont = fonts[0];
		currentFontSize = fSizes[0];
		oColor = new Color[1];
		oColor[0] = Color.MAGENTA;
		bColor = new Color[1];
		tColor = new Color[1];
		tbColor = new Color[1];
		bColor[0] = Color.WHITE;
		tColor[0] = Color.BLACK;
		tbColor[0] = Color.BLACK;
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		menu.add(reset);
		
		north = new JButton("North");
		east = new JButton("East");
		south = new JButton("South");
		west = new JButton("West");
		
		buttonPanel = new JPanel();
		buttonLayout = new GridLayout(1,2);
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(north);
		buttonPanel.add(east);
		buttonPanel.add(south);
		buttonPanel.add(west);
		north.addActionListener(this);
		east.addActionListener(this);
		south.addActionListener(this);
		west.addActionListener(this);
		north.setBorder(new LineBorder(oColor[0]));
		east.setBorder(new LineBorder(oColor[0]));
		south.setBorder(new LineBorder(oColor[0]));
		west.setBorder(new LineBorder(oColor[0]));
		reset.setBorder(new LineBorder(oColor[0]));
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(fonts[0]);
		System.out.println(north.getFont());
		bigLayout = new GridLayout(1,2);
		bigPanel = new JPanel();
		bigPanel.setLayout(bigLayout);
		bigPanel.add(buttonPanel);
		bigPanel.add(menu);
		
		frame.add(bigPanel, BorderLayout.NORTH);
		frame.add(textArea, BorderLayout.CENTER);
		frame.setSize(1000,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String [] args) {
		test app = new test();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == north) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(1,2);
			bigLayout = new GridLayout(1,2);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menu);
			bigPanel.remove(buttonPanel);
			
			menu.setLayout(new GridLayout(1,2));
			menu.remove(fontColors);
			menu.remove(fontOptions);
			menu.remove(fontSizes);
			menu.remove(textBackgrounds);
			menu.remove(outlines);
			menu.remove(reset);
			menu.add(fontColors);
			menu.add(fontOptions);
			menu.add(fontSizes);
			menu.add(textBackgrounds);
			menu.add(outlines);
			menu.add(reset);
			bigPanel.add(buttonPanel);
			bigPanel.add(menu);
			frame.add(bigPanel, BorderLayout.NORTH);
		}
		if(e.getSource() == south) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(1,2);
			bigLayout = new GridLayout(1,2);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menu);
			bigPanel.remove(buttonPanel);
			
			menu.setLayout(new GridLayout(1,2));
			menu.remove(fontColors);
			menu.remove(fontOptions);
			menu.remove(fontSizes);
			menu.remove(textBackgrounds);
			menu.remove(outlines);
			menu.remove(reset);
			menu.add(fontColors);
			menu.add(fontOptions);
			menu.add(fontSizes);
			menu.add(textBackgrounds);
			menu.add(outlines);
			menu.add(reset);
			bigPanel.add(buttonPanel);
			bigPanel.add(menu);
			frame.add(bigPanel, BorderLayout.SOUTH);
		}
		if(e.getSource() == east) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(2, 1);
			bigLayout = new GridLayout(2, 1);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menu);
			bigPanel.remove(buttonPanel);
			menu.setLayout(new GridLayout(2, 1));
			menu.remove(fontColors);
			menu.remove(fontOptions);
			menu.remove(fontSizes);
			menu.remove(textBackgrounds);
			menu.remove(outlines);
			menu.remove(reset);
			menu.add(fontColors);
			menu.add(fontOptions);
			menu.add(fontSizes);
			menu.add(textBackgrounds);
			menu.add(outlines);
			menu.add(reset);
			bigPanel.add(buttonPanel);
			bigPanel.add(menu);
			frame.add(bigPanel, BorderLayout.EAST);
		}
		if(e.getSource() == west) {
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(2, 1);
			bigLayout = new GridLayout(2, 1);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menu);
			bigPanel.remove(buttonPanel);
			menu.setLayout(new GridLayout(2, 1));
			menu.remove(fontColors);
			menu.remove(fontOptions);
			menu.remove(fontSizes);
			menu.remove(textBackgrounds);
			menu.remove(outlines);
			menu.remove(reset);
			menu.add(fontColors);
			menu.add(fontOptions);
			menu.add(fontSizes);
			menu.add(textBackgrounds);
			menu.add(outlines);
			menu.add(reset);
			bigPanel.add(buttonPanel);
			bigPanel.add(menu);
			frame.add(bigPanel, BorderLayout.WEST);
		}
		for(int x = 0; x<fontNames.length; x++) {
			if(e.getSource() == fontOption[x]) {
				currentFont = new Font(fonts[x].getName(), Font.PLAIN, currentFontSize);
				textArea.setFont(currentFont);
				north.setFont(currentFont);
				south.setFont(currentFont);
				east.setFont(currentFont);
				west.setFont(currentFont);
				reset.setFont(currentFont);
				outlines.setFont(currentFont);
				textBackgrounds.setFont(currentFont);
				fontOptions.setFont(currentFont);
				fontSizes.setFont(currentFont);
				fontColors.setFont(currentFont);
				
				for(int f = 0; f<fontSizes.getItemCount();f++) fontSize[f].setFont(currentFont);
				for(int f = 0; f<fontColors.getItemCount();f++) fontColor[f].setFont(currentFont);
				for(int f = 0; f<textBackgrounds.getItemCount();f++) textBackground[f].setFont(currentFont);
				for(int f = 0; f<outlines.getItemCount();f++) outline[f].setFont(currentFont);

			}
		}
		for(int x = 0; x<fSizes.length; x++) {
			if(e.getSource() == fontSize[x]) {
				currentFont = new Font(currentFont.getFontName(), Font.PLAIN, fSizes[x]);
				textArea.setFont(currentFont);
				currentFontSize = fSizes[x];
				textArea.setFont(currentFont);
				north.setFont(currentFont);
				south.setFont(currentFont);
				east.setFont(currentFont);
				west.setFont(currentFont);
				reset.setFont(currentFont);
				outlines.setFont(currentFont);
				textBackgrounds.setFont(currentFont);
				fontOptions.setFont(currentFont);
				fontSizes.setFont(currentFont);
				fontColors.setFont(currentFont);
				
				
				for(int f = 0; f<fontOptions.getItemCount();f++) fontOption[f].setFont(new Font(fontOption[f].getFont().getName(), Font.PLAIN, fSizes[x]));
				for(int f = 0; f<fontSizes.getItemCount();f++) fontSize[f].setFont(currentFont);
				for(int f = 0; f<fontColors.getItemCount();f++) fontColor[f].setFont(currentFont);
				for(int f = 0; f<textBackgrounds.getItemCount();f++) textBackground[f].setFont(currentFont);
				for(int f = 0; f<outlines.getItemCount();f++) outline[f].setFont(currentFont);

			
			}
		}
		for(int x = 0; x<fColors.length; x++) {
			if(e.getSource() == fontColor[x]) {
				textArea.setForeground(fColors[x]);
				north.setForeground(fColors[x]);
				south.setForeground(fColors[x]);
				east.setForeground(fColors[x]);
				west.setForeground(fColors[x]);
				reset.setForeground(fColors[x]);
				menu.setForeground(fColors[x]);
				
				for(int f = 0; f<fontOptions.getItemCount();f++) fontOption[f].setForeground(fColors[x]);
				for(int f = 0; f<fontSizes.getItemCount();f++) fontSize[f].setForeground(fColors[x]);
				for(int f = 0; f<fontColors.getItemCount();f++) fontColor[f].setForeground(fColors[x]);
				for(int f = 0; f<textBackgrounds.getItemCount();f++) textBackground[f].setForeground(fColors[x]);
				for(int f = 0; f<outlines.getItemCount();f++) outline[f].setForeground(fColors[x]);
				
				if(x == 3) {
					Color c = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));					
					textArea.setForeground(c);
					north.setForeground(c);
					south.setForeground(c);
					east.setForeground(c);
					west.setForeground(c);
					reset.setForeground(c);
					menu.setForeground(c);
					
					for(int f = 0; f<fontOptions.getItemCount();f++) fontOption[f].setForeground(c);
					for(int f = 0; f<fontSizes.getItemCount();f++) fontSize[f].setForeground(c);
					for(int f = 0; f<fontColors.getItemCount();f++) fontColor[f].setForeground(c);
					for(int f = 0; f<textBackgrounds.getItemCount();f++) textBackground[f].setForeground(c);
					for(int f = 0; f<outlines.getItemCount();f++) outline[f].setForeground(c);
					
				}
			}
		}
		for(int x = 0; x<tColors.length; x++) {
			if(e.getSource() == textBackground[x]) {
				textArea.setBackground(tColors[x]);
				if(x == 3) {
					textArea.setBackground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
				}
			}
		}
		for(int x = 0; x<oColors.length; x++) {
			if(e.getSource() == outline[x]) {
				Color colore = oColors[x];
				if(x == 3) {
					colore = (new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
				}
				if(x==0) {
					colore  = new Color(241,52,54,0);
				}
				north.setBorder(new LineBorder(colore));
				east.setBorder(new LineBorder(colore));
				south.setBorder(new LineBorder(colore));
				west.setBorder(new LineBorder(colore));
				reset.setBorder(new LineBorder(colore));
				
			}
		}
		
		if(e.getSource()== reset) {
			frame.remove(bigPanel);
			frame.remove(bigPanel);
			buttonLayout = new GridLayout(1,2);
			bigLayout = new GridLayout(1,2);
			buttonPanel.setLayout(buttonLayout);
			bigPanel.setLayout(bigLayout);
			bigPanel.remove(menu);
			bigPanel.remove(buttonPanel);
			
			menu.setLayout(new GridLayout(1,2));
			menu.remove(fontColors);
			menu.remove(fontOptions);
			menu.remove(fontSizes);
			menu.remove(textBackgrounds);
			menu.remove(reset);
			menu.add(fontColors);
			menu.add(fontOptions);
			menu.add(fontSizes);
			menu.add(textBackgrounds);
			menu.add(outlines);
			menu.add(reset);
			
			bigPanel.add(buttonPanel);
			bigPanel.add(menu);
			frame.add(bigPanel, BorderLayout.NORTH);
			
			textArea.setText(null);
			textArea.setBackground(bColor[0]);
			textArea.setForeground(tColor[0]);
			Font temp = new Font(fontNames[0], Font.PLAIN, fSizes[0]);
			textArea.setFont(temp);
			north.setBorder(new LineBorder(oColor[0]));
			east.setBorder(new LineBorder(oColor[0]));
			west.setBorder(new LineBorder(oColor[0]));
			south.setBorder(new LineBorder(oColor[0]));
			reset.setBorder(new LineBorder(oColor[0]));
			menu.setBorder(new LineBorder(new Color(0,0,0,0)));
			
			north.setFont(resetFont);
			south.setFont(resetFont);
			east.setFont(resetFont);
			west.setFont(resetFont);
			reset.setFont(resetFont);
			fontColors.setFont(resetFont);
			fontSizes.setFont(resetFont);
			fontOptions.setFont(resetFont);
			textBackgrounds.setFont(resetFont);
			outlines.setFont(resetFont);
			
			
			textArea.setForeground(Color.BLACK);
			textArea.setForeground(Color.BLACK);
			north.setForeground(Color.BLACK);
			south.setForeground(Color.BLACK);
			east.setForeground(Color.BLACK);
			west.setForeground(Color.BLACK);
			reset.setForeground(Color.BLACK);
			menu.setForeground(Color.BLACK);
			
			for(int f = 0; f<fontOptions.getItemCount();f++) { fontOption[f].setFont(new Font(fontNames[f], Font.PLAIN, fSizes[0])); fontOption[f].setForeground(Color.BLACK); fontOption[f].setBorder(new LineBorder(new Color(0,0,0,0)));}
			for(int f = 0; f<fontSizes.getItemCount();f++) { fontSize[f].setFont(resetFont); fontSize[f].setForeground(Color.BLACK); fontSize[f].setBorder(new LineBorder(new Color(0,0,0,0)));}
			for(int f = 0; f<fontColors.getItemCount();f++) { fontColor[f].setFont(resetFont); fontColor[f].setForeground(Color.BLACK); fontColor[f].setBorder(new LineBorder(new Color(0,0,0,0)));}
			for(int f = 0; f<textBackgrounds.getItemCount();f++) { textBackground[f].setFont(resetFont); textBackground[f].setForeground(Color.BLACK); textBackground[f].setBorder(new LineBorder(new Color(0,0,0,0)));}
			for(int f = 0; f<outlines.getItemCount();f++) { outline[f].setFont(resetFont); outline[f].setForeground(Color.BLACK); outline[f].setBorder(new LineBorder(new Color(0,0,0,0)));}
		}
		
		frame.revalidate();
	}
}
