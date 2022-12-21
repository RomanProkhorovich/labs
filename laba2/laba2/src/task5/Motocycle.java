package task5;

import java.util.Random;

import static task1.Car.generateRandomString;

public class Motocycle implements ITransport {
    private class Model{
        private String nameOfModel = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;
        public Model(String name,double Price){
            nameOfModel=name;
            price=Price;
        }
        public Model(String name,double Price,Model _next, Model _prev){
            nameOfModel=name;
            price=Price;
            next=_next;
            prev=_prev;
        }
    }
    private int size = 0;
    private String mark;
    private Model head;
    private long lastModified;
    public int getModelsCount(){
        return size;
    }
    public double getModelPrice(String name) throws NoSuchModelNameException {
        Model current=head;
        do {
            if (current.nameOfModel.equals(name)) {
                return current.price;
            }
                current = current.next;

        }
        while (current!=head);
        throw new NoSuchModelNameException("модели с таким именем не сущетсвует",name);
    }
    public String getMark(){
        return mark;
    }

    {
        lastModified=System.currentTimeMillis();
    }
    public Motocycle(String name, double price)  {

        if (price<0) throw new ModelPriceOutOfBoundsException("цена не может быть отрицателньой",price);

        head=new Model(generateRandomString(6),price);
        head.next=head;
        head.prev=head;
        size=1;
    }

    public Motocycle(String name, int count) throws DuplicateModelNameException {
        mark=name;
        Random rnd=new Random();
        head=new Model("standart",rnd.nextDouble()*1000);
        head.next=head;
        head.prev=head;
        size++;
        for (int i=0; i<count-1;i++){
            addNewModel(generateRandomString(6),rnd.nextDouble()*1000);
        }

    }
    public void changeModelName(String name,String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (!checkUniqueName(newName)) throw new DuplicateModelNameException("модели с таким именем уже существует",name);
        Model current=head;
        boolean flag=false;
        do {
            if (current.nameOfModel.equals(name)){
                current.nameOfModel=newName;
                flag=true;
                lastModified=System.currentTimeMillis();
            }
            current=current.next;
        }
        while (current!=head && !flag);
        if (!flag) throw new NoSuchModelNameException("нет модели с таким названием",name);

    }
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        if (price<=0) throw new ModelPriceOutOfBoundsException("f",price);
        if(checkUniqueName(name)) {
            Model last = head.prev;
            Model newModel = new Model(name, price, head, last);
            last.next = newModel;
            head.prev = newModel;
            size++;
            lastModified = System.currentTimeMillis();
        }
        else throw new DuplicateModelNameException("модель с таким названием уже существует",name);
    }
    public void deleteModel(String name) throws NoSuchModelNameException {
        Model deleted=null;
        boolean flag=false;
        Model current=head;
        do {
            if (current.nameOfModel.equals(name)){
                deleted=current;
                flag=true;
            }
            else current=current.next;
        }
        while (current!=head && !flag);
        if(flag){
            if (deleted==head)
                head=head.next;
            Model next= deleted.next;
            Model prev=deleted.prev;

            deleted.next.prev=prev;
            deleted.prev.next=next;
            size--;
            lastModified=System.currentTimeMillis();
        }
        else throw new NoSuchModelNameException("нет модели с таким названием",name);
    }
    public void changeModelPrice(String name,double newPrice) throws NoSuchModelNameException {
        Model current=head;
        boolean flag=false;
        if(newPrice<=0) throw new ModelPriceOutOfBoundsException("",newPrice);
        do {
            if (current.nameOfModel.equals(name)){
                current.price=newPrice;
                flag=true;
                lastModified=System.currentTimeMillis();
            }
            current=current.next;
        }
        while (current!=head&& !flag);
        if(!flag) throw new NoSuchModelNameException("не существует модели с таким названием",name);
    }
    public  void changeMark(String newName){
        mark =newName;
        lastModified=System.currentTimeMillis();
    }
    public String[] getNameOfAllModels()
    {
        String[] res=new String[size];
        Model current=head;
        int index=0;
        do {
            res[index]=current.nameOfModel;
            current=current.next;
            index++;
        }
        while (current!=head);
        return res;
    }
    public double[] getPriceOfAllModels(){
        double[] res=new double[size];
        Model current=head;
        int index=0;
        do {
            res[index]=current.price;
            current=current.next;
            index++;
        }
        while (current!=head);
        return res;
    }
    private boolean checkUniqueName(String name){
        Model current=head;
        do {
            if (current.nameOfModel.equals(name)){
                return false;
            }
            current=current.next;
        }
        while (current!=head);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        Model current=head;
        do {
            sb.append(current.nameOfModel + " " + current.price + "\n");
            current=current.next;
        }
        while (current!=head);
        return sb.toString();
    }
}
