package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import models.HumanBeing;

/**
 * Класс {@code CollectionManager} отвечает за управление коллекцией объектов {@code HumanBeing}.
 * Коллекция хранится в виде {@code java.util.Stack}. Класс обеспечивает:
 * <ul>
 *   <li>Генерацию уникальных идентификаторов для новых объектов.</li>
 *   <li>Добавление, удаление, обновление объектов.</li>
 *   <li>Вывод информации о коллекции и её сортировку.</li>
 *   <li>Вычисление среднего значения поля impactSpeed.</li>
 * </ul>
 *
 *
 * @version 1.0
 */
public class CollectionManager {

    /**
     * Дата инициализации коллекции.
     */
    private final LocalDate initializationDate;

    /**
     * Следующий уникальный идентификатор для новых объектов.
     */
    private long nextId;

    /**
     * Коллекция объектов {@code HumanBeing}, хранится в виде {@code Stack}.
     */
    private final Stack<HumanBeing> collection;

    /**
     * Конструктор {@code CollectionManager}.
     * Принимает уже загруженную коллекцию.
     *
     * @param initialCollection коллекция объектов, полученная извне.
     */
    public CollectionManager(final Stack<HumanBeing> initialCollection) {
        this.collection = initialCollection;
        this.initializationDate = LocalDate.now();
        this.nextId = 1;

        for (HumanBeing human : collection) {
            if (human.getId() >= nextId) {
                nextId = human.getId() + 1;
            }
        }
    }

    /**
     * Генерирует новый уникальный идентификатор.
     *
     * @return новый уникальный идентификатор (больше 0).
     */
    public Long generateId() {
        if (collection.isEmpty()) {
            nextId = 1;
        }
        return nextId++;
    }

    /**
     * Ищет элемент в коллекции по его id.
     *
     * @param id идентификатор.
     * @return объект {@code HumanBeing} или {@code null}, если элемент не найден.
     */
    public HumanBeing findById(final Long id) {
        for (HumanBeing human : collection) {
            if (human.getId().equals(id)) {
                return human;
            }
        }
        return null;
    }

    /**
     * Добавляет новый объект в коллекцию.
     *
     * @param human объект {@code HumanBeing}.
     */
    public void add(final HumanBeing human) {
        collection.push(human);
    }

    /**
     * Возвращает коллекцию объектов.
     *
     * @return {@code Stack<HumanBeing>}.
     */
    public Stack<HumanBeing> getCollection() {
        return collection;
    }

    /**
     * Обновляет объект с заданным id, заменяя его новым объектом,
     * при этом сохраняет поля id и creationDate.
     *
     * @param id       идентификатор обновляемого объекта.
     * @param newHuman новый объект {@code HumanBeing} с обновлёнными значениями.
     * @return {@code true}, если обновление прошло успешно, иначе {@code false}.
     */
    public boolean updateById(final Long id, final HumanBeing newHuman) {
        final ListIterator<HumanBeing> iterator = collection.listIterator();
        while (iterator.hasNext()) {
            final HumanBeing oldHuman = iterator.next();
            if (oldHuman.getId().equals(id)) {
                final LocalDate creationDate = oldHuman.getCreationDate();
                final HumanBeing updated = new HumanBeing(
                        id,
                        newHuman.getName(),
                        newHuman.getCoordinates(),
                        creationDate,
                        newHuman.getRealHero(),
                        newHuman.getHasToothpick(),
                        newHuman.getImpactSpeed(),
                        newHuman.getMinutesOfWaiting(),
                        newHuman.getWeaponType(),
                        newHuman.getMood(),
                        newHuman.getCar()
                );
                iterator.set(updated);
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет объект из коллекции по его id.
     *
     * @param id идентификатор объекта.
     * @return {@code true}, если объект удалён, иначе {@code false}.
     */
    public boolean removeById(final Long id) {
        final Iterator<HumanBeing> it = collection.iterator();
        while (it.hasNext()) {
            final HumanBeing human = it.next();
            if (human.getId().equals(id)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет объект из коллекции по индексу.
     *
     * @param index индекс объекта.
     * @return {@code true}, если объект удалён, иначе {@code false}.
     */
    public boolean removeAt(final int index) {
        if (index >= 0 && index < collection.size()) {
            collection.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Удаляет первый элемент коллекции.
     *
     * @return {@code true}, если объект удалён, иначе {@code false}.
     */
    public boolean removeFirst() {
        if (!collection.isEmpty()) {
            collection.remove(0);
            return true;
        }
        return false;
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает информацию о коллекции.
     *
     * @return строка с информацией о типе коллекции, дате инициализации и количестве элементов.
     */
    public String getInfo() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Тип коллекции: ").append(collection.getClass().getName()).append("\n");
        sb.append("Дата инициализации: ").append(initializationDate).append("\n");
        sb.append("Количество элементов: ").append(collection.size());
        return sb.toString();
    }

    /**
     * Возвращает отсортированную коллекцию объектов {@code HumanBeing}.
     *
     * @return отсортированный список объектов (по {@code compareTo}).
     */
    public List<HumanBeing> getSortedCollection() {
        final List<HumanBeing> sortedList = new ArrayList<>(collection);
        Collections.sort(sortedList);
        return sortedList;
    }

    /**
     * Вычисляет среднее значение поля impactSpeed для всех объектов коллекции.
     *
     * @return среднее значение impactSpeed или 0, если коллекция пуста.
     */
    public double averageOfImpactSpeed() {
        if (collection.isEmpty()) {
            return 0;
        }
        long sum = 0;
        for (HumanBeing human : collection) {
            sum += human.getImpactSpeed();
        }
        return (double) sum / collection.size();
    }

    /**
     * Возвращает объект с минимальными координатами.
     *
     * @return объект {@code HumanBeing} или {@code null}, если коллекция пуста.
     */
    public HumanBeing minByCoordinates() {
        if (collection.isEmpty()) {
            return null;
        }
        HumanBeing min = collection.peek();
        for (HumanBeing human : collection) {
            if (human.getCoordinates().compareTo(min.getCoordinates()) < 0) {
                min = human;
            }
        }
        return min;
    }
}
