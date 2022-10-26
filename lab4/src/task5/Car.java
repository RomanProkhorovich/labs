package task5;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Car implements ITransport {
    private class Model implements Serializable{
        private String NameOfModel;
        private double Price= Double.NaN;
        public Model(String name, double price){
            NameOfModel=name;
            Price=price;
        }
        @Override
        public Model clone(){
            return new Model(NameOfModel,Price);
        }
        @Override
        public boolean equals(Object obj){
            return obj.getClass() == Model.class &&
                    ((Model) obj).Price == Price &&
                    ((Model) obj).NameOfModel.equals(NameOfModel);
        }
    }


    private String Mark;
    private Model[] Models;
    public Car(String name, int count){
        Mark =name;
        Models =generateRandomModelMas(count);
    }
    private Model[] generateRandomModelMas(int count) {
        int o=0;
        String newName;
        Model[] res=new Model[count];
        while(o < count) {
            newName=generateRandomString(6);
            if(isUniqueModelName(newName,res)){
                res[o]=new Model(newName,(Math.random() * 100 ));
                o++;
            }
        }
        return res;
    }
    private boolean isUniqueModelName(String name,Model[] mas){

        for (Model ma : mas) {
            if (ma != null && ma.NameOfModel.equals(name)) return false;
        }
        return true;
    }
    public static String generateRandomString(int length){
        StringBuilder sb=new StringBuilder();
        Random r= new Random();
        for (int i=0; i<6; i++){
            sb.append((char)(new Random().nextInt(65,122)));
        }
        return sb.toString();
    }
    public String getMark(){
        return Mark;
    }

    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (!isUniqueModelName(newName,Models)) throw new DuplicateModelNameException("модель с таким именем уже существует",newName);
        boolean flag=false;
        int i=0;
        while(i<Models.length && !flag){
            Model m= Models[i];
            if(m.NameOfModel.equals(name)) {
                m.NameOfModel=newName;
                flag=true;
            }
            i++;
        }
        if (!flag) throw new NoSuchModelNameException("модель с таким названием не существует",name);
    }
    public void setModelPrice(String name, double newPrice) throws NoSuchModelNameException {
        if (newPrice<0) throw new ModelPriceOutOfBoundsException("цена не может быть отрицаиельной",newPrice);
        boolean flag=false;
        int i=0;
        while(i<Models.length && !flag){
            Model m= Models[i];
            if(m.NameOfModel.equals(name)) {
                m.Price=newPrice;
                flag=true;
            }
        }
        if (!flag) throw new NoSuchModelNameException("модели с таким именем не сущесвтует",name);
    }
    public double getModelPrice(String name) throws NoSuchModelNameException {

        for (Model m: Models) {
            if (m.NameOfModel.equals(name)) {
                return m.Price;
            }
        }
        throw new NoSuchModelNameException("модели с таким названием не существет",name);
    }
    public String[] getNameOfAllModels() {
        String[] res = new String[Models.length];
        for (int i = 0; i< Models.length; i++) {
            res[i]= Models[i].NameOfModel;
        }
        return res;
    }
    public double[] getPriceOfAllModels() {
        double[] res = new double[Models.length];
        for (int i = 0; i< Models.length; i++) {
            res[i]= Models[i].Price;
        }
        return res;
    }
    public void addNewModel(String name, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException {
        if (!isUniqueModelName(name,Models)){
            throw new DuplicateModelNameException("модель с таким названием уже существует",name);
        }
        if (name!=null && price>=0) {
            int len= Models.length;
            Models = Arrays.copyOf(Models,len+1);
            Models[len]=new Model(name, price);
        }
        else if(price<0) throw new ModelPriceOutOfBoundsException("цена не может быть отрицателньой",price);
    }
    public void deleteModel(String name) throws NoSuchModelNameException {
        Model[] result = new Model[Models.length-1];
        int index=0;
        while (index< Models.length && !Models[index].NameOfModel.equals(name)) {//equals
            index++;
        }
        if (index< Models.length){
        System.arraycopy(Models, 0, result, 0, index);
        System.arraycopy(Models, index + 1, result, index, Models.length - index - 1);
        }
        else throw new NoSuchModelNameException("модель с таким именем не сущесивует",name);
        Models=result;
    }
    public int getModelsCount() {
        return Models.length;
    }
    public  void setMark(String newMark){
        Mark =newMark;
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        for (Model m:Models) {
            sb.append(m.NameOfModel).append(" ").append(m.Price).append("\n");
        }
        return sb.toString();
    }
    @Override
    public Car clone(){

        try {
            Car res = (Car)super.clone();
            for (int i = 0; i < getModelsCount(); i++) {
                res.addNewModel(Models[i].NameOfModel, Models[i].Price);
            }
            return res;

        } catch (DuplicateModelNameException | CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int hashCode(){
        return Mark.length()+getModelsCount();
    }
    @Override
    public boolean equals(Object obj){
        if ((obj.getClass()!= Car.class || !((Car) obj).getMark().equals(Mark) ||
                ((Car) obj).getModelsCount()!=Models.length)){
            return false;
        }
        for (int i=0;i<getModelsCount();i++){
            if (!Models[i].equals(((Car) obj).Models[i])) return false;
        }
        return true;
    }
}