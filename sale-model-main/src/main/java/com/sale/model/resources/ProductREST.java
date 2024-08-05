package com.sale.model.resources;

import com.sale.model.entity.Product;
import com.sale.model.repository.ProductDB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import java.util.List;

@Path("/product")
public class ProductREST {

    @Inject
    private ProductDB productDB;

    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Long id, Product updatedProduct) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O ID do produto está faltando na requisição.")
                    .build();
        }

        if (updatedProduct.getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Nome não informado").build();
        }
        if (updatedProduct.getValue() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Valor não informado").build();
        }

        ProductDB productDB = ProductDB.getInstance();
        List<Product> productList = productDB.getProductList();

        Product productToUpdate = null;
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                productToUpdate = product;
                break;
            }
        }

        if (productToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Produto com o ID fornecido não encontrado.")
                    .build();
        }

        if (updatedProduct.getName() != null) {
            productToUpdate.setName(updatedProduct.getName());
        }

        if (updatedProduct.getValue() != null) {
            productToUpdate.setValue(updatedProduct.getValue());
        }

         return Response.status(Response.Status.CREATED)
                .entity(productToUpdate)
                .build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context HttpServletRequest request, Product product) {
        if (product.getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Nome não informado").build();
        }
        if (product.getValue() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Valor não informado").build();
        }

        product.setId(Long.valueOf(productDB.getInstance().getProductList().size() + 1));
        productDB.getInstance().getProductList().add(product);

        return Response.status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id, @Context HttpServletRequest request) {
        try {
            productDB.getInstance().getProductList().remove(id);
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id inexistente").build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@Context HttpServletRequest request) {
        List<Product> productList = productDB.getInstance().getProductList();
        return Response.ok(productList).build();
    }

}
