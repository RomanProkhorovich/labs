import task5.*;

import java.io.*;

public class program {
    public static void main(String args[]) throws DuplicateModelNameException, IOException, ClassNotFoundException {
        FileOutputStream fio=new FileOutputStream("D:\\labs\\lab3\\src\\\\task5\\textFiles\\numeric.txt");
        ITransport tr=new Car("name",10);
        Transport.printInfo(tr);
        ObjectOutputStream ob=new ObjectOutputStream(fio);
        ob.writeObject(tr);
        System.out.println();

        FileInputStream fis=new FileInputStream("D:\\labs\\lab3\\src\\\\task5\\textFiles\\numeric.txt");
        ObjectInputStream ins=new ObjectInputStream(fis);
        ITransport car=(Car)ins.readObject();
        Transport.printInfo(car);
    }
}
