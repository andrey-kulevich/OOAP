package brokenrobotgame.model.quantities;

/**  ласс, описывающий уровень радиационного облучени€ в «ивертах */
public class RadiationSievert {

    /** уровень радиации */
    private final double radiation;

    /**  онструктор
     *
     * @param radiation уровень радиации
     */
    public RadiationSievert(double radiation) {
        if (radiation < 0) throw new IllegalArgumentException("radiation cannot be negative");
        this.radiation = radiation;
    }

    /** ѕолучить уровень радиации
     *
     * @return уровень радиации
     */
    public double getRadiation() { return radiation; }

    /** ѕолучить отформатированную строку-сообщение с уровнем радиации
     *
     * @return строка
     */
    public String getFormattedRadiation() {
        return "radiation is " + String.format("%.3f", radiation) + " Sv";
    }

    /** ѕолучить оценку опасности радиационного облучени€
     *
     * @return строка с оценкой опасности облучени€
     */
    public String getEstimateOfRadiationPollution() {
        if (radiation < 0.25d) return "Permissible single exposure";
        else if (radiation > 0.25d && radiation < 1) return "Mild radiation sickness";
        else if (radiation > 1 && radiation < 4.5d) return "Severe radiation sickness";
        else return "Lethal exposure!";
    }
}
