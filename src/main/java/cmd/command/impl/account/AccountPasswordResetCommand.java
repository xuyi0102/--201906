package cmd.command.impl.account;

import cmd.command.Subject;
import cmd.command.annotation.AdminCommand;
import cmd.command.annotation.CommandMeta;
import cmd.command.impl.AbstractCommand;
@CommandMeta(
        name = {"CZMM"},
        desc = "重置密码",
        group = "账号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("密码重置");
    }
}
