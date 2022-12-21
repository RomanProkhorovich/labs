package task5;

import java.io.*;

public class Transport {
    public static double getAvarege(ITransport transport){
        double[] mas=transport.getPriceOfAllModels();
        int count=transport.getModelsCount();
        double sum=0;
        for (double i:mas) {
            sum+=i;
        }
        return sum/count;
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
        int type;
        if (v.getClass().equals(Car.class))
            type=0;
        else type=1;
        String mark = v.getMark();
        int number = v.getModelsCount();
        String[] models = v.getNameOfAllModels();
        double[] prices = v.getPriceOfAllModels();
        int j = mark.length();
        dos.writeInt(type);
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
    public static ITransport inputTransport (InputStream in) throws IOException, NoSuchModelNameException, DuplicateModelNameException, DuplicateModelNameException {
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
        if (flag == 0) {
            Car auto1 = new Car(mark,0);
            for (int i = 0; i<modelsNumber; i++) {
                auto1.addNewModel(models[i], prices[i]);
            }
            return auto1;
        }
        else {
            Motocycle motorcycle1 = new Motocycle(mark,0);
            for (int i = 0; i<modelsNumber; i++) {
                motorcycle1.addNewModel(models[i], prices[i]);
            }
            return motorcycle1;
        }
    }





    ///////////////////////////////////

    public static void writeTransport (ITransport v, Writer out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        String mark = v.getMark();
        int flag;
       if (v.getClass().equals(Car.class)){
           flag=0;
       }
       else flag=1;
        int number = v.getModelsCount();
        String[] models = v.getNameOfAllModels();
        double[] prices = v.getPriceOfAllModels();
        pw.println(flag);
        pw.println(mark);
        pw.println(number);
        for (int i = 0; i<number; i++)
            pw.println(models[i]);
        for (int i = 0; i<number; i++)
            pw.println(prices[i]);
        pw.flush();
    }
    public static ITransport readTransport(Reader in) throws IOException, NoSuchModelNameException, DuplicateModelNameException {
        BufferedReader br = new BufferedReader(in);
        int flag = Integer.parseInt(br.readLine());
        String mark = br.readLine();
        int number = Integer.parseInt(br.readLine());
        String[] models = new String[number];
        double[] prices = new double[number];
        for (int i = 0; i<number; i++)
            models[i] = br.readLine();
        for (int i = 0; i<number; i++)
            prices[i] = Double.parseDouble(br.readLine());
        if (flag == 0) {
            Car auto1 = new Car(mark,0);
            for (int i = 0; i<number; i++) {
                auto1.addNewModel(models[i], prices[i]);
            }
            return auto1;
        }
        else {
            Motocycle motorcycle1 = new Motocycle(mark,0);
            for (int i = 0; i<number; i++) {
                motorcycle1.addNewModel(models[i], prices[i]);
            }
            return motorcycle1;
        }

    }
}






