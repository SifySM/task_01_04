package edu.vsu.ru;

public class Main {
    public static void main(String[] args) {
        example1();
        example2();
        example3();
        example4();
    }

    private static void example1() {
        System.out.println("Example 1: ");
        DateTime dateTime = new DateTime(2022, 3, 5, 12,5, 39);

        System.out.println(dateTime.plusYear(4).plusDays(4).plusHours(5).toString(Date.Format.TheDDthNameOfMonthYYYY_EN));
        System.out.println();
    }

    private static void example4() {
        System.out.println("Example 4: ");
        DateTime dateTime = new DateTime(2022, 3, 5, 12,5, 39);

        System.out.println(dateTime.minusMonth(7).minusDays(6).minusHours(24).minusMinutes(40).minusSeconds(40).toString(Date.Format.DDNameOfMonthYYYY_RU));
        System.out.println();
    }

    private static void example2() {
        System.out.println("Example 2: ");
        DateTime dateTime = new DateTime();
        if (dateTime.set(5, 9, 4 , -2, 7, 555)) {
            //дальнейшие действия
        } else {
            writeError();
        }
        System.out.println();
    }

    private static void example3() {
        System.out.println("Example 3: ");

        DateTime dateTime1 = new DateTime();
        DateTime dateTime2 = new DateTime();

        dateTime1.fromString("28.05.2003 18:00");
        dateTime2.fromString("29.05.2004", "17:00:05");

        System.out.println("dateTime1 - dateTime2 = " + DateTime.compare(dateTime1, dateTime2));
        System.out.println();
    }

    private static void writeError() {
        System.out.println("Введённые данные некорректны");
    }
}
