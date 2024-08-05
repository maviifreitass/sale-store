package com.sale.model.tests;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.sale.model.entity.Order;
import com.sale.model.entity.Product;
import com.sale.model.repository.ProductDB;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 *
 * @author maria
 */
public class ProductSale {

    @InjectMocks
    private ProductDB productDB;

    @Mock
    private Order order;
    private List<Product> listaTeste;

    public ProductSale() {

        this.listaTeste = new ArrayList<Product>();
        // Criando produtos
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Produto 1");
        product1.setValue(10.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Produto 2");
        product2.setValue(20.0);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Produto 3");
        product3.setValue(30.0);

        // Adicionando produtos ao pedido
        this.listaTeste.add(product1);
        this.listaTeste.add(product2);
        this.listaTeste.add(product3);

    }

    @BeforeEach
    public void setUp() {
        this.order = new Order();
    }

    @Test
    public void testCriacaoDeProduto() {
        // Simular produto para criação
        Product productToCreate = new Product();
        productToCreate.setId(1L);
        productToCreate.setName("Produto 1");
        productToCreate.setValue(7.9);
        
        assertFalse(productToCreate.getValue() <= 0, "O valor do produto não pode ser negativo ou zero");

        assertNotNull(productToCreate.getId());
        assertNotNull(productToCreate.getName());
        assertNotNull(productToCreate.getValue());
        
        productDB.getInstance().getProductList().add(productToCreate);
    }

    @Test
    public void testVerificacaoAoDeletarProduto() {
        int listSize = productDB.getInstance().getProductList().size();
        assertFalse(productDB.getInstance().getProductList().isEmpty(), "A lista de produtos está vazia");

        assertNotNull(productDB.getInstance().getProductList().get(0), "Produto inválido");

        productDB.getInstance().getProductList().remove(0);

        assertEquals(listSize - 1, productDB.getInstance().getProductList().size(), "O produto não foi removido com êxito");
    }

    @Test
    public void testVerificaRetornoAoRequisitarTodosProdutos() {
        listaTeste = productDB.getInstance().getProductList();
        assertNotNull(listaTeste, "Lista de produtos é nula");
        assertFalse(listaTeste.isEmpty(), "A lista de produtos está vazia");
    }

}
