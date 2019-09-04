package cmd.command.impl;

import cmd.command.Command;
import cmd.service.AccountService;
import cmd.service.GoodsService;
import cmd.service.OrderService;
public abstract class AbstractCommand implements Command {

    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;
    public AbstractCommand() {
        this.accountService = new AccountService();
        this.goodsService = new GoodsService();
        this.orderService = new OrderService();
    }
}
