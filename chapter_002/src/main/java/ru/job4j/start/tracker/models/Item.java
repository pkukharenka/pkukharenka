package ru.job4j.start.tracker.models;

import java.util.Arrays;

/**
 * Класс, описывающий объект типа Item.
 *
 * @author Pyotr Kukharenka
 * @since 28.11.2017
 */

public class Item {
    private int id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (created != item.created) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (desc != null ? !desc.equals(item.desc) : item.desc != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(comments, item.comments);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (int) (created ^ (created >>> 32));
        result = 31 * result + Arrays.hashCode(comments);
        return result;
    }

    @Override
    public String toString() {
        return "id='" + id + '\''
                + ", имя='" + name + '\''
                + ", описание='" + desc + '\''
                + '}';
    }
}