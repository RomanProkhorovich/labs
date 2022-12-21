package MyFirstPackage;

 public class MyFirstPackage{
	int[] numbs;

	public int getElement(int index){
		return numbs[index];
	}
	public void change(int index, int value){
		numbs[index]=value;
	}
	public MyFirstPackage(int size){
		if(size>0){
			//Random rnd = new Random();
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
		for (int i=0; i<count; i++){
				sum= sum + numbs[i];
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