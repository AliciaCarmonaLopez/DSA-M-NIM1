package edu.upc.dsa.Services;

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
import java.util.ArrayList;
import java.util.List;
@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductsService {
    private ProductManager pm;
    public ProductsService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm == null) {
            pm.addProduct("B001", "Coca cola", 2);
            pm.addProduct("C002", "Café amb gel", 1.5);
            pm.addProduct("A002", "Donut", 2.25);
            pm.addProduct("A003", "Croissant", 1.25);
        }
    }

    @GET
    @ApiOperation(value = "order products by price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/")
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySales() {

        List<Product> products = this.pm.productsBySales();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }
    @POST
    @ApiOperation(value = "add a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/{id}/{name}/{surname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("id") String id, @PathParam("name") String name, @PathParam("surname") String surname) {
        User u = new User(id, name, surname);
        this.pm.addUser(id, name, surname);
        return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "add a new Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/{id}/{name}/{price}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(@PathParam("id") String id, @PathParam("name") String name, @PathParam("price") double price) {
        Product p = new Product(id, name, price);
        this.pm.addProduct(id, name, price);
        return Response.status(201).entity(p).build();
    }

    @POST
    @ApiOperation(value = "add a new Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Order.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOrder(Order o) {
        this.pm.addOrder(o);
        return Response.status(201).entity(o).build();
    }

    @PUT
    @ApiOperation(value = "process order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack() {

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
