import java.io.*;
import java.util.*;

public class aclass {
	public aclass() {
		
File fileName = new File("/Users/abhiram/Desktop/CarData.txt");
		Queue<Car> queue = new LinkedList<Car>();
		Stack<Car> stack = new Stack<Car>();
		PriorityQueue<Car> pqueue = new PriorityQueue<Car>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			input.readLine();
			while((text = input.readLine())!= null) {
			   String[] pieces = text.split("\\s+");
				   queue.add(new Car(Integer.parseInt(pieces[0]), Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]),Integer.parseInt(pieces[3]), Integer.parseInt(pieces[4]), Integer.parseInt(pieces[5]), Integer.parseInt(pieces[6]), Integer.parseInt(pieces[7])));
			}
			System.out.println("Queue");
			String formatted = String.format("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s","Car ID","Miles Per Gallon","Engine Size (cube in)",	"Horse powers","Weight in pounds","Acceleration (0 to 60)","Country of Origin","Number of Cylinders");
			System.out.println(formatted);
				while(!queue.isEmpty())
				{
					Car c = queue.poll();
					String queueformatted = String.format("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s",c.getCid(),c.getM(),c.getS(),c.getH(),c.getW(),c.getA(),c.getCount(),c.getCy());
					System.out.println(queueformatted);
					stack.push(c);
				}	
			
			System.out.println("\nStack");	
			System.out.println(formatted);
		
			while(!stack.empty()) 
				{
					Car c = stack.pop();
					String stackformat = String.format("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s",c.getCid(),c.getM(),c.getS(),c.getH(),c.getW(),c.getA(),c.getCount(),c.getCy());
					System.out.println(stackformat);
					pqueue.add(c);
				}
		
			
			System.out.println("\nPriority Queue");	
			System.out.println(formatted);		
	
		while(!pqueue.isEmpty())
		{
			Car c = pqueue.poll();
			String pqueueformat = String.format("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s",c.getCid(),c.getM(),c.getS(),c.getH(),c.getW(),c.getA(),c.getCount(),c.getCy());
			System.out.println(pqueueformat);
		}
	
		}catch(Exception e ) {
			e.printStackTrace();
		}
	
	}
	public static void main(String [] args) {
		aclass app = new aclass();
	}
	
	class Car implements Comparable<Car> {
		int accel;
		int mpg;
		int hp;
		int size;
		int weight;
		int cyl;
		int cid;
		int useless;
		public Car(int c, int m, int s, int h, int w, int a, int country, int cy) {
			this.accel = a;
			this.mpg = m;
			this.hp = h;
			this.size = s;
			this.weight = w;
			this.cyl = cy;
			this.cid = c;
			this.useless = country;
		}
		public int getA() {
			return accel;
		}
		public int getM() {
			return mpg;
		}
		public int getH() {
			return hp;
		}
		public int getS() {
			return size;
		}
		public int getW() {
			return weight;
		}
		public int getCy() {
			return cyl;
		}
		public int getCid() {
			return cid;
		}
		public int getCount() {
			return useless;
		}
		@Override
		public int compareTo(Car o) {
			
			
			Integer comparer = getA();
			Integer comparee = o.getA();
			if((comparer.compareTo(comparee))!= 0) {
				return comparer.compareTo(comparee);
			}
			
			comparer = getM();
			comparee = o.getM();
			if(comparer.compareTo(comparee)!= 0) {
				return comparer.compareTo(comparee);
			}
			comparer = getH();
			comparee = o.getH();
			if(comparer.compareTo(comparee)!= 0) {
				return comparer.compareTo(comparee);
			}
			comparer = getS();
			comparee = o.getS();
			if(comparer.compareTo(comparee)!= 0) {
				return comparer.compareTo(comparee);
			}
			comparer = getW();
			comparee = o.getW();
			if(comparer.compareTo(comparee)!= 0) {
				return comparer.compareTo(comparee);
			}
			comparer = getCy();
			comparee = o.getCy();
			if(comparer.compareTo(comparee)!= 0) {
				return comparer.compareTo(comparee);
			}
			comparer = getCid();
			comparee = o.getCid();
			if(comparer.compareTo(comparee)!= 0) {
				return comparer.compareTo(comparee);
			}
			
			return 0;
		}
		
		
	}
}
