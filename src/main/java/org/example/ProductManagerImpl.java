package org.example;

import java.util.*;


public class ProductManagerImpl implements ProductManager{
    private List<Product> listaProducts;
    private HashMap<String, User> listaUsersH;
    private   Queue<Order> colaComandas;
    public ProductManagerImpl(){
        listaProducts = new ArrayList<>();
        listaUsersH = new HashMap<>();
        colaComandas = new QueueImpl<Order>(20);
    }
    @Override
    public List<Product> productsByPrice() {
        Comparator<Product> pricecomparator = Comparator.comparingDouble(Product::getPrice);
        listaProducts.sort(pricecomparator);
        return listaProducts;

    }

    @Override
    public List<Product> productsBySales() {
        Comparator<Product> salescomparator = Comparator.comparingDouble(Product::getSales);
        listaProducts.sort(salescomparator.reversed());
        return listaProducts;
    }

    @Override
    public void addOrder(Order order) {
        try{colaComandas.push(order);}
        catch(FullQueueException fullExp){
            fullExp.printStackTrace();
        }

    }

    @Override
    public Order processOrder() {
        Order o = new Order();
        try{o = colaComandas.pop();}
        catch(EmptyQueueException empExp){
            empExp.printStackTrace();
        }
        HashMap<String, Integer> h = o.getPedido();
        for(String s: h.keySet()){
            for(Product p: listaProducts){
                if(p.getProductId() == s){
                    int sales = h.get(s) + p.getSales();
                    p.setSales(sales);
                }
            }
        }
        listaUsersH.get(o.getIdUser()).añadirComanda(o);
        return o;
    }

    @Override
    public List<Order> ordersByUser(String userId) {
        return listaUsersH.get(userId).getListaComandasU();
    }

    @Override
    public void addUser(String s, String name, String surname) {
        User u = new User(s, name, surname);
        listaUsersH.put(s, u);

    }

    @Override
    public void addProduct(String productId, String name, double price) {
        Product p = new Product(productId, name, price);
        listaProducts.add(p);
    }
}
