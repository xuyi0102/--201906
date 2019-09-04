package cmd.command.impl.entrance;

import cmd.command.Command;
import cmd.command.Subject;
import cmd.command.annotation.CommandMeta;
import cmd.command.annotation.EntranceCommand;
import cmd.command.impl.AbstractCommand;
import cmd.common.AccountStatus;
import cmd.entity.Account;
@CommandMeta(
        name = {"DL","LOGIN"},
        desc = "登录",
        group = "入口命令"
)
@EntranceCommand
public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("登录");
        Account account = subject.getAccount();
        if(account != null) {
            System.out.println("已经登录过了");
            return;
        }
        System.out.println("请输入用户名：");
        String username = Command.scanner.nextLine();
        System.out.println("请输入密码：");
        String password = Command.scanner.nextLine();
        //去数据查询是否有此账户，有返回，无返回null
        account = this.accountService.login(username,password);

        if(account != null && account.getAccountStatus() == AccountStatus.UNLOCK) {
            System.out.println(account.getAccountType().getDesc()+"登录成功");
            subject.setAccount(account);
        }else {
            System.out.println("登录失败，密码或者用户名错误");
        }
    }
}
