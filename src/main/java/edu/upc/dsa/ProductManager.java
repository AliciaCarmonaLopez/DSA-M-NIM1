package edu.upc.dsa;

import edu.upc.dsa.Queue.Queue;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface ProductManager {
    public List<Product> productsByPrice();

    public List<Product> productsBySales();
    public void addOrder(Order order);
    public Order processOrder();
    public List<Order> ordersByUser(String userId);


    public void addUser(String s, String name, String surname);
    public void addProduct(String productId, String name, double price);


    public Queue<Order> getColaComandas();

    public int getSalesByName(String s);

    public HashMap<String,User> getListaUsersH();

    public int getNumProducts();
}
