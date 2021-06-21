import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class test {
	public test() {
		
		File fileName = new File("/Users/abhiram/Desktop/guitar.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			String[][] print = null;
			String[] notes = {"G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A","G#", "G", "F#", "F", "E" };
			int[][] hold = {{29,24,19,14,10,5},{28,23,18,13,9,4},{27,22,17,12,8,3},{26,21,16,11,7,2},{25,20,15,10,6,1}};
			int row = 0;
			
			while((text = input.readLine())!= null) {						
				String[] str = text.split(",");
				if(print == null ) 
				{
					print = new String[30][1+str.length];
					
					
					for(int x = 1; x<= notes.length; x++) {
						print[x][0] = notes[x-1];
					}
					print[0][0] = "Measure: ";
					for(int x = 1; x<= str.length; x++) {
						print[0][x] = x+"";
					}
					for(int m = 0; m < str.length; m++) {
						for(int c = 0; c< 6; c++){
							if(str[m].charAt(c) == '*' || str[m].charAt(c) == 'o') 
							{
								print[hold[row][c]][m+1] = "O";
							}
						}
					}
					row++;
				}
				
			}
			
			for(int r = 0; r< print.length; r++) {
				for(int c = 0; c<print[0].length; c++) {
					if(print[r][c] == null) {
						System.out.print("\t");
					}else {
						System.out.print(print[r][c]+"\t");
					}
					
				}
				System.out.println();
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
			
		
	}
	
	public static void main(String [] args) {
		test app = new test();
	}
}
