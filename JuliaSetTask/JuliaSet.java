import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;

public class JuliaSet extends JPanel implements AdjustmentListener, MouseListener
{

	BufferedImage image;
	JFrame frame;
	int red,green,blue;
	JScrollBar ABar,BBar, zoomBar, hueBar, satBar, brBar, hue2Bar, iterBar;
	double A = 0.0; 
	double B = 0.0;
	JPanel scrollPanel,labelPanel,bigPanel;
	JLabel ALabel, BLabel, zoomLabel, hueLabel, satLabel, brLabel, hue2Label, iterLabel;
	int width = 1000;
	int height = 500;
	int pixels = 1;
	double zoom = 1.0;
	float hue = 1.0f;
	float saturation = 1.0f;
	float bright = 1.0f;
	float hue2;
	float maxIter = 300;
	
	
	public JuliaSet()
	{
		frame=new JFrame("Julia Set");
		frame.add(this);
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ABar=new JScrollBar(JScrollBar.HORIZONTAL,0,0,-1000,1000);
		A=ABar.getValue();
		ABar.addAdjustmentListener(this);
		ABar.addMouseListener(this);
		BBar=new JScrollBar(JScrollBar.HORIZONTAL,0,0,-1000,1000);
		B=BBar.getValue();
		BBar.addAdjustmentListener(this);
		BBar.addMouseListener(this);
		zoomBar=new JScrollBar(JScrollBar.HORIZONTAL,100,0,0,200);
		zoom=zoomBar.getValue()/100.0;
		zoomBar.addAdjustmentListener(this);
		hueBar=new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,1000);
		hueBar.addAdjustmentListener(this);
		satBar=new JScrollBar(JScrollBar.HORIZONTAL,1000,0,0,1000);
		satBar.addAdjustmentListener(this);
		brBar=new JScrollBar(JScrollBar.HORIZONTAL,1000,0,0,1000);
		brBar.addAdjustmentListener(this);
		hue2Bar=new JScrollBar(JScrollBar.HORIZONTAL,0,0,0,1000);
		hue2Bar.addAdjustmentListener(this);
		iterBar=new JScrollBar(JScrollBar.HORIZONTAL,300,0,0,1000);
		iterBar.addAdjustmentListener(this);
		
		GridLayout grid=new GridLayout(8,1);
		ALabel = new JLabel("A");
		BLabel = new JLabel("B");
		zoomLabel = new JLabel("Zoom");
		hueLabel = new JLabel("Hue");
		satLabel= new JLabel("Saturation");
		brLabel = new JLabel("Brightness");
		hue2Label = new JLabel("Inner Hue");
		iterLabel = new JLabel("Max Iterations");
		
		labelPanel=new JPanel();
		labelPanel.setLayout(grid);
		labelPanel.add(ALabel);
		labelPanel.add(BLabel);
		labelPanel.add(zoomLabel);
		labelPanel.add(hueLabel);
		labelPanel.add(satLabel);
		labelPanel.add(brLabel);
		labelPanel.add(hue2Label);
		labelPanel.add(iterLabel);
		
		scrollPanel=new JPanel();
		scrollPanel.setLayout(grid);
		scrollPanel.add(ABar);
		scrollPanel.add(BBar);
		scrollPanel.add(zoomBar);
		scrollPanel.add(hueBar);
		scrollPanel.add(satBar);
		scrollPanel.add(brBar);
		scrollPanel.add(hue2Bar);
		scrollPanel.add(iterBar);
		
		bigPanel=new JPanel();
		bigPanel.setLayout(new BorderLayout());
		bigPanel.add(labelPanel,BorderLayout.WEST);
		bigPanel.add(scrollPanel,BorderLayout.CENTER);

		frame.add(bigPanel,BorderLayout.SOUTH);

		frame.setVisible(true);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(drawJulia(g),0,0,null);

	}
	public BufferedImage drawJulia(Graphics g)
	{
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int w = 0; w< width; w+=pixels) 
		{	
			for(int h = 0; h < height; h+=pixels) 
			{
				float iter = 300;
				double zx = 1.5*((w-width/2.0)/(0.5 * zoom * width));
				double zy = ((h-height/2.0)/(0.5 * zoom * height));	
				while((((zx*zx) + (zy*zy))<6) && iter > 0) {
					double store = ((zx*zx)-(zy*zy))+A;
					zy = (2.0*zx*zy)+B;
					zx = store;
					iter--;
					
				}
				int c;
				if(iter>0)c = Color.HSBtoRGB((hue*(maxIter / iter) % 1), saturation, bright);
				else c = Color.HSBtoRGB(hue2, 1, 1);
				image.setRGB(w,h,c);
			}
		}
		g.drawImage(image,0,0,null);
		return image;
	}



	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource()==ABar) A= ((double)ABar.getValue()/1000.0); ALabel.setText(" A: "+A+ "\t\t");
		if(e.getSource()==BBar) B=((double)BBar.getValue()/1000.0);BLabel.setText(" B: "+B+ "\t\t");
		if(e.getSource()==hueBar) hue = (float) (hueBar.getValue()/100.0 * 1.0f);hueLabel.setText(" Hue: "+hue+ "\t\t");
		if(e.getSource()==hue2Bar) hue2 = (float) (hue2Bar.getValue()/100.0 * 1.0f);hue2Label.setText(" Inner Hue: "+hue2+ "\t\t");
		if(e.getSource()==zoomBar) zoom = zoomBar.getValue()/100.0;zoomLabel.setText(" Zoom: "+zoom+ "\t\t");
		if(e.getSource()==satBar) saturation = (float)(satBar.getValue()/100.0);satLabel.setText(" Saturation: "+saturation+ "\t\t");
		if(e.getSource()==brBar) bright = (float)(brBar.getValue()/1000.0);brLabel.setText(" Brightness: "+bright+ "\t\t");
		if(e.getSource()==iterBar) maxIter = (float)(iterBar.getValue());iterLabel.setText(" Max Iteration: "+maxIter+ "\t\t");
		repaint();
	}

	public static void main(String[] args)
	{
		JuliaSet app=new JuliaSet();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		pixels = 3;
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		pixels = 1;
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
