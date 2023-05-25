package list.listreader;

import java.util.ArrayList;

/**
 * Manages the ArrayList with the List objects
 */
public class ListRegister {
    private static ListRegister register;
    private ArrayList<List> lists;

    private ListRegister() {
        //List of all the Items
        lists = CsvManager.loadData();
    }

    public static ListRegister getRegister() {
        if (register == null) {
            register = new ListRegister();
        }
        return register;
    }

    public ArrayList<List> getList() {
        return lists;
    }

    public static void setRegister(ListRegister register) {
        ListRegister.register = register;
    }

    public void setList(ArrayList<List> lists) {
        this.lists = lists;
    }

    public void loadRegister() {
        this.lists = CsvManager.loadData();
    }
}

