import MyFirstPackage.*;
 
class MyFirstClass{
	public static void main(String[] args){
		MyFirstPackage cl=new MyFirstPackage(5);
		System.out.println("исходный массив:");
		cl.printMas();
		System.out.println();
		cl.change(0,10 );
		System.out.println("массив после замены:");
		cl.printMas();
		System.out.println();
		System.out.print("среднее арифметическое=");
		System.out.println(cl.getAverage());
	}	
}
