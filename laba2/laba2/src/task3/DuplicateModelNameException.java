package task3;

public class DuplicateModelNameException extends Exception{
    String modelName;
    String message;
    public DuplicateModelNameException(String text,String name){
        modelName=name;
        message=text;
    }
}
