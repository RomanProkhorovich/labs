package task3;

public class ModelPriceOutOfBoundsException extends Exception{
    double modelpPice;
    String message;
    public ModelPriceOutOfBoundsException(String text,double price){
        modelpPice =price;
        message=text;
    }
}
