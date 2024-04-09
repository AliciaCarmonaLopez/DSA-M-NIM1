package edu.upc.dsa;


import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;
import edu.upc.dsa.Queue.Queue;
import edu.upc.dsa.Queue.QueueImpl;
import edu.upc.dsa.Queue.EmptyQueueException;
import edu.upc.dsa.Queue.FullQueueException;
import java.util.*;

public class ProductManagerImpl implements ProductManager{

    private List<Product> listaProducts;
    private HashMap<String, User> listaUsersH;
    private Queue<Order> colaComandas;

    public HashMap<String,User> getListaUsersH(){return listaUsersH;}

    @Override
    public int getNumProducts() {
        return listaProducts.size();
    }

    public Queue<Order> getColaComandas(){return colaComandas;}
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);
    private static ProductManager instance;
    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }
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
        logger.info("New order: " + order);
        try{colaComandas.push(order);}
        catch(FullQueueException fullExp){

            fullExp.printStackTrace();
        }
        logger.info("New order added");
    }

    @Override
    public Order processOrder() {
        Order o = new Order();
        try{o = colaComandas.pop();}
        catch(EmptyQueueException empExp){
            logger.error("Empty order's queue");
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
        listaUsersH.get(o.getIdUser()).addComanda(o);
        logger.info("New order processed" + o);
        return o;
    }

    @Override
    public List<Order> ordersByUser(String userId) {
        return listaUsersH.get(userId).getListaComandasU();
    }

    @Override
    public void addUser(String s, String name, String surname) {
        logger.info("User with name: " + name);
        User u = new User(s, name, surname);
        listaUsersH.put(s, u);
        logger.info("New user added");

    }

    @Override
    public void addProduct(String productId, String name, double price) {
        Product p = new Product(productId, name, price);
        listaProducts.add(p);
    }

    public int getSalesByName(String productID){
        for(Product p:listaProducts){
            if(p.getProductId() == productID)
                return p.getSales();
        }
        return 0;
    }
}
