package com.blenmaterial.Beans;

/**
 * Created by Blensmile on 2016/2/22.
 */
public class ContactsBean {
    public String name;
    public int icon;
    public int message;

    public ContactsBean(String name,int icon){
        this(name,icon,0);
    }
    public ContactsBean(String name,int icon,int message){
        this.name = name;
        this.icon  = icon;
        this.message = message;
    }
}
