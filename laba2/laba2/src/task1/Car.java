package task1;
import java.util.Arrays;
import java.util.Random;

public class Car {
    private class Model{
        private String NameOfModel;
        private double Price= Double.NaN;
        public Model(String name, double price){
            NameOfModel=name;
            Price=price;
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

        for (int i=0; i< mas.length; i++) {
            if (mas[i]!= null && mas[i].NameOfModel.equals(name)) return false;
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
    public boolean changeModelName(String name, String newName){
        boolean flag=false;
        for (Model m: Models){
            if(m.NameOfModel.equals(name)) {
                m.NameOfModel=newName;
                flag=true;
            }
        }
        return flag;
    }
    public boolean changeModelPrice(String name, double newPrice){
        boolean flag=false;
        for (Model m: Models){
            if(m.NameOfModel == name) {
                m.Price=newPrice;
                flag=true;
            }
        }
        return flag;
    }
    public double getModelPrice(String name){
        for (Model m: Models){
            if(m.NameOfModel == name) {
                return  m.Price;
            }
        }
        return -1;
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
    public boolean addNewModel(String name, double price){
        if (name!=null && isUniqueModelName(name, Models) && price>=0) {
            int len= Models.length;
            Models = Arrays.copyOf(Models,len+1);
            Models[len]=new Model(name, price);
            return  true;
        }
        else return false;
    }
    public void deleteModel(String name){
        Model[] result = new Model[Models.length-1];
        int index=0;
        while (index< Models.length && Models[index].NameOfModel != name) {
            index++;
        }
        if (index< Models.length){
        System.arraycopy(Models, 0, result, 0, index);
        System.arraycopy(Models, index + 1, result, index, Models.length - index - 1);
        }
    }
    public int getModelsCount() {
        return Models.length;
    }
    public  void changeMark(String newMark){
        Mark =newMark;
    }
}