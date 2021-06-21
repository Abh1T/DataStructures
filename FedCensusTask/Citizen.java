import java.io.*;
import java.util.ArrayList;

public class Citizen implements Comparable<Citizen>{
	private String firstName;
	private String lastName;
	private String streetName;
	private int streetNum;
	private String relation;
	private String rentOrOwn;
	private double propValue;
	private String gender;
	private double age;
	private String status;
	private int ageAt;
	private boolean attendSchool;
	private boolean canRead;
	private String birthplace;
	private String fatherBirth;
	String motherBirth;
	String motherTongue;
	int yearImmi;
	String occ;
	String ind;
	String trans;

	public Citizen() {
		
	}
	public Citizen(String firstName, String lastName, String street, String streetNum, String relation,
			String rentOrOwn, String propValue, String gender, String age, String status, String ageAt,
			String attendSchool, String canRead, String birthplace, String fatherBirth, String motherBirth,
			String motherTongue, String yearImmi, String occ, String ind, String trans) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = street;
		try {
			this.streetNum = Integer.parseInt(streetNum);
		}catch(NumberFormatException e) {
			this.streetNum = 1;
		}
		this.relation = relation;
		this.rentOrOwn = rentOrOwn.substring(0,1);
		
		if(propValue.charAt(0)=='$')
			propValue = propValue.substring(1);
		try {
			this.propValue = Double.parseDouble(propValue);
		}catch(NumberFormatException e) {
			if(propValue.contains("/")) {
				String whole = propValue.substring(0, propValue.indexOf(" "));
				String numer = propValue.substring(propValue.indexOf(" ")+1, propValue.indexOf("/"));
				String denom = propValue.substring(propValue.indexOf("/")+1);
				this.propValue = Double.parseDouble(whole)+ Double.parseDouble(numer)/Double.parseDouble(denom);
			}
		}
		this.gender = gender;
		try {
			this.age = Double.parseDouble(age);
		}catch(NumberFormatException e) {
			if(age.charAt(0) == '.' || age.equals("un"))  this.age = -1;
			else if(age.charAt(1) == ' ' && age.contains("/")) 
			{
				String whole = age.substring(0, age.indexOf(" "));
				double dec;
				if(age.substring(age.indexOf(" ")+1, age.indexOf("/")).contains("*")) {
					dec = 0.5;
				}else {
					String numer = age.substring(age.indexOf(" ")+1, age.indexOf("/"));
					String denom = age.substring(age.indexOf("/")+1);
					dec = Double.parseDouble(numer)/Double.parseDouble(denom);
				}
				this.age = Double.parseDouble(whole)+dec;
			}
			
			else if(age.contains("*")) 
			{
				this.age = Double.parseDouble(age.substring(0, age.indexOf("*")));
			}
			else 
			{
				String numer = age.substring(0, age.indexOf("/"));
				String denom = age.substring(age.indexOf("/")+1);
				this.age = Double.parseDouble(numer)/Double.parseDouble(denom);
			}
			
		}
		this.status = status;
		try {
			this.ageAt = Integer.parseInt(ageAt);
		}catch(NumberFormatException e) {
			this.ageAt = -1;
		}
		
		if(attendSchool.equals("Yes")) {
			this.attendSchool = true;
		}else {
			this.attendSchool = false;
		}
		
		if(canRead.equals("Yes"))
			this.canRead = true;
		else this.canRead = false;
		
		this.birthplace = birthplace;
		this.fatherBirth = fatherBirth;
		this.motherBirth = motherBirth;
		this.motherTongue = motherTongue;
		
		
		try {
			this.yearImmi = Integer.parseInt(yearImmi);
		}catch(NumberFormatException e) {
			this.yearImmi = -1;
		}
		this.occ = occ.substring(0,1).toUpperCase()+occ.substring(1).toLowerCase();
		this.ind = ind;
		this.trans = trans;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	public String getStreet() {
		return streetName;
	}

	
	public int getStreetNum() {
		return streetNum;
	}

	
	public String getRelation() {
		return relation;
	}

	
	public String getRentOrOwn() {
		return rentOrOwn;
	}

	
	public double getPropValue() {
		return propValue;
	}

	
	public String getGender() {
		return gender;
	}

	
	public double getAge() {
		return age;
	}

	
	public String getStatus() {
		return status;
	}

	public int getAgeAt() {
		return ageAt;
	}

	
	public boolean getAttendSchool() {
		return attendSchool;
	}

	
	public boolean getCanRead() {
		return canRead;
	}

	
	public String getBirthplace() {
		return birthplace;
	}

	
	public String getFatherBirth() {
		return fatherBirth;
	}

	
	public String getMotherBirth() {
		return motherBirth;
	}

	
	public String getMotherTongue() {
		return motherTongue;
	}

	
	public int getYearImmi() {
		return yearImmi;
	}

	public String getOcc() {
		return occ;
	}

	
	public String getInd() {
		return ind;
	}

	
	public String getTrans() {
		return trans;
	}
	@Override
	public int compareTo(Citizen o) {
		if(getFirstName().compareTo(o.getFirstName())<0)
			return -1;
		if(getFirstName().compareTo(o.getFirstName())>0)
			return 1;
		if(getLastName().compareTo(o.getLastName())<0)
			return -1;
		if(getLastName().compareTo(o.getLastName())>0)
			return 1;
		if(getStreet().compareTo(o.getStreet())<0)
			return -1;
		if(getStreet().compareTo(o.getStreet())>0)
			return 1;	
		if(getStreetNum()<(o.getStreetNum()))
			return -1;
		if(getStreetNum()>(o.getStreetNum()))
			return 1;
		if(getRelation().compareTo(o.getRelation())<0)
			return -1;
		if(getRelation().compareTo(o.getRelation())>0)
			return 1;
		if(getRentOrOwn().compareTo(o.getRentOrOwn())<0)
			return -1;
		if(getRentOrOwn().compareTo(o.getRentOrOwn())>0)
			return 1;
		if(getPropValue()<(o.getPropValue()))
			return -1;
		if(getPropValue()>(o.getPropValue()))
			return 1;
		if(getGender().compareTo(o.getGender())<0)
			return -1;
		if(getGender().compareTo(o.getGender())>0)
			return 1;
		if(getAge()<(o.getAge()))
			return -1;
		if(getAge()>(o.getAge()))
			return 1;
		if(getStatus().compareTo(o.getStatus())<0)
			return -1;
		if(getStatus().compareTo(o.getStatus())>0)
			return 1;
		if(getAgeAt()<(o.getAgeAt()))
			return -1;
		if(getAgeAt()>(o.getAgeAt()))
			return 1;
		if(getAttendSchool())
			return -1;
		if(!getAttendSchool())
			return 1;
		return 0;
	}
	public String toString() {
		return String.format("%-25sAge: %s",lastName+", "+firstName, age );
	}
}
