import task5.*;

import java.io.*;

public class program {
    public static void main(String args[]) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        String path="D:\\labs\\lab3\\src\\task5\\textFiles\\byte.txt";
        ITransport tr=new Car("Name",5);
        Transport.printInfo(tr);

       Transport.outputTransport(tr,new FileOutputStream(path));
       Transport.writeTransport(tr,new FileWriter(path));

    }
}

