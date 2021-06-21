import java.util.*;
import javax.swing.*;
import java.io.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import javax.sound.sampled.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class test extends JFrame implements Runnable,AdjustmentListener, ActionListener
{

	JToggleButton button [][] = new JToggleButton[37][150];
	JScrollPane buttonPane;
	JScrollBar tempoBar;
	JMenuBar menuBar;
	JMenu file,InstrumentMenu, adjustMenu, songs;
	JMenuItem save,load, addColumn, removeColumn, add20Column, remove20Column, ngnl, mario, old;
	JMenuItem[] instrumentItems;
	JButton stopPlay,clear, random;
	JFileChooser fileChooser;
	JLabel[] labels = new JLabel[button.length];
	JPanel buttonPanel, labelPanel, tempoPanel,menuButtonPanel;
	JLabel tempoLabel;
	boolean notStopped = true;
	JFrame frame = new JFrame();
	String[] clipNames;
	Clip[] clip;
	int tempo;
	Thread timing;
	boolean playing = false;
	int row = 0, col = 0;
	Font font = new Font("Times New Roman", Font.PLAIN, 10);
	String[] instrumentNames = {"Piano"};


	public test()
	{
		setSize(1000, 600);
		clipNames = new String[] {"C0", "B1", "ASharp1", "A1", "GSharp1", "G1", "FSharp1", "F1", "E1", "DSharp1","D1","CSharp1","C1", "B2", "ASharp2", "A2", "GSharp2", "G2", "FSharp2", "F2", "E2", "DSharp2","D2","CSharp2","C2", "B3", "ASharp3", "A3", "GSharp3", "G3", "FSharp3", "F3", "E3", "DSharp3","D3","CSharp3","C3"};
		clip = new Clip[clipNames.length];
		String initInstrument = instrumentNames[0];
		try {
			for(int x = 0; x< clipNames.length; x++) {
				URL url = this.getClass().getClassLoader().getResource(initInstrument+" - "+clipNames[x]+".wav");
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				clip[x] = AudioSystem.getClip();
				clip[x].open(audioIn);
			}
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(button.length, button[0].length, 2,5));
		for(int r = 0; r<button.length; r++) {
			String name = clipNames[r].replaceAll("Sharp", "#");
			for(int c = 0; c<button[0].length; c++) {
				button[r][c] = new JToggleButton();
				button[r][c].setFont(font);
				button[r][c].setText(name);
				button[r][c].setPreferredSize(new Dimension(30,30));
				button[r][c].setMargin(new Insets(0,0,0,0));
				buttonPanel.add(button[r][c]);
			}
		}
		tempoBar = new JScrollBar(JScrollBar.HORIZONTAL,200,0,50,1000);
		tempoBar.addAdjustmentListener(this);
		tempo = tempoBar.getValue();
		tempoLabel = new JLabel("Tempo: "+tempo);
		tempoPanel=new JPanel();
		tempoPanel.add(tempoLabel, BorderLayout.WEST);
		tempoPanel.add(tempoBar, BorderLayout.CENTER);
		
		random =  new JButton("Random");
		
		String currDir = System.getProperty("user.dir");
		fileChooser = new JFileChooser(currDir);

		menuBar = new JMenuBar();
		menuBar.setLayout(new GridLayout(1,2));
		file = new JMenu("File");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		file.add(save);
		file.add(load);
		random.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);

		InstrumentMenu = new JMenu("Instruments");
		instrumentItems = new JMenuItem[instrumentNames.length];
		for(int x = 0; x< instrumentNames.length; x++) {
			instrumentItems[x] = new JMenuItem(instrumentNames[x]);
			instrumentItems[x].addActionListener(this);
			InstrumentMenu.add(instrumentItems[x]);
		}

		menuBar.add(file);
		menuBar.add(InstrumentMenu);
		
		songs = new JMenu("Songs");
		mario = new JMenuItem("Hunter");
		mario.addActionListener(this);
		ngnl = new JMenuItem("This Game");
		ngnl.addActionListener(this);
		old = new JMenuItem("Old Iphone");
		old.addActionListener(this);
		songs.add(mario);
		songs.add(ngnl);
		songs.add(old);
		menuBar.add(songs);
		
		adjustMenu = new JMenu("Adjust Columns");
		addColumn = new JMenuItem("Add Column");
		addColumn.addActionListener(this);
		removeColumn = new JMenuItem("Remove Column");
		removeColumn.addActionListener(this);
		add20Column = new JMenuItem("Add 20 Columns");
		add20Column.addActionListener(this);
		remove20Column = new JMenuItem("Remove 20 Columns");
		remove20Column.addActionListener(this);
		adjustMenu.add(addColumn);
		adjustMenu.add(removeColumn);
		adjustMenu.add(add20Column);
		adjustMenu.add(remove20Column);
		menuBar.add(adjustMenu);
		
		menuButtonPanel = new JPanel();
		menuButtonPanel.setLayout(new GridLayout(1,2));
		stopPlay = new JButton("Play");
		stopPlay.addActionListener(this);
		menuButtonPanel.add(stopPlay);

		menuButtonPanel.add(random);
		clear = new JButton("Clear");
		clear.addActionListener(this);
		menuButtonPanel.add(clear);
		menuBar.add(menuButtonPanel, BorderLayout.EAST);

		buttonPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		this.add(buttonPane, BorderLayout.CENTER);
		this.add(tempoPanel, BorderLayout.SOUTH);
		this.add(menuBar, BorderLayout.NORTH);

		setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timing=new Thread(this);
		timing.start();
	}
	public void run() {
		do {
			try {
				if(!playing) {
					timing.sleep(0);
				}else {
					for(int r= 0; r< button.length; r++) {
						if(button[r][col].isSelected()) {
							clip[r].start();
							button[r][col].setForeground(Color.YELLOW);
						}
					}
					timing.sleep(tempo);
					for(int r= 0; r< button.length; r++) {
						if(button[r][col].isSelected()) {
							clip[r].stop();
							clip[r].setFramePosition(0);
							button[r][col].setForeground(Color.BLACK);
						}
					}
					col++;
					if(col  == button[0].length) {
						col = 0;
					}
				}
				}catch(Exception e){
			}
		}while(notStopped);
	}

	public static void main(String [] args)
	{
		test app = new test();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == stopPlay) {
			playing = !playing;
			if (!playing)
				stopPlay.setText("Play");
			else
				stopPlay.setText("Stop");
		}
		if(e.getSource() == load){
            int returnVal = fileChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                try{
                    File loadFile = fileChooser.getSelectedFile();
                    BufferedReader input = new BufferedReader(new FileReader(loadFile));
                    String temp;
                    temp = input.readLine();
                    tempo = Integer.parseInt(temp.substring(0,3));
                    tempoBar.setValue(tempo);
                    Character[][] song = new Character[button.length][temp.length()-2];

                    int r = 0;
                    while((temp=input.readLine()) != null){
                        for(int c = 2;c<song[0].length;c++){
                            song[r][c-2] = temp.charAt(c);
                        }
                        r++;
                    }
                    setNotes(song);
                }catch (IOException ee){

                }
                col = 0;
                playing = false;
                stopPlay.setText("Play");
            }
        }
		
		if (e.getSource() == clear) {
			for (int r = 0; r<button.length; r++) {
				for (int c = 0; c<button[0].length; c++) {
					if(button[r][c] != null)
					button[r][c].setSelected(false);
				}
			}
			col = 0;
            playing = false;
            stopPlay.setText("Play");
		}
		if(e.getSource() == save) {
			saveSong();
		}

		if(e.getSource() == ngnl) {
			try{
                File loadFile = new File("/Users/abhiram/Desktop/ngnlop.txt");
                BufferedReader input = new BufferedReader(new FileReader(loadFile));
                String temp;
                temp = input.readLine();
                tempo = Integer.parseInt(temp.substring(0,3));
                tempoBar.setValue(tempo);
                Character[][] song = new Character[button.length][temp.length()-2];

                int r = 0;
                while((temp=input.readLine()) != null){
                    for(int c = 2;c<song[0].length;c++){
                        song[r][c-2] = temp.charAt(c);
                    }
                    r++;
                }
                setNotes(song);
            }catch (IOException ee){

            }
            col = 0;
            playing = false;
            stopPlay.setText("Play");
        
		}
		if(e.getSource() == old) {
			try{
                File loadFile = new File("/Users/abhiram/Desktop/iphone.txt");
                BufferedReader input = new BufferedReader(new FileReader(loadFile));
                String temp;
                temp = input.readLine();
                tempo = Integer.parseInt(temp.substring(0,3));
                tempoBar.setValue(tempo);
                Character[][] song = new Character[button.length][temp.length()-2];

                int r = 0;
                while((temp=input.readLine()) != null){
                    for(int c = 2;c<song[0].length;c++){
                        song[r][c-2] = temp.charAt(c);
                    }
                    r++;
                }
                setNotes(song);
            }catch (IOException ee){

            }
            col = 0;
            playing = false;
            stopPlay.setText("Play");
        
		}
		if(e.getSource() == mario) {
			try{
                File loadFile = new File("/Users/abhiram/Desktop/Hunter.txt");
                BufferedReader input = new BufferedReader(new FileReader(loadFile));
                String temp;
                temp = input.readLine();
                tempo = Integer.parseInt(temp.substring(0,3));
                tempoBar.setValue(tempo);
                Character[][] song = new Character[button.length][temp.length()-2];

                int r = 0;
                while((temp=input.readLine()) != null){
                    for(int c = 2;c<song[0].length;c++){
                        song[r][c-2] = temp.charAt(c);
                    }
                    r++;
                }
                setNotes(song);
            }catch (Exception  ee){

            }
            col = 0;
            playing = false;
            stopPlay.setText("Play");
        
		}
		if(e.getSource() == addColumn) {
			column(-1);
			playing = false;
			stopPlay.setText("Play");
		}
		if(e.getSource() == removeColumn) {
			if(button[0].length-1>1) {
				column(1);
				playing = false;
				col = button[0].length-1;
				stopPlay.setText("Play");
			}
		}
		if(e.getSource() == add20Column) {
			column(-20);
			playing = false;
			stopPlay.setText("Play");
		}
		if(e.getSource() == remove20Column) {
			if(button[0].length-20>1) {
				column(20);
				playing = false;
				col = button[0].length-20;
				stopPlay.setText("Play");
			}
		}
		if(e.getSource() == random) {
			System.out.println("ran");
			JToggleButton bu = new JToggleButton();
			for(int r = 0; r<button.length; r++) {
				for(int c =0; c< button[0].length; c++) {
					System.out.println(r+", "+c);
					bu = button[r][c];
					int is = (int)(Math.random()*20);
					System.out.println("This is the random value: "+is);
					if(is %2 ==0) {
						System.out.println("true");
						button[r][c].setSelected(true);
					}else {
						button[r][c].setSelected(false);
					}
				}
			}
		}
		for (int y = 0; y<instrumentItems.length; y++) {

			if (e.getSource() == instrumentItems[y]) {

				String selectedInstrument = instrumentNames[y];
				try {

					for (int x=0;x<clipNames.length;x++) {
						URL url = this.getClass().getClassLoader().getResource(selectedInstrument+" - "+clipNames[x]+".wav");
						AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
						clip[x] = AudioSystem.getClip();
						clip[x].open(audioIn);
					}

				} catch (UnsupportedAudioFileException ee) {
					ee.printStackTrace();
				} catch (IOException ee) {
					ee.printStackTrace();
				} catch (LineUnavailableException ee) {
					ee.printStackTrace();
				}

				col = 0;
				playing = false;
        		stopPlay.setText("Play");
			}
		}
    }
	public void column(int num) {
		System.out.println("Used to have: "+button[0].length);
		JToggleButton[][] temp = new JToggleButton[button.length][button[0].length-num];
		for(int r = 0; r<temp.length; r++) {
			for(int c =0; c< temp[0].length-1; c++) {
				temp[r][c] = new JToggleButton();
				try {
					if(button[r][c].isSelected()) temp[r][c].setSelected(true);
				}catch(Exception E) {}
			}
		}
		buttonPane.remove(buttonPanel);
		buttonPanel = new JPanel();
		
		button = new JToggleButton[37][temp[0].length];
		buttonPanel.setLayout(new GridLayout(button.length, button[0].length));
		for(int r = 0; r<button.length; r++) {
			String name = clipNames[r].replaceAll("Sharp", "#");
			for(int c = 0; c<button[0].length; c++) {
				button[r][c] = new JToggleButton();
				button[r][c].setFont(font);
				button[r][c].setText(name);
				button[r][c].setPreferredSize(new Dimension(30,30));
				button[r][c].setMargin(new Insets(0,0,0,0));
				buttonPanel.add(button[r][c]);
			}
		}
		this.remove(buttonPane);
		buttonPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(buttonPane, BorderLayout.CENTER);
		for(int r = 0; r<temp.length; r++) {
			for(int c =0; c< temp[0].length; c++) {
				temp[r][c] = new JToggleButton();
				try {
					if(temp[r][c].isSelected()) button[r][c].setSelected(true);
				}catch(Exception E) {
					E.printStackTrace();
				}
			}
		}
		System.out.println("New Columns: "+temp[0].length);
		this.revalidate();
	}	
	public void saveSong() {
        FileFilter filter = new FileNameExtensionFilter("*.txt","txt");
        fileChooser.setFileFilter(filter);
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try{
                String st = file.getAbsolutePath();
                if(st.indexOf(".txt")>=0)
                    st = st.substring(0,st.length()-4);
                String output = "";
                String[] noteNames = {" ","c ","b ","a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c ","b ",
                        "a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c ","b ",
                        "a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c "};
                for(int r = 0;r<button.length+1;r++){
                    if(r==0){
                        output+= tempo;
                        for(int x = 0;x<button[0].length;x++)
                            output+=" ";
                    }
                    else{
                        output += noteNames[r];
                        for(int c = 0;c<button[0].length;c++){
                            if(button[r-1][c].isSelected())
                                output+="X";
                            else output+="-";
                        }
                    }
                    output+="\n";
                }

                BufferedWriter outputStream = new BufferedWriter(new FileWriter(st+".txt"));
                outputStream.write(output);
                outputStream.close();
            }catch (IOException e){

            }
        }
    }
	public void setNotes(Character[][] notes){
        buttonPane.remove(buttonPanel);


        buttonPanel = new JPanel();
        button = new JToggleButton[37][notes[0].length];
        buttonPanel.setLayout(new GridLayout(button.length, button[0].length, 2, 5));
        for(int r = 0;r<button.length;r++){
            String name = clipNames[r].replaceAll("Sharp","#");
            for(int c = 0;c<button[0].length-1;c++){
                button[r][c] = new JToggleButton();
                button[r][c].setFont(font);
                button[r][c].setText(name);
                button[r][c].setPreferredSize(new Dimension(30,30));
                button[r][c].setMargin(new Insets(0,0,0,0));
                buttonPanel.add(button[r][c]);
            }
        }
        this.remove(buttonPane);
        buttonPane = new JScrollPane(buttonPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(buttonPane,BorderLayout.CENTER);

        for(int r = 0;r<button.length;r++){
            for(int c = 0;c<button[0].length;c++){
                try{
                    if(notes[r][c] == 'X')
                        button[r][c].setSelected(true);
                    else button[r][c].setSelected(false);

                }catch(NullPointerException e){

                }catch(ArrayIndexOutOfBoundsException ee){

                }
            }
            this.revalidate();
        }


    }
    
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		tempo = tempoBar.getValue();
		tempoLabel.setText("Tempo: "+tempo);

	}


}
