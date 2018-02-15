package data.storage;

import data.pojo.Tag;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class RepositoryTest {
    private static Repository repository;

    @BeforeMethod
    public void setUp() throws Exception {
        repository = new Repository();
    }

    /**
     * Вспомогательный метод для заполнения данными
     * добавляет 6 {@link Tag} в {@link Repository}
     *
     * @return список добавленных {@link Tag}
     */
    public static List<Tag> fillWithData(Repository repository) {
        if (repository == null) repository = new Repository();

        List<Tag> list = new ArrayList<>(10);
        list.add(new Tag("1", "tag_1"));
        list.add(new Tag("2", "tag_2"));
        list.add(new Tag("3", "tag_3"));
        list.add(new Tag("4", "tag_4"));
        list.add(new Tag("5", "tag_5"));
        list.add(new Tag("6", "tag_6"));

        list.forEach(repository::addTag);

        return list;
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    /**
     * проверка добавления единичного объекта
     */
    @Test
    public void testAddTag() throws Exception {
        String id = "simple_id";
        Tag tag = new Tag(id, "simple_tag");
        repository.addTag(tag);
        Tag tagFromRepos = repository.getTagById(id);
        assertEquals(tagFromRepos, tag);
    }

    /**
     * проверка на получение всех тегов
     */
    @Test
    public void testGetAllTag() throws Exception {
        List<Tag> tags = fillWithData(repository);
        Set<Tag> allTags = repository.getAllTags();
        assertEquals(allTags, tags, "сравнение совпадающих коллекций");

        Tag tag = repository.getLimitTags(1).stream().findFirst().get();
        repository.deleteTagById(tag.getId());
        Set<Tag> oneTagRemoved = repository.getAllTags();
        assertNotEquals(oneTagRemoved, tags, "сравнение коллекций после того как " +
                "один тег удалили");
    }

//    @Test
//    public void testGetNot() throws Exception {
//        List<Tag> tags = fillWithData(repository);
//        System.out.println(repository.getTagById("asdfasdf"));
//    }
}