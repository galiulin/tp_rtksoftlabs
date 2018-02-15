package utils;

import data.pojo.Tag;
import data.storage.Repository;

import java.util.HashSet;
import java.util.Set;

public class FillEmptyRepo {
    /**
     * Вспомогательный метод для заполнения данными
     * если репозиторий пустой то
     * добавляет 6 {@link Tag} в {@link Repository},
     * иначе возвращает все элементы из репозитория.
     *
     * @return список добавленных {@link Tag}
     */
    public static Set<Tag> fillWithData(Repository repository) {


        if (repository == null) throw new IllegalArgumentException("Репозиторий не может быть null");

        if (repository.getSize() != 0) {
            return repository.getAllTags();
        }

        Set<Tag> list = new HashSet<>(10);
        list.add(new Tag("1", "tag_1"));
        list.add(new Tag("2", "tag_2"));
        list.add(new Tag("3", "tag_3"));
        list.add(new Tag("4", "tag_4"));
        list.add(new Tag("5", "tag_5"));
        list.add(new Tag("6", "tag_6"));

        list.forEach(repository::addTag);

        return list;
    }

}
