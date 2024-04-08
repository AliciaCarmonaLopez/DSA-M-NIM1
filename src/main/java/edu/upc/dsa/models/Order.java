package edu.upc.dsa.models;

import java.util.HashMap;

public class Order {
    private String idUser;
    private HashMap<String, Integer> pedido;
    public Order (String id ){
        this.idUser = id;
        pedido = new HashMap<>();
    }
    public Order(){}

    public HashMap<String, Integer> getPedido() {
        return pedido;
    }

    public void setPedido(HashMap<String, Integer> pedido) {
        this.pedido = pedido;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void addLP(int Cantidad, String idProduct){
        pedido.put(idProduct,Cantidad);
    }
}
