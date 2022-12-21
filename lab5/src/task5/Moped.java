package task5;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Moped implements  ITransport{
    private String mark;
    private LinkedList<Model> models=new LinkedList<Model>();
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
        for (int i = 0; i <getModelsCount() ; i++) {
            names[i]=models.get(i).NameOfModel;
        }
        return names;
    }

    @Override
    public double[] getPriceOfAllModels() {
        double[] prices=new double[getModelsCount()];
        for (int i = 0; i <getModelsCount() ; i++) {
            prices[i]=models.get(i).Price;
        }
        return prices;
    }

    @Override
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        if (price<=0)
            throw new ModelPriceOutOfBoundsException("",price);
        if (!isUnique(name))
            throw new DuplicateModelNameException("","");
        models.addLast(new Model(name,price));
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        if (isUnique(name))
            throw new NoSuchModelNameException("","");
        models.remove(search(name));
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
            throw  new ModelPriceOutOfBoundsException("",newPrice);
        if (isUnique(name))
            throw new NoSuchModelNameException("","");

        search(name).Price=newPrice;
    }

    @Override
    public double getModelPrice(String name) throws NoSuchModelNameException {
      if (isUnique(name))
          throw new NoSuchModelNameException("","");
      return search(name).Price;
    }

    @Override
    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (isUnique(name))
            throw new NoSuchModelNameException("","");
        if (!isUnique(newName))
            throw new DuplicateModelNameException("","");
        search(name).NameOfModel=newName;
    }
    public Moped(String newMark,int count){
        mark=newMark;
        Random rnd=new Random();
        for (int i = 0; i < count; i++) {
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
