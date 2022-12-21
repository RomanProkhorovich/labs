package task5;

public interface ITransport {
    public String getMark();
    public String[] getNameOfAllModels();
    public double[] getPriceOfAllModels();
    public void addNewModel(String name, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException;
    public void deleteModel(String name) throws NoSuchModelNameException;
    public int getModelsCount();
    public  void changeMark(String newMark);
    public void changeModelPrice(String name, double newPrice) throws NoSuchModelNameException;
    public double getModelPrice(String name) throws NoSuchModelNameException ;
    public void changeModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    }
