package data.dao;

import data.pojo.Tag;
import data.storage.Repository;
import utils.FillEmptyRepo;

import java.util.Set;

public class DefaultRealization implements TagDao {
    private Repository repository;

    public DefaultRealization(){
        repository = new Repository();
    }

    public DefaultRealization(Repository repository){
        this.repository = repository;
    }

    @Override
    public Set<Tag> getTagList(int limit) {
        fillRepo();
        return repository.getLimitTags(limit);
    }

    @Override
    public void deleteTagById(String tagId) {
        repository.deleteTagById(tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        repository.updateTag(tag);
    }

    @Override
    public Tag getTagById(String tagId) {
        return repository.getTagById(tagId);
    }

    @Override
    public Set<Tag> getLimitTagsByName(int limit, String searchWord) {
        fillRepo();
        return repository.getLimitTagsBySearchWord(limit, searchWord);
    }

    /**
     * вспомогательный метод для наполнения базы примерами
     * */
    private void fillRepo(){
        FillEmptyRepo.fillWithData(repository);
    }
}
