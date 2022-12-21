package task5;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    double modelpPice;
    String message;
    public ModelPriceOutOfBoundsException(String text,double price){

        modelpPice =price;
        message=text;
    }
}
