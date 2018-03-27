package ru.job4j.todolist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.dao.ItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 * Сервлет
 *
 * @author Pyotr Kukharenka
 * @since 21.03.2018
 */

@WebServlet("/item")
public class ItemServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ItemServlet.class);
    private static final String SHOW_ALL = "showAll";
    private static final String EDIT = "edit";
    private static final String ADD = "add";
    private static final String DEL = "delete";
    private final ItemDao repository = new ItemDao();
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        final String action = req.getParameter("command");
        LOG.info(action);
        String result = "";
        switch (action) {
            case SHOW_ALL:
                result = this.showAll(req);
                break;
            case EDIT:
                result = this.edit(req);
                break;
            case ADD:
                result = this.add(req);
                break;
            case DEL:
                this.delete(req);
                break;
            default:
                break;
        }
        this.writeResponse(resp, result);
    }

    private void writeResponse(HttpServletResponse resp, String result) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"))) {
            pw.write(result);
            pw.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void delete(HttpServletRequest req) {
        final Item item = new Item();
        item.setId(Integer.parseInt(req.getParameter("id")));
        this.repository.delete(item);
    }

    private String add(HttpServletRequest req) throws JsonProcessingException {
        final Item item = new Item();
        item.setDesc(req.getParameter("desc"));
        item.setCreated(LocalDate.now());
        item.setDone(false);
        this.repository.save(item);
        return mapper.writeValueAsString(item);
    }

    private String showAll(HttpServletRequest req) throws JsonProcessingException {
        List<Item> items;
        final boolean done = Boolean.valueOf(req.getParameter("done"));
        if (done) {
            items = this.repository.findItemsByDone();
        } else {
            items = this.repository.findAll();
        }
        return mapper.writeValueAsString(items);
    }

    private String edit(HttpServletRequest req) throws JsonProcessingException {
        final Item item = this.repository.getItem(Integer.parseInt(req.getParameter("id")));
        item.setDone(Boolean.valueOf(req.getParameter("done")));
        LOG.info("{}", item);
        this.repository.update(item);
        return mapper.writeValueAsString(item);
    }
}
