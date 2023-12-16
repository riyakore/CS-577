import java.util.Scanner;
//import java.io.File;

public class HelloWorld {
    public static void main(String[] args) {

        //File file = new File(filepath);
        Scanner scnr = new Scanner(System.in);
        int number = scnr.nextInt();
        scnr.nextLine();

        for (int i = 0; i < number; i++) {
            String name = scnr.nextLine();
            System.out.println("Hello, " + name + "!");
        }
    }
}