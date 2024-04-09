package edu.upc.dsa.services;

import io.swagger.annotations.Api;
import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Order;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductsService {
    private ProductManager pm;
    public ProductsService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.getNumProducts() == 0) {
            pm.addProduct("B001", "Coca cola", 2);
            pm.addProduct("C002", "Café amb gel", 1.5);
            pm.addProduct("A002", "Donut", 2.25);
            pm.addProduct("A003", "Croissant", 1.25);

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

            pm.addUser("1111111", "Juan", "lopez");
            pm.addUser("2222222", "David", "Rincon");
            pm.addUser("3333333", "Juan", "Hernández");
        }
    }

    @GET
    @ApiOperation(value = "order products by price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/productsByPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByPrice() {

        List<Product> products = this.pm.productsByPrice();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }
    @GET
    @ApiOperation(value = "order products by sales", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/productsBySales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySales() {

        List<Product> products = this.pm.productsBySales();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @PUT
    @ApiOperation(value = "process order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processOrder() {

        Order o = this.pm.processOrder();
        return Response.status(201).entity(o).build();
    }

    @GET
    @ApiOperation(value = "list the orders of a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Order.class, responseContainer="List"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByUser(@PathParam("id") String id) {

        List<Order> orders = this.pm.ordersByUser(id);

        GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(orders) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
