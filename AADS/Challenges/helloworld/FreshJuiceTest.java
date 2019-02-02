/*
We can create a class and we can declare the allawed values with the command enum
*/
class FreshJuice{
	enum FreshJuiceSize {SMALL, MEDIUM, LARGE}
	FreshJuiceSize size;
}

public class FreshJuiceTest{
	public static void main(String[] args) {
		FreshJuice juice = new FreshJuice(); //We create a new juice
		juice.size = FreshJuice.FreshJuiceSize.MEDIUM; //We declare it to the medium size
		System.out.println("Size: " + juice.size);
	}
}