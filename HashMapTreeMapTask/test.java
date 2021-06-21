package src;
import java.util.*;
import java.io.*;
import java.math.*;
public class test {

	public test() 
	{
		File fileName = new File("/Users/abhiram/Desktop/BowlingData.txt");
		TreeMap<Integer, PriorityQueue<Bowler>> bowlerMap = new TreeMap<Integer, PriorityQueue<Bowler>>();

		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while((text=input.readLine()) != null) {
				String[] pieces = text.split(" ");
				int score = Integer.parseInt(pieces[2]);
				if (!bowlerMap.containsKey(score))
					bowlerMap.put(score, new PriorityQueue<Bowler>());
				Bowler b = new Bowler(pieces[0], pieces[1], score);
				bowlerMap.get(score).add(b);
			}

		}catch(IOException e) {e.printStackTrace();}
		System.out.println("MAP");
		System.out.println(bowlerMap);

		
		
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++KEYS+++++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println();
		for (Integer key:bowlerMap.keySet())
			System.out.println(key);
		
		
		
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++ENTRY SET++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println();
		Iterator entrySet = bowlerMap.entrySet().iterator();
		while (entrySet.hasNext())
			System.out.println(entrySet.next());

		
		
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++VALUES++++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println();
		for (Integer key:bowlerMap.keySet()) {
			PriorityQueue<Bowler> tempQueue = bowlerMap.get(key);
			String output = "[";
			while(!tempQueue.isEmpty()) {
				Bowler b = (Bowler)tempQueue.poll();
				output += b.toString();
				if (tempQueue.peek() != null)
					output += ", ";
			}
			output += "]";
			System.out.println(output);
		}
	}

	
	
	public class Bowler implements Comparable<Bowler> {
		String first;
		String last;
		int score;
		public Bowler(String first, String last, int sc) {
			this.first = first;
			this.last = last;
			this.score = sc;
		}
		public String getFirst() {return first;}
		public String getLast() {return last;}
		public int compareTo(Bowler other) {
			if (last.compareTo(other.getLast()) > 0)
				return 1;
			if (last.compareTo(other.getLast()) < 0)
				return -1;
			if (first.compareTo(other.getFirst()) > 0)
				return 1;
			if (last.compareTo(other.getFirst()) < 0)
				return -1;
			return 0;
		}
		public String toString() {
			return first + " " + last;
		}
	}


	
	public static void main(String [] args) {
		test app = new test();
	}
	
}
