package com.example.langdexuming.jadeapp.data_class;
/**
 *
 * @author langdexuming
 */
public class Test {

    
    private static String name;
    private static Machine machine;
    private static String localAdress;
    private static int imgUrl;
    public static void main(String[] args){
        for(Machine_typeid c:Machine_typeid.values())
            System.out.println(c.name);
        
        /*通过设备类型查找设备名称*/
        /*1.1类型初始化*/
        // machine=new Machine(Machine_typeid.D0701);
         /*1.2姓名+类型初始化*/
        Machine machine=new Machine(Machine_typeid.R0705);
        //machine=new Machine();
         /*1.3*/
         
         /*2.获取名称*/
         name=machine.getName(); 
         localAdress=machine.getLocalAdress();
         imgUrl=machine.getImgUrl();
         System.out.println(name);
         System.out.println(machine.getTypeId());
         System.out.println(localAdress);
         System.out.println(imgUrl);
        
    }
    
}
