package comands;

import managers.CommandExecutor;

public class ClearCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public ClearCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.clear();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
