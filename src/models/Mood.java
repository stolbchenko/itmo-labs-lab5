package models;

/**
 * Перечисление {@code Mood} задаёт возможные эмоциональные состояния.
 *
 * <p>Доступные состояния:
 * <ul>
 *   <li>{@code SADNESS}</li>
 *   <li>{@code GLOOM}</li>
 *   <li>{@code CALM}</li>
 *   <li>{@code RAGE}</li>
 *   <li>{@code FRENZY}</li>
 * </ul>
 *
 * @author Ilya Stolbchenko.
 * @version 1.0.
 */
public enum Mood {
    SADNESS,
    GLOOM,
    CALM,
    RAGE,
    FRENZY;

    /**
     * Возвращает строку с именами всех состояний перечисления, разделёнными запятыми.
     *
     * @return строка с перечислением имен, например: "SADNESS, GLOOM, CALM, RAGE, FRENZY".
     */
    public static String getNames() {
        final StringBuilder nameList = new StringBuilder();
        for (var mood : values()) {
            nameList.append(mood.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
