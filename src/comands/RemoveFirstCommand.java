package comands;

import managers.CommandExecutor;

public class RemoveFirstCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public RemoveFirstCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.removeFirst();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
