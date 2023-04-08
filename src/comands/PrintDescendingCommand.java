package comands;

import managers.CommandExecutor;

public class PrintDescendingCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public PrintDescendingCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {commandExecutor.printDescending();}
    public void description(){
        System.out.println(name + ": " + description);
    }
}
