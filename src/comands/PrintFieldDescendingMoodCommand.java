package comands;

import managers.CommandExecutor;

public class PrintFieldDescendingMoodCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public PrintFieldDescendingMoodCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.printFieldDescendingMood();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}

