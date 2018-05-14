package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;

/**
 * Потокобезопасная структура для хранения объектов типа User.
 *
 * @author Pyotr Kukharenka
 * @since 04.01.2018
 */
@ThreadSafe
public class UserStorage {
    /**
     * Массив для хранения объектов типа User.
     */
    @GuardedBy("this")
    private User[] users;
    /**
     * Количество объектов в массиве.
     */
    @GuardedBy("this")
    private int index;
    /**
     * Дефолтный размер массива.
     */
    private static final int CAPACITY = 10;

    /**
     * Конструктор для инициализации структуры.
     */
    public UserStorage() {
        this.users = new User[CAPACITY];
        this.index = 0;
    }

    /**
     * Возвращает true если новый объект добавлен
     * в хранилище.
     *
     * @param user - объект для добавления.
     * @return - true если новый объект добавлен в хранилище.
     */
    public synchronized boolean add(User user) {
        this.checkSize();
        this.users[index++] = user;
        return false;
    }

    /**
     * Если количество элементов в массиве равно
     * размеру массива, создаем новый массив в 1,5
     * раза больше текущего и копируем туда
     * объекты из старого массива.
     */
    private synchronized void checkSize() {
        int oldSize = this.users.length;
        if (oldSize == index) {
            int newSize = oldSize + (oldSize >> 1);
            this.users = Arrays.copyOf(this.users, newSize);
        }
    }

    /**
     * Возвращает true если объект удален из хранилища
     *
     * @param user - объект для удаления
     * @return - true если объект удален из хранилища.
     */
    public synchronized boolean delete(User user) {
        boolean flag = false;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].equals(user)) {
                System.arraycopy(this.users, i + 1, this.users, i, this.users.length - i - 1);
                this.users[index - 1] = null;
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Возвращает объект по индексу.
     *
     * @param index - индекс в массиве.
     * @return - объект
     */
    public synchronized User get(int index) {
        return this.users[index];
    }

    /**
     * Возвращает true если объект изменен.
     *
     * @param user - объект с изменениями.
     * @return - true если объект изменен.
     */
    public synchronized boolean update(User user) {
        boolean flag = false;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].equals(user)) {
                this.users[i] = user;
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Возвращет объект по его id.
     *
     * @param id - id объекта, который необходимо найти.
     * @return - найденный объект.
     */
    public synchronized User findById(int id) {
        User result = null;
        for (User user : users) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает количество объектов в хранилище.
     *
     * @return - количество объектов в хранилище.
     */
    public int size() {
        return this.index;
    }

    /**
     * Производит перевод денег от одного пользователя к другому.
     *
     * @param fromId - id с которого будет идти перевод.
     * @param toId   - id куда переводятся деньги
     * @param amount - сумма перевода
     * @return - true если все прошло успешно.
     */
    public boolean transfer(int fromId, int toId, int amount) {
        boolean flag = false;
        User fromUser = this.findById(fromId);
        User toUser = this.findById(toId);
        if (fromUser.getAmount() >= amount) {
            toUser.setAmount(toUser.getAmount() + amount);
            fromUser.setAmount(fromUser.getAmount() - amount);
            flag = true;
        }
        return flag;
    }
}
