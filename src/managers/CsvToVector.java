package managers;
import beginningClasses.*;
import managers.CommandExecutor;
import managers.CommandManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import static managers.Printer.print;

public class CsvToVector {
    public static void csvToVector(String csvFile, Vector collection, Scanner scanner){
        FileReader fr = null;
        Scanner scan = null;
        try {
            fr = new FileReader(csvFile);
        } catch (FileNotFoundException e) {
            print("Файл не найден, проверьте права доступа или путь к файлу и запустите программу заново");
            System.exit(0);
        }catch (NullPointerException e){print("Переменная окружения CSV задана не корректно, задайте ее и запустите программу заново");
            System.exit(0);
        }

        try {
            scan = new Scanner(fr);
        }catch (NullPointerException e){print("Переменная окружения CSV задана не корректно, задайте ее и запустите программу заново");
        System.exit(0);}

        Set<Integer> ids = new HashSet<>();
        while (scan.hasNextLine()) {
            String[] st = scan.nextLine().split("; ");
            try {
                HumanBeing t = new HumanBeing();
                t.setId(parseInt(st[0]));
                if (ids.contains(parseInt(st[0])) || parseInt(st[0]) < 0){
                    throw new NumberFormatException();
                }else {ids.add(parseInt(st[0]));}
                t.setName(st[1]);
                t.setCoordinates(new Coordinates(parseDouble(st[2].split(",")[0]), parseDouble(st[2].split(",")[1])));
                t.setCreationDate(LocalDateTime.parse(st[3]));
                t.setRealHero(parseBoolean(st[4]));
                t.setHasToothpick(parseBoolean(st[5]));
                if (st[6].equals("null")) {
                    t.setImpactSpeed(null);
                } else {
                    t.setImpactSpeed(parseDouble(st[6]));
                }
                if (st[7].equals("null")) {
                    t.setWeaponType(null);
                } else {
                    t.setWeaponType(WeaponType.valueOf(st[7]));
                }
                if (st[8].equals("null")) {
                    t.setMood(null);
                } else {
                    t.setMood(Mood.valueOf(st[8]));
                }
                if (!(st[9].split(",")[0].equals("null"))) {
                    t.setCar(new Car(st[9].split(",")[0], parseBoolean(st[9].split(",")[1])));
                } else {
                    t.setCar(new Car(null, parseBoolean(st[9].split(",")[1])));
                }
                collection.add(t);
            }catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                print("В файле есть некорректные данные. Хотите его очистить и продолжить(yes) или исправите его и запустите программу заново(no)?(yes или no)");
                boolean yn = Validator.yesNo(scanner);
                if (!yn) {
                    System.exit(0);
                }
                else{
                    CommandExecutor commandExecutor = new CommandExecutor(collection,csvFile,scan);
                    commandExecutor.clear();
                    break;
                }
            }
        }
        scan.close();
    }
}
