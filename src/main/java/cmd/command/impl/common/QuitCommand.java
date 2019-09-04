package cmd.command.impl.common;

import cmd.command.Subject;
import cmd.command.annotation.AdminCommand;
import cmd.command.annotation.CommandMeta;
import cmd.command.annotation.CustomerCommand;
import cmd.command.annotation.EntranceCommand;
import cmd.command.impl.AbstractCommand;

@CommandMeta(
        name = {"TC"},
        desc = "退出系统",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("退出系统,欢迎下次使用");
        this.scanner.close();
        System.exit(0);//正常退出
    }
}
