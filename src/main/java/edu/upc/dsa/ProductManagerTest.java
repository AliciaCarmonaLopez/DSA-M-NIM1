package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;

import java.util.List;

public class ProductManagerTest {


    public static void main(String[] args) {
        ProductManager pm;
        pm = new ProductManagerImpl();
        pm.addProduct("B001", "Coca cola", 2);
        pm.addProduct("C002", "Café amb gel", 1.5);
        pm.addProduct("A002", "Donut", 2.25);
        pm.addProduct("A003", "Croissant", 1.25);

        List<Product> products = pm.productsByPrice();
        System.out.println("Productos ordenados por precio descendente:");
        for (Product producto : products) {
            System.out.println(producto.getName());
        }

        pm.addUser("1111111", "Juan", "lopez");
        pm.addUser("2222222", "David", "Rincon");
        pm.addUser("3333333", "Juan", "Hernández");

        Order o1 = new Order("1111111");
        o1.addLP(3, "B001");
        o1.addLP(2, "C002");

        Order o2 = new Order("1111111");
        o2.addLP(3, "A002");
        o2.addLP(1, "B001");

        Order o3 = new Order("2222222");
        o3.addLP(3, "B001");
        o3.addLP(2, "A002");

        pm.addOrder(o1);
        pm.addOrder(o2);
        pm.addOrder(o3);

        Order order1 = pm.processOrder();
        System.out.println("Productos ordenados por precio descendente:"+ order1.getPedido().keySet());
        Order order2 = pm.processOrder();
        Order order3 = pm.processOrder();

        List<Product> products1 = pm.productsByPrice();
        List<Product> products2 = pm.productsBySales();
        System.out.println("Productos ordenados por precio descendente:");
        for (Product producto : products2) {
            System.out.println(producto.getSales());
        }
        List<Order> orders1 = pm.ordersByUser("1111111");
        List<Order> orders2 = pm.ordersByUser("2222222");

    }
}
