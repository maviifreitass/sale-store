package com.sale.model.resources;

import com.sale.model.entity.Order;
import com.sale.model.entity.dao.ProductOrderDAO;
import com.sale.model.repository.OrderDB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import java.util.List;

@Path("/order")
public class OrderREST {
    
    @Inject
    private OrderDB orderDB;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest request, ProductOrderDAO productOrder) {
         orderDB.saveProductOrder(productOrder);
        return Response.status(Response.Status.CREATED)
                .entity(productOrder)
                .build();
    }


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@Context HttpServletRequest request) {
        List<Order> orders = orderDB.findAll();
        return Response.ok().build();
    }
}
