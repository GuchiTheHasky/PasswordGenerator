package guchi.the.hasky.generator;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

public class Main {
    private static void printMenu(){
        System.out.println("""
                    Menu:
                    1. Generate password.
                    0. Exit.""");
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PasswordGenerator generator = new PasswordGenerator();

        while (true){
            printMenu();
            int action = -1;

            try {
                action = Integer.parseInt(reader.readLine());
                if (action == 0){
                    break;
                }
                System.out.println("Input password size(min 8):");
                int size = Integer.parseInt(reader.readLine());
                if (!generator.checkSize(size)){
                    System.out.println("Error, wrong dara.");
                }
                else {
                    ByteArrayOutputStream arrayOutputStream = generator.getPassword(size);
                    System.out.println("Password is: " + (arrayOutputStream.toString()));
                }
            }catch (Exception e) {
                System.out.println("Error, numbers only.");
            }
        }
    }
}
