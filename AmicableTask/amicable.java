package amicablenumbers;
import java.io.*;
import java.util.ArrayList;

public class amicable {
	public amicable() {
		int[] pieceinints = null;
		File fileName = new File("/Users/abhiram/Desktop/amicable.txt");
		try
		{
			BufferedReader input=new BufferedReader(new FileReader(fileName));
			String text;
			while((text=input.readLine())!=null)
			{
					isAmicable(text);
			
			}

		}catch(IOException e)
		{
			System.out.println("File not found.");
		}
			
	}
	
	public static String isAmicable(String ami) {
		String [] temp = ami.split(" ");
		int[] thenumbers = new int[temp.length];
		int count = 0;
		ArrayList<Integer> factorsone = new ArrayList<Integer>();
		ArrayList<Integer> factorstwo = new ArrayList<Integer>();
		for (String i : temp) {
			thenumbers[count] = Integer.parseInt(i);
			count++;
		}
		
		for (int i : thenumbers) {
		}
		int x = 0;
		do{
			int num = thenumbers[x];
			int sum = 0;
			 for(int i = 1; i < num; i++) {
		            if (num % i == 0) {
		                if(x%2==0) {
		                	factorsone.add(i);	
		                }else {
		                	factorstwo.add(i);
		                }
		            	
		            }
		        }
			
			x++;
		}while(x!=2);
		
		String ghost1 = "\tFactors of "+thenumbers[0]+" are ";
		String ghost2 = "\tFactors of "+thenumbers[1]+" are ";
		boolean b1 = false;
		boolean b2 = false;
		int y = 0;
		do {
			int ext = 0;
			int sum = 0;
			if(y%2==0) {
				for(int i :factorsone) {
				sum+= i;
					if(factorsone.size()==1) {
					ghost1 = 	"\t The Factor of "+thenumbers[0]+" is "+i+". ";
					}else if(factorsone.size()==2) {
						if(ext==(factorsone.size()-1)) {
							ghost1 += (i+". ");
						}else {
							ghost1 += (i + " and ");
						}
					}else if(ext==(factorsone.size()-2)) {
						ghost1+= (i+", and ");
					}else if((ext<factorsone.size()-2)) {
						ghost1+= (i+", ");
					}else {
						ghost1+=(i+". ");
					}
					ext++;
				}
				if(sum == thenumbers[1]) {
					b1 = true;
					
				}
				ghost1+= ("Sum is "+sum+".");
				
			}else {
				for(int i :factorstwo) {
					sum+= i;
					if(factorstwo.size()==1) {
						ghost2 = 	"\t The Factor of "+thenumbers[1]+" is "+i+". ";
						}else if(factorstwo.size()==2) {
							if(ext==(factorstwo.size()-1)) {
								ghost2 += (i+". ");
							}else {
								ghost2 += (i + " and ");
							}
						}else if(ext==(factorstwo.size()-2)) {
						ghost2+= (i+", and ");
					}else if((ext<factorstwo.size()-2)) {
						ghost2+= (i+", ");
					}else {
						ghost2+=(i+". ");
					}
					ext++;
					}
					if(sum == thenumbers[0]) {
						b2 = true;
					}
					ghost2+= ("Sum is "+sum+".");
			}
			y++;
		}while(y!=2);
		
		
		if(b1 && b2) {
			System.out.println("The numbers "+thenumbers[0]+ " and "+ thenumbers[1] + " are amicable");
		}else {
			System.out.println("The numbers "+thenumbers[0]+ " and "+ thenumbers[1] + " are not amicable");
		}

		System.out.println(ghost1);
		System.out.println(ghost2);
		System.out.println();
		
		return null;
	}
	
	public static void main(String [] args) {
		amicable app = new amicable();
	}
}
