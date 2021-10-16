package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private UserRepository repository;

    @Override
    public void init() {
        repository = new InMemoryUserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");

            switch (action == null ? "all" : action) {
                case "delete":
//                    int id = getId(request);
//                    log.info("Delete {}", id);
//                    repository.delete(id);
//                    response.sendRedirect("meals");
                    break;
                case "create":
                case "update":
//                    final Meal meal = "create".equals(action) ?
//                            new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
//                            repository.get(getId(request));
//                    request.setAttribute("meal", meal);
//                    request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                    break;
                case "all":
                default:
                    log.info("getAll");
                    request.setAttribute("users",
                          repository.getAll());
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                    break;
            }
        log.debug("forward to users");
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
