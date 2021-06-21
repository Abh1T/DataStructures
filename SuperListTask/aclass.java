import java.util.*;
public class aclass
{

	public aclass()
	{
		//This is to test your code. If it works, you should get my answers!
		SuperList<Integer> list=new SuperList<Integer>();
		for(int i = 0; i<30;i++) 
		{
			list.add((int) (Math.random()*999) +1);
		}
		System.out.println("Arraylist: "+list.toString());
		System.out.println("Arraylist Size: "+list.size());
		
		SuperList<Integer> stack = new SuperList<Integer>();
		while(list.size() != 0) {
			stack.push(list.remove(0));
		}
		SuperList<Integer> queue=new SuperList<Integer>();
		String output="[";
		while(!stack.isEmpty())
		{
			int num=stack.pop();
			output+=num;
			if(stack.peekStack()!=null)
				output+=", ";
			queue.add(num);
		}
		output+="]";
		System.out.println("\nPrinted Stack: "+output);
		output="[";
		int index = 0;
		while(!queue.isEmpty()) 
		{
			for(int x = 0; x< 30; x++) {
				int rand = (int)(Math.random()*list.size());
				list.add(rand, queue.pop());
			}
		}
		
		output += "]";
		System.out.println("\nQueue: "+output);
		System.out.println("\nList 2.0: "+list);
		int sum = 0;
		for(int x = 0; x< list.size(); x++) 
		{
			sum += list.get(x);
		}
		System.out.println("\nTotal Sum: "+sum);
		int evensum = 0;
		int oddsum = 0;
		for(int x = 0; x< list.size(); x++) 
		{
			if(x%2==0)
			evensum += list.get(x);
			else oddsum += list.get(x);
		}
		System.out.println("Even Sum: "+evensum);
		System.out.println("Odd Sum: "+oddsum);
		
		int size = list.size();
		for(int x = 0; x< size; x++) 
		{
			if(list.get(x)%2==0)
				list.add(list.get(x));
		}
		System.out.println("\nList with added even values: "+list);
		
		
		
		size = list.size();
		for(int x = 0; x< size; x++) 
		{
			if(((list.get(x))%3)==0) 
			{
				list.remove(x);
				size = list.size();
				x--;
			}
		}
		System.out.println("\nList without values divisible by 3: "+list);
		list.add(3, 55555);
		System.out.println("\nList with 4th value of 55555: "+list);
		for(int x = 0; x< size; x++) 
		{
			int var = x;
			while(var>=0 && list.get(var)>list.get(var+1)) {
				list.add(var, list.remove(var+1));
				var--;
			}
		}
		System.out.println("\nSorted List: "+list);
		int val;
		if(list.size()%2==0) {
			val = (list.get(list.size()/2)+list.get((list.size()/2)-1))/2;
		}else {
			val = list.get(list.size()/2);
		}
		System.out.println("\nSize of the list is: "+list.size());
		System.out.println("\nThe median value of the list is: "+val);
		
		size = list.size()/2;
		System.out.print("\nValues before the median: ");
		for(int x = 0; x< size; x++)
		{
			System.out.print(list.get(x)+" ");
		}
		System.out.println();
		
		size = list.size()/2;
		System.out.print("Values after the median: ");
		if(list.size()%2!=0) {
		for(int x = size+1; x< list.size(); x++)
		{
			System.out.print(list.get(x)+" ");
		}
		System.out.println();
		}else {
			for(int x = size; x< list.size(); x++)
			{
				System.out.print(list.get(x)+" ");
			}
			System.out.println();
		}
		SuperList<String> stringList = new SuperList<String>();
		String sentence = "According to all known laws of aviation, there is no way a bee should be able to fly.";
		String [] words = sentence.replaceAll("[ ,.!?]", " ").replace("  ", " ").split(" ");
		for(String s : words) {
			if(!s.equals("\\s")) {
				stringList.add(s);
			}
		}
		System.out.println("\nString List: "+stringList);
		for(int x = 0; x< stringList.size(); x++) 
		{
			if(stringList.get(x).length() <= 3) {
				stringList.remove(x);
				x--;
			}
		}
		System.out.println("\nString List without <=3 letter words: "+stringList);
		for(int x = 0; x< stringList.size()-1; x++) 
		{
			int var = x;
			while(var>=0 &&  (stringList.get(var).compareTo(stringList.get(var+1))> 0)) {
				stringList.add(var, stringList.remove(var+1));
				var--;
			}
		}
		System.out.println("\nSorted String List: "+stringList);
	
		int numWords = 0;
		int wordLength = 0;
		for(int x = 0; x< stringList.size(); x++) {
			numWords++;
			wordLength+= stringList.get(x).length();
		}
		double temp = (double)wordLength/numWords;
		double fl =  Math.round( temp* 100.0)/100.0;
		System.out.println("\nAverage Word Length: "+fl);
	}
	public static void main(String[] args)
	{
		aclass app=new aclass();
	}

}
