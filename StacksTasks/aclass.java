package asyncstacks;
import java.util.Stack;
import java.io.*;
import java.math.*;
import java.util.*;
public class aclass {
	public aclass() {
		
		Stack<Integer> decimalToBinary = new Stack<Integer>();
		for(int x = 0; x< 5; x++)
		decimalToBinary.push(1);
		decimalToBinary.push(0);
		decimalToBinary.push(1);
		notfirstTask(decimalToBinary);
		decimalToBinary.clear();
		
		firstTask(257);
		
		
		//firstTask(decimalToBinary);
		secondTask("Second");	
		thirdTask();
	}
	
	public static void main(String [] args) {
		aclass app = new aclass();
	}
	public void notfirstTask(Stack<Integer> i) {
		Stack<Integer> stackConversion = i;
		int factor = (int)Math.pow(2, stackConversion.size());
		int sum = 0;
		
		while(!stackConversion.empty()) {
			int val = (stackConversion.pop()%2);
			sum+=factor*val;
			factor/=2;
		}
		System.out.println("The Binary Value is: "+sum);
		
	}
	public void firstTask(int i) {
		System.out.println("\nFirst Task");
		int x = i;
		Stack<Integer> binaryStack = new Stack<Integer>();
		while(x>0) {
			binaryStack.add(x%2);
			x/=2;
			
		}
		System.out.print("The number "+i+" in binary is: ");
		while(!binaryStack.empty()) {
			System.out.print(binaryStack.pop());
		}
		System.out.println();
	}
	
	public void secondTask(String string) {
		Stack<Character> reversal = new Stack<Character>();
		String s = string;
		for(int x = 0; x< s.length(); x++) {
			reversal.push(Character.valueOf(s.charAt(x)));
		}
		System.out.println("\nSecond Task");
		System.out.print("The String "+s+" reversed is: ");
		while(!reversal.empty()) 
		{
			System.out.print(reversal.pop());
		}
		System.out.println();
	}
	
	public void thirdTask() {
		System.out.println("\nThird Task");
		
		File fileName = new File("/Users/abhiram/Desktop/StarWarsCharacters.csv");
		ArrayList<Characters> chars = new ArrayList<Characters>();
		int x = 0;
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int sum = 0;
			input.readLine();
			while((text = input.readLine())!= null) {
				String[] pieces = text.split(",");
				if(pieces.length == 9)
				chars.add(new Characters(pieces[0], pieces[6], pieces[7], pieces[8], pieces[5]));
				else chars.add(new Characters(pieces[0], pieces[7], pieces[8], pieces[9], pieces[6]));
				x++;
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
		
		Stack<Characters> age = new Stack<Characters>();
		Stack<Characters> male = new Stack<Characters>();
		Stack<Characters> female = new Stack<Characters>();
		Stack<Characters> droids = new Stack<Characters>();
		for(Characters c : chars) {
			//System.out.println(c.getBirth()+" "+c.getGender());
			if(!c.getBirth().equals("NA") && !c.getBirth().equals("none")) {
				age.push(c);
			}
			if(c.getSpecies().equals("Droid")) {
				droids.push(c);
			}else if(c.getGender().equals("male")) {
				male.push(c);
			}else if(c.getGender().equals("female")) {
				female.push(c);
			}
		}
		System.out.println("Male Characters");
		operation(male);
		System.out.println("Female Characters");
		operation(female);
		System.out.println("Droids");
		operation(droids);
		System.out.println("Ages");
		ageoperation(age);
					
}
	
	public void operation(Stack<Characters> crer) {
		Stack<Characters> lo = crer;
		String primary = String.format("%-30s %-30s", "Name", "Homeworld");
		System.out.println(primary);
		while(!lo.empty()) {
			Characters c = lo.pop();
			if(!c.getHomeworld().equals("NA"))
			primary = String.format("%-30s %-30s", c.getName(), c.getHomeworld());
			else primary = String.format("%-30s %-30s", c.getName(), "Unknown");
			System.out.println(primary);
		}
		System.out.println();
	}
	
	public void ageoperation(Stack<Characters> crer) {
		Stack<Characters> lo = crer;
		String primary = String.format("%-30s %-30s %-30s", "Name", "Homeworld", "Birth Year (BBY)");
		System.out.println(primary);
		while(!lo.empty()) {
			Characters c = lo.pop();
			String s = c.getBirth();
			s = s.substring(0, s.indexOf('B'));
			double d = Double.parseDouble(s)*1.0;
			if(!c.getHomeworld().equals("NA"))
				primary = String.format("%-30s %-30s %-30s", c.getName(), c.getHomeworld(), d);
				else primary = String.format("%-30s %-30s %-30s", c.getName(), "Unknown", d);
			System.out.println(primary);
		}	
	}

	
}
 class Characters {
	String name;
	String gender;
	String homeworld;
	String species;
	String birth;
	public Characters(String n, String g, String h, String s, String b) {
		this.name = n;
		this.gender = g;
		this.homeworld = h;
		this.species = s;
		this.birth = b;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getHomeworld() {
		return homeworld;
	}
	public String getSpecies() {
		return species;
	}
	public String getBirth() {
		return birth;
	}
}
