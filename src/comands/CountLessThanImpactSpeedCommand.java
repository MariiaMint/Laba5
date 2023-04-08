package comands;

import managers.CommandExecutor;

public class CountLessThanImpactSpeedCommand implements Command{
        CommandExecutor commandExecutor;
        String description;
        String name;

        public CountLessThanImpactSpeedCommand(CommandExecutor commandExecutor, String description, String name) {
            this.commandExecutor = commandExecutor;
            this.description = description;
            this.name = name;
        }

        @Override
        public void execute(String par) {
            commandExecutor.countLessThanImpactSpeed(par);
        }
        public void description(){
            System.out.println(name + ": " + description);
        }
}
