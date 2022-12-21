package task4;

public class NoSuchModelNameException extends Exception{
    String modelName;
    String message;
    public NoSuchModelNameException(String text,String name){
        modelName=name;
        message=text;
    }
}
