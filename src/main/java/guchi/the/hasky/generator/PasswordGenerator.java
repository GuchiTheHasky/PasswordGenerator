package guchi.the.hasky.generator;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {
    private final List<String> passwords = new ArrayList<>();

    public boolean checkSize(int size){
        return size >= 8;
    }

    private void writeInFile(ByteArrayOutputStream stream) throws IOException {
        passwords.add(stream.toString());
        FileWriter writer = new FileWriter("output.txt");
        for(String password : passwords) {
            writer.write("Password: " + password + "\n");
        }
        writer.close();
    }

    public ByteArrayOutputStream getPassword(int length) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Basket[] baskets = new Basket[3];
        baskets[0] = new Basket('0', '9');
        baskets[1] = new Basket('a', 'z');
        baskets[2] = new Basket('A', 'Z');

        int size = 0;
        for (; size < 5; size++) {
            int index = (int) (Math.random() * 3);
            generateChar(stream, baskets, index);
        }
        for (int i = 0; i < baskets.length; i++) {
            generateChar(stream, baskets, i);
            size++;
        }
        while (size < length) {
            int index = (int) (Math.random() * 3);
            generateChar(stream, baskets, index);
            size++;
        }
        writeInFile(stream);
        return stream;
    }

    private static void generateChar(ByteArrayOutputStream is, Basket[] baskets, int index) {
        Basket basket = baskets[index];
        is.write((char) basket.getRnd());
    }

    private static class Basket {
        int begin;
        int quantity;

        public Basket(char begin, char end) {
            this.begin = begin;
            this.quantity = end - begin + 1;
        }
        public int getRnd() {
            return ((int) (Math.random() * quantity) + begin);
        }
    }


}

