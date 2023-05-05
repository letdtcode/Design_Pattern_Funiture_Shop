package com.it.design_pattern_furniture_web.controllers.client.checkout;

import com.it.design_pattern_furniture_web.models.services.order.OrderService;
import com.it.design_pattern_furniture_web.models.services.paypal.PayPalService;
import com.it.design_pattern_furniture_web.models.view_models.orders.OrderCreateRequest;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.SessionUtils;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HandleThirdPartyPayment", value = "/checkout/third-party-payment")
public class HandleThirdPartyPayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        int userId = SessionUtils.getUserIdLogin(request);
        if (userId == 0) {
            ServletUtils.redirect(response, request.getContextPath() + "/cart/items?error=true");
            return;
        }

        Payment payment = null;
        try {
            payment = PayPalService.getInstance().handlePayment(paymentId, payerId);
        } catch (PayPalRESTException e) {
            ServletUtils.redirect(response, request.getContextPath() + "/cart/items?error=true");
            return;
        }
        HttpSession session = request.getSession();
        OrderCreateRequest orderCreateRequest = (OrderCreateRequest) session.getAttribute("createOrderReq");
        boolean res = OrderService.getInstance().createOrder(request, orderCreateRequest, userId);
        if (!res) {
            ServletUtils.redirect(response, request.getContextPath() + "/cart/items?error=true");
            return;
        }
        ServletUtils.redirect(response, request.getContextPath() + "/cart/items?success=true");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
