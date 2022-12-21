package task5;

import java.io.Serializable;

public interface ITransport extends Serializable, Cloneable {
    public String getMark();
    public String[] getNameOfAllModels();
    public double[] getPriceOfAllModels();
    public void addNewModel(String name, double price) throws DuplicateModelNameException;
    public void deleteModel(String name) throws NoSuchModelNameException;
    public int getModelsCount();
    public  void setMark(String newMark);
    public void setModelPrice(String name, double newPrice) throws NoSuchModelNameException;
    public double getModelPrice(String name) throws NoSuchModelNameException ;
    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    }
