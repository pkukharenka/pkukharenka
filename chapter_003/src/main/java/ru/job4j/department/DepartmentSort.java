package ru.job4j.department;


import java.util.*;

/**
 * Класс позволяет добавить недостающие подразделения
 * организации и остортировать их в порядке убывания и
 * возростания.
 *
 * @author Pyotr Kukharenka
 * @since 15.12.2017
 */

public class DepartmentSort {

    /**
     * Метод возвращает отсортированный массив с добавленными
     * подразделениями, которые отсутствовали изначально.
     * <p>
     * Метод осуществляет преобразование входящего массива в
     * список уникальных коллекций, содержащих дерево подразделения.
     * Сортировка осуществляется по средствам компаратора, пердаваемого
     * в метод в качестве параметра. В первую очередь с помощью метода
     * splitArray производится разбивка элементов (строки) входящего массива
     * по разделителю "\". Полученная коллекция добавляется в Set, который
     * в дальгейшем поможет избежать дублирующих коллекций.
     * <p>
     * Цикл for просматривает полученную коллекцию и с помощью метода
     * subList мы получаем коллекции родительских подразделений указанного
     * подразделения. Данный цикл позволит нам добавить отсутсвующие подразделения
     *
     * @param array      - массив строковых значений.
     * @param comparator - компаратор.
     * @return отсортированный массив.
     */
    public String[] checkDepart(String[] array, Comparator<List<String>> comparator) {
        Set<List<String>> values = new TreeSet<>(comparator);
        for (String str : array) {
            List<String> temp = this.splitArray(str);
            values.add(temp);
            for (int pos = 1; pos <= temp.size() - 1; pos++) {
                List<String> test = new ArrayList<>(temp.subList(0, pos));
                values.add(test);
            }
        }
        return this.toArray(values);
    }

    /**
     * Метод возвращает коллекцию дерева одного подразделения.
     *
     * @param dep - строковое обозначения дерева
     * @return коллекция подразделений.
     */
    private List<String> splitArray(String dep) {
        return new ArrayList<>(Arrays.asList(dep.split("[\\\\]")));

    }

    /**
     * Метод возвращает преобразованный Set в отсортированный массив строк вида "ХХ\ХХ\ХХХ"
     * С помощью метода join производится слияние элементов каждой коллекции
     * в единое строкове значение с раздлителем "\". При этом преобразование
     * коллекции в массив производится методом x.toArray(T[]).
     *
     * @param set - коллекция значений.
     * @return
     */
    private String[] toArray(Set<List<String>> set) {
        List<String> list = new ArrayList<>();
        for (List<String> strings : set) {
            list.add(String.join("\\", strings.toArray(new String[strings.size()])));
        }
        return list.toArray(new String[list.size()]);
    }
}

/**
 * Класс компаратор, осуществляющий осртировку элементов коллекции
 * по возрастанию старшего в иерархии подразделения.
 */
class AscComparator implements Comparator<List<String>> {

    /**
     * Метод возвращает 1 если коллекция о1 по иерархии выше
     * коллекции о2, -1 если о1 ниже о2 и 0 - если содержимое
     * коллекций равны. Результат достигается за счет поэлементного
     * сравнения элементов двух коллекций, что реализуется за счет
     * итераторов каждой коллекции. В случае если результат сравнения
     * первых элементов равен 0 выполняется метод checkChild.
     *
     * @param o1 - первая коллекция.
     * @param o2 - вторая коллекция.
     * @return 0, 1, или -1.
     */
    @Override
    public int compare(List<String> o1, List<String> o2) {
        Iterator<String> it1 = o1.iterator();
        Iterator<String> it2 = o2.iterator();
        int res = it1.next().compareTo(it2.next());
        return res != 0 ? res : this.checkChild(it1, it2);
    }

    /**
     * Метод осуществляет поэлементное сравнение элементов коллекций
     * следующий за первыми. Логика заключается в следующем:
     * возможно 3 варианта развития событий:
     * 1. вторые (третьи) элементы есть в 2 коллекциях -> сравниваем элементы ->
     * -> повторяем пункт 1.
     * 2. Элемент есть в 1 коллекции, а во второй нет -> o1 выше о2.
     * 3. Элемент есть во 2 коллекции, а в первой нет -> o1 ниже о2.
     *
     * @param it1 - итератор по коллекции о1.
     * @param it2 - итератор по коллекции о2.
     * @return 0, 1, или -1.
     */
    public int checkChild(Iterator<String> it1, Iterator<String> it2) {
        int res = 0;
        if (it1.hasNext() && it2.hasNext()) {
            res = it1.next().compareTo(it2.next());
            if (res == 0 && it1.hasNext() && it2.hasNext()) {
                res = it1.next().compareTo(it2.next());
                if (res == 0 && (it1.hasNext() && !it2.hasNext())) {
                    res = 1;
                } else if (res == 0 && (!it1.hasNext() && it2.hasNext())) {
                    res = -1;
                }
            } else if (res == 0 && it1.hasNext() && !it2.hasNext()) {
                res = 1;
            } else if (res == 0 && !it1.hasNext() && it2.hasNext()) {
                res = -1;
            }
        } else if (it1.hasNext() && !it2.hasNext()) {
            res = 1;
        } else if (!it1.hasNext() && it2.hasNext()) {
            res = -1;
        }
        return res;
    }
}

/**
 * Класс компаратор, осуществляющий осртировку элементов коллекции
 * по убыванию старшего в иерархии подразделения, расширяет класс
 * AscComparator.
 */
class DescComparator extends AscComparator implements Comparator<List<String>> {
    /**
     * Метод возвращает 1 если коллекция о2 по иерархии выше
     * коллекции о1, -1 если о2 ниже о1 и 0 - если содержимое
     * коллекций равны. Результат достигается за счет поэлементного
     * сравнения элементов двух коллекций, что реализуется за счет
     * итераторов каждой коллекции. В случае если результат сравнения
     * первых элементов равен 0 выполняется метод класс родителя checkChild.
     *
     * @param o1 - первая коллекция.
     * @param o2 - вторая коллекция.
     * @return 0, 1, или -1.
     */
    @Override
    public int compare(List<String> o1, List<String> o2) {
        Iterator<String> it1 = o1.iterator();
        Iterator<String> it2 = o2.iterator();
        int res = it2.next().compareTo(it1.next());
        return res != 0 ? res : super.checkChild(it1, it2);
    }
}