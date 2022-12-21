package task5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class QuadBike implements ITransport{
    private String mark;
    private ArrayList<Model> models;
    private class Model implements Serializable {
        private String NameOfModel;
        private double Price= Double.NaN;
        public Model(String name, double price){
            NameOfModel=name;
            Price=price;
        }
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public String[] getNameOfAllModels() {
        String[] names=new String[getModelsCount()];
        for (int i = 0; i < getModelsCount(); i++) {
            names[i]= models.get(i).NameOfModel;
        }
        return names;
    }

    @Override
    public double[] getPriceOfAllModels() {
        double[] prices=new double[getModelsCount()];
        for (int i = 0; i < getModelsCount(); i++) {
            prices[i]= models.get(i).Price;
        }
        return prices;
    }

    @Override
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        if (price<=0)
            throw new ModelPriceOutOfBoundsException("",price);
        if (isUnique(name)){
            models.add(new Model(name,price));
        }
        else throw new DuplicateModelNameException("","");
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        if (!isUnique(name)){
            models.remove(search(name));
        }
        else throw new NoSuchModelNameException("","");
    }

    @Override
    public int getModelsCount() {
        return models.size();
    }

    @Override
    public void setMark(String newMark) {
        mark=newMark;
    }

    @Override
    public void setModelPrice(String name, double newPrice) throws NoSuchModelNameException {
        if (newPrice<=0)
            throw new ModelPriceOutOfBoundsException("",newPrice);
        Model m=search(name);
        if (m==null)
            throw new NoSuchModelNameException("","");
        m.Price=newPrice;
    }

    @Override
    public double getModelPrice(String name) throws NoSuchModelNameException {
        if (!isUnique(name)) {
            return search(name).Price;
        }
        throw new NoSuchModelNameException("","");
    }

    @Override
    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
       if(isUnique(newName)){
           Model m=search(name);
           if (m==null)
               throw new NoSuchModelNameException("","");
           m.NameOfModel=newName;
       }
       else
           throw new DuplicateModelNameException("","");
    }

    public QuadBike(String Mark,int size){
        mark=Mark;
        models=new ArrayList<>(size);
        Random rnd=new Random();
        for (int i = 0; i < size; i++) {
            models.add(new Model("Model"+(i+1),rnd.nextDouble()));
        }
    }

    private Model search(String name){
        for (Model m:models) {
            if (m.NameOfModel.equals(name))
                return m;
        }
        return null;
    }
    private boolean isUnique(String name){

        return search(name)==null;
    }
}
