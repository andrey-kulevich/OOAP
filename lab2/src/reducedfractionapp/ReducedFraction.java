package reducedfractionapp;


/** Несократимая дробь.*/
public class ReducedFraction {
    
    /* =========================== Свойства =============================== */
 
    /* ---------------------- Числитель и знаменатель --------------------- */
    private int numerator, denominator;
    
    
    /* =========================== Операции ============================== */

    /* ---------------------------- Порождение ---------------------------- */
    
    /** Создание дроби с указанием ее числителя и знаменателя.
    *
     * @param numerator числитель дроби
     * @param denominator знаменатель дроби
    */
    public ReducedFraction(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Деление на ноль"); //Проверка деления на ноль
        int gcf = findGreatestCommonFactor(numerator, denominator); //Найти наибольший общий делитель
        this.numerator = numerator / gcf; //Установить сокращенный числитель
        this.denominator = denominator / gcf; //Установить сокращенный знаменатель
        if (this.denominator < 0) { //Если знаменатель дроби отрицательный
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /** Создание дроби на основе целого числа.
    *
     * @param numerator числитель дроби
    */ 
    public ReducedFraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }
  
    /* --------------------- Арифметические операции ---------------------- */
    
    /** Сложение двух дробей.
    *
     * @param other слагаемое
     * @return сумма
    */
    public ReducedFraction add(ReducedFraction other) {
        return new ReducedFraction(numerator * other.denominator + other.numerator * denominator,
                denominator * other.denominator);
    }

    /** Вычитание двух дробей.
    *
     * @param other вычитаемое
     * @return разность
    */
    public ReducedFraction subtract(ReducedFraction other) {
        return new ReducedFraction(numerator * other.denominator - other.numerator * denominator,
                denominator * other.denominator);
    }

    /** Умножение двух дробей.
    *
     * @param other множитель
     * @return произведение
    */
    public ReducedFraction multiplicate(ReducedFraction other) {
        return new ReducedFraction(numerator * other.numerator,
                denominator * other.denominator);
    }
    
    /** Деление двух дробей.
    *
     * @param other делитель
     * @return частное
    */
    public ReducedFraction divide(ReducedFraction other) {
        return new ReducedFraction(numerator * other.denominator,
                denominator * other.numerator);
    }

    /* --------------------- Операции сравнения ---------------------- */

    /** Сравнение двух дробей.
    *
     * @param other дробь, с которой будет сравниваться текущая
     * @return результат сравнения (0, если равны, меньше 0, если текущая дробь меньше,
     * и больше 0, если текущая дробь больше)
    */
    public int compare(ReducedFraction other) {
        return Integer.compare(numerator * other.denominator, other.numerator * denominator);
    }
    
    /** Эквивалентность двух дробей.
    *
     * @param o объект, с которым сравнивается дробь
     * @return результат сравнения на эквивалентность
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReducedFraction that = (ReducedFraction) o;
        return numerator == that.numerator &&
                denominator == that.denominator;
    }
    
    /* --------------------- Операции преобразования ---------------------- */
    
    /** Представить как строку.
    *
     * @return Представление дроби в виде строки
    */
    @Override
    public String toString() {
        if (numerator == 0) return "0";
        if (denominator == 1) return String.valueOf(numerator);
        return numerator + "/" + denominator;
    }

    /** Представить как вещественное число.
    *
     * @return представление дроби в виде вещественного числа
    */
    public double toDouble() {
        return (double) numerator / denominator;
    }

    /* --------------------- Вспомогательные операции ---------------------- */

    /** Найти наибольший общий делитель двух целых чисел.
     *
     * @param first первое целое число
     * @param second второе целое число
     * @return наибольший общий делитель для переданных чисел
     */
    private int findGreatestCommonFactor(int first, int second ) {
        int gcf = 1; //Наибольший общий делитель для числителя и знаменателя
        for (int i = Math.max(first, second); i > 0 && gcf == 1; i--) {
            if (first % i == 0 && second % i == 0) gcf = i;
        }
        return gcf;
    }

}
