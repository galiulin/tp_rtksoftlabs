package data.dao;

import data.pojo.Tag;

import java.util.Set;

public interface TagDao {

    /**
     * Получение множества {@link Tag} с ограниченным лимитом
     *
     * @param limit максимальное количество выдаваемых {@link Tag}
     */
    Set<Tag> getTagList(int limit);

    /**
     * удаление {@link Tag} по идентификатору
     */
    void deleteTagById(String tagId);


    /**
     * Обновление {@link Tag}
     */
    void updateTag(Tag tag);


    /**
     * Получение {@link Tag} по его id
     */
    Tag getTagById(String tagId);


    /**
     * Поиск ограниченного количества {@link Tag} содержащих в наименовании
     * указанное слово
     *
     * @param limit      Максимальное требуемое количество {@link Tag}.
     * @param searchWord искомое слово
     */
    Set<Tag> getLimitTagsByName(int limit, String searchWord);
}
