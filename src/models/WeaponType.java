package models;

/**
 * Перечисление {@code WeaponType} задаёт возможные типы оружия.
 *
 * <p>Доступные типы оружия:
 * <ul>
 *   <li>{@code SHOTGUN}</li>
 *   <li>{@code RIFLE}</li>
 *   <li>{@code BAT}</li>
 * </ul>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public enum WeaponType {
    SHOTGUN,
    RIFLE,
    BAT;

    /**
     * Возвращает строку с именами всех типов оружия, разделённых запятыми.
     *
     * @return строка, например: "SHOTGUN, RIFLE, BAT"
     */
    public static String getNames() {
        final StringBuilder nameList = new StringBuilder();
        for (var weapon : values()) {
            nameList.append(weapon.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
