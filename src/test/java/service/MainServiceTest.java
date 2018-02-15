package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.MainController;
import data.dao.DefaultRealization;
import data.pojo.Tag;
import data.storage.Repository;
import data.storage.RepositoryTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class MainServiceTest {
    private MainService mainService;
    private List<Tag> tags;

    @BeforeMethod
    public void setUp() throws Exception {
        Repository repository = new Repository();
        tags = RepositoryTest.fillWithData(repository);

        mainService = new MainService(new ObjectMapper(), new DefaultRealization(repository));
    }

    @Test
    public void testGetTagList() throws Exception {
        System.out.println(mainService.getTagList());
    }

    @Test
    public void testDeleteTagById() throws Exception {
    }

    @Test
    public void testUpdateTag() throws Exception {
    }

    @Test
    public void testGetTagById() throws Exception {
    }

    @Test
    public void testGetLimitTagsByName() throws Exception {
    }
}