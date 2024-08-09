/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sale.model.repository;

import com.sale.model.entity.Order;
import com.sale.model.entity.dao.ProductOrderDAO;
import com.sale.model.repository.util.PostgresWrapper;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maria
 */
public class OrderDB implements Serializable {

    @Inject
    private EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    public void save(Order customer) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveProductOrder(ProductOrderDAO productOrder) {
        String query = "SELECT adicionar_produto_pedido(?, ?, ?, ?);";
        PostgresWrapper pw = new PostgresWrapper();
        pw.openPostgresConnection();

        try ( Connection connection = pw.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productOrder.getClientId());
            preparedStatement.setInt(2, productOrder.getProductId());
            preparedStatement.setString(3, productOrder.getProductSize());
            preparedStatement.setInt(4, productOrder.getProductQuantity());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
