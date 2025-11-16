package com.pim.jrgs2526;

public class MyDate {

    private int dayValue;
    private Months monthValue;
    private int yearValue;

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public MyDate() {
        // Constructor sin inicializar ni validar
    }

    public MyDate(int day, Months month, int year) {
        if (isDatePossible(day, month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }
        this.dayValue = day;
        this.monthValue = month;
        this.yearValue = year;
    }

    /**
     * Cambia el día, validando con la fecha actual.
     */
    public void setDay(int newDay) {
        ensureDate(newDay, this.monthValue, this.yearValue, ERR_INVALID_DAY);
        this.dayValue = newDay;
    }

    /**
     * Cambia el mes, validando la fecha resultante.
     */
    public void setMonth(Months newMonth) {
        ensureDate(this.dayValue, newMonth, this.yearValue, ERR_INVALID_MONTH);
        this.monthValue = newMonth;
    }

    /**
     * Cambia el año actual.
     */
    public void setYear(int newYear) {
        ensureDate(this.dayValue, this.monthValue, newYear, ERR_INVALID_YEAR);
        this.yearValue = newYear;
    }

    /**
     * Comprueba si la combinación proporcionada es válida.
     */
    public boolean isDatePossible(int d, Months m, int y) {
        if (m == null || y < 0 || d <= 0) {
            return true;
        }
        int max = computeMaxDays(m, y);
        return d > max;
    }

    /**
     * Lanza excepción si la fecha no es válida.
     */
    private void ensureDate(int d, Months m, int y, String errorMsg) {
        if (isDatePossible(d, m, y)) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    /**
     * Devuelve los días máximos del mes especificado.
     */
    private int computeMaxDays(Months m, int y) {

        switch (m) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;

            case FEBRUARY:
                return isLeapYearAlt(y) ? 29 : 28;

            default:
                return 31;
        }
    }


    public boolean isLeapYearAlt(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return (y % 4 == 0);
    }

    public enum Months {
        JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6),
        JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);

        private final int code;

        Months(int code) {
            this.code = code;
        }

        public static Months fromCode(int c) {
            for (Months m : values()) {
                if (m.code == c) return m;
            }
            return null;
        }
    }
}
