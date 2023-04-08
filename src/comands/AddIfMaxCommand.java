package comands;
import managers.CommandExecutor;

public class AddIfMaxCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public AddIfMaxCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.addIfMax();
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}