package task2;
import java.util.Date;

import static task1.Car.generateRandomString;

public class Motocycle {
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
    public int getSize(){
        return size;
    }
    public double getPrice(String name){
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

    public Motocycle(String name, double price){
        head=new Model(generateRandomString(6),price);
        head.next=head;
        head.prev=head;
        size=1;
        lastModified=System.currentTimeMillis();
    }
    public void changeModelName(String name,String newName){
        Model current=head;
        do {
            if (current.nameOfModel==name){
                current.nameOfModel=newName;
                lastModified=System.currentTimeMillis();
            }
            current=current.next;
        }
        while (current!=head);
    }
    public void addModel(String name, double price){
        if(checkUniqueName(name)) {
            Model last = head.prev;
            Model newModel = new Model(name, price, head, last);
            last.next = newModel;
            head.prev = newModel;
            size++;
            lastModified = System.currentTimeMillis();
        }
    }
    public void deleteModel(String name){
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
    }
    public void changePrice(String name,double newPrice){
        Model current=head;
        do {
            if (current.nameOfModel.equals(name)){
                current.price=newPrice;
                lastModified=System.currentTimeMillis();
            }
            current=current.next;
        }
        while (current!=head);
    }
    public  void changeMark(String newName){
        mark =newName;
        lastModified=System.currentTimeMillis();
    }
    public String[] getAllModelName()//попробовать оптимизировать
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
    public double[] getAllModelPrice(){
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
