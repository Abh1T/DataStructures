
public 	class Word implements Comparable<Word>{
	String str;
	boolean b;
	public Word(String s, boolean b ) {
		this.str = s;
		this.b = b;
	}
	public String getString() {
		return str;
	}
	@Override
	public int compareTo(Word o) {
		if(b)
		return str.toLowerCase().compareTo(o.getString().toLowerCase());
		//this is the code for ascending order ^
		else return -str.toLowerCase().compareTo(o.getString().toLowerCase());
	}
}