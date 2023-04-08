package comands;

import managers.CommandExecutor;

public class AddCommand implements  Command{
    CommandExecutor commandExecutor;
    String description;
    String name;
    public AddCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.add();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
