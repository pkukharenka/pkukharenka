package ru.job4j.todolist.dao;

import org.junit.After;
import org.junit.Test;
import ru.job4j.todolist.model.Item;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemDaoTest {

    private final ItemDao repository = new ItemDao();

    @After
    public void cleanDatabase() {
        this.repository.deleteAll();
    }

    @Test
    public void whenAddNewItem() {
        final Item item = new Item();
        item.setDesc("Test description");
        item.setCreated(LocalDate.now());
        item.setDone(true);
        this.repository.save(item);
        int size = this.repository.findAll().size();
        assertThat(size, is(1));
    }

    @Test
    public void whenUpdateExistingItem() {
        final Item item = new Item();
        item.setDesc("Old description");
        item.setCreated(LocalDate.now());
        item.setDone(false);
        this.repository.save(item);
        final String newDesc = "New description";
        item.setDesc(newDesc);
        this.repository.update(item);
        assertThat(this.repository.findAll().size(), is(1));
        assertThat(this.repository.findAll().get(0).getDesc(), is(newDesc));
        assertThat(this.repository.findAll().get(0).isDone(), is(false));
    }

    @Test
    public void whenDeleteThenSizeIs0() {
        final Item item = new Item();
        item.setDesc("Test");
        item.setCreated(LocalDate.now());
        item.setDone(true);
        final int id = this.repository.save(item);
        item.setId(id);
        this.repository.delete(item);
        assertThat(this.repository.findAll().size(), is(0));
    }

    @Test
    public void whenGetOneThenTakeOne() {
        final Item item = new Item();
        item.setDesc("Test");
        item.setCreated(LocalDate.now());
        item.setDone(true);
        final int id = this.repository.save(item);
        assertThat(this.repository.getItem(id).getDesc(), is("Test"));
    }

}