package Personpack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonBase {
    ArrayList<People> recordpersons = new ArrayList<>();

    public ArrayList<People> getRecordpersons() {
        return recordpersons;
    }

    public void setrecordpersons(ArrayList<People> recordpersons) {
        this.recordpersons = recordpersons;
    }

    public People createPerson() {
        Scanner input = new Scanner(System.in);
        PersonBuilder person = PersonBuilder.getInstance();
        System.out.println("Введите фамилию: ");
        person.setsurname(input.nextLine());
        System.out.println("Введите имя: ");
        person.setname(input.nextLine());
        System.out.println("Введите телефон: ");
        person.setphone(input.nextLine());
        System.out.println("Выберите роль:\nАдминистратор - 1\nПользователь - другое");
        if ((input.nextInt()) == 1) {
            person.setrole(Role.admin);
        } else {
            person.setrole(Role.user);
        }
        return person.build();
    }

    public void fillRecord(ArrayList<People> record) {
        boolean flag = true;
        while (flag) {
            System.out.println("Вы хотите добавить нового человека? 1 - Да, 2 - Нет");
            Scanner input = new Scanner(System.in);
            int a = input.nextInt();
            if (a == 1) {
                record.add(createPerson());
            } else if (a == 2) {
                System.out.println("База людей сохранена");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setrecordpersons(record);
    }

    public void outputToTxtFile(String filename, ArrayList<People> record) throws IOException {
        filename = filename + ".txt";
        FileWriter fileWriter = new FileWriter(filename);

        for (People x : record) {
            fileWriter.write(x.toString());
            fileWriter.write("\n");
        }
        fileWriter.flush();
    }

    public ArrayList<People> inputFile(String filename) throws IOException {
        String[] parsing;
        ArrayList<People> output = new ArrayList<>();
        java.io.File file = new File(filename + ".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");

            People person = new People();
            person.setName(parsing[0]);

            person.setSurname(parsing[1]);

            person.setPhoneNumber(parsing[2]);

            person.setRole(Role.valueOf(parsing[3]));

            output.add(person);
        }
        return output;
    }
    public People Worker(ArrayList<People> pb) {
        String a;
        System.out.println("Введите Ваше имя");
        Scanner input = new Scanner(System.in);
        a = input.nextLine();
        for (People person : pb) {
            if (person.getName().contains(a)) {
                return person;
            }
        }
        return null;
    }
}