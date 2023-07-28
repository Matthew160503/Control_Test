import java.util.Arrays;

public class Animal {
    private String animalType;
    private String name;
    private String[] commands;

    public Animal(String animalType, String name, String[] commands) {
        this.animalType = animalType;
        this.name = name;
        this.commands = commands;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }

    public String[] getCommands() {
        return commands;
    }

    public String getStringCommands(){
        return Arrays.toString(commands);
    }

    @Override
    public String toString() {
        return animalType + ";" + name + ";" + Arrays.toString(commands)+"\n";
    }

    public void newCommands(String temp){
        commands=temp.split(",");
    }
}
