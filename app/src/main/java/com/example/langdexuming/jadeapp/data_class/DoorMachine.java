package com.example.langdexuming.jadeapp.data_class;

/**
 * Created by langdexuming on 2015/10/24 00:30
 * Email:langdexuming_x@163.com.
 */
public class DoorMachine extends Machine {
    public DoorMachine() {
        type= Machine_type.door;
    }

    public DoorMachine(String name) {
        this.name=name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
