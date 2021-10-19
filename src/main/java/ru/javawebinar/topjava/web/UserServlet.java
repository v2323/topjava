package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class UserServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private UserRepository repository;

    @Override
    public void init() {
        repository = new InMemoryUserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        User user = new User(id.isEmpty() ? null : Integer.parseInt(id),
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("password"),
                Role.USER);
        log.info(user.isNew() ? "Create {}" : "Update {}", user);
        repository.save(user);
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "getAll" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id);
                response.sendRedirect("users");
                break;
            case "create":
            case "update":
                final User user = action.equals("create") ? new User(null, "", "", "", Role.USER) :
                        repository.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                break;
            case "getByEmail":
                log.info("getByEmail");
                User currentUser = repository.getByEmail(request.getParameter("email"));
                request.setAttribute("users",
                        Collections.singletonList(currentUser));
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
            case "enterAsAdmin":
            case "getAll":
            default:
                log.info("getAll");
                request.setAttribute("users",
                        repository.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

