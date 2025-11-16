package com.pim.jrgs2526;

public class MyDate {

    private int day;
    private Months month;
    private int year;

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public MyDate() {
        // Constructor por defecto sin validación
    }

    public MyDate(int day, Months month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setMonth(Months month) {
        if (!isValidDate(this.day, month, this.year)) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        this.month = month;
    }

    public void setYear(int year) {
        if (!isValidDate(this.day, this.month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        this.year = year;
    }

    public void setDay(int day) {
        if (!isValidDate(day, this.month, this.year)) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this.day = day;
    }

    /**
     * Comprueba si la combinación de día, mes y año forma una fecha válida.
     */
    public boolean isValidDate(int d, Months m, int y) {
        // Reglas básicas
        if (m == null || d <= 0 || d > 31 || y < 0) {
            return false;
        }

        int maxDays = daysInMonth(m, y);
        return d <= maxDays;
    }

    /**
     * Devuelve el número de días del mes indicado, teniendo en cuenta años bisiestos.
     */
    private int daysInMonth(Months m, int y) {
        // Meses de 30 días
        if (m == Months.APRIL || m == Months.JUNE ||
                m == Months.SEPTEMBER || m == Months.NOVEMBER) {
            return 30;
        }

        // Febrero depende de si el año es bisiesto
        if (m == Months.FEBRUARY) {
            return isLeapYear(y) ? 29 : 28;
        }

        // El resto tienen 31
        return 31;
    }

    public boolean isLeapYear(int year) {
        // Misma lógica, distinta forma escrita
        boolean divisibleBy4 = (year % 4) == 0;
        boolean divisibleBy100 = (year % 100) == 0;
        boolean divisibleBy400 = (year % 400) == 0;

        return divisibleBy400 || (divisibleBy4 && !divisibleBy100);
    }

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth(int monthNumber) {
            for (Months m : Months.values()) {
                if (m.monthNumber == monthNumber) {
                    return m;
                }
            }
            return null;
        }
    }
}
