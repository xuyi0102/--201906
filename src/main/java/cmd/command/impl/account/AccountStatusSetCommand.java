package cmd.command.impl.account;

import cmd.command.Subject;
import cmd.command.annotation.AdminCommand;
import cmd.command.annotation.CommandMeta;
import cmd.command.impl.AbstractCommand;
@CommandMeta(
        name = {"QTZH"},
        desc = "启停账号",
        group = "账号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("启停账号");
    }
}
