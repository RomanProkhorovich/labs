package task5;

public class Transport {
    public static double getAvarege(ITransport transport){
        double[] mas=transport.getPriceOfAllModels();
        int count=transport.getModelsCount();
        double sum=0;
        for (double i:mas) {
            sum+=i;
        }
        return sum/count;
    }

    public static void printInfo(ITransport transport){
        System.out.println(transport);

    }
}
