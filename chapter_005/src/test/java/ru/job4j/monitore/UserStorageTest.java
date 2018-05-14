package ru.job4j.monitore;

import org.junit.Test;
import ru.job4j.storage.User;
import ru.job4j.storage.UserStorage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    /**
     * Поток для добавления нового пользователя.
     */
    public class StorageAdd extends Thread {
        private final UserStorage storage;
        private final User user;

        public StorageAdd(UserStorage storage, User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            this.storage.add(this.user);
        }
    }

    /**
     * Поток для перевода денег между пользователями.
     */
    public class StorageTransfer extends Thread {
        private final UserStorage storage;
        private final User fromUser;
        private final User toUser;
        private final int amount;

        public StorageTransfer(UserStorage storage, User fromUser, User toUser, int amount) {
            this.storage = storage;
            this.fromUser = fromUser;
            this.toUser = toUser;
            this.amount = amount;
        }

        @Override
        public void run() {
            this.storage.transfer(this.fromUser.getId(), this.toUser.getId(), this.amount);
        }
    }

    @Test
    public void whenAddTwoUserFromTwoThreadThenSizeTwo() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User first = new User(1, 100);
        User second = new User(2, 50);
        Thread th1 = new StorageAdd(storage, first);
        Thread th2 = new StorageAdd(storage, second);
        Thread th3 = new StorageTransfer(storage, first, second, 50);
        Thread th4 = new StorageTransfer(storage, first, second, 50);
        Thread th5 = new StorageTransfer(storage, first, second, 50);
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th1.join();
        th2.join();
        th3.join();
        th4.join();
        th5.join();
        assertThat(storage.size(), is(2));
        assertThat(storage.findById(1).getAmount(), is(0));
        assertThat(storage.findById(2).getAmount(), is(150));
    }

}