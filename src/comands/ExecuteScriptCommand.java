package comands;
import managers.CommandExecutor;

public class ExecuteScriptCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public ExecuteScriptCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.executeScript(par);
    }
    public void description(){
        System.out.println(name + ": " + description);
    }
}
