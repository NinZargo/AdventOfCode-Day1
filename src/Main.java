import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Day1Parser();
        Day1Optimized("newData.txt");
    }

    public static void Day1Parser(){
        FileWriter writer;
        String record;

        try (BufferedReader bin = new BufferedReader(new FileReader("data.txt"))) {
            writer = new FileWriter("newData.txt", false);

            while ((record = bin.readLine()) != null) {
                writer.write(convertStringToNumber(record) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Failed" + ioe);
        }
    }

    public static String convertStringToNumber(String input) {
        HashMap<String, String> numberWords = new HashMap<>();
        numberWords.put("zero", "0");
        numberWords.put("one", "1");
        numberWords.put("two", "2");
        numberWords.put("three", "3");
        numberWords.put("four", "4");
        numberWords.put("five", "5");
        numberWords.put("six", "6");
        numberWords.put("seven", "7");
        numberWords.put("eight", "8");
        numberWords.put("nine", "9");

        for (String word : numberWords.keySet()) {
            input = input.replaceAll(word, numberWords.get(word));
        }

        return input;
    }

    public static void Day1Optimized(String filename) {
        try (BufferedReader bin = new BufferedReader(new FileReader(filename))) {
            String record;
            int temp, length, total = 0;

            while ((record = bin.readLine()) != null) {
                String clean = record.replaceAll("\\D+", ""); // extracts only numbers
                System.out.println(record);
                System.out.println(clean);
                length = clean.length();

                temp = switch (length) {
                    case 1 -> Integer.parseInt(clean) + Integer.parseInt(clean) * 10;
                    case 2 -> Integer.parseInt(clean);
                    default -> (clean.charAt(0) - '0') * 10 + (clean.charAt(length - 1) - '0'); // subtracting '0' turns character to Integer
                };
                System.out.println(temp);
                total += temp;
            }

            System.out.println(filename + " Total : " + total);
        } catch (IOException ioe) {
            System.out.println(filename + " Failed " + ioe);
        }
    }

}