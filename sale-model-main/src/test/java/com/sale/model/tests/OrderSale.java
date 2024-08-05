package com.sale.model.tests;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.sale.model.entity.Order;
import com.sale.model.entity.Payment;
import com.sale.model.entity.Product;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 *
 * @author maria
 */
public class OrderSale {

    @Mock
    private Order order;
    private List<Product> listaTeste;

    public OrderSale() {

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
    public void testNaoPermiteRegistrarPagamentoProdutoVazio() {
        //adding products
        this.order.addProduct(this.listaTeste.get(0));
        //seting payment
        this.order.setPayment(Payment.CREDIT);
        //checking condicions
        assertNotNull(this.order.getProducts(), "failure - Order's products can not be null");
        boolean test = (this.order.getProducts().isEmpty() ? false : true);
        assertTrue(test, "failure - Order's products can not be null");
    }

    @Test
    public void testNaoPermiteParcelasIgualOuInferiorA20() {
        //adding order's products
        this.order.addProduct(this.listaTeste.get(0));
        this.order.addProduct(this.listaTeste.get(1));
        this.order.addProduct(this.listaTeste.get(2));
        //seting payments 
        this.order.setPayment(Payment.CREDIT);
        this.order.setQuota(2);
        this.order.setQuotaValue(30);
        //checking condicions
        assertEquals(30, this.order.getQuotaValue(), "failure - Order's quota can't coust less or R$:20.00");
    }

    @Test
    public void testNaoPermiteParcelasAoSelecionarCredito() {
        //adding order's products
        this.order.addProduct(this.listaTeste.get(0));
        this.order.addProduct(this.listaTeste.get(1));
        this.order.addProduct(this.listaTeste.get(2));
        this.order.setPayment(Payment.CREDIT);

        this.order.setQuota(2);
        this.order.setQuotaValue(30);
        assertEquals(Payment.CREDIT, this.order.getPayment(), "failure - Pagamento com parcelas apenas para opção no crédito");
    }

    @Test
    public void testNaoPermiteRegistrarPagamentoSemSelecionarFormaDePagamento() {
        this.order.addProduct(this.listaTeste.get(0));
        assertNull(this.order.getPayment(), "Falha - Pagamento não deve ser registrado sem selecionar uma forma de pagamento");
    }

    @Test
    public void testNaoPermiteParcelasAcimaDeLimite() {
        this.order.addProduct(this.listaTeste.get(0));
        this.order.addProduct(this.listaTeste.get(1));
        this.order.addProduct(this.listaTeste.get(2));
        this.order.setPayment(Payment.CREDIT);
        this.order.setQuota(19);
        this.order.setQuotaValue(50);
        assertEquals(19, this.order.getQuota(), "Falha - Número de parcelas não deve exceder o limite permitido");
    }

    @Test
    public void testValorDaParcelaNegativo() {
        this.order.addProduct(this.listaTeste.get(0));
        this.order.addProduct(this.listaTeste.get(1));
        this.order.addProduct(this.listaTeste.get(2));
        this.order.setPayment(Payment.CREDIT);
        this.order.setQuota(10);
        this.order.setQuotaValue(30);
        
        assertEquals(30, this.order.getQuotaValue(), "Falha - Valor da parcela não pode ser negativo");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}   
}
