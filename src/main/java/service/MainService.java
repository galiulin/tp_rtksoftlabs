package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.dao.TagDao;
import data.pojo.Tag;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class MainService {
    private final String PROPERTY_FILE_PATH = "src/main/resources/controller.properties";

    private final ObjectMapper mapper;
    private final TagDao tagDao;

    /*пока будет тут*/
    private Properties props;

    public MainService(ObjectMapper mapper, TagDao tagDao) {

        this.mapper = mapper;
        this.tagDao = tagDao;
        props = new Properties();
        props.put("controller.tag.limit", 100);

        /*TODO сделать загрузку проперти файла*/
//        try {
//            props.load(input);
//        } catch (IOException e) {
//            /*fixme обработать exception*/
//            throw new RuntimeException("не удалось прочесть файл с настройками " + PROPERTY_FILE_PATH, e);
//        }
    }


    /**
     * Получение json c объектами {@link Tag} с ограниченным лимитом
     * <p>
     * максимальное количество выдаваемых задается в файле controller.properties
     * параметром controller.tag.limit
     * fixme в настоящий момент выставляется захардкоженное значение, property файл не используется
     */
    public String getTagList() {
        try {
            int limit = (int) props.get("controller.tag.limit");
            Set<Tag> tagSet = tagDao.getTagList(limit);
            return mapper.writeValueAsString(tagSet);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("значение задано не корректно");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("не удалось собрать json");
        }
    }

    /**
     * удаление {@link Tag} по идентификатору
     */
    public void deleteTagById(String tagId) {
        tagDao.deleteTagById(tagId);
    }


    /**
     * Обновление {@link Tag}
     *
     * @param tag {@link Tag} как Json
     */
    public void updateTag(String tag) {
        try {
            Tag tempTag = mapper.readValue(tag, Tag.class);
            tagDao.updateTag(tempTag);
        } catch (IOException e) {
            /**
             * fixme написать обертку для текущего исключения
             * */
            throw new RuntimeException("не удалось прочесть тег ", e);
        }
    }

    public void updateTag(Tag tag){
        tagDao.updateTag(tag);
    }


    /**
     * Получение {@link Tag} по его id
     */
    public String getTagById(String tagId) {
        try {
            Tag tagById = tagDao.getTagById(tagId);
            String json = mapper.writeValueAsString(tagById);
            return json;
        } catch (JsonProcessingException e) {
            /**
             * fixme написать обертку \ обработать исключение
             * */
            throw new RuntimeException("не удалось сконвертировать тег в JSON", e);
        }
    }


    /**
     * Поиск ограниченного количества {@link Tag} содержащих в наименовании
     * указанное слово
     *
     * @param searchWord искомое слово
     */
    public String getLimitTagsByName(String searchWord) {
        try {
            int limit = (int) props.get("controller.tag.limit");
            Set<Tag> tagSet = tagDao.getLimitTagsByName(limit, searchWord);
            return mapper.writeValueAsString(tagSet);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("значение задано не корректно");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("не удалось собрать json");
        }
    }


}
