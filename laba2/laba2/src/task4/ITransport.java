package task4;

public interface ITransport {
    public String getMark();
    public String[] getNameOfAllModels();
    public double[] getPriceOfAllModels();
    public void addNewModel(String name, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException;
    public void deleteModel(String name) throws NoSuchModelNameException;
    public int getModelsCount();
    public  void changeMark(String newMark);
}
