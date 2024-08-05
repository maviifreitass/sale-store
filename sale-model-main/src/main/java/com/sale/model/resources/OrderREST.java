package com.sale.model.resources;

import com.sale.model.entity.Order;
import com.sale.model.entity.Payment;
import com.sale.model.repository.OrderDB;
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

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest request, Order order) {
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Produtos não informados").build();
        }

        if (!order.getPayment().equals(Payment.CREDIT) && (order.getQuota() != null || order.getQuotaValue() != 0)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Parcelas são permitidas apenas para pagamento no crédito").build();
        }

        if (order.getPayment().equals(Payment.CREDIT)) {
            if (order.getQuota() == null || order.getQuota() == 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Parcela não informada").build();
            } else if (order.getQuotaValue() <= 20) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Parcela não pode ser igual ou inferior a R$ 20,00").build();
            }
        }

        order.setId(Long.valueOf(OrderDB.getInstance().getOrderList().size() + 1));
        OrderDB.getInstance().getOrderList().add(order);
        return Response.status(Response.Status.CREATED)
                .entity(order)
                .build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@Context HttpServletRequest request) {
        List<Order> orders = OrderDB.getInstance().getOrderList();

        return Response.ok(orders).build();
    }
}
