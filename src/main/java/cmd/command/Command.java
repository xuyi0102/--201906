package cmd.command;

import java.io.Console;
import java.util.Scanner;
public interface Command {

    //每个命令都会创建Scanner对象 在这里直接创建
    Scanner scanner = new Scanner(System.in);
    //统一调用此方法
    void execute(Subject subject);
}
