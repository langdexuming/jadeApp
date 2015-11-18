/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.langdexuming.jadeapp.data_class;

/**
 *
 * @author langdexuming
 */
public enum Machine_typeid {
    R0705("E8X-R0705TP-V",1),
    R1001("E8W-R1001TC",2),
    R0707("E8W-R0707TC-V",3),
    D0701("E8X-D0701TJ",4),
    D0304("E8X-D0304TJ",5),
    D0109("E8X-D0109TM",6),
    D0108("E8X-D0108TM",7);
    /**/
    public String name;
    public int index;
    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private Machine_typeid(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public String getName(){
        return name;
    }

    // 普通方法
    public static String getName(int index) {
        for (Machine_typeid c : Machine_typeid .values()) {//遍历整个枚举数据堆
            if (c.getIndex() == index) {
                return c.name;//
            }
            return null;
        }
        return null;
    }

}
