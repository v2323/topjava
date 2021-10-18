package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private UserRepository repository;

    @Override
    public void init() {
        repository = new InMemoryUserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
//        Integer userId = Integer.parseInt(request.getParameter("userId"));
        System.out.println(request.getParameter("roles"));


        User user = new User(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("password"),
//                "user3",
//                "user3@mail.ru",
//                "user",
                Role.USER
//                request.getParameter("roles").equals(Role.ADMIN)?Role.ADMIN:Role.USER
//                new HashSet<Role>().add((request.getParameter("roles").equals(Role.ADMIN)?Role.ADMIN:Role.USER))
        );

//        User user = new User(3, "User3", "user3@mail.ru", "user", Role.USER);
        log.info(user.isNew() ? "Create {}" : "Update {}", user);
        repository.save(user);
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "getAll" : action) {
            case "delete":
//                    int id = getId(request);
//                    log.info("Delete {}", id);
//                    repository.delete(id);
//                    response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final User user = "create".equals(action) ?
                        new User(null, "", "", "", Role.USER) :
                        repository.get(getId(request));
//                        repository.get(3);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                break;
            case "getAll":
                log.info("getAll");
                request.setAttribute("users",
                        repository.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
            case "getByEmail":
                log.info("getByEmail");
                User currentUser = repository.getByEmail("user@mail.ru");
                request.setAttribute("users",
                        Collections.singletonList(currentUser));
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
            default:
//                    log.info("getAll");
//                    request.setAttribute("users",
//                          repository.getAll());
//                    request.getRequestDispatcher("/users.jsp").forward(request, response);
//                    break;
        }
        log.debug("forward to users");
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
