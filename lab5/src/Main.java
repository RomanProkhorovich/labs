import task5.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, DuplicateModelNameException, ClassNotFoundException, InstantiationException, NoSuchModelNameException, IOException, ParseException {
       /* Class t = Class.forName(args[0]);
        Object car = t.getConstructor(String.class, int.class).newInstance(args[1], Integer.parseInt(args[2]) - 1);
        t.getDeclaredMethod("addNewModel", String.class, double.class).invoke(car, args[3], Double.parseDouble(args[4]));
        System.out.println(car.toString());

        System.out.println();

        t.getDeclaredMethod(args[5], String.class, double.class).invoke(car, args[6], Double.parseDouble(args[7]));
        System.out.println(car.toString());


        */ITransport tr=new Skooter("name",5);


        /*tr.addNewModel("newM",1);
        Transport.printInfo(tr);
        System.out.println();

        tr.deleteModel("Model1");
        Transport.printInfo(tr);*//*

         */
        Transport.writeTransport(tr, new FileWriter("D:\\labs\\lab5\\src\\task5\\textFiles\\numeric.txt"));
        ITransport tr2=Transport.readTransport(new FileReader("D:\\labs\\lab5\\src\\task5\\textFiles\\numeric.txt"));
        Transport.printInfo(tr2);

        /*Transport.outputTransport(tr,new FileOutputStream("D:\\labs\\lab5\\src\\task5\\textFiles\\byte.txt"));
        ITransport tr2=Transport.inputTransport(new FileInputStream("D:\\labs\\lab5\\src\\task5\\textFiles\\byte.txt"));
        Transport.printInfo(tr2);*/

       /* ITransport tr2=new Skooter("name1",2);
        Transport.printInfo(tr);
        System.out.println();
        Transport.printInfo(tr2);
        System.out.println();
        System.out.println(Transport.getAvarege(tr));
        System.out.println();
        System.out.println(Transport.getAvarege(tr2));
        System.out.println();
        System.out.println(Transport.getAvarege(tr,tr2));*/

//


       /* ITransport transport=new Moped("",5);
        Transport.printInfo(transport);
        transport.addNewModel("Model",1);
        System.out.println();
        Transport.printInfo(transport);
        transport.setModelPrice("Model2",2);
        Transport.printInfo(transport);*/

       /* ITransport tr2 = Transport.create("mark",3,tr);
        System.out.println(tr2.getClass().getName());


        Transport.printInfo(tr2);*/

        System.out.println(Transport.getAvarege(new QuadBike("",5),new Moped("",5),new Car("",3)));
    }
}