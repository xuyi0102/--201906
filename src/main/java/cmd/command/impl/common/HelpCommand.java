package cmd.command.impl.common;

import cmd.command.Command;
import cmd.command.Subject;
import cmd.command.annotation.AdminCommand;
import cmd.command.annotation.CommandMeta;
import cmd.command.annotation.CustomerCommand;
import cmd.command.annotation.EntranceCommand;
import cmd.command.impl.AbstractCommand;
import cmd.command.impl.Commands;
import cmd.entity.Account;

import java.util.*;

@CommandMeta(
        name = {"BZ"},
        desc = "帮助信息",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("帮助信息");
        Account account = subject.getAccount();
        if(account == null) {
            entranceHelp();
        }else {
            //当登录成功之后  用户看不到对应的命令信息  输入的如果是回车
            //那么在 Command getCommand  函数内 并不能返回对应信息 只会返回 CACHED_HELP_COMMAND 导致执行到这个地方
            //所以 这里需要处理。
            switch (account.getAccountType()) {
                case CUSTOMER:
                    customerHelp();
                    break;
                case ADMIN:
                    adminHelp();
                    break;
                    default:
            }
        }
    }

    public void entranceHelp() {
        printHelp("欢迎",Commands.ENTRANCE_COMMANDS.values());
    }

    public void adminHelp() {
        printHelp("管理端",Commands.ADMIN_COMMANDS.values());
    }

    public void customerHelp() {
        printHelp("客户端",Commands.CUSTOMER_COMMANDS.values());
    }

    //Map.values()方法的返回值为Collection  这里存放的是command  每个command都有CommandMeta
    public void printHelp(String title, Collection<Command> collection) {
        System.out.println("***********"+title+"*************");

        //组装：将分组作为key值   将描述desc和name作为value值
        //   key=入口命令    value  登录(DL LOGIN)
        Map<String, List<String>> helpInfo = new HashMap<>();
        for (Command command : collection) {
            CommandMeta commandMeta = command.getClass().getDeclaredAnnotation(CommandMeta.class);
            String group = commandMeta.group();
            List<String> funcs = helpInfo.get(group);
            if(funcs == null) {
                funcs = new ArrayList<>();
                helpInfo.put(group,funcs);
            }
            funcs.add(commandMeta.desc() + "(" + join(commandMeta.name()) + ")");
        }

        int i = 0;
        //Map中采用Entry内部类来表示一个映射项，映射项包含Key和Value
        //Map.Entry里面包含getKey()和getValue()方法
        //entrySet 键-值 对的集合
        for (Map.Entry<String, List<String>> entry : helpInfo.entrySet()) {
            i++;
            System.out.println(i + ". " + entry.getKey());//公共命令
            int j = 0;
            for (String item : entry.getValue()) {
                System.out.println("\t" + (i) + "." + (j) + " " + item);
            }
        }
        System.out.println("输入菜单括号后面的编号（忽略大小写），进行下一步操作");
        System.out.println("*************************************************");
    }

    private String join(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String item : array) {
            sb.append(item).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
