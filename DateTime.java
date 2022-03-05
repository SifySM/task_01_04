package edu.vsu.ru;

public class DateTime {
    private Date date = new Date();
    private Time time = new Time();

    public DateTime(int year, int month, int days, int hours, int minutes, int seconds) {

        if (!(set(year, month, days, hours, minutes, seconds))) {
            throw new IllegalArgumentException();
        }

    }
    public DateTime() {
        date.set(0);
        time.set(0);
    }

    public boolean set(int years, int months, int days, int hours, int minutes, int seconds) {

        if (!(date.set(days, months, years)) || (!time.set(hours, minutes, seconds))) {
            return false;
        }

        return true;
    }

    public int getYears() {
        return date.getYears();
    }

    public int getMonth() {
        return date.getMonth();
    }

    public int gatDays() {
        return date.getDaysOfMonth();
    }

    public int gatHours() {
        return time.getHours();
    }

    public int gatMinutes() {
        return time.getMinutes();
    }

    public int getSeconds() {
        return time.getSeconds();
    }


    public static long compare(DateTime dateTime1, DateTime dateTime2) {

        return (dateTime1.date.getDaysOfOurEra() - dateTime2.date.getDaysOfOurEra()) * 24 * 60 * 60 +
                (dateTime1.time.getSecondsInDay() - dateTime2.time.getSecondsInDay());
    }


    public DateTime plusYear(int years) {
        date.plusYear(years);

        return this;
    }

    public DateTime minusYear(int years) {
        date.minusYear(years);

        return this;
    }

    public DateTime plusMonth(int months) {
        date.plusMonth(months);

        return this;
    }

    public DateTime minusMonth(int months) {
        date.minusMonth(months);

        return this;
    }

    public DateTime plusDays(int days) {
        date.plusDays(days);

        return this;
    }

    public DateTime minusDays(int days) {
        date.minusDays(days);

        return this;
    }

    public DateTime plusHours(int hours) {
        time.plusHours(hours);
        int justHours = time.getHours();

        while (justHours > 24) {
            date.plusDays(1);
            time.minusHours(24);
            justHours -= 24;
        }

        return this;
    }

    public DateTime minusHours(int hours) {
        int justHours = time.getHours();

        while (justHours > 24) {
            date.plusDays(1);
            time.minusHours(24);
            justHours -= 24;
        }

        if (justHours < hours) {
            hours -= justHours;
            time.minusHours(justHours);
            date.minusDays(1);
            time.plusHours(24 - hours);
        } else {
            time.minusHours(hours);
        }
        return this;
    }

    public DateTime plusMinutes(int minutes) {
        time.plusMinutes(minutes);

        return this;
    }

    public DateTime minusMinutes(int minutes) {
        time.minusMinutes(minutes);

        return this;
    }

    public DateTime plusSeconds(int seconds) {
        time.plusSeconds(seconds);

        return this;
    }

    public DateTime minusSeconds(int seconds) {
        time.minusSeconds(seconds);

        return this;
    }


    public boolean fromString(String str) {
        String[] dataTime = str.split(" ");
        String dateStr = dataTime[0];
        String timeStr = dataTime[1];

        if ((date.fromString(dateStr)) && (time.fromString(timeStr))) {
            date.fromString(dateStr);
            time.fromString(timeStr);
            return true;
        }
        return false;
    }

    public boolean fromString(String dateStr, String timeStr) {
        if ((date.fromString(dateStr)) && (time.fromString(timeStr))) {
            date.fromString(dateStr);
            time.fromString(timeStr);
            return true;
        }
        return false;
    }


    public String toString(Date.Format format, String dateRegex, String timeRegex, boolean showSeconds) {
        return date.toString(dateRegex, format) + "   " + time.toString(timeRegex, showSeconds);
    }

    public String toString(Date.Format format, String timeRegex, boolean showSeconds) {
        return date.toString(format) + "   " + time.toString(timeRegex, showSeconds);
    }

    public String toString(String timeRegex, boolean showSeconds) {
        return date.toString() + "   " + time.toString(timeRegex, showSeconds);
    }

    public String toString(Date.Format format, String dateRegex) {
        return date.toString(dateRegex, format) + "   " + time.toString();
    }

    public String toString(Date.Format format) {
        return date.toString(format) + "   " + time.toString();
    }

    public String toString() {
        return date.toString() + "   " + time.toString();
    }
}
