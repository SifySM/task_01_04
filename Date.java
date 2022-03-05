package edu.vsu.ru;

public class Date {
    private int daysOfOurEra;

    public Date(int daysOfOurEra) {
        this.daysOfOurEra = daysOfOurEra;
    }
    public Date(int day, int month, int year) {
        if (!set(year, month, day)) {
            throw new IllegalArgumentException();
        }
    }
    public Date() {
        this.daysOfOurEra = 0;
    }


    public int getYears() {
        int year = daysOfOurEra / 365 + 1;
        int n = 0;
        while (year > 1460) {
            n++;
            year = year - 1460 - 1;
        }
        return daysOfOurEra / 365 + 1 - n;
    }

    public int getMonth() {
        return extractQuanOfDaysInAllFreeMonths() / 28 + 1;
    }

    public int getDaysOfMonth() {
        int year1 = daysOfOurEra / 365;
        int n = 0;
        while (year1 > 1460) {
            n++;
            year1 = year1 - 1460 - 1;
        }

        int year = daysOfOurEra / 365 - n;

        int day = daysOfOurEra - year * 365 - extractQuanOfDaysInAllFreeMonths();

        if (year >= 1600) day -= (int) Math.ceil(((double) year - 1600) / 400);

        for (int i = 1; i < year; i++) {
            if ((i % 4 == 0) && (i % 100 != 0)) {
                day -= 1;
            }
        }
        return day;
    }

    public int getDaysOfOurEra() {
        return daysOfOurEra;
    }

    public void set(int daysOfOurEra) {
        this.daysOfOurEra = daysOfOurEra;
    }

    public boolean set(int day, int month, int year) {
        if ((month < 0 || day < 0 || month > 12 || day > dayInMonth(month))) {
            return false;
        }


        daysOfOurEra = 365 * (year - 1);

        if (year >= 1600) {
            daysOfOurEra += (int) Math.ceil(((double) year - 1600) / 400);
        }

        daysOfOurEra += (year - 1) / 4 - year / 100;

        if ((year % 4 == 0) && (year % 100 != 0) && (month > 2)) {
            daysOfOurEra += 1;
        }

        for (int i = 1; i < month; i++) {
            daysOfOurEra += dayInMonth(i);
        }

        daysOfOurEra += day;

        return true;
    }


    public boolean fromString(String str) {
        final char[] DELIMITERS = {':', '.', '-', '/', '*'};
        int regexIndex = -1;
        int strLength = str.length();

        for (int i = 0; i < strLength; i++) {
            for (char delimiter : DELIMITERS) {
                if (str.charAt(i) == delimiter) {
                    regexIndex = i;
                    i = strLength;
                    break;
                }
            }
        }

        String regex = (String.valueOf(str.charAt(regexIndex)));

        String[] date = str.split(new StringBuilder().append("\\").append(regex).toString());

        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        return set(day, month, year);
    }


    public static int compare(Date date1, Date date2) {
        return date1.daysOfOurEra - date2.daysOfOurEra;
    }


    public void plusDays(int days) {
        daysOfOurEra += days;
    }

    public void minusDays(int days) {
        daysOfOurEra -= days;
    }

    public Date plusMonth(int month) {
        int nowMonth = getMonth();

        for (int i = nowMonth; i < nowMonth + month; i++) {
            daysOfOurEra += dayInMonth(i);
        }
        return this;
    }

    public Date minusMonth(int month) {
        int nowMonth = getMonth();

        for (int i = 0; i < month; i++) {
            if (nowMonth == 0){
                nowMonth = 12;

            }
            daysOfOurEra -= dayInMonth(nowMonth);
            nowMonth--;
        }

        return this;
    }

    public Date plusYear(int years) {
        int nowYear = getYears();

        for (int i = nowYear; i < nowYear + years; i++) {
            daysOfOurEra += 365;

            if ((i % 4 == 0) && (i % 100 != 0)) {
                daysOfOurEra += 1;
            }

            if ((nowYear + years) % 400 == 0) {
                if ((nowYear + years) >= 1600) {
                    daysOfOurEra -= (int) Math.ceil(((nowYear + years) - 1600) / 400);
                }
            }
        }

        return this;
    }

