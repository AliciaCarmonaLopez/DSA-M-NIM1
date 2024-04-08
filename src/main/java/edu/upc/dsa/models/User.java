package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String s;
    private String name;
    private String surname;
    private List<Order> listaComandasU;


    public User(String s, String name, String surname){
        this.s = s;
        this.name = name;
        this.surname = surname;
        listaComandasU = new LinkedList<>();
    }
    public User(){

    }
    public void addComanda(Order o){
        listaComandasU.add(o);
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getS() {
        return s;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public List<Order> getListaComandasU() {
        return listaComandasU;
    }

    public void setListaComandasU(List<Order> listaComandasU) {
        this.listaComandasU = listaComandasU;
    }
}
