package cmd.service;

import cmd.dao.BaseDao;
import cmd.dao.OrderDao;
import cmd.entity.Order;

import java.util.List;
public class OrderService extends BaseDao {
    private GoodsService goodsService;

    private OrderDao orderDao;

    public OrderService() {
        this.goodsService = new GoodsService();
        this.orderDao = new OrderDao();
    }

    public List<Order> queryOrderByAccount(Integer accountId) {
        return this.orderDao.queryOrderByAccount(accountId);
    }

    public boolean commitOrder(Order order) {
        //提交订单
        return this.orderDao.insertOrder(order);
    }
}
