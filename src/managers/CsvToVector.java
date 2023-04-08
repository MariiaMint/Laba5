package managers;
import beginningClasses.*;
import managers.CommandExecutor;
import managers.CommandManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static managers.Printer.print;

public class CsvToVector {
    public static void csvToVector(String csvFile, Vector collection){
        FileReader fr = null;
        try {
            fr = new FileReader(csvFile);
        } catch (FileNotFoundException e) {
            print("Файл не найден");;
        }
        Scanner scan = new Scanner(fr);

        while (scan.hasNextLine()) {
            String[] st = scan.nextLine().split("; ");

            HumanBeing t = new HumanBeing();
            t.setId(parseInt(st[0]));
            t.setName(st[1]);
            t.setCoordinates(new Coordinates(parseDouble(st[2].split(",")[0]), parseDouble(st[2].split(",")[1])));
            t.setCreationDate(LocalDateTime.parse(st[3]));
            t.setRealHero(parseBoolean(st[4]));
            t.setHasToothpick(parseBoolean(st[5]));
            if (st[6].equals("null")) {t.setImpactSpeed(null);}
            else {t.setImpactSpeed(parseDouble(st[6]));}
            if (st[7].equals("null")) {t.setWeaponType(null);}
            else {t.setWeaponType(WeaponType.valueOf(st[7]));}
            if (st[8].equals("null")) {t.setMood(null);}
            else {t.setMood(Mood.valueOf(st[8]));}
            t.setCar(new Car(st[9].split(",")[0], parseBoolean(st[9].split(",")[1])));

            collection.add(t);
        }
        scan.close();
    }
}
