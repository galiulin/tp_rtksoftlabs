package data.storage;

import data.pojo.Tag;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Repository {
    private Map<String, Tag> tags;

    /*TODO keyNames используется для получения по имени, возможно стоит отказаться*/
    private Map<String, Tag> keyNames;

    public Repository() {
        TreeMap<String, Tag> temp = new TreeMap<>();
        tags = Collections.synchronizedMap(temp);
        keyNames = new ConcurrentHashMap<>();
    }

    /**
     * Добавление тегов
     */
    public void addTag(Tag tag) {

        tag.getId().intern();
        tag.getName().intern();
        this.tags.put(tag.getId(), tag);
        this.keyNames.put(tag.getName(), tag);
    }

    /**
     * получение всех имеющихся тегов
     */
    public Set<Tag> getAllTags() {
        return tags.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    /**
     * получение ограниченного количества тегов
     */
    public Set<Tag> getLimitTags(int limit) {
        return tags.entrySet().stream().limit(limit).map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    /**
     * получение тега по его id
     *
     * @throws RuntimeException {@link TagNotFound} если тег с указанным id не найден
     */
    public Tag getTagById(String tagId) {
        Tag tag = tags.get(tagId);
        if (tag == null) throw new TagNotFound("Тэг с указанным id не найден");
        return tag;
    }

    /**
     * поиск тегов по содержащейся подстроке, результат ограничен
     * <p>
     * TODO переписать коллекцию keyNames для оптимизированного поиска по имени
     */
    public Set<Tag> getLimitTagsBySearchWord(int limit, String searchWord) {
        Set<Tag> set = tags.entrySet().stream().map(Map.Entry::getValue)
                .filter(o -> o.getName().contains(searchWord))
                .limit(limit)
                .collect(Collectors.toSet());
        if (set == null || set.isEmpty()) throw new TagNotFound("теги не найдены");
        return set;
    }

    /**
     * получение тега по имени
     *
     * @throws RuntimeException {@link TagNotFound} если тег с указанным id не найден
     */
    public Tag getTagByName(String name) {
        Tag tag = keyNames.get(name);
        if (tag == null) throw new TagNotFound("Тэг с указанным id не найден");
        return tag;
    }


    /**
     * обновление тега
     */
    public void updateTag(Tag tag) {
        /* т.к. при добавлении в коллекции типа ключ значение
         * по ключу происходит замена - вызываем добавление
         * */
        addTag(tag);
    }

    /**
     * удаление тега по его id
     */
    public void deleteTagById(String tagId) {
        Tag tag = tags.remove(tagId);
        if (tag != null) {
            keyNames.remove(tag.getName());
        }
    }

    /**
     * получение количества элементов в репозитории
     */
    public int getSize() {
        return tags.size();
    }
}
