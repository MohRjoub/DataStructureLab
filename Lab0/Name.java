package application;

public class Name implements Comparable<Name>{
	private String name;
	private char gender;
	private int frequency;

	public Name(String name, char gender, int frequency) {
		setName(name);
		setGender(gender);
		setFrequency(frequency);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		if (gender =='F' || gender=='M') 
		this.gender = gender;
		else 
			System.out.println("Enter M or F only");
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	@Override
	public int compareTo(Name o) {

		return o.getName().compareTo(this.getName());
	}
	@Override
	public String toString() {
		return "Name [name=" + name + ", gender=" + gender + ", frequency=" + frequency + "]";
	}

}
