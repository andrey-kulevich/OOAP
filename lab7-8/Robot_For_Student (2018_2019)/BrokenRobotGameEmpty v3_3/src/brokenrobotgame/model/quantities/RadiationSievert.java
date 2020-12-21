package brokenrobotgame.model.quantities;

/** �����, ����������� ������� ������������� ��������� � �������� */
public class RadiationSievert {

    /** ������� �������� */
    private final double radiation;

    /** �����������
     *
     * @param radiation ������� ��������
     */
    public RadiationSievert(double radiation) {
        if (radiation < 0) throw new IllegalArgumentException("radiation cannot be negative");
        this.radiation = radiation;
    }

    /** �������� ������� ��������
     *
     * @return ������� ��������
     */
    public double getRadiation() { return radiation; }

    /** �������� ����������������� ������-��������� � ������� ��������
     *
     * @return ������
     */
    public String getFormattedRadiation() {
        return "radiation is " + String.format("%.3f", radiation) + " Sv";
    }

    /** �������� ������ ��������� ������������� ���������
     *
     * @return ������ � ������� ��������� ���������
     */
    public String getEstimateOfRadiationPollution() {
        if (radiation < 0.25d) return "Permissible single exposure";
        else if (radiation > 0.25d && radiation < 1) return "Mild radiation sickness";
        else if (radiation > 1 && radiation < 4.5d) return "Severe radiation sickness";
        else return "Lethal exposure!";
    }
}
