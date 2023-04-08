package comands;

import managers.CommandExecutor;

public class SaveCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public SaveCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.save();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
