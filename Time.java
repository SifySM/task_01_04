package edu.vsu.ru;


public class Time {
    private int secondsInDay;

    public Time(int secondsInDay) {
        this.secondsInDay = secondsInDay;
    }
    public Time(int hours, int minutes, int seconds) {
        if (!set(hours, minutes, seconds)) {
            throw new IllegalArgumentException();
        }
    }
    public Time() {
        this.secondsInDay = 0;
    }

    public boolean set(int hours, int minutes, int seconds) {

        if (minutes > 0 && minutes < 60 &&
                seconds > 0 && seconds < 60 &&
                hours > 0 && hours < 24) {

            this.secondsInDay = seconds + minutes * 60 + hours * 60 * 60;
            return true;

        }

        return false;
    }

    public void set(int secondsInDay) {
        this.secondsInDay = secondsInDay;
    }

    public int getSecondsInDay() {
        return secondsInDay;
    }

    public int getHours() {
        return secondsInDay / (60 * 60);
    }

    public int getMinutes() {
        return (secondsInDay - (getHours()) * 60 * 60) / 60;
    }

    public int getSeconds() {
        return secondsInDay - (getHours() * 60 * 60) - (getMinutes() * 60);
    }

    public static int compare(Time time1, Time time2) {
        return time1.secondsInDay - time2.secondsInDay;
    }

    public Time plusHours(int hours) {
        secondsInDay += hours * 3600;

        return this;
    }

    public Time minusHours(int hours) {
        secondsInDay -= hours * 3600;

        return this;
    }

    public Time plusMinutes(int minutes) {
        secondsInDay += minutes * 60;

        return this;
    }

    public Time minusMinutes(int minutes) {
        secondsInDay -= minutes * 60;

        return this;
    }

    public Time plusSeconds(int seconds) {
        secondsInDay += seconds;

        return this;
    }

    public Time minusSeconds(int seconds) {
        secondsInDay -= seconds;

        return this;
    }


    /**
     * This method converts the time from the storage type to the standard template HH:MM:SS
     * <p>
     * <br><b>Example: </b>secondsInDay = 80 413 =>
     * <br> String time: "22:20:13"
     * <p>
     *
     * @return the time in string format given
     */
    public String toString() {
        return toString(":", true);
    }

    /**
     * This method return time from the storage type to the type specified in the input parameters
     *
     * <p>
     * <br><b>Example:</b> secondsInDay = 80 413, regex = ".", showSeconds = false => String time: - "22:20"
     * <p>
     *
     * @param regex       manual separator setting
     * @param showSeconds (don't) show seconds
     * @return the time in string format given the specified input parameters
     */
    public String toString(String regex, boolean showSeconds) {
        int hours = getHours();
        while (hours > 24) {
            hours -= 24;
        }
        String addZeroToHours = "";
        if (hours < 10) {
            addZeroToHours = "0";
        }

        int minutes = getMinutes();
        String addZeroToMinutes = "";
        if (minutes < 10) {
            addZeroToMinutes = "0";
        }

        int seconds = getSeconds();
        String addZeroToSeconds = "";
        if (seconds < 10) {
            addZeroToSeconds = "0";
        }

        if (showSeconds) {
            return addZeroToHours + hours + regex + addZeroToMinutes + minutes + regex + addZeroToSeconds + seconds;
        } else {
            return addZeroToHours + hours + regex + addZeroToMinutes + minutes;
        }
    }

    public boolean fromString(String str) {
        final char[] DELIMITERS = {':', '.', '-', '/', '*'};
        boolean isIndexOfRegex = false;

        for (int c : DELIMITERS) {
            if (str.charAt(1) == c) {
                isIndexOfRegex = true;
            }
        }

        String regex = (String.valueOf(str.charAt(2)));

        if (isIndexOfRegex) {
            regex = (String.valueOf(str.charAt(1)));
        }

        String[] time = str.split(new StringBuilder().append("\\").append(regex).toString());

        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = 0;

        if (time.length == 3) {
            seconds = Integer.parseInt(time[2]);
        }

        return set(hours, minutes, seconds);
    }
}
