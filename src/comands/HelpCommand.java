package comands;

import managers.CommandExecutor;

public class HelpCommand implements Command {
    CommandExecutor commandExecutor;
    String description;
    String name;

    public HelpCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.help();
    }

    public void description() {
        System.out.println(name + ": " + description);
            }
}

