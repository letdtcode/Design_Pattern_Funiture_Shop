package com.it.design_pattern_furniture_web.models.services.order;

import com.it.design_pattern_furniture_web.models.repositories.order.OrderRepository;
import com.it.design_pattern_furniture_web.models.view_models.order_items.OrderItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.order_items.OrderItemViewModel;
import com.it.design_pattern_furniture_web.models.view_models.orders.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderService implements IOrderService{
    private static OrderService instance = null;
    public static OrderService getInstance(){
        if(instance == null)
            instance = new OrderService();
        return instance;
    }
    @Override
    public int insertOrder(OrderCreateRequest request) {
        return OrderRepository.getInstance().insert(request);
    }

    @Override
    public boolean updateOrder(OrderUpdateRequest request) {
        return OrderRepository.getInstance().update(request);
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        return OrderRepository.getInstance().delete(orderId);
    }

    @Override
    public OrderViewModel retrieveOrderById(Integer orderId) {
        return OrderRepository.getInstance().retrieveById(orderId);
    }

    @Override
    public ArrayList<OrderViewModel> retrieveAllOrder(OrderGetPagingRequest request) {
        return OrderRepository.getInstance().retrieveAll(request);
    }

    @Override
    public ArrayList<OrderViewModel> retrieveOrderByUserId(int userId) {
        return OrderRepository.getInstance().retrieveOrderByUserId(userId);
    }

    @Override
    public ArrayList<OrderViewModel> retrieveDeliveredOrder(OrderGetPagingRequest request) {
        return OrderRepository.getInstance().retrieveDeliveredOrder(request);
    }

    @Override
    public ArrayList<OrderViewModel> retrieveNewOrder(OrderGetPagingRequest request) {
        return OrderRepository.getInstance().retrieveNewOrder(request);
    }

    @Override
    public long getTotalOrder() {
        return OrderRepository.getInstance().getTotalOrder();
    }

    @Override
    public BigDecimal getRevenue() {
        return OrderRepository.getInstance().getRevenue();
    }

    @Override
    public ArrayList<OrderViewModel> getTopOrderSoon(int top) {
        return OrderRepository.getInstance().getTopOrderSoon(top);
    }

    @Override
    public OrderOverviewViewModel getOrderOverviewStatistics() {
        return OrderRepository.getInstance().getOrderOverviewStatistics();
    }

    @Override
    public boolean createOrder(HttpServletRequest request, OrderCreateRequest orderReq, int userId) {
        return OrderRepository.getInstance().createOrder(request, orderReq, userId);
    }

    @Override
    public boolean clearOrder(int orderId) {
        return OrderRepository.getInstance().clearOrder(orderId);
    }

    @Override
    public ArrayList<OrderItemViewModel> getItemByOrderId(int orderId) {
        return OrderRepository.getInstance().getItemByOrderId(orderId);
    }

    @Override
    public int insertOrderItem(OrderItemCreateRequest request) {
        return OrderRepository.getInstance().insertOrderItem(request);
    }

    @Override
    public boolean deleteOrderItem(Integer entityId) {
        return OrderRepository.getInstance().deleteOrderItem(entityId);
    }
}
