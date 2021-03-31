
/**
 *  We have to comment out parts of code to implement each part of the Lab
 */

class X{
	
	//Part II (a), (b), (c) , Part IV (a), (b), (c)
	void printmethod(){
		System.out.println("In the superclass X");
	}
	//Part II (d)
	void printmethod(int k){
		System.out.println("In the superclass X with int k = " + k);
	}
	
	//Part III (a), (b), (c)
	public static final int CONSTANT = 99; 

}

interface Z{
	
	void printmethod();//Part II (a)
	int printmethod();//Part II (b)
	void printmethod(int i);//Part II (c), (d)
	public static final int CONSTANT = 100;//Part III (a), (b), (c)
}

class Y extends X implements Z{
	
	//Part II (a) same signature and same returm type, Part IV (a), (b), (c)
	public void printmethod(){
		System.out.println("In the subclass Y");
	}
	
	//Part II (b) different return type
	public int printmethod(){
		System.out.println("In the subclass Y with return type int");
		return 5;
	}
	
	//Part II (c) same return type but different signature
	public void printmethod(int i){
		System.out.println("In the subclass Y with parameter int i = " + i);
	}
	public void printmethod(){
		System.out.println("In the subclass Y");
	}
	
	//Part II (d) same return type, same number of paremeters and parameter type but different parameters' names
	public void printmethod(int m){
		System.out.println("In the subclass Y with int m  = " + m);
	}
	

	//Part III (b), (c)
	public static void main(String[] args) {
		X y1 = new Y();
		Z y2 = new Y();
		System.out.println("static final constant in Superclass X = " + y1.CONSTANT);
		System.out.println("static final constant in Interface Z = " + y2.CONSTANT);
	}
	

	///Part IV (c)
	public void printmethod_superclass(){
		super.printmethod();
	}
}



public class Lab5{ 
	public static void main(String[] args){

		//////Part I: (a) & (b) & (c)
		X[] xa = new X[2];
		Y[] ya = new Y[3];
		xa = ya;		// without compile-time error, without run-time error
		ya = xa;		// compile-time error
		xa = (X[])ya;	// here is a cast required, without compile-time error, without run-time error
		ya = (Y[])xa;	// without compile-time error, but run-time error
		
		//////Part II
		Y y = new Y();
		y.printmethod();
		//(a) Java can compile the result, because printmethod() in X and Z are override-equivalent

		Y y = new Y();
		int a = y.printmethod();
		//(b) Java can "not" compile the result, because printmethod() in X and Zare not override-equivalent

		Y y = new Y();
		y.printmethod(3);//(c) Java can compile the result, because we override the method in Z
		y.printmethod();//(c) Java can compile the result, because we override method in X
	
		Y y = new Y();
		y.printmethod(3);
		//(d) Java can compile the result, because the different name of parameters still make printmethod() in X and Z override-equivalent
		

		//////Part III
		Y y = new Y();
		System.out.println(y.CONSTANT);
		//(a)compile-time error, when the callee class extends superclass constant and interface constant with the same name. 
		//Even though they have the same value, it could not solve the compile-time error
		

		//////Part IV
		Y y = new Y();
		((X)y).printmethod();//(a)call the subclass method

		X x = new X();
		(Y(x)).printmethod();
		//(b) compile-time error, because static type can not be the subclass of the dynamic class type

		Y y = new Y();
		y.printmethod_superclass();
		//(c) create a method in the subclass(name could not be the same)  and call "super.superclassnethod();" in the subclass method

	}
}



