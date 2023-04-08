package comands;

import managers.CommandExecutor;

public class ShowCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public ShowCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.show();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}

