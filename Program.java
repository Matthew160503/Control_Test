import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Program {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) { 
            flag = controller();
        }
    }

    public static boolean controller(){
        System.out.println("\nВы попали в реестр домашних животных.Введите номер желаемого действия:\n"+
                           "1 - Завести новое животное\n2 - Определить животное в правильный класс\n"+
                           "3 - Увидеть список команд, которое выполняет животное\n4 - обучить животное"+
                           "новым командам\n5 - выйти из программы\n");

        try (FileWriter writer = new FileWriter("Allanimals.txt", true);
             BufferedReader reader = new BufferedReader(new FileReader("Allanimals.txt"));
             Counter count = new Counter(0)){
            int operationNum = Integer.parseInt(sc.next());
            switch (operationNum) {
            case 1:
                count.add();
                Animal animal1 = addAnimal();
                writer.write(animal1.toString());
                return true;
            case 2:
                Animal animal2 =getAnimal(reader);
                checkTypeOfAnimal(animal2);
                return true;
            case 3:
                System.out.println("Команды этого питомца:");
                Animal animal3 = getAnimal(reader);
                System.out.println(animal3.getStringCommands());
                return true;
            case 4:
                Animal animal4 = getAnimal(reader);
                System.out.println("Введите комманды, которые выучил питомец через запятую без пробелов");
                String newCommands = ","+sc.next();
                String allCommands = ((animal4.getStringCommands()).replace("[","")).replace("]","").replace(" ","")+newCommands;
                animal4.newCommands(allCommands);
                writer.write(animal4.toString());
                return true;
            case 5:
                System.out.println("Вы успешно вышли из программы!\n");
                return false;
            default:
                System.out.println("Операции под данным номером не существует.Попробуйте еще раз!\n");
                return true;
        }
        } catch (CloseCounterException e){
            System.out.println(e.getMessage());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Введено не число. Попробуйте еще раз!!!\n");
            return true;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return true;
        }
        
    }

    public static Animal addAnimal(){
        System.out.println("Введите тип животного:");
        String type = sc.next();
        System.out.println("Введите имя животного:");
        String name = sc.next();
        System.out.println("Введите команды которые умеет это животное через запятую, без пробелов");
        String[] commands = sc.next().split(",");

        Animal newAnimal = new Animal(type, name, commands);
        return newAnimal;
    }
    
    public static void checkTypeOfAnimal(Animal anim){
        try (FileWriter writer = new FileWriter(String.format("%s.txt", anim.getAnimalType()), true)){
            System.out.printf("Питомец %s зачислен в класс %s\n", anim.getName(),anim.getAnimalType());         
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Animal getAnimal(BufferedReader reader) throws IOException{
        String last = "", line;
        while (null != (line = reader.readLine())) {
            last = line;
        }
        String[] temp = last.split(";");
        String temporary = (temp[2].replace("[","")).replace("]","");
        String[] animCommands = temporary.split(", ");
        Animal animal = new Animal(temp[0], temp[1], animCommands);
        return animal;
    }
}