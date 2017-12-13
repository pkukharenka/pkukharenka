package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы функций банковской системы.
 *
 * @author Pyotr Kukharenka
 * @since 13.12.2017
 */
public class BankTest {
    /**
     * Поля для тестирования методов. Имеем два объекта типа User и два объекта
     * типа Account.
     */
    private static final User FIRST = new User("Ivan", "1234");
    private static final User SECOND = new User("Pyotr", "1345");
    private static final Account FIRST_ACC = new Account(100.12, "1555");
    private static final Account SECOND_ACC = new Account(55.55, "6666");

    /**
     * При добавлении двух пользователей размер хранилища
     * должен быть 2.
     */
    @Test
    public void whenAddTwoUsersThenSizeTwo() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addUser(SECOND);
        assertThat(bank.getAllUsers().size(), is(2));
    }

    /**
     * Добавляем двух пользователей и удаляем первого. Размер
     * хранилища должен быть 1.
     */
    @Test
    public void deleteUser() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addUser(SECOND);
        bank.deleteUser(FIRST);
        assertThat(bank.getAllUsers().size(), is(1));
    }

    /**
     * Добавляем одного клиента и создаем у него 2
     * банковских счета. Проверяем, что под индексом 1 коллекции
     * счетов содержится реквизит второго счет.
     */
    @Test
    public void addAccountToUser() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addAccountToUser(FIRST_ACC, FIRST);
        bank.addAccountToUser(SECOND_ACC, FIRST);
        assertThat(bank.getUserAccounts(FIRST).get(1).getRequisites(), is("6666"));
    }

    /**
     * Добавляем одного клиента и создаем у него один банковский
     * счет. Затем производим удаление этого счет. Размер харнилища
     * счетов этого клиента равен 0.
     */
    @Test
    public void deleteAccountFromUser() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addAccountToUser(FIRST_ACC, FIRST);
        bank.deleteAccountFromUser(FIRST_ACC, FIRST);
        assertThat(bank.getUserAccounts(FIRST).size(), is(0));
    }

    /**
     * Добавляем одного клиента, создаем у него два счет. Проверяем,
     * размер коллекции со счетами этого клиента равен 2.
     */
    @Test
    public void getUserAccounts() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addAccountToUser(FIRST_ACC, FIRST);
        bank.addAccountToUser(SECOND_ACC, FIRST);
        assertThat(bank.getUserAccounts(FIRST).size(), is(2));
    }

    /**
     * Добавляем двух клиентов и у каждого из них создаем по одному
     * счету. Осуществляем перевод со счета первого 50.02 единиц на счет
     * второго и проверяем, что теперь на счете второго содержится сумма
     * его старого счета и суммы перевода.
     */
    @Test
    public void transferMoney() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addUser(SECOND);
        bank.addAccountToUser(FIRST_ACC, FIRST);
        bank.addAccountToUser(SECOND_ACC, SECOND);
        bank.transferMoney(FIRST, FIRST_ACC, SECOND, SECOND_ACC, 50.02);
        assertThat(bank.getUserAccounts(SECOND).get(0).getValue(), is(105.57));
    }

    /**
     * Добавляем одного клиента и создаем у него два счета. Осуществляем
     * перевод с первого счета 25 единиц на второй счет и проверяем, что
     * на первом счета осталась сумма 25.1 (с учетом трансфера в тесте выше)
     */
    @Test
    public void whenTransferFromAccountToAccountUserThen() {
        Bank bank = new Bank();
        bank.addUser(FIRST);
        bank.addAccountToUser(FIRST_ACC, FIRST);
        bank.addAccountToUser(SECOND_ACC, FIRST);
        bank.transferMoney(FIRST, FIRST_ACC, FIRST, SECOND_ACC, 25);
        assertThat(bank.getUserAccounts(FIRST).get(0).getValue(), is(25.1));
    }

    /**
     * Добавляем одного клиента и создаем у него два счета. Осуществляем
     * перевод суммы превышающей допустиму. Проверяем что false.
     */
    @Test
    public void whenTransferBigAmountThenFalse() {
        Bank bank = new Bank();
        bank.addUser(SECOND);
        bank.addAccountToUser(FIRST_ACC, SECOND);
        bank.addAccountToUser(SECOND_ACC, SECOND);
        assertThat(bank.transferMoney(SECOND, FIRST_ACC, SECOND, SECOND_ACC, 1000), is(false));
    }
}