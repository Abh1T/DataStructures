import java.io.*;
import java.util.*;

public class aclass2 {
	public aclass2() {
		
		File fileName = new File("/Users/abhiram/Desktop/PassengerInfo.txt");
		Queue<Passenger> passengers = new LinkedList<Passenger>();
		PriorityQueue<Passenger> prioritypass = new PriorityQueue<Passenger>();
		PriorityQueue<Passenger> toBeSorted = new PriorityQueue<Passenger>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			ArrayList<String> str = new ArrayList<>();
			//Passenger p = 
			while((text = input.readLine())!= null) {
			   str.add(text);
			   str.add(input.readLine());
			   str.add(input.readLine());
			   String[] pieces = str.get(0).split(" ");
			   Passenger p = new Passenger(pieces[0], pieces[1], str.get(1), str.get(2));
			   passengers.add(p);
			   prioritypass.add(p);
			   str.clear();  
			}
			
			
			System.out.println("\n\nQueue of Passengers\n");
            for(Passenger p: passengers) {
            	String[] calc = p.etdCalc().split(":");
                System.out.println(p.getLastName()+", "+p.getFirstName()+" - "+p.flightCity()+" - "+p.flightTime()+" - "+p.etdCalc());
                if((Integer.parseInt(calc[1]) < 60) & Integer.parseInt(calc[0]) == 0) {
                    toBeSorted.add(p);
                }
            }

           System.out.println("\n\nPriority Queue of Passengers\n");
            while(!prioritypass.isEmpty()){
                Passenger p = prioritypass.poll();
                System.out.println(p.getLastName()+", "+p.getFirstName()+" - "+p.flightCity()+" - "+p.flightTime()+" - "+p.etdCalc());
            }

            System.out.println("\n\nMust be moved to the head\n");
            while(!toBeSorted.isEmpty()){
                Passenger p = toBeSorted.poll();
                System.out.println(p.getLastName()+", "+p.getFirstName()+" - "+p.flightCity()+" - "+p.flightTime()+" - "+p.etdCalc());
            }
            
        }catch (IOException e){
           System.out.println("File not Found");
        }
		
	}
	
	
	
	public static void main(String [] args) {
		aclass2 app = new aclass2();

	}
	
	
	

}
