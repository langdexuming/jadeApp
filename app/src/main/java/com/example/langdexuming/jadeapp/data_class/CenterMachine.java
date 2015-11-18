package com.example.langdexuming.jadeapp.data_class;

/**
 * Created by langdexuming on 2015/10/24 00:30
 * Email:langdexuming_x@163.com.
 */
public class CenterMachine extends Machine {
    public CenterMachine() {
        type= Machine_type.center;
    }

    public CenterMachine(String name) {
        this.name=name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
