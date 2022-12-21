import task5.*;

import java.time.Instant;
import java.util.Date;

public class Program {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {

        ITransport car= new Motocycle("Bib",10);
        car.addNewModel("Ika",95);
        car.addNewModel("Ika",95);
        System.out.println("после добавления мотоцикла:");
        Transport.printInfo(car);

        car.changeModelName("Ika","Bibika");
        System.out.println("после изменения имени:");
        Transport.printInfo(car);

        car.deleteModel("Bibika");
        System.out.println("после удаления мотоцикла:");
        Transport.printInfo(car);

        System.out.println(Transport.getAvarege(car));
    }
}
