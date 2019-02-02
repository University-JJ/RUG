public class Puppy{

	//Instance variable
	int puppyAge;

	public Puppy(String name)
	{
		System.out.println("The name chosen is: " + name);
	}

	public void setAge(int age)
	{
		puppyAge = age;
	}

	public int getAge()
	{
		System.out.println("Puppy's age is: " + puppyAge);
		return puppyAge;
	}


	public static void main(String[] args) {
		/*Object creation*/
		Puppy myPuppy = new Puppy("Scooby");

		/*Call class method to set puppy's age*/
		myPuppy.setAge(2);

		/*Call another class method to get puppy's age*/
		myPuppy.getAge();

		/*You can access instance variables as follows as well*/
		System.out.println("Variable with getAge: " + myPuppy.getAge());
	}
}