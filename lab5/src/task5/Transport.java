package task5;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Transport {
    static String[] transports={Car.class.getName() ,Motocycle.class.getName(),
            Moped.class.getName(),QuadBike.class.getName(),Skooter.class.getName()};
    static ITransport[] classes={new Car("",0),new Motocycle("",0),
            new Moped("",0),new QuadBike("",0),new Skooter("",0)};



    public static double getAvarege(ITransport transport){
        double[] mas=transport.getPriceOfAllModels();
        double count=transport.getModelsCount();
        double sum=0;
        sum = getSum(mas);
        return sum/count;
    }

    private static double getSum(double[] mas) {
        double sum=0;
        for (double i: mas) {
            sum +=i;
        }
        return sum;
    }


    public static double getAvarege(ITransport ... A){
        double sum=0;
        double coont=0;

        for (ITransport i:A) {
            coont+=i.getModelsCount();
            sum+=getSum(i.getPriceOfAllModels());
        }

        return  sum/coont;
    }

    public static void printInfo(ITransport transport){
        //System.out.println(transport);
        String[] names = transport.getNameOfAllModels();
        double[] pricec = transport.getPriceOfAllModels();
        for (int i=0; i< names.length; i++){
            System.out.println("название модели: " + names[i]+ " цена модели: "+ pricec[i]);
        }
    }

//байтовы вывод
    public static void outputTransport (ITransport v, OutputStream out) throws IOException{
        DataOutputStream dos = new DataOutputStream(out);

        String mark = v.getMark();
        int number = v.getModelsCount();
        String[] models = v.getNameOfAllModels();
        double[] prices = v.getPriceOfAllModels();
        int j = mark.length();
        dos.writeInt(Arrays.asList(transports).indexOf(v.getClass().getName()));
        dos.writeInt(j);
        dos.writeBytes(mark);
        dos.writeInt(number);
        for (int i = 0; i<number; i++)
        {
            dos.writeInt(models[i].length());
            dos.writeBytes(models[i]);
        }
        for (int i = 0; i<number; i++)
            dos.writeDouble(prices[i]);
    }
//байтовый ввод
    public static ITransport inputTransport (InputStream in) throws IOException, NoSuchModelNameException, DuplicateModelNameException{
        DataInputStream dis = new DataInputStream(in);
        int flag = dis.readInt();
        int size = dis.readInt();
        byte[] buffer = new byte[size];
        for (int i = 0; i<size; i++)
            buffer[i] = dis.readByte();
        String mark = new String(buffer);
        int modelsNumber = dis.readInt();
        String[] models = new String[modelsNumber];
        double[] prices = new double[modelsNumber];
        for (int i = 0; i<modelsNumber; i++) {
            int nextSize = dis.readInt();
            byte[] bufferSize = new byte[nextSize];
            for (int j = 0; j<nextSize; j++) {
                bufferSize[j] = dis.readByte();
            }
            models[i] = new String(bufferSize);
        }
        for (int i = 0; i<modelsNumber; i++)
            prices[i] = dis.readDouble();
        ITransport transport=classes[flag];
        transport.setMark(mark);
        for (int i = 0; i<modelsNumber; i++) {
            transport.addNewModel(models[i], prices[i]);
        }
        return transport;

    }





    ///////////////////////////////////

    public static void writeTransport (ITransport v, Writer out) throws IOException {

        PrintWriter pw = new PrintWriter(out);
        String mark = v.getMark();
        int number = v.getModelsCount();
        String[] models = v.getNameOfAllModels();
        double[] prices = v.getPriceOfAllModels();
        pw.printf("%s\n",Arrays.asList(transports).indexOf(v.getClass().getName()));
        pw.printf("%s\n",mark);
        pw.printf("%d\n",number);
        for (int i = 0; i<number; i++)
            pw.printf(models[i] +"\n");
        for (int i = 0; i<number; i++)
            pw.printf("%f\n",prices[i]);
        pw.flush();
    }
    public static ITransport readTransport(Reader in) throws IOException, NoSuchModelNameException, DuplicateModelNameException, ParseException {
        Scanner br = new Scanner(in);
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRANCE);

        int flag = Integer.parseInt(br.nextLine());
        String mark = br.nextLine();
        int number = Integer.parseInt(br.nextLine());
        String[] models = new String[number];
        double[] prices = new double[number];
        for (int i = 0; i<number; i++)
            models[i] = br.nextLine();
        for (int i = 0; i<number; i++)
            prices[i] = numberFormat.parse((br.nextLine())).doubleValue();
        ITransport transport=classes[flag];
        transport.setMark(mark);
        for (int i = 0; i<number; i++) {
            transport.addNewModel(models[i], prices[i]);
        }
        return transport;
    }


    public static ITransport create(String mark,int size,ITransport transport)  {
        Class  cl = transport.getClass();
        try {
            ITransport tr= (ITransport) cl.getConstructor(String.class ,int.class).newInstance(mark,size);
            return tr;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            return null;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}






