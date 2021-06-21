import java.util.*;
import java.io.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class test {
	public test() {
		
		File fileName = new File("/Users/abhiram/Desktop/spiral.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int count = 1;
			while((text = input.readLine())!= null) {						
				int run = Integer.parseInt(text);
			   spirals(run);
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
			
		
	}
	
	public String[][] spirals(int i1 ){
		int i = i1;
		String [][] arr = new String [i][i];
		for(int x=0; x<i; x++) {
			for(int y = 0; y<i; y++) {
				arr[x][y] = "-";
			}			
		}
		int startRow = 0;
		int startColumn = 0;
		int endRow = i-1;
		int endColumn = i-1;
		boolean uhoh = false;
		if(i%4==2) {
			uhoh = true;
		}
		
		while(startRow<= endRow && startColumn <= endColumn) {
			for(int c = startColumn; c<=endColumn; c++) {
				arr[startRow][c] = "*";
			}
			
			if(startColumn%2==1)
				startColumn++;
			startRow++;
			
			for(int r = startRow; r <= endRow; r++) {
				arr[r][endColumn] = "*";
			}
			endColumn--;

			
			for(int c = endColumn; c>= startColumn; c--) {
				arr[endRow][c] = "*";
				
			}
			endRow--;

			startRow++;
			for(int r = endRow; r>= startRow; r--) {
				arr[r][startColumn] = "*";
			}
			endRow--;
			endColumn--;
			startColumn++;
			
			if(uhoh) {
				//System.out.println("Row: "+((i+1)/2)+" Col: "+(i/2));
				arr[(i+1)/2][(i/2)-1] = "-";
			}
			
			
		}
		for(int x=0; x<i; x++) {
			for(int y = 0; y<i; y++) {
				System.out.print(arr[x][y]);
			}
			System.out.println();
			
		}
		
		return arr;
		
	}
	
	public static void main(String [] args) {
		test app = new test();
	}
}
