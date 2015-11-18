package com.example.langdexuming.jadeapp.data_class;

import com.example.langdexuming.jadeapp.data_class.Machine;

/**
 * Created by langdexuming on 2015/10/24 00:07
 * Email:langdexuming_x@163.com.
 */
public class RoomMachine extends Machine {
    public RoomMachine() {
        type= Machine_type.room;
    }

    public RoomMachine(String name) {
        this.name=name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
