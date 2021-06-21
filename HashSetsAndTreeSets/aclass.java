import java.util.*;

public class aclass {
	public aclass() {
		ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
		int numsets = (int)((Math.random()*11)+2);
		for(int x = 0; x< numsets; x++) {
		HashSet<Integer> hash = new HashSet<Integer>();
			while(hash.size()<10) 
			{
				hash.add((int)((Math.random()*30)+1));
				
			}
			list.add(hash);
		}
		System.out.println("Task 1\n");
		int counter = 1;
		for(HashSet<Integer> set : list) {
			String print = "Set "+counter+": ";
			for(Integer i: set) {
				print+=i+" ";
			}
			System.out.println(print);
			counter++;
		}
		
		
		HashSet<Integer> h = new HashSet<Integer>();
		HashSet<Integer> hi = new HashSet<Integer>();
		h.add(1);
		h.add(2);
		h.add(3);
		h.add(4);
		h.add(5);
		hi.add(2);
		hi.add(4);
		hi.add(5);
		hi.add(6);
		hi.add(7);
		System.out.print("\nIntersections: ");
		System.out.println(intersections(h,hi)+"\n");
		
		HashSet<Integer> h1 = new HashSet<Integer>();
		HashSet<Integer> hi1 = new HashSet<Integer>();
		h1.add(2);
		h1.add(3);
		h1.add(4);
		h1.add(5);
		h1.add(6);
		hi1.add(3);
		hi1.add(4);
		hi1.add(5);
		hi1.add(6);
		hi1.add(7);
		System.out.print("Unions: ");
		System.out.println(unions(h1,hi1)+"\n");
		
		HashSet<Integer> h2 = new HashSet<Integer>();
		HashSet<Integer> hi2 = new HashSet<Integer>();
		h2.add(8);
		h2.add(4);
		h2.add(11);
		h2.add(13);
		h2.add(6);
		hi2.add(4);
		hi2.add(11);
		hi2.add(5);
		hi2.add(6);
		hi2.add(7);
		System.out.print("Even Intersections: ");
		System.out.println(evenintersections(h2,hi2)+"\n");
		
		HashSet<Integer> h3 = new HashSet<Integer>();
		HashSet<Integer> hi3 = new HashSet<Integer>();
		h3.add(2);
		h3.add(4);
		h3.add(11);
		h3.add(17);
		h3.add(6);
		hi3.add(4);
		hi3.add(17);
		hi3.add(2);
		hi3.add(6);
		hi3.add(7);
		System.out.print("Even Unions: ");
		System.out.println(evenunions(h3,hi3)+"\n");
		
		
	}
	
	
	
	public static void main(String [] args) {
		aclass app = new aclass();

	}
	
	public Set<Integer> intersections(HashSet<Integer> h1, HashSet<Integer> hi1 ){
		HashSet<Integer> h = h1;
		HashSet<Integer> hi = hi1;
		Set<Integer> set = new HashSet<Integer>();
		for(int i : h) {
			if(hi.contains(i))
				set.add(i);
		}
		return set;
		
	}
	public Set<Integer> unions(HashSet<Integer> h, HashSet<Integer> hi1 ){
		HashSet<Integer> hi = hi1;
		Set<Integer> com = h;
		for(int x : hi)
			com.add(x);
		return com;
		
	}
	public Set<Integer> evenintersections(HashSet<Integer> h1, HashSet<Integer> hi1 ){
		HashSet<Integer> h = h1;
		HashSet<Integer> hi = hi1;
		Set<Integer> set = new HashSet<Integer>();
		for(int i : h) {
			if(i%2==0)
			if(hi.contains(i))
				set.add(i);
		}
		return set;
		
	}
	public Set<Integer> evenunions(HashSet<Integer> h1, HashSet<Integer> hi1 ){
		HashSet<Integer> h = h1;
		HashSet<Integer> hi = hi1;
		Set<Integer> com = new HashSet<Integer>();
		for(int x : hi) {
			if(x%2==0) {
			com.add(x);
			}
		}
		for(int x : h) {
			if(x%2==0) {
				com.add(x);
			}
		}
		return com;
		
	}

}
