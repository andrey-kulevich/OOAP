package brokenrobotgame.model;

/** ���������� ��������� ���������� �������� */
public interface Destroyable {

    /** ��������� ������ ��������� */
    void destroy();

    /** ���������, �������� �� ������ */
    boolean isDestroyed();

    /** ��������� ������
     *
     * @param damage ����
     */
    void hit(int damage);

    /** �������� ������� ��������� �������
     *
     * @return ���������� ������� ���������
     */
    int getDurabilityRest();
}
