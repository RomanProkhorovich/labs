import task5.*;

import java.io.*;

public class program {
    public static void main(String args[]) throws DuplicateModelNameException, CloneNotSupportedException, NoSuchModelNameException {
        ITransport tr1=new Motocycle("Name1",10);

      //  tr1.addNewModel("some name",0.1);
        ITransport tr2= ((Motocycle) tr1).clone();
        tr2.setModelPrice(tr2.getNameOfAllModels()[0],10);


        System.out.println(tr1);
        System.out.println();
        System.out.println(tr2);

    }
}
