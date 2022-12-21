

class MyFirstClass{
	public static void main(String[] args){
		MySecondClass cl=new MySecondClass(5);
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

class MySecondClass{
	private int[] numbs;

	public int getElement(int index){
		return numbs[index];
	}
	public void change(int index, int value){
		numbs[index]=value;
	}
	public MySecondClass(int size){
		if(size>0){
			numbs=new int[size];
			for(int i=0; i<size; i++){
				numbs[i]=(int)( Math.random() * 100 );
			}
		}
		else numbs=null;
	}
	public float getAverage(){
		int sum=0;
		int count=numbs.length;
		for (int i:numbs){
			sum+= i;
		}
		return (float)sum/(float)count;
	}
	public void printMas(){
		for (int i : numbs) {
			System.out.print(i);
			System.out.print(" ");
		}
	}

}