import java.io.IOException;
import java.util.Scanner;

//В работе есть исключения на случаи предусмотренные заданием. Некоторые исключения по умолчанию - их не трогал
//некоторых не было - создал.
public class Main {
    private static boolean isRoman(char c){
        if (c == 'I' | c == 'V' | c == 'X')
            return true;
        else return false;
    }
    private static int romanToInteger (String roman) {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
        }
        return -1;
    }
    private static String integerToRoman(int num){
        //число соответсвует индексу латинской записи
        String[] romanNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return romanNums[num];
    }
    public static String calc(String inputString) throws IOException{
        //пришлось инициализировать некоторые переменные, так как среда ругалась
        int num1, num2, operators = 0, res = 111;
        char op = '1';

        //удаление всех пробельных символов
        inputString = inputString.replaceAll("\\s+", "");

        if (inputString.lastIndexOf("+") != -1){
            op = '+';
            operators++;
        } else if (inputString.lastIndexOf("-") != -1) {
            op = '-';
            operators++;
        } else if (inputString.lastIndexOf("/") != -1) {
            op = '/';
            operators++;
        } else if (inputString.lastIndexOf("*") != -1) {
            op = '*';
            operators++;
        }

        //проверка наличия оператора или их количества большего, чем нужно
        if (operators != 1) throw new IOException();

        String[] nums = inputString.split("[+/*-]");

        //проверка соответсвия систем счисления
        if (isRoman(nums[0].charAt(0)) == isRoman(nums[1].charAt(0))){
            if (isRoman(nums[0].charAt(0))){
                num1 = romanToInteger(nums[0]);
                num2 = romanToInteger(nums[1]);
            }
            else {
                num1 = Integer.parseInt(nums[0]);
                num2 = Integer.parseInt(nums[1]);
            }
        }
        //исключение так как разные системы счисления
        else throw new IOException();
        //проверка соответствия введенных значений условию от 1 до 10
        if (num1 < 1 | num2 < 1 | num1 > 10 | num2 > 10) throw new IOException();
        //произведение операции согласно оператору в исходном выражении
        switch (op){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            case '*':
                res = num1 * num2;
                break;
        }
        if ((isRoman(nums[0].charAt(0)) & res < 1) | res == 111)throw new IOException();

        if (isRoman(nums[0].charAt(0))) return integerToRoman(res);
        else return Integer.toString(res);
    }
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        String ex = in.nextLine();
        System.out.println(calc(ex));
    }
}
