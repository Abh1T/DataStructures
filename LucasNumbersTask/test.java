package src;
import java.util.*;
import java.io.*;
import java.math.*;
public class test {

	public test() {
	File file = new File("/Users/abhiram/Desktop/lucas.txt");	
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String text;
			while((text = reader.readLine())!= null) {
			   BigInteger i = new BigInteger(text);
			   System.out.println(fib(i));
			   
			}
			
		}catch(IOException e) {
		
		}
	}
	
	public static BigInteger fib(BigInteger i) {
		BigInteger first = BigInteger.valueOf(2);
		BigInteger second = BigInteger.ONE;
		BigInteger sum = (first.add(second));
		if(i.intValue() == 1)
			return second;
		if(i.intValue() == 2)
			return sum;
		
		for(BigInteger x = BigInteger.valueOf(0); x.intValue() < i.intValue()+1; x = x.add(BigInteger.ONE)) {
			if(x.equals(BigInteger.ZERO)) {
				 first = BigInteger.valueOf(2);
				 second = BigInteger.ONE;
				 sum = (first.add(second));
			}else if(x.equals(BigInteger.ONE)) {
				first = BigInteger.valueOf(2);
				 second = BigInteger.ONE;
				 sum = (first.add(second));
			}else{
				sum = first.add(second);
				first = second;
				second = sum;
				
			}
			
			
		}
		return sum;
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String [] args) {
		test app = new test();
	}
	
	
}
