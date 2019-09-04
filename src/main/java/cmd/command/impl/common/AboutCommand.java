package cmd.command.impl.common;

import cmd.command.Subject;
import cmd.command.annotation.AdminCommand;
import cmd.command.annotation.CommandMeta;
import cmd.command.annotation.CustomerCommand;
import cmd.command.annotation.EntranceCommand;
import cmd.command.impl.AbstractCommand;
@CommandMeta(
        name = {"GY","ABOUT"},
        desc = "关于系统",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class AboutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("关于系统");
        System.out.println("作者：许怡");
        System.out.println("时间：20190618");
    }
}
