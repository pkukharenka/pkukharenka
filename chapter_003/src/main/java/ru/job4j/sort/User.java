package ru.job4j.sort;

/**
 * Класс, описывающий объект типа User.
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */

public class User implements Comparable<User> {
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Возраст пользователя.
     */
    private int age;

    /**
     * Конструктор, инициализирующий пользователя.
     *
     * @param name - имя пользователя
     * @param age  - возраст пользователя.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Переопределенный метод toString для вывода элементов
     * коллекции на консоль.
     *
     * @return объект в виде текста.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    /**
     * В компараторе произвотсдия сравнения возрастов пользователей,
     * в случае если у нескольких пользователей возраст оказывается
     * одинаковым проверяются имена. При это входящий объект сравнивается
     * с текущим
     *
     * @param o - входящий объект
     * @return значение типа int
     */
    @Override
    public int compareTo(User o) {
        final int res = ((Integer) this.age).compareTo(o.getAge());
        return res != 0 ? res : this.name.compareTo(o.getName());
    }
}
