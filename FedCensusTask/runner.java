import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class runner {
	ArrayList<Citizen> listofCitizens = new ArrayList<Citizen>();
	public runner() {
		File fileName = new File("/Users/abhiram/Desktop/FedCensus1930_CambriaCountyPA.txt");
		try
		{
			BufferedReader input=new BufferedReader(new FileReader(fileName));
			String text;
			
			while((text=input.readLine())!=null)
			{
				if(text.length()>2 && text.substring(0,2).equals("17")) {	
					String first = text.substring(71,88).trim();
					String last = text.substring(55,71).trim();
					String streetName = text.substring(20,36).trim();
					String streetNumber = text.substring(36,45).trim();
					String relation = text.substring(88,108).trim();
					String rentOwn = text.substring(108, 113).trim();
					String valueOfProp = text.substring(113,121).trim();
					String gender = text.substring(133,134).trim();
					String age = text.substring(143,151).trim();
					String status = text.substring(151,156).trim();
					String ageF = text.substring(156,162).trim();
					String attend = text.substring(162,167).trim();
					String canRead = text.substring(167,173).trim();
					String birth = text.substring(173,190).trim();
					String fbirth = text.substring(190,207).trim();
					String mbirth = text.substring(207, 224).trim();
					String motherTongue = text.substring(224, 235).trim();			
					String year = text.substring(235,241).trim();
					String occ = text.substring(252,270).trim();
					String industry = text.substring(274,303).trim();
					String remarks = text.substring(342).trim();
					listofCitizens.add( new Citizen(first,  last,  streetName, streetNumber,  relation, rentOwn, valueOfProp, gender, age, status, ageF, attend, canRead, birth, fbirth, mbirth, motherTongue, year, occ, industry, remarks));
					
					
				}
			}
		}catch(IOException e)
		{
			System.out.println("File not found.");
		}
		for(int x = listofCitizens.size()-1; x>=0; x--) {
			if(listofCitizens.get(x).getFirstName().equals(".") && listofCitizens.get(x).getLastName().equals(".")) {
				listofCitizens.remove(x);
			}
		}
		Collections.sort(listofCitizens);
		for(Citizen c: listofCitizens) {
			System.out.println(c);
		}
		
		aLotofSpace();
		System.out.println("Street and who lives on it");
		streetCitizen();
		aLotofSpace();
		System.out.println("Birthplace and Ages");
		birthPlaceAge();
		aLotofSpace();
		System.out.println("Mother Tongue Spoken and Citizen");
		motherTongueName();
		aLotofSpace();
		System.out.println("Occupation and Father Birthplace");
		occupationFatherBirthplace();
		aLotofSpace();
		System.out.println("Gender and Transcriber Remarks");
		genderRemarks();
		aLotofSpace();
		System.out.println("Rent or Own and Property Value");
		rentOwnValue();
		aLotofSpace();
		System.out.println("Custom: Father Birthplace and Property Value");
		fatherBirthplacePropValue();	
	}
	public void aLotofSpace() {
		System.out.println("\n\n\n\n\n\n\n\n");
	}
	public void streetCitizen() {
		TreeMap<String, TreeSet<Citizen>> streetCitizenMap = new TreeMap<>();
		for(Citizen c : listofCitizens) {
			if(!streetCitizenMap.containsKey(c.getStreet())) streetCitizenMap.put(c.getStreet(), new TreeSet<Citizen>());
			streetCitizenMap.get(c.getStreet()).add(c);
		}
		Iterator<String> it = streetCitizenMap.keySet().iterator();
		while(it.hasNext()) {
			String street = it.next();
			System.out.println(street+":");
			TreeSet<Citizen> temp = streetCitizenMap.get(street);
			for(Citizen c : temp) {
				System.out.println("\t"+c);
			}
		}
	}
	public void birthPlaceAge() 
	{
		TreeMap<String, PriorityQueue<Double>> birthplaceMap = new TreeMap<>();
		for(Citizen c : listofCitizens) {
			if(!birthplaceMap.containsKey(c.getBirthplace()))birthplaceMap.put(c.getBirthplace(), new PriorityQueue<Double>());
			birthplaceMap.get(c.getBirthplace()).add(c.getAge());
		}
		Iterator<String> iter = birthplaceMap.keySet().iterator();
		while(iter.hasNext()) {
			String birthplace = iter.next();
			System.out.println(birthplace+":");
			PriorityQueue<Double> temp = birthplaceMap.get(birthplace);
			System.out.print("[");
			while(!temp.isEmpty()) {
				double age = temp.poll();
				if(age>=0) {
					if(temp.peek()!= null)
						System.out.print(age+", ");
					else System.out.print(age);
				}
			}
			System.out.println("]");
		}
	}
	public void motherTongueName() {
		TreeMap<String, ArrayList<String>> motherTongueNameMap = new TreeMap<>();
		for(Citizen c : listofCitizens) {
			if(!motherTongueNameMap.containsKey(c.getMotherTongue())) motherTongueNameMap.put(c.getMotherTongue(), new ArrayList<String>());
			motherTongueNameMap.get(c.getMotherTongue()).add(c.getFirstName()+" "+c.getLastName());
		}
		Iterator<String> iter = motherTongueNameMap.keySet().iterator();
		while(iter.hasNext()) {
			String motherTongue = iter.next();
			System.out.println(motherTongue+":");
			ArrayList<String> temp = motherTongueNameMap.get(motherTongue);
			for(String c: temp) {
				System.out.println("\t"+c);
			}
		}
	}
	public void occupationFatherBirthplace() {
		TreeMap<String, HashSet<String>> occupationFatherBirthplace = new TreeMap<>();
		for(Citizen c: listofCitizens) {
			if(!occupationFatherBirthplace.containsKey(c.getOcc())) occupationFatherBirthplace.put(c.getOcc(), new HashSet<String>());
			occupationFatherBirthplace.get(c.getOcc()).add(c.getFatherBirth());
		}
		Iterator<String> iter = occupationFatherBirthplace.keySet().iterator();
		while(iter.hasNext()) {
			String occupation = iter.next();
			System.out.println(occupation+":");
			HashSet<String> temp = occupationFatherBirthplace.get(occupation);
			Iterator<String> hashIter = temp.iterator();
			while(hashIter.hasNext()) System.out.println("\t"+hashIter.next());
			
		}
	}
	public void genderRemarks() {
		TreeMap<String, HashSet<String>> genderRemarksMap = new TreeMap<>();
		for(Citizen c : listofCitizens) {
			if(!genderRemarksMap.containsKey(c.getGender())) genderRemarksMap.put(c.getGender(), new HashSet<String>());
			genderRemarksMap.get(c.getGender()).add(c.getTrans());
		}
		Iterator<String> iter = genderRemarksMap.keySet().iterator();
		while(iter.hasNext()) 
		{
			String gender = iter.next();
			System.out.println(gender+":");
			HashSet<String> temp = genderRemarksMap.get(gender);
			for(String s : temp) {
				System.out.println("\t"+s);
			}
		}
	}
	public void rentOwnValue() {
		TreeMap<String, TreeSet<Double>> rentOwnValueMap = new TreeMap<>();
		for(Citizen c : listofCitizens) {
			if(!rentOwnValueMap.containsKey(c.getRentOrOwn())) rentOwnValueMap.put(c.getRentOrOwn(), new TreeSet<Double>());
			rentOwnValueMap.get(c.getRentOrOwn()).add(c.getPropValue());
		}
		Iterator<String> iter = rentOwnValueMap.keySet().iterator();
		while(iter.hasNext()) {
			String rentOwn = iter.next();
			System.out.println(rentOwn+":");
			TreeSet<Double> temp = rentOwnValueMap.get(rentOwn);
			for(Double num : temp) {
				System.out.println("\t"+num);
			}
		}
	}
	public void fatherBirthplacePropValue() {
		TreeMap<String, TreeSet<Double>> fatherBirthplacePropMap = new TreeMap<>();
		for(Citizen c: listofCitizens) {
			if(!fatherBirthplacePropMap.containsKey(c.getFatherBirth())) fatherBirthplacePropMap.put(c.getFatherBirth(), new TreeSet<Double>());
			fatherBirthplacePropMap.get(c.getFatherBirth()).add(c.getPropValue());
		}
		Iterator<String> iter = fatherBirthplacePropMap.keySet().iterator();
		ArrayList<Double> rich = new ArrayList<Double>();
		while(iter.hasNext()) {
			String fatherBirthplace = iter.next();
			System.out.println(fatherBirthplace+":");
			TreeSet<Double> temp = fatherBirthplacePropMap.get(fatherBirthplace);
			Double richie = 1000.0;
			int pop = 0;
			int richPop = 0;
			for(Double num : temp) {
				System.out.println("\t"+num);
				if(num>=richie) {
					richPop++;
				}
				pop++;
			}
			System.out.println();
			System.out.println(fatherBirthplace+": "+richPop+" who rent or own houses over $1,000 out of the "+pop+" sampled.\n");
		}
	}
	
	public static void main(String [] args) {
		runner app = new runner();
	}
}
