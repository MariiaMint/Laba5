import beginningClasses.*;
import managers.CommandExecutor;
import managers.CommandManager;
import managers.CsvToVector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import static managers.Printer.print;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String csvFile = args[0];
        //String csvFile = "myfile.csv";
        Vector <HumanBeing> collection = new Vector<>();
        Scanner scanner = new Scanner(in);

        //парсер csv в Vector
        CsvToVector.csvToVector(csvFile,collection);
        CommandExecutor commandExecutor = new CommandExecutor(collection,csvFile, scanner);
        CommandManager commandManager = new CommandManager(commandExecutor);
        while (true) {
            print("Введите команду (чтобы увидеть справку по командам введите help)");
            String command = scanner.nextLine();

            try {
                String[] command_list = command.split(" ");
                if (command_list.length == 1) {
                    commandManager.execute(command_list[0], "");
                } else if (command_list.length == 2) {
                    commandManager.execute(command_list[0], command_list[1]);
                }
            } catch (NullPointerException e) {
                print("Данной команды не найдено");
            }
            catch (NoSuchElementException e){
                print("вы нажали Ctrl + D, перезапустите программу и больше так не делайте");break;}
        }
    }
}










