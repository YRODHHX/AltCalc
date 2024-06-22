import java.util.Scanner;

public class AltCalc {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите строку");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        calculate(s);
    }

    static void calculate(String s) throws Exception {
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
            validateNumericOperand(data[1]);
        }

        removeQuotes(data);

        if (action == '+') {
            printInQuotes(concatenateStrings(data[0], data[1]));
            if (data[0].length() > 10) {
                throw new IllegalArgumentException("Длина строки не должна превышать 10 символов");
            }
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            multiplyString(data[0], multiplier);
            if (data[0].length() > 10) {
                throw new IllegalArgumentException("Длина строки не должна превышать 10 символов");
            }
        } else if (action == '-') {
            subtractString(data[0], data[1]);
            if (data[0].length() > 10) {
                throw new IllegalArgumentException("Длина строки не должна превышать 10 символов");
            }
        } else {
            int divisor = Integer.parseInt(data[1]);
            divideString(data[0], divisor);
            if (data[0].length() > 10) {
                throw new IllegalArgumentException("Длина строки не должна превышать 10 символов");
            }
        }
    }

    static void removeQuotes(String[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }
    }

    static void validateNumericOperand(String operand) throws Exception {
        if (!isNumeric(operand)) {
            throw new Exception("Строчку можно делить или умножать только на число");
        }
        int value = Integer.parseInt(operand);
        if (value < 1 && value > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }
    }

    static void printInQuotes(String text) {
        if (text.length() > 40) {
            System.out.println("\"" + text.substring(0, 40) + "..." + "\"");
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

    static String concatenateStrings(String str1, String str2) {
        return str1 + str2;
    }

    static void multiplyString(String str, int multiplier) {
        if (multiplier >= 1 && multiplier <= 10) {
            String result = "";
            for (int i = 0; i < multiplier; i++) {
                result += str;
            }
            printInQuotes(result);
        } else {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }
    }

    static void subtractString(String str1, String str2) {
        int index = str1.indexOf(str2);
        if (index == -1) {
            printInQuotes(str1);
        } else {
            String result = str1.substring(0, index) + str1.substring(index + str2.length());
            printInQuotes(result);
        }
    }

    static void divideString(String str, int divisor) {
        if (divisor < 1 && divisor > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }
        int newLen = str.length() / divisor;
        String result = str.substring(0, newLen);
        printInQuotes(result);
    }
}