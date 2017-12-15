package ru.job4j.bank;

import java.util.*;

/**
 * Класс, описывающий основные функции бакнка.
 *
 * @author Pyotr Kukharenka
 * @since 13.12.2017
 */

public class Bank {
    /**
     * Список клиентов банка и их банковских счетов
     */
    private Map<User, List<Account>> base = new HashMap<>();

    /**
     * Метод добавления клиента в базу. При этом изначально
     * клиент инициализируется без коллекции банковских счетов.
     *
     * @param user - клиент.
     */
    public void addUser(User user) {
        this.base.put(user, null);
    }

    /**
     * Метод производит удаление клиента из базы.
     *
     * @param user - клиент.
     */
    public void deleteUser(User user) {
        this.base.remove(user);
    }

    /**
     * Метод производит добавление счета клиенту. При этом изнально
     * проверяется существует ли у этого клиента счета, если да, то
     * в коллекцию добавляется новый счет. Если же нет, по ключу
     * добавляется коллекция и только потом в нее записывается новый счет.
     *
     * @param account - банковский счет.
     * @param user    - клиент.
     */
    public void addAccountToUser(Account account, User user) {
        this.base.putIfAbsent(user, new ArrayList<>());
        this.base.get(user).add(account);
    }

    /**
     * Метод удаляет счет клиента.
     *
     * @param account - банковский счет.
     * @param user    - клиент.
     */
    public void deleteAccountFromUser(Account account, User user) {
        this.base.get(user).remove(account);
    }

    /**
     * Метод возвращает список счетов конкретного клиента.
     *
     * @param user - клиент.
     * @return - список счетов клиента.
     */
    public List<Account> getUserAccounts(User user) {
        return this.base.get(user);
    }

    /**
     * Метод возвращает список всех клиентов банка.
     *
     * @return список клиентов
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(this.base.keySet());
        return users;
    }

    /**
     * Метод производит перевод денег с одного банковского счета
     * на другой. Перевод произойдет только в том случае, если
     * счет куда переводятся деньги существует и количество денег
     * на счете с которого будет перевлд не меньше суммы перевода.
     *
     * @param srcUser - клиент источник перевода
     * @param srcAccount - счет с которого осуществляется перевод
     * @param dstUser - клиент получатель.
     * @param dstAccount - счет получателя.
     * @param amount - сумма перевода
     * @return true если все прошло успешно.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser,
                                 Account dstAccount, double amount) {
        boolean flag = false;
        if (this.base.get(dstUser).contains(dstAccount) && amount <= dstAccount.getValue()) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
            flag = true;
        }
        return flag;
    }

}
