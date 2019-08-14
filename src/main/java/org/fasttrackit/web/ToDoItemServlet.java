package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpServer;
import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.ToDoItem;
import org.fasttrackit.service.ToDoItemService;
import org.fasttrackit.transfer.SaveToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/to-do-items")
public class ToDoItemServlet extends HttpServlet {

    private ToDoItemService toDoItemService = new ToDoItemService();


// endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        SaveToDoItemRequest request =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), SaveToDoItemRequest.class);

        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException|ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ToDoItem> toDoItems = toDoItemService.getToDoItems();

            String responseJson = ObjectMapperConfiguration.getObjectMapper()
                    .writeValueAsString(toDoItems);

            resp.getWriter().print(responseJson);
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id= req.getParameter ( "id");

        try {
            toDoItemService.deleteToDoItem(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error " + e.getMessage());
        }
    }
}
