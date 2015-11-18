/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.langdexuming.jadeapp.data_class;

import com.example.langdexuming.jadeapp.R;

/**
 *
 * @author langdexuming
 */
public class Machine {
    /*是否自动补全机器信息*/
    public boolean isAutoMakeUpInfo;
    /*机器类型*/
    public enum Machine_type{baseMachine,room,
        door,
        center}
    /*Machine数据*/
    public String name;
    public Machine_typeid typeid;
    public Machine_type type;
    public String IpAdress;
    public String localAdress;
    public int imgUrl;
    public boolean WorkState=false;
    public boolean HasCamera=false;
    public boolean isDoorMachine=false;
    public boolean isRoomMachine=false;
    public boolean isCenterMachine=false;
    
    /**/
    public Machine(String name,String typeid, Machine_type type,String Adress,String IpAdress){
        this.localAdress="地址不被透露";
    //    imgUrl=autoMatchImgUrl();

    }
    /**/
    public Machine(String name){
        this.localAdress="地址不被透露";

    }
    /**/
    public Machine(String name,Machine_typeid typeid){
        this.name=name;
        this.typeid=typeid;
        if(typeid==Machine_typeid.D0108||typeid==Machine_typeid.D0109||typeid==Machine_typeid.D0304||typeid==Machine_typeid.D0701){
            isDoorMachine=true;
        }
        this.localAdress="地址不被透露";
        imgUrl=autoMatchImgUrl(typeid.getIndex());//通过Machine_typeid的getIndex()索引
    }

    /**/
    public Machine(Machine_typeid typeid){
         this.name="我的设备"+typeid.name;
        this.typeid=typeid;
        this.localAdress="地址不被透露";
        imgUrl=autoMatchImgUrl(typeid.getIndex());//通过Machine_typeid的getIndex()索引

    }
    public int getImgUrl(int index){
        return this.imgUrl;
    }
   /**/
    public String getLocalAdress(){

        return this.localAdress;
    }
    /**/
    public String getName(){

        return this.name;
    }
    /**/
    public Machine(){
        name="普通机器";//显式初始化
        type=Machine_type.baseMachine;//显示初始化
    }
    /**/
    public boolean IsWork(){
        return WorkState;
    }
    /**/
    public boolean HasCamera(Machine machine){
        return HasCamera;
    }
    public String getTypeId(){
        return this.typeid.getName();
    }
   public int getImgUrl(){
       return this.imgUrl;
   }
    private int autoMatchImgUrl(int typeid_index){
        /*多种实现方式*/
        /*1.1*/
        switch(typeid_index){
            case 1:
                imgUrl=R.mipmap.ic_product1_1;
                break;
            case 2:
                imgUrl=R.mipmap.ic_product1_2;
                break;
            case 3:
                imgUrl=R.mipmap.ic_product1_3;
                break;
            case 4:
                imgUrl=R.mipmap.ic_product1_4;
                break;
            case 5:
                imgUrl=R.mipmap.ic_product1_5;
                break;
            case 6:
                imgUrl=R.mipmap.ic_product1_6;
                break;
            case 7:
                imgUrl=R.mipmap.ic_product1_7;
                break;
            default:
                return 0;
                
        }
        return imgUrl;
    }
}

