package brokenrobotgame.model;

/** Определяет поведение разрушимых объектов */
public interface Destroyable {

    /** Разрушить объект полностью */
    void destroy();

    /** Проверить, разрушен ли объект */
    boolean isDestroyed();

    /** Повредить объект
     *
     * @param damage урон
     */
    void hit(int damage);

    /** Получить текущую прочность объекта
     *
     * @return оставшиеся единицы прочности
     */
    int getDurabilityRest();
}
