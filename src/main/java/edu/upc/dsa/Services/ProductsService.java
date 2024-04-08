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
}
