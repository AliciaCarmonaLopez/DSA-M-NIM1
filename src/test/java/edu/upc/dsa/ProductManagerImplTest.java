package edu.upc.dsa;
import edu.upc.dsa.models.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductManagerImplTest {
    ProductManager pm;
    @Before
    public void setUp() throws Exception{
        this.pm = new ProductManagerImpl();
        pm.addProduct("B001", "Coca cola", 2);
        pm.addProduct("C002", "Café amb gel", 1.5);
        pm.addProduct("A002", "Donut", 2.25);
        pm.addProduct("A003", "Croissant", 1.25);

        pm.addUser("1111111", "Juan", "lopez");
        pm.addUser("2222222", "David", "Rincon");
        pm.addUser("3333333", "Juan", "Hernández");

    }

    @After
    public void tearDown() {
        this.pm =null;
    }

    @Test
    public void testRealizarPedido() throws Exception {
        Assert.assertEquals(0, this.pm.getColaComandas().num());
        Order o1 = new Order("1111111");
        o1.addLP(3, "B001");
        o1.addLP(2, "C002");
        this.pm.addOrder(o1);
        Order o2 = new Order("1111111");
        o2.addLP(3, "A002");
        o2.addLP(1, "B001");
        this.pm.addOrder(o2);
        Assert.assertEquals(2, this.pm.getColaComandas().num());

    }

    @Test
    public void testServirPedido() throws Exception {
        Assert.assertEquals(0, this.pm.getColaComandas().num());
        Assert.assertEquals(0, this.pm.getSalesByName("B001"));
        Assert.assertEquals(0, this.pm.getSalesByName("C002"));
        Order o1 = new Order("1111111");
        o1.addLP(3, "B001");
        o1.addLP(2, "C002");
        this.pm.addOrder(o1);
        Order o2 = new Order("1111111");
        o2.addLP(3, "A002");
        o2.addLP(1, "B001");
        this.pm.addOrder(o2);
        Assert.assertEquals(2, this.pm.getColaComandas().num());
        Order o3 = this.pm.processOrder();
        Assert.assertEquals(o1, o3);
        Assert.assertEquals(1, this.pm.getColaComandas().num());
        Assert.assertEquals(3, this.pm.getSalesByName("B001"));
        Assert.assertEquals(2, this.pm.getSalesByName("C002"));
        Assert.assertEquals(o1, this.pm.getListaUsersH().get("1111111").getListaComandasU().get(0));
    }
}
