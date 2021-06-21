import java.io.*;
import java.util.*;

public class aclass {
	public aclass() {
		
File fileName = new File("/Users/abhiram/Desktop/paragraph.txt");
		Queue <Word> queue = new LinkedList <Word>();
		PriorityQueue<Word> pqueue = new PriorityQueue<Word>();
		Queue <Word> queue2 = new LinkedList <Word>();
		PriorityQueue<Word> pqueue2 = new PriorityQueue<Word>();
		
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while((text = input.readLine())!= null) {
			   text = text.replace("? ", " ");
			   text = text.replace("! ", " ");
			   text = text.replace(", ", " ");
			   text = text.replace(". ", " ");
			   text = text.replace("? ", " ");
			   text = text.replace(" ,", " ");
			   text = text.replace("; ", " ");
			   text = text.replace(".", " ");
			   text = text.replace("\n", "");
			   String[] pieces = text.split(" ");
			   
			   try {
				   for(int x = 0; x< pieces.length; x++) {
					   if(pieces[x]!= null && pieces[x]!= "\s"&& pieces[x]!= "") {
						   queue.add(new Word(pieces[x], true));
						   pqueue.add(new Word(pieces[x], true));
						   queue2.add(new Word(pieces[x], false));
						   pqueue2.add(new Word(pieces[x], false));
					   }
				   }
			   }catch(NumberFormatException ex) {
				   
				   
			   }
			}
			String formater= "";
			formater = String.format("%-20s %-20s", "Queue", "Priority Queue");
			System.out.println(formater);
			for(int x = 0; x<queue.size(); x++) {
				while(queue.peek() != null && pqueue.peek() != null) {
				formater = String.format("%-20s %-20s", queue.poll().getString(), pqueue.poll().getString());
				System.out.println(formater);
				}
			}
			
			System.out.println("\n\n");
			formater = String.format("%-20s %-20s", "Queue", "Descending Priority Queue");
			System.out.println(formater);
			for(int x = 0; x<queue2.size(); x++) {
				while(queue2.peek() != null && pqueue2.peek() != null) {
				formater = String.format("%-20s %-20s", queue2.poll().getString(), pqueue2.poll().getString());
				System.out.println(formater);
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
		
	}
	
	
	
	public static void main(String [] args) {
		aclass app = new aclass();

	}
	
	
	

}
