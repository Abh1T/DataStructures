
Time Travel
Stephen Dentler
•
Sep 18, 2020
10 points
Read through the document and solve this task.

I picked this one because I want you to take a look at the Date or Calendar class. If you use one of those, this is a much, much, much easier problem.

Time Travel
Google Docs

TravelFile.txt
Text
Class comments
Your work
Turned in

test.java
Java
Private comments
Time Travel 
package day4time;
import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class test {
	public test() {
		
		File fileName = new File("/Users/abhiram/Desktop/travel.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			input.readLine();
			int count = 1;
			while((text = input.readLine())!= null) {						
			   String[] pieces = text.split(" ");
			   //System.out.println(dater(pieces, count));
			   count++;
			}
			String[] array = {"3650", "0", "0"};
			System.out.println(dater(array, 1));
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
			
		
	}
	

	public String dater(String [] o, int i ){
		String[] pieces = o;
		System.out.println("Trip "+i+": ");
		int days = Integer.parseInt(pieces[0]);
		int hours = Integer.parseInt(pieces[1]);
		int minutes = Integer.parseInt(pieces[2]);
		Date date = new Date();
		SimpleDateFormat fo =new SimpleDateFormat("hh:mm a ", Locale.ENGLISH);
		SimpleDateFormat f = new SimpleDateFormat(" dd/MMM/yyyy", Locale.ENGLISH);
		Calendar cal = Calendar.getInstance();
		Date temp = cal.getTime();
		String s = fo.format(temp);
		System.out.println("\t Departure Date and Time is: "+s+"on"+f.format(cal.getTime()));
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
		cal.add(Calendar.MINUTE, minutes);
		int x =0;
		int d = days;
		while((d/365)>=4) {
			x++;
			d-=1460;
			if(d<0)
				break;
		}
		cal.add(Calendar.DATE, days+x);
		return "\t Arrival Date and Time is: "+fo.format(cal.getTime())+"on"+f.format(cal.getTime());
	}
	
	public static void main(String [] args) {
		test app = new test();
	}
}

