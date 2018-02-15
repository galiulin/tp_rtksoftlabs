package data.dao;

import data.pojo.Tag;
import data.storage.Repository;
import data.storage.RepositoryTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class DefaultRealizationTest {
    private static List<Tag> list;
    private static TagDao tagDao;

    @BeforeMethod
    public void setUp() throws Exception {
        Repository repository = new Repository();
        list = RepositoryTest.fillWithData(repository);
        tagDao = new DefaultRealization(repository);
    }

    /**
     * получение множества тегов с указанным лимитом
     */
    @Test
    public void testGetTagList() throws Exception {
        int limit = ((list.size() * 100) - (list.size() * 30)) / 100;

        Set<Tag> limitTags = tagDao.getTagList(limit);
        assertEquals(limitTags.size(), limit, "проверка на количество лимитированных");
    }

    /**
     * удаление тега по id
     */
    @Test
    public void testDeleteTagById() throws Exception {
        Tag tag = list.get(1);
        Tag tagByIdBefore = tagDao.getTagById(tag.getId());
        assertNotNull(tagByIdBefore, "проверка на то что указанный тег существует");
        tagDao.deleteTagById(tag.getId());
        Tag tagById = tagDao.getTagById(tag.getId());
        assertNull(tagById, "проверка на отсутствие");
    }


    /**
     * обновление тега
     */
    @Test
    public void testUpdateTag() throws Exception {
        Tag tag = list.get(1);
        Tag tagBeforeUpdate = tagDao.getTagById(tag.getId());
        assertEquals(tagBeforeUpdate.getName(), tag.getName(), "искомый объект не добавлен");
        Tag newTag = new Tag(tag.getId(), "this is new tagName");
        tagDao.updateTag(newTag);
        Tag tagAfterUpdate = tagDao.getTagById(tag.getId());
        assertEquals(tagAfterUpdate.getName(), newTag.getName(), "новое имя не установилось");
        assertNotEquals(tagAfterUpdate.getName(), tagBeforeUpdate.getName(), "имя не изменилось");
    }


    /**
     * получение тегов по id
     */
    @Test
    public void testGetTagById() throws Exception {
        for (Tag tag : list) {
            assertEquals(tagDao.getTagById(tag.getId()), tag);
        }
    }

    /**
     * получение тегов по имени
     */
    @Test
    public void testGetLimitTagsByName() throws Exception {
        Tag tagGetName = list.get(1);
        String searchWord = tagGetName.getName().split("_")[0];
        int limit = ((list.size() * 100) - (list.size() * 30)) / 100;
        Set<Tag> limitTagsByName = tagDao.getLimitTagsByName(limit, searchWord);
        assertEquals(limitTagsByName.size(), limit);
        for (Tag tag : limitTagsByName) {
            assertTrue(tag.getName().contains(searchWord));
        }

    }
}