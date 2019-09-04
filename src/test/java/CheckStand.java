import cmd.command.Subject;
import cmd.command.impl.AbstractCommand;
import cmd.command.impl.Commands;
import cmd.entity.Account;

public class CheckStand extends AbstractCommand {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new CheckStand().execute(subject);
    }

    @Override
    public void execute(Subject subject) {
        Commands.getCachedHelpCommand().execute(subject);
        while(true) {
            System.out.println(">>");
            String line = scanner.nextLine();
            String commandCode = line.trim().toUpperCase();
            Account account = subject.getAccount();
            if(account == null) {
                Commands.getEntranceCommand(commandCode).execute(subject);
            }else {

                switch (account.getAccountType()) {
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).execute(subject);
                        break;
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).execute(subject);
                        break;
                    default:
                }
            }
        }
    }
}
