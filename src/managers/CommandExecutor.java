package managers;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Vector;
import beginningClasses.*;
import comands.Command;
import static beginningClasses.HumanBeing.*;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.System.in;
import static managers.CommandManager.execute;
import static managers.FileScanner.scan;
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
                sb.append(element.toCSV());
            }
            fw.write(sb.toString());
            fw.close();

        } catch (IOException | NullPointerException e) {
            print("команда сохранить не может быть выполнена, проверьте, что у файла есть необходимые права доступа");
        }
    }

    //SHOW
    public void show(){
        print("id; name; coordinates; creationDate; realHero; hasToothpick; impactSpeed; weaponType; mood; carName; carCool");
        for (HumanBeing obj : collection){
            print(obj.toString());
        }
    }

    //remove_first
    public void removeFirst(){
        if (!collection.isEmpty()) {
            collection.removeElementAt(0);
        }
        else {print("коллекция итак пуста");}
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
                    print("выберите что из перечисленного вы хотите изменить(вводите по одному слову параметр)" + pars);
                    print("чтобы закончить изменение введите 'stop'");
                    par = scanner.nextLine();
                    while (!(pars.contains(par)) && !par.equals("stop")) {
                        print("выберите что из перечисленного вы хотите изменить(вводите по одному слову параметр)" + pars);
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
        if (!collection.isEmpty()) {
            print("\tВремя инициализации: " + collection.firstElement().creationDateToString());
        }
    }

    //sort
    public void sort(){
        Collections.sort(collection);
    }

    //print_descending
    public void printDescending(){
        ArrayList<HumanBeing> list = new ArrayList<>(collection);
        list.sort(new HumanComparator());
        Collections.reverse(list);
        print("id; name; coordinates; creationDate; realHero; hasToothpick; impactSpeed; weaponType; mood; carName; carCool");
        for (HumanBeing obj:list) {
            print(obj.toString());
        }
    }

    //add_if_max
    public void addIfMax() {
        HumanBeing person = creatingHuman(collection, scanner);
        if (!collection.isEmpty()) {
            Vector<HumanBeing> vector = new Vector<>(collection);
            Collections.sort(vector);
            Vector<HumanBeing> vector2 = new Vector<>();
            vector2.add(person);
            vector2.add(vector.elementAt(vector.size() - 1));
            Collections.sort(vector2);
            if (vector2.elementAt(vector2.size() - 1) == person) {
                collection.add(person);
            } else {
                print("Элемент не является максимальным, мы его не добавили");
            }
        }else{collection.add(person);}
    }

    //execute_script
    static Vector<String> filePaths = new Vector<>();
    public void executeScript(String arg) {
        ArrayList<String> listCommands = scan(arg);
        filePaths.add(arg);
        int m = -1;
        int i = -1;
        for (String command:listCommands) {
            m++;
            String[] st = command.split(" ");
            if (i > -1 && m-i<11){continue;}
            if (st[0].equals("add")) {
                i = m;
                try {
                    HumanBeing human = humanForScript(listCommands, i);
                    collection.add(human);
                    print(human.getName() + " добавлен");
                }catch (NumberFormatException e){print("В файле есть некорректные данные");break;}
            }
            else if (st[0].equals("add_if_max")){
                i = m;
                HumanBeing human = new HumanBeing();
                try {
                    human = humanForScript(listCommands, i);
                }catch (NumberFormatException e){print("В файле есть некорректные данные");break;}
                if (!collection.isEmpty()) {
                    Vector<HumanBeing> vector = new Vector<>(collection);
                    Collections.sort(vector);
                    Vector<HumanBeing> vector2 = new Vector<>();
                    vector2.add(human);
                    vector2.add(vector.elementAt(vector.size() - 1));
                    Collections.sort(vector2);
                    if (vector2.elementAt(vector2.size() - 1) == human) {
                        collection.add(human);
                    } else {
                        print("Элемент не является максимальным, мы его не добавили");
                    }
                }else{collection.add(human);}
            }
            else if (st[0].equals("update_by_id")){
                i = m;
                if (st.length == 2 && st[1].chars().allMatch(Character::isDigit)) {
                    HumanBeing human = new HumanBeing();
                    try {
                        human = humanForScript(listCommands, i);
                    }catch (NumberFormatException e){print("В файле есть некорректные данные");break;}
                    removeBId(st[1]);
                    human.setId(Integer.valueOf(st[1]));
                    collection.add(human);
                }else {print("В файле есть ошибка, исправьте ее");break;}
            }
            else if (st[0].equals("remove_by_id")) {
                if (st.length == 2 && st[1].chars().allMatch(Character::isDigit)) {
                    removeBId(st[1]);
                }else {print("В файле есть ошибка, исправьте ее");break;}
            }
            else {
                if (st.length == 1) {
                    execute(st[0], "");
                } else if (st.length == 2) {
                    if (st[0].equals("execute_script")) {
                        if (filePaths.contains(st[1])) {
                            print("Команда " + st[0] + " " + st[1] + " уже была выполнена, дальнейшее выполнение приведёт к рекурсии");
                        } else {
                            execute(st[0], st[1]);
                        }
                    } else {
                        execute(st[0], st[1]);
                    }
                }
            }
            filePaths.clear();
        }
    }
    private HumanBeing humanForScript(ArrayList<String> listCommands, int i) throws NumberFormatException{
        HumanBeing human = new HumanBeing();
        try {
            int id=0;
            if(!collection.isEmpty()) {
                for (HumanBeing smn : collection) {
                    id = Integer.max(id, smn.getId() + 1);
                }
            }
            human.setId(id);
            human.setCreationDate(LocalDateTime.now());
           if (listCommands.get(i + 1).chars().allMatch(Character::isLetter)) {
               human.setName(listCommands.get(i + 1));
           }
           else {throw new NumberFormatException();}
            human.setCoordinates(new Coordinates(parseDouble(listCommands.get(i + 2).replace(",", ".")), parseDouble(listCommands.get(i + 3).replace(",", "."))));
            human.setRealHero(parseBoolean(listCommands.get(i+4)));
            human.setHasToothpick(parseBoolean(listCommands.get(i+5)));
            if (listCommands.get(i+6).equals("null")) {
                human.setImpactSpeed(null);
            } else {
                human.setImpactSpeed(parseDouble(listCommands.get(i+6).replace(",", ".")));
            }
            if (listCommands.get(i+7).equals("null")) {
                human.setWeaponType(null);
            } else {
                human.setWeaponType(WeaponType.valueOf(listCommands.get(i+7)));
            }
            if (listCommands.get(i+8).equals("null")) {
                human.setMood(null);
            } else {
                human.setMood(Mood.valueOf(listCommands.get(i+8)));
            }
            if (!(listCommands.get(i+9).equals("null")) && listCommands.get(i + 9).chars().allMatch(Character::isLetter)) {
                human.setCar(new Car(listCommands.get(i+9), parseBoolean(listCommands.get(i+10))));
            } else if (listCommands.get(i+9).equals("null")){
                human.setCar(new Car(null, parseBoolean(listCommands.get(i+10))));
            }else {throw new NumberFormatException();}
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new NumberFormatException();
        }
        return human;
    }
}





