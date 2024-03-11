package com.example.customarray;

import java.util.Arrays;
import java.util.Comparator;

/**
 * CustomArrayList - кастомная реализация списка, основанная на массиве.
 *
 * @param <T> тип элементов в списке
 */
public class CustomArrayList<T> {

    /**
     * Начальная емкость по умолчанию
     *
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив объектов, в котором хранятся элементы списка
     *
     */
    private transient Object[] array;

    /**
     * Размер списка
     *
     */
    private int size;

    /**
     * Конструктор без параметров, создающий список с начальной емкостью по умолчанию (10).
     */
    public CustomArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, создающий список с заданной начальной емкостью.
     *
     * @param initialCapacity начальная емкость списка
     * @throws IllegalArgumentException если начальная емкость меньше 0
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            array = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            array = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    public void add(T element) {
        ensureCapacity(size + 1);
        array[size++] = element;
    }

    /**
     * Добавляет элемент в указанную позицию в списке.
     *
     * @param index   позиция, в которую нужно добавить элемент
     * @param element элемент, который нужно добавить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    public void add(int index, T element) {
        outOfBoundsCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Получает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент списка по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        outOfBoundsCheck(index);
        return (T) array[index];
    }

    /**
     * Удаляет элемент из списка по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    public T remove(int index) {
        outOfBoundsCheck(index);
        @SuppressWarnings("unchecked")
        T removedElement = (T) array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
        return removedElement;
    }

    /**
     * Очищает список, удаляя все его элементы.
     */
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }



    /**
     * Возвращает текущий размер списка.
     *
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст, в противном случае - false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Сортирует элементы списка в естественном порядке с использованием быстрой сортировки (QuickSort).
     */
    public void sort() {
        quickSort(0, size - 1, null);
    }
    /**
     * Сортирует элементы списка с использованием заданного компаратора.
     *
     * @param comparator компаратор для сортировки
     */
    public void sort(Comparator<T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    // Приватные методы
    /**
     * Убеждается, что внутренний массив имеет достаточную емкость для хранения заданного количества элементов.
     * Если текущая емкость меньше требуемой, внутренний массив будет увеличен.
     *
     * @param minCapacity минимальная требуемая емкость для хранения элементов
     */

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int oldCapacity = array.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    /**
     * Проверяет, выходит ли индекс за пределы списка для операций доступа по индексу (get, remove).
     *
     * @param index индекс, который нужно проверить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    private void outOfBoundsCheck(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Рекурсивно выполняет сортировку элементов списка методом быстрой сортировки (QuickSort).
     *
     * @param low        индекс самого нижнего элемента в подсписке
     * @param high       индекс самого верхнего элемента в подсписке
     * @param comparator компаратор для сравнения элементов
     */
    private void quickSort(int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int p = partition(low, high, comparator);
            quickSort(low, p - 1, comparator);
            quickSort(p + 1, high, comparator);
        }
    }

    /**
     * Выполняет разделение списка на две части относительно опорного элемента и возвращает его индекс.
     *
     * @param low        индекс самого нижнего элемента в подсписке
     * @param high       индекс самого верхнего элемента в подсписке
     * @param comparator компаратор для сравнения элементов
     * @return индекс опорного элемента после разделения списка
     */
    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<T> comparator) {
        T pivot = (T) array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator != null) {
                if (comparator.compare((T) array[j], pivot) <= 0) {
                    i++;
                    swap(i, j);
                }
            } else {
                Comparable<? super T> comparablePivot = (Comparable<? super T>) pivot;
                if (comparablePivot.compareTo((T) array[j]) >= 0) {
                    i++;
                    swap(i, j);
                }
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
