package task4;

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
    public double GetPrice(String name){
        Model current=head;
        do {
            if (current.nameOfModel.equals(name)) return current.price;
            current=current.next;
        }
        while (current!=head);
        return Double.NaN;
    }
    public String getMark(){
        return mark;
    }

    public Motocycle(String name, double price) throws ModelPriceOutOfBoundsException {

        if (price<0) throw new ModelPriceOutOfBoundsException("цена не может быть отрицателньой",price);

        head=new Model(generateRandomString(6),price);
        head.next=head;
        head.prev=head;
        size=1;
        lastModified=System.currentTimeMillis();
    }
    public void changeModelName(String name,String newName) throws NoSuchModelNameException {
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
        if (flag) throw new NoSuchModelNameException("нет модели с таким названием",name);

    }
    public void addNewModel(String name, double price) throws DuplicateModelNameException {
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
            current=current.next;
        }
        while (current!=head && !flag);
        if(flag){
            deleted.prev.next=deleted.next;
            deleted.next.prev=deleted.prev;
            size--;
            lastModified=System.currentTimeMillis();
        }
        else throw new NoSuchModelNameException("нет модели с таким названием",name);
    }
    public void changePrice(String name,double newPrice) throws NoSuchModelNameException {
        Model current=head;
        boolean flag=false;
        do {
            if (current.nameOfModel.equals(name)){
                current.price=newPrice;
                flag=true;
                lastModified=System.currentTimeMillis();
            }
            current=current.next;
        }
        while (current!=head);
        if(!flag) throw new NoSuchModelNameException("не существует модели с таким названием",name);
    }
    public  void changeMark(String newName){
        mark =newName;
        lastModified=System.currentTimeMillis();
    }
    public String[] getNameOfAllModels()//попробовать оптимизировать
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


}
