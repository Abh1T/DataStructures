package day3data;
import java.util.*;
import java.io.*;
import java.text.*;

public class test {
	public test() {
		
		File fileName = new File("/Users/abhiram/Desktop/ffl.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			input.readLine();
			ArrayList<Player> players = new ArrayList<Player>();
			while((text = input.readLine())!= null) {						
			   String[] pieces = text.split(";");
			   Player playr = new Player(pieces[1], pieces[2], pieces[3], Double.parseDouble(pieces[4]), Double.parseDouble(pieces[5]), Double.parseDouble(pieces[6]), Double.parseDouble(pieces[7]), Double.parseDouble(pieces[8]), Double.parseDouble(pieces[9]), Double.parseDouble(pieces[0]));
			   players.add(playr);
		
			}
			
			sorter(players);
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
			
		
	}
	
	public static ArrayList<Player> sorter(ArrayList<Player> play){
		ArrayList<Player> list = play;
		Collections.sort(list);
		
		for(Player p : list) {
			System.out.println(p.ToString());
		}
		return list;
	}
	
	
	public class Player implements Comparable<Player>{
		 String name;
		 String pos;
		 String team;
		 double bye;
		 double overall;
		 double stddev;
		 double highrd;
		 double lowrd;
		 double times;
		 double pick;
		public Player(String nam, String po, String tea, double by, double overal, double stdde, double highr, double lowr, double time, double pic){
			 name = nam;
			 pos = po;
			 team = tea;
			 bye = by;
			 overall = overal;
			 stddev = stdde;
			 highrd = highr;
			 lowrd = lowr;
			 times = time;
			 pick = pic;
		}
		@Override
		public int compareTo(Player o) {
			if(getDifference() - o.getDifference() > 0 ) {
				return 1;
			}else if(getDifference() - o.getDifference() < 0) {
				return -1;
			}
			 if(getOverall() > o.getOverall()) {
				return 1;
			}else if(getOverall() < o.getOverall()) {
				return -1;
			}
			return 0;
		}
		public String ToString() {
			DecimalFormat decimal = new DecimalFormat("0.000");
			String formatted = String.format("%-30s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", name, pos, team, bye, overall, stddev, highrd, lowrd, times);
			return formatted;
			}
		public double getOverall() {
			return overall;
		}
		public double getDifference() {
			int lt = (int)lowrd;
			int lr = (int)(lowrd*100)%100;
			int ht = (int)highrd;
			int hr = (int)(highrd*100)%100;
			return (lt-ht)* 12 + (lr-hr); 
		}
	}
	
	
	
	public static void main(String [] args) {
		test app = new test();
	}
}
