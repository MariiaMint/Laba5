package managers;
import java.io.*;
import java.util.*;
import java.util.Vector;
import beginningClasses.*;
import comands.Command;
import static beginningClasses.HumanBeing.*;
import static managers.CommandManager.execute;
import static managers.FileScanner.fileScanner;
import static managers.Printer.print;
import static managers.Validator.*;
import static managers.Validator.etoDouble;

public class CommandExecutor {

    static Vector<HumanBeing> collection = new Vector<>();
    String csvFile;
    Scanner scanner;


    public CommandExecutor(Vector collection, String csvFile, Scanner scanner) {
        this.collection = collection;
        this.csvFile = csvFile;
        this.scanner = scanner;
    }

    //HELP
    public void help() {
        for (Command element : CommandManager.getCommands().values()) {
            element.description();
        }
    }

    // EXIT
    public void exit() {
        print("Завершаемся...");
        scanner.close();
        System.exit(0);
    }

    //ADD
    public void add() {

        HumanBeing human = creatingHuman(collection, scanner);
        collection.add(human);
    }

    //SAVE
    public void save() {
        try {
            FileWriter fw = new FileWriter(csvFile);
            StringBuilder sb = new StringBuilder();
            // Append strings from array
            for (HumanBeing element : collection) {
                sb.append(element.toString());
            }
            fw.write(sb.toString());
            fw.close();

        } catch (IOException e) {
            print("");
        }
    }

    //SHOW
    public void show(){
        print("id; name; coordinates; creationDate; realHero; hasToothpick; impactSpeed; weaponType; mood; carName; carCool");
        for (HumanBeing obj : collection){
            System.out.print(obj.toString());
        }
    }

    //remove_first
    public void removeFirst(){
        collection.removeElementAt(0);
    }

    //clear
    public void clear(){
        collection.removeAllElements();
    }

    //print_field_descending_mood
    public void printFieldDescendingMood(){
        int apathy = 0;
        int sorrow = 0;
        int sadness = 0;
        for (HumanBeing obj : collection){
            if (obj.getMood() == Mood.APATHY){
                apathy += 1;
            }
            else if(obj.getMood() == Mood.SADNESS){
                sadness += 1;
            }
            else if(obj.getMood() == Mood.SORROW){
                sorrow += 1;
            }
        }
        for (int i = 0; i < sorrow; i++) {
            print("SORROW");
        }
        for (int i = 0; i < sadness; i++) {
            print("SADNESS");
        }
        for (int i = 0; i < apathy; i++) {
            print("APATHY");
        }
    }

    //count_less_than_impact_speed impactSpeed
    public void countLessThanImpactSpeed(String strIs){
        Double impactSpeed;
        while(true) {
            if (strIs.isBlank()) {
                print("Введите число");
                strIs = scanner.nextLine();
            } else {
                try {
                    impactSpeed = Double.parseDouble(strIs);
                    break;
                } catch (NumberFormatException e) {
                    print("скорость должна быть числом, введите ее правильно");
                    strIs = scanner.nextLine();
                }
            }
        }
        int number = 0;
        for (HumanBeing obj: collection){
            if (!(obj.getImpactSpeed() == null)){
                if (obj.getImpactSpeed() < impactSpeed){number += 1;}
            }
        }
        print("количество элементов, значение поля impactSpeed которых меньше заданного равно " + number);
    }

    //removeBId id
    public void removeBId(String strId){
        int id = id(strId,scanner);
        boolean removed = false;
        for (HumanBeing obj: collection){
            if (obj.getId() == id){
                collection.removeElement(obj);
                removed = true;
                break;
            }
        }
        if (removed){
            print("Человек удален");
        }
        else {
            print("Нет человека с таким id");
        }
    }

    //update id
    public void update(String strId){
        int id = id(strId, scanner);
        boolean here = false;
        for (HumanBeing person:collection) {
            if(person.getId() == id){
                here = true;
                List<String> pars = Arrays.asList("name", "coordinates", "realHero", "hasToothpick", "impactSpeed", "weaponType", "mood", "car");
                String par = "";
                while (!par.equals("stop")) {
                    print("выберите что из перечисленного вы хотите изменить(вводите по одному слову параметр)" + pars.toString());
                    print("чтобы закончить изменение введите 'stop'");
                    par = scanner.nextLine();
                    while (!(pars.contains(par)) && !par.equals("stop")) {
                        print("выберите что из перечисленного вы хотите изменить(вводите по одному слову параметр)" + pars.toString());
                        par = scanner.nextLine();
                    }
                    switch (par) {
                        case "name" -> {
                            print("Введите имя человека");
                            person.setName(onlyLetters(scanner));
                        }
                        case "coordinates" -> createCoordinates(person, scanner);
                        case "realHero" -> {
                            print("Является ли человек настоящим героем {yes/no}");
                            boolean realhero = yesNo(scanner);
                            person.setRealHero(realhero);
                        }
                        case "hasToothpick" -> person.setHasToothpick(yesNo(scanner));
                        case "impactSpeed" -> {
                            print("Введите скорость человека(число или пустая строка)");
                            Double impactSpeed = etoDouble(true, scanner);
                            person.setImpactSpeed(impactSpeed);
                        }
                        case "weaponType" -> {
                            print("Каким оружием обладает ваш человек(введите слово из данного списка\n AXE\n HAMMER\n PISTOL\n RIFLE");
                            person.setWeaponType(weaponType(scanner));
                        }
                        case "mood" -> {
                            print("Какое настроение у человека(введите слово из данного списка либо пустую строку)\n SADNESS\n SORROW\n APATHY");
                            person.setMood(mood(scanner));
                        }
                        case "car" -> creatCar(person, scanner);
                    }
                }
            }
        }
        if (!here){
            print("Человека с таким id нет");
        }
    }

    //info
    public void info() {
        print("Информация о коллекции:");
        print("\tТип: Vector");
        print("\tКласс объектов: HumanBeing");
        print("\tКоличество элементов: " + collection.size());
        print("\tВремя инициализации: " + collection.firstElement().creationDateToString());

    }

    //sort
    public void sort(){
        Collections.sort(collection);
    }

    //print_descending
    public void printDescending(){
        Vector<HumanBeing> vector = new Vector<>(collection);
        Collections.sort(vector, new IdComparator());
        print(vector.toString());
    }

    //add_if_max
    public void addIfMax() {
        HumanBeing person = creatingHuman(collection, scanner);
        Vector<HumanBeing> vector = new Vector<>();
        vector = collection;
        Collections.sort(vector);
        Vector<HumanBeing> vector2 = new Vector<>();
        vector2.add(person);
        vector2.add(vector.elementAt(vector.size() - 1));
        Collections.sort(vector2);
        if (vector2.elementAt(vector2.size() - 1) == person) {
            collection.add(person);
        }
    }

    //execute_script
    public void executeScript(String file){
        ArrayList<String> script = fileScanner(file);
        for (String command: script){
            String[] str = command.split(" ");
            if (str[0].equals("execute_script")){
                print("В файле есть команда, которая приводит к рекурсии, ее мы не выполним");
            }
            else if (str.length == 1) {
                execute(str[0], "");
            } else if (str.length == 2) {
                execute(str[0], str[1]);
            }
        }
    }
}





