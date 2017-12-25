package ru.job4j.set;

/**
 * Контейнер типа Set на базе хэш-таблицы. Начальный размер
 * контейнера = 16 при коэффициенте загрузки = 0,75. Основа
 * контейнера массив односвязных списков типа Entry.
 *
 * @author Pyotr Kukharenka
 * @since 22.12.2017
 */

public class HashSetContainer<E> {
    /**
     * Массив односвязных списков.
     */
    private Entry<E>[] table;
    /**
     * Начальный размер массива.
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * Коэффициент загрузки массива.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * Параметр, определяющий момент расширения массива.
     * При достижении количества элементов массива данного
     * параметра происходит расширение массива в 2 раза.
     */
    private int threshold;
    /**
     * Количество элементов в контейнере
     */
    private int size;

    /**
     * Конструктор, создающий массив на 16 ячеек и устанавливающий
     * начальный параметр загрузки = 12.
     */
    public HashSetContainer() {
        this.threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
        this.table = (Entry<E>[]) (new Entry[DEFAULT_CAPACITY]);
    }

    /**
     * Добавляет новый элемент в контейнер. Проверяем что количество
     * эементов не превышает параметра загрузки, если превышает, производим
     * расширение массива и перезапись всех элементов.
     * <p>
     * Вычисляем hash для добавляемого элемента. На основании полученного
     * hash и зная размер массива, вычисляем позицию в массиве, куда
     * элемент будет записан (побитовое & позволит нам не выйти за рамки
     * текущего размера массива). Определив ячейку проверяем, что в этой
     * ячейке (односвязном списке) нет элементов у которых равны hash и
     * значение. Если такие есть подлнимаем флаг duplicate и выходим из цикла.
     * Если флаг не поднят значит записываем элемент первым в односвязный
     * список в этой ячейке и присваиваем ему указатель на следующий элемент
     * этого списка.
     *
     * @param value - объект для добавление в контейнер
     * @throws NullPointerException - в случае если мы пытаемся добавить
     *                              элемент со значением null.
     */
    public void add(E value) {
        boolean duplicate = false;
        if (this.size >= this.threshold) {
            this.resize();
        }
        if (value == null) {
            throw new NullPointerException("Value must not be null");
        } else {
            int hash = value.hashCode();
            int index = hash & (this.table.length - 1);
            for (Entry<E> e = this.table[index]; e != null; e = e.next) {
                if (e.hash == hash && e.value == value) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                this.table[index] = new Entry<>(hash, value, this.table[index]);
                this.size++;
            }
        }
    }

    /**
     * Увеличивает размер масисва в 2 раза при достижении количества
     * элементов параетру загрузки. Для каждого элемента старого массива
     * вычисляются позиции в новом массиве с учетом его размерности.
     * Элементы старого массива после перезаписи в новый зануляем.
     */
    private void resize() {
        Entry<E>[] oldTab = this.table;
        int oldSize = oldTab.length;
        int newSize = oldSize * 2;
        Entry<E>[] newTab = (Entry<E>[]) new Entry[newSize];
        this.table = newTab;
        for (int i = 0; i < oldTab.length; i++) {
            Entry<E> e = oldTab[i];
            if (e != null) {
                int index = e.hash & (newSize - 1);
                newTab[index] = e;
                oldTab[i] = null;
            }

        }
    }

    /**
     * Возвращает true если такой объект есть в контейнере.
     * Вычисляем hash и ячейку в массиве где должен быть
     * данный элемент. Далее проверяем связный список этой
     * ячейки на совпадение hash и значения.
     *
     * @param value - объект для поиска в контейнере.
     * @return - true если такой объект есть в контейнере.
     */
    public boolean contains(E value) {
        boolean flag = false;
        int hash = value.hashCode();
        int index = hash & (this.table.length - 1);
        for (Entry<E> e = this.table[index]; e != null; e = e.next) {
            if (e.hash == hash && e.value.equals(value)) {
                flag = true;
            }

        }
        return flag;
    }

    /**
     * Возвращает true если объект удален из контейнера.
     * Вычисляем hash и ячейку в массиве где должен быть
     * данный элемент. Далее проверяем связный список этой
     * ячейки на совпадение hash и значения. Если параметры
     * совпали, определяем позицию найденного элемента в
     * связном списке:
     * 1. Если элемент был первым, присваиваем текущей ячейке
     * массива следующий элемент связного списка
     * 2. Если элемент не первый, то предыдущий элемент в качестве
     * указателя получает ссылку на следующий за удаляемым элемент.
     *
     * @param value - объект для удаления
     * @return - true если объект удален из контейнера и false, если
     * такого объекта нет.
     */
    public boolean remove(E value) {
        boolean flag = false;
        Entry<E>[] tab = this.table;
        int hash = value.hashCode();
        int index = hash & (tab.length - 1);
        Entry<E> entry = tab[index];
        for (Entry<E> prev = null; entry != null; prev = entry, entry = entry.next) {
            if (entry.hash == hash && entry.value.equals(value)) {
                if (prev == null) {
                    tab[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                this.size--;
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Возвращает размер контейнера
     *
     * @return - размер контейнера.
     */
    public int size() {
        return this.size;
    }

    /**
     * Односвязный список для реализации массива односвязных
     * списков.
     *
     * @param <E> - тип списка.
     */
    private static class Entry<E> {
        /**
         * Хэш-код объекта
         */
        final int hash;
        /**
         * Значение объекта.
         */
        final E value;
        /**
         * Указатель на следующий объект.
         */
        Entry<E> next;

        /**
         * Конструктор для инициализации элементов списка.
         *
         * @param hash  - Хэш-код объекта
         * @param value - Значение объекта.
         * @param next  - Указатель на следующий объект.
         */
        public Entry(int hash, E value, Entry<E> next) {
            this.hash = hash;
            this.value = value;
            this.next = next;
        }
    }

}


