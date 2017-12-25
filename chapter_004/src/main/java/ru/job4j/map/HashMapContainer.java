package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер на базе ассоциативного массива хранящий
 * пары типа "ключ - значение".
 *
 * @author Pyotr Kukharenka
 * @since 25.12.2017
 */

public class HashMapContainer<K, V> implements Iterable<K> {
    /**
     * Ассоциативный массив, хранящий пары типа "ключ - значение"
     */
    private Entry<K, V>[] table;
    /**
     * Количество элементов в массиве.
     */
    private int size;

    private int modCount;
    /**
     * Степень загрузки, для определения момента расширения
     * массива.
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * Размер массива при инициализации.
     */
    private static final int CAPACITY = 16;
    /**
     * При достижении заполнения массива этому параметру
     * происходит его расширение.
     */
    private int threshold;

    /**
     * Конструктор инициализирующий контейнер, ассоциативный массив
     * на 16 ячеек и предельную загрузку массива = 12 элементам.
     */
    public HashMapContainer() {
        this.table = (Entry<K, V>[]) new Entry[CAPACITY];
        this.size = 0;
        this.modCount = 0;
        this.threshold = (int) (CAPACITY * LOAD_FACTOR);
    }

    /**
     * Добавление пары ключ-значение в контейнер. Алгоритм работы метода:
     * 1. Проверка загруженности массива, если загрузка равна или превышает
     * предельную -> увеличиваем размер массива.
     * 2. На основании хэша ключа вычисляем ячейку массива.
     * 3. Проверяем ключи данной ячейки на равенство хэшей и значений,
     * если равны, перезаписываем значение и поднимаем флаг duplicate
     * 4. Если дубликатов не найдено записываем новое значение
     *
     * @param key   - ключ.
     * @param value - значение
     * @return - true, если значение добавлено, false - если значение
     * перезаписано.
     */
    public boolean insert(K key, V value) {
        boolean duplicate = false;
        if (this.size >= this.threshold) {
            this.resize();
        }
        int hash = key.hashCode() ^ (key.hashCode() >>> 16);
        int index = hash & (this.table.length - 1);
        for (Entry<K, V> e = this.table[index]; e != null; e = e.next) {
            if (e.hash == hash && e.key.equals(key)) {
                e.value = value;
                this.modCount++;
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            this.table[index] = new Entry<>(hash, key, value, this.table[index]);
            this.size++;
            this.modCount++;
        }
        return !duplicate;
    }

    /**
     * Увеличение размера массива в 2 раза и перезапись элементов
     * в новый массив. Элементы старого массива после перезаписи
     * в новый зануляем.
     */
    private void resize() {
        Entry<K, V>[] oldTab = this.table;
        int oldSize = this.table.length;
        int newSize = oldSize * 2;
        this.threshold = (int) (LOAD_FACTOR * newSize);
        Entry<K, V>[] newTab = (Entry<K, V>[]) new Entry[newSize];
        this.table = newTab;
        for (int i = 0; i < oldTab.length; i++) {
            Entry<K, V> e = oldTab[i];
            if (e != null) {
                newTab[e.hash & (newSize - 1)] = e;
                oldTab[i] = null;
            }
        }
    }

    /**
     * Возвращает true если объект удален из контейнера.
     * Вычисляем hash и ячейку в массиве по ключу. Далее
     * проверяем связный список этой ячейки на совпадение hash и ключа.
     * Если параметры совпали, определяем позицию найденного элемента в
     * связном списке:
     * 1. Если элемент был первым, присваиваем текущей ячейке
     * массива следующий элемент связного списка
     * 2. Если элемент не первый, то предыдущий элемент в качестве
     * указателя получает ссылку на следующий за удаляемым элемент.
     *
     * @param key - ключ элемента.
     * @return - true если объект удален из контейнера и false, если
     * такого объекта нет.
     */
    public boolean delete(K key) {
        boolean flag = false;
        Entry<K, V>[] tab = this.table;
        int hash = key.hashCode() ^ (key.hashCode() >>> 16);
        int index = hash & (tab.length - 1);
        Entry<K, V> e = tab[index];
        for (Entry<K, V> prev = null; e != null; prev = e, e = e.next) {
            if (e.hash == hash && e.key.equals(key)) {
                if (prev == null) {
                    this.table[index] = null;
                } else {
                    prev.next = e.next;
                }
                flag = true;
                this.size--;
                this.modCount++;
            }
        }
        return flag;
    }

    /**
     * Возвращает значение по ключу.
     *
     * @param key - ключ
     * @return - значение по ключу.
     */
    public V get(K key) {
        V value = null;
        int hash = key.hashCode() ^ (key.hashCode() >>> 16);
        int index = hash & (this.table.length - 1);
        Entry<K, V> first = this.table[index];
        if (first != null) {
            for (Entry<K, V> e = first; e != null; e = e.next) {
                if (e.hash == hash && e.key.equals(key)) {
                    value = e.value;
                }
            }
        }
        return value;
    }

    /**
     * Возвращает размер контейнера
     *
     * @return - размер контейнера.
     */
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    /**
     * Итератор элементов типа ключ-значение, является базовым
     * для итератора по ключу и итератора по значению.
     */
    abstract class HashMapIterator {
        /**
         * Указатель на текущую пару ключ-значение.
         */
        private Entry<K, V> current;
        /**
         * Указатель на очередную пару ключ-значение.
         */
        private Entry<K, V> next;
        /**
         * Счетчик изменений (fast-fail)
         */
        private int count;
        /**
         * Номер обрабатываемой ячейки массива.
         */
        private int index;

        /**
         * Конструктор, инициализирующий поля, а также осуществляющий
         * поиск очередного ненулевой пары ключ-значение.
         */
        public HashMapIterator() {
            this.current = null;
            this.next = null;
            this.count = modCount;
            this.index = 0;
            Entry<K, V>[] tab = table;
            if (tab != null && tab[this.index] == null) {
                do {
                    this.index++;
                } while (this.index < tab.length && tab[this.index] == null);
                this.next = tab[this.index];
            }
        }

        /**
         * Возвращает true, если есть очередной элемент для итерации
         *
         * @return - true, если есть очередной элемент для итерации
         */
        public boolean hasNext() {
            return this.next != null;
        }

        /**
         * Возвращает очередную пару ключ-значение. Логика работы
         * заключается в следующем: если следующая пара в текущей
         * ячейке массива равна null, то переходим к следующей ненулевой
         * ячейке.
         *
         * @return - возвращает очередную пару ключ-значение.
         * @throws NoSuchElementException          - выбрасывается в случае отсутствия очередного
         *                                         элемента для итерации.
         * @throws ConcurrentModificationException - выбрасывается в случае изменения
         *                                         (добавление, удалении) в контейнере в момент итерации элементов.
         */
        public Entry<K, V> nextEntry() {
            Entry<K, V>[] tab = table;
            Entry<K, V> e = this.next;
            this.current = e;
            if (e == null) {
                throw new NoSuchElementException();
            }
            if (this.count != modCount) {
                throw new ConcurrentModificationException();
            }
            if (tab != null && e.next == null) {
                do {
                    this.index++;
                } while (this.index < tab.length - 1 && tab[this.index] == null);
                this.next = tab[this.index];
            } else {
                this.next = e.next;
            }
            return e;
        }
    }

    /**
     * Итератор ключей контейнера.
     */
    class KeyIterator extends HashMapIterator implements Iterator<K> {
        /**
         * Возвращает очередной ключ элементов контейнера.
         *
         * @return - очередной ключ элементов контейнера.
         */
        @Override
        public K next() {
            return nextEntry().key;
        }
    }

    /**
     * Пара типа ключ-значение.
     *
     * @param <K> - параметризованный ключ элемента.
     * @param <V> - параметризованное значение элемента.
     */
    public class Entry<K, V> {
        /**
         * Хэш-код ключа.
         */
        private int hash;
        /**
         * Значение ключа.
         */
        final private K key;
        /**
         * Пара ключу - значение
         */
        private V value;
        /**
         * Указатель на следующий элемент связного списка.
         */
        Entry<K, V> next;

        /**
         * Конструктор для инициализации.
         *
         * @param hash  - хэш-код ключа.
         * @param key   - значение ключа.
         * @param value - пара ключу - значение
         * @param next  - указатель на следующий элемент связного списка.
         */
        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
