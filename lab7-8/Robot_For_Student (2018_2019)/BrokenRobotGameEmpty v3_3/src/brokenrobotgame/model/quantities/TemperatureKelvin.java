package brokenrobotgame.model.quantities;

/** Класс, описывающий температуру в Кельвинах */
public class TemperatureKelvin {

    /** температура */
    private final double temperature;

    /** Конструктор
     *
     * @param temperature уровень температуры
     */
    public TemperatureKelvin(double temperature) {
        if (temperature < 0) throw new IllegalArgumentException("temperature cannot be negative");
        this.temperature = temperature;
    }

    /** Получить уровень температуры
     *
     * @return температура
     */
    public double getTemperature() { return temperature; }

    /** Получить отформатированную строку-сообщение с уровнем температуры
     *
     * @return строка
     */
    public String getFormattedTemperature() {
        return "temperature is " + String.format("%.3f", temperature) + " K";
    }
}
