package cmd.command.impl.goods;

import cmd.command.Subject;
import cmd.command.annotation.AdminCommand;
import cmd.command.annotation.CommandMeta;
import cmd.command.annotation.CustomerCommand;

import cmd.command.impl.AbstractCommand;
import cmd.entity.Goods;


import java.util.List;
@CommandMeta(
        name = {"LLSP"},
        desc = "浏览商品",
        group = "商品信息"
)

@AdminCommand
@CustomerCommand
public class GoodsBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("商品浏览");
        List<Goods> goodsList = this.goodsService.queryAllGoods();

        if(goodsList.isEmpty()) {
            System.out.println("没有任何的商品");
        }else {
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }
}
