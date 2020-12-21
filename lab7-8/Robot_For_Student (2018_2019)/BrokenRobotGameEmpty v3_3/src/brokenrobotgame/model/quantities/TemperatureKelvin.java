package brokenrobotgame.model.quantities;

/** �����, ����������� ����������� � ��������� */
public class TemperatureKelvin {

    /** ����������� */
    private final double temperature;

    /** �����������
     *
     * @param temperature ������� �����������
     */
    public TemperatureKelvin(double temperature) {
        if (temperature < 0) throw new IllegalArgumentException("temperature cannot be negative");
        this.temperature = temperature;
    }

    /** �������� ������� �����������
     *
     * @return �����������
     */
    public double getTemperature() { return temperature; }

    /** �������� ����������������� ������-��������� � ������� �����������
     *
     * @return ������
     */
    public String getFormattedTemperature() {
        return "temperature is " + String.format("%.3f", temperature) + " K";
    }
}
