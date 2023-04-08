package comands;

import managers.CommandExecutor;

public class ExitCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;
    public ExitCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.exit();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
