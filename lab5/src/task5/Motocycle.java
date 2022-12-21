package task5;

import java.io.Serializable;
import java.util.Random;

import static task5.Car.generateRandomString;

public class Motocycle implements ITransport {

    private class Model implements Serializable,Cloneable {
        private String nameOfModel = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model(String name, double Price) {
            nameOfModel = name;
            price = Price;
        }

        public Model(String name, double Price, Model _next, Model _prev) {
            nameOfModel = name;
            price = Price;
            next = _next;
            prev = _prev;
        }

        @Override
        public boolean equals(Object obj) {
            return nameOfModel.equals(((Model) obj).nameOfModel) && price == ((Model) obj).price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }

    private int size = 0;
    private String mark;
    private Model head;

    private transient long lastModified;

    public int getModelsCount() {
        return size;
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {
        Model current = head;
        do {
            if (current.nameOfModel.equals(name)) {
                return current.price;
            }
            current = current.next;

        }
        while (current != head);
        throw new NoSuchModelNameException("модели с таким именем не сущетсвует", name);
    }

    public String getMark() {
        return mark;
    }

    {
        lastModified = System.currentTimeMillis();
    }


    public Motocycle(String name, int count)  {
        mark = name;
        Random rnd = new Random();
        for (int i = 0; i < count - 1; i++) {
            try {
                addNewModel(generateRandomString(6), rnd.nextDouble() * 1000);
            }
            catch (DuplicateModelNameException e){
                throw new RuntimeException();
            }

        }

    }

    public void setModelName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (!checkUniqueName(newName))
            throw new DuplicateModelNameException("модели с таким именем уже существует", name);
        Model current = head;
        boolean flag = false;
        do {
            if (current.nameOfModel.equals(name)) {
                current.nameOfModel = newName;
                flag = true;
                lastModified = System.currentTimeMillis();
            }
            current = current.next;
        }
        while (current != head && !flag);
        if (!flag) throw new NoSuchModelNameException("нет модели с таким названием", name);

    }

    public void addNewModel(String name, double price) throws DuplicateModelNameException {
        if (price <= 0) throw new ModelPriceOutOfBoundsException("f", price);
        if (head == null) {
            head = new Model(name, price);
            head.next = head;
            head.prev = head;
            size = 1;
        } else if (checkUniqueName(name)) {
            Model last = head.prev;
            Model newModel = new Model(name, price, head, last);
            last.next = newModel;
            head.prev = newModel;
            size++;
            lastModified = System.currentTimeMillis();
        } else throw new DuplicateModelNameException("модель с таким названием уже существует", name);
    }

    private void addNewModel(Model m) throws DuplicateModelNameException {
        addNewModel(m.nameOfModel,m.price);
    }

    public void deleteModel(String name) throws NoSuchModelNameException {
        Model deleted = null;
        boolean flag = false;
        Model current = head;
        do {
            if (current.nameOfModel.equals(name)) {
                deleted = current;
                flag = true;
            } else current = current.next;
        }
        while (current != head && !flag);
        if (flag) {
            if (deleted == head)
                head = head.next;
            Model next = deleted.next;
            Model prev = deleted.prev;

            deleted.next.prev = prev;
            deleted.prev.next = next;
            size--;
            lastModified = System.currentTimeMillis();
        } else throw new NoSuchModelNameException("нет модели с таким названием", name);
    }

    public void setModelPrice(String name, double newPrice) throws NoSuchModelNameException {
        Model current = head;
        boolean flag = false;
        if (newPrice <= 0) throw new ModelPriceOutOfBoundsException("", newPrice);
        do {
            if (current.nameOfModel.equals(name)) {
                current.price = newPrice;
                flag = true;
                lastModified = System.currentTimeMillis();
            }
            current = current.next;
        }
        while (current != head && !flag);
        if (!flag) throw new NoSuchModelNameException("не существует модели с таким названием", name);
    }

    public void setMark(String newName) {
        mark = newName;
        lastModified = System.currentTimeMillis();
    }

    public String[] getNameOfAllModels() {
        String[] res = new String[size];
        Model current = head;
        int index = 0;
        do {
            res[index] = current.nameOfModel;
            current = current.next;
            index++;
        }
        while (current != head);
        return res;
    }

    public double[] getPriceOfAllModels() {
        double[] res = new double[size];
        Model current = head;
        int index = 0;
        do {
            res[index] = current.price;
            current = current.next;
            index++;
        }
        while (current != head);
        return res;
    }

    private boolean checkUniqueName(String name) {
        Model current = head;
        do {
            if (current.nameOfModel.equals(name)) {
                return false;
            }
            current = current.next;
        }
        while (current != head);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Model current = head;
        do {
            sb.append(current.nameOfModel).append(" ").append(current.price).append("\n");
            current = current.next;
        }
        while (current != head);
        return sb.toString();
    }

    @Override
    public Motocycle clone() throws CloneNotSupportedException {
        try {
            Motocycle newMoto = (Motocycle) super.clone();
            newMoto.head=null;
            Model cur=head;
            do {
                newMoto.addNewModel(cur.clone());
                cur=cur.next;
            }
            while (cur!=head);

            return newMoto;
        } catch (DuplicateModelNameException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int hashCode() {
        return mark.length() + getModelsCount();
    }

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Motocycle)  || !((Motocycle) obj).getMark().equals(mark) ||
                ((Motocycle) obj).getModelsCount() != getModelsCount()) {
            return false;
        }
        Model cur = head;
        Model objCur = ((Motocycle) obj).head;
        do {
            if (!cur.equals(objCur))
                return false;
            cur = cur.next;
            objCur = objCur.next;
        }
        while (!cur.equals(head));
        return true;
    }
}