    public Date minusYear(int years) {
        int nowYear = getYears();
        for (int i = nowYear; i < nowYear + years; i++) {
            daysOfOurEra -= 365;
            if ((i % 4 == 0) && (i % 100 != 0)) {
                daysOfOurEra -= 1;
            }
            if ((nowYear + years) % 400 == 0) {
                if ((nowYear + years) >= 1600) {
                    daysOfOurEra -= (int) Math.ceil(((nowYear + years) - 1600) / 400);
                }
            }
        }
        return this;
    }



    public String toString(String regex, Format format) {
        int year = getYears();
        int month = getMonth();
        int day = getDaysOfMonth();

        switch (format) {
            case DDMMYYYY -> {
                return day + regex + month + regex + year;
            }
            case DDMMYY -> {
                year %= 100;
                return day + regex + month + regex + year;
            }
            case YYYYMMDD -> {
                return year + regex + month + regex + day;
            }
            case YYMMDD -> {
                year %= 100;
                return year + regex + month + regex + day;
            }
            case TheDDthNameOfMonthYYYY_EN -> {
                String[] namesOfMonth = {"January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"};

                return "The " + day + "th of " + namesOfMonth[month - 1] + ", " + year;
            }
            case DDMonthYYYY_EN -> {
                String[] namesOfMonth = {"January" +
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"};

                return day + " " + namesOfMonth[month - 1] + " " + year;
            }
            case DDMonYYYY_EN -> {
                String[] namesOfMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Ju", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                return day + " " + namesOfMonth[month - 1] + " " + year;
            }
            case DDNameOfMonthYYYY_RU -> {
                String[] namesOfMonth = {"Января", "Февраля", "Марта", "Апреля", "Maя", "Июня", "Июля", "Августа", "Сентября",
                        "Октября", "Ноября", "Декбря"};

                return day + " " + namesOfMonth[month - 1] + " " + year + " года,";
            }
            default -> throw new IllegalStateException("Unexpected value: " + format);
        }
    }

    public String toString(Format format) {
        return toString(".", format);
    }

    public String toString() {
        return toString(Format.DDMMYYYY);
    }


    private static int dayInMonth(int i) {
        return (int) (28 + (i + Math.floor(i / 8)) % 2 + 2 % i + 2 * Math.floor(1 / i));
    }

    private int extractQuanOfDaysInAllFreeMonths() {
        int quanDaysInMonths = 0;
        int year = daysOfOurEra / 365;
        int year1 = daysOfOurEra / 365;
        int n = 0;
        while (year1 > 1460) {
            n++;
            year1 = year1 - 1460 - 1;
        }
        year -= n;

        int daysAreNotIncludedInWholeYear = daysOfOurEra - year * 365;
        if (year >= 1600) {
            daysAreNotIncludedInWholeYear -= (int) Math.ceil(((double)year - 1600) / 400);
        }

        daysAreNotIncludedInWholeYear = daysAreNotIncludedInWholeYear - (year - 1) / 4 + year / 100;

        for (int i = 1; i < 12; i++) {
            int dayInMonth = dayInMonth(i);
            if (daysAreNotIncludedInWholeYear > dayInMonth) {
                quanDaysInMonths += dayInMonth;
                daysAreNotIncludedInWholeYear -= dayInMonth;
                if ((i == 2) && ((year + 1) % 4 == 0) && ((year + 1) % 100 != 0)) {
                    quanDaysInMonths++;
                    daysAreNotIncludedInWholeYear--;
                }
            } else break;
        }
        return quanDaysInMonths;
    }

    public enum Format {
        DDMMYYYY,
        YYYYMMDD,
        DDMMYY,
        YYMMDD,
        DDNameOfMonthYYYY_RU,
        TheDDthNameOfMonthYYYY_EN,
        DDMonYYYY_EN,
        DDMonthYYYY_EN
    }
}
/* private static int convertFromDateToNumber (double year, int month, int day, Boolean ok) {
        if (!(month < 0 || day < 0 || month > 12 || day > dayInMonth(month))) {
            ok = false;
            return 0;
        }
        ok = true;
        int daysOfOurEra = 365*((int) year - 1);

        if (year >= 1600) {
            daysOfOurEra += (int) Math.ceil((year - 1600)/400);
        }

        daysOfOurEra += (year - 1) / 4 - year / 100;

        if ((year % 4 == 0) && (year % 100 != 0) && (month > 2)) {
            daysOfOurEra += 1;
        }

        for (int i = 1; i < month; i++) {
            daysOfOurEra += dayInMonth(i);
        }

        daysOfOurEra += day;

        return daysOfOurEra;
    }*/
