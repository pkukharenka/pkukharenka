package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Model;
import ru.job4j.service.ModelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 06.04.2018
 */

@Slf4j
@WebServlet("/json")
public class MainServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ModelServiceImpl modelService = new ModelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        final String action = req.getParameter("action");
        final String name = req.getParameter("query");
        List<Model> result = null;
        if ("fillBrand".equals(action)) {
            result = this.modelService.findBrands(name);
        } else if ("fillModel".equals(action)) {
            result = this.modelService.findModelsByBrand(Long.parseLong(req.getParameter("id")));
        }


        String ready = this.mapper.writeValueAsString(result);
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"))) {
            pw.print(ready);
            pw.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
