package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.dao.DefaultRealization;
import data.pojo.Tag;
import service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    private MainService mainService = new MainService(new ObjectMapper(), new DefaultRealization());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tagList = mainService.getTagList();
        resp.getWriter().write(tagList);
    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tagId = req.getParameter("tagId");
        String tagName = req.getParameter("tagName");
        Tag tag = new Tag(tagId, tagName);
        mainService.updateTag(tag);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteTagId = req.getParameter("tagId");
        mainService.deleteTagById(deleteTagId);
    }
}
