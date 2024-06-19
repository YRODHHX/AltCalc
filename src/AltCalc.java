import java.util.Scanner;

public class AltCalc {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите строку");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char action;
        String[] data;
        if (s.contains(" + ")) {
            data = s.split(" \\+ ");
            action = '+';
        } else if (s.contains(" - ")) {
            data = s.split(" - ");
            action = '-';
        } else if (s.contains(" * ")) {
            data = s.split(" \\* ");
            action = '*';
        } else if (s.contains(" / ")) {
            data = s.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действий");
        }
        if (action == '*' || action == '/') {
            if (!isNumeric(data[1])) {
                throw new Exception("Строчку можно делить или умножать только на число");
            }
            int operand = Integer.parseInt(data[1]);
            if (operand > 1 && operand < 10) {
                throw new IllegalArgumentException("Число должно быть от 1 до 10");
            }
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        if (action == '+') {
            printInQuotes(data[0] + data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            String result = "";
            if (multiplier >= 1 && multiplier <= 10) {
                for (int i = 0; i < multiplier; i++) {
                    result += data[0];
                }
            } else {
                throw new IllegalArgumentException("Число должно быть от 1 до 10");
            }
            printInQuotes(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index) + data[0].substring(index + data[1].length());
                printInQuotes(result);
            }
        } else {
            int divisor = Integer.parseInt(data[1]);
            if (divisor < 1 || divisor > 10) {
                throw new IllegalArgumentException("Число должно быть от 1 до 10");
            }
            int newLen = data[0].length() / divisor;
            String result = data[0].substring(0, newLen);
            printInQuotes(result);
        }
    }

    static void printInQuotes(String text) {
        if (text.length() > 40) {
            System.out.println("...");
        } else {
            System.out.println("\"" + text + "\"");
        }
    }

    static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}