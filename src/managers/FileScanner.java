package managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static managers.Printer.print;

public class FileScanner {
    public static ArrayList<String> fileScanner(String filepath){
        ArrayList<String> script = new ArrayList<>();
        try{
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String comm = scanner.nextLine();
                script.add(comm);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            print("Файл не найден");
        }
        return script;
    }
}
