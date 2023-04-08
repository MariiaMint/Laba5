package comands;

import managers.CommandExecutor;

public class SortCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;
    public SortCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.sort();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
