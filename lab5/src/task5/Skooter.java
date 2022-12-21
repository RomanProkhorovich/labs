package task5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Skooter implements ITransport {
    private HashMap<String,Double> models=new HashMap<>();
    private String mark;
    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public String[] getNameOfAllModels() {
        String[] names=new String[getModelsCount()];
        Object[] a= (models.keySet().toArray());
        for (int i = 0; i < getModelsCount(); i++) {
            names[i]=a[i].toString();
        }

        return names;
    }

    @Override
    public double[] getPriceOfAllModels() {
        double[] mas=new double[models.size()];
        int i=0;
        for (Double v:models.values()) {
            mas[i]= v;
            i++;
        }
        return mas;
    }

    @Override
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        if (price<=0)
            throw new ModelPriceOutOfBoundsException("",price);
        if (isUniqueName(name))
            models.put(name,price);
        else throw new DuplicateModelNameException("","");
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        if (!isUniqueName(name))
            models.remove(name);
        else
            throw  new NoSuchModelNameException("","");
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
        if (isUniqueName(name))
            throw new NoSuchModelNameException("","");
        models.replace(name,newPrice);
    }

    @Override
    public double getModelPrice(String name) throws NoSuchModelNameException {
        if (!isUniqueName(name))
            return models.get(name);
        throw new NoSuchModelNameException("","");
    }

    @Override
    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (isUniqueName(newName)){
            if (!isUniqueName(name)){
                models.put(newName,models.get(name));
                models.remove(name);
            }
            else
                throw new NoSuchModelNameException("","");

        }
        throw new DuplicateModelNameException("","");
    }

    private boolean isUniqueName(String name){
        for (String a:models.keySet()) {
            if (a.equals(name))
                return false;
        }
        return true;
    }
    public Skooter(String Mark,int count){
        mark=Mark;
        Random rnd=new Random();
        for (int i = 0; i < count; i++) {
            try {
                addNewModel("Model"+(i+1),rnd.nextDouble());
            } catch (DuplicateModelNameException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
