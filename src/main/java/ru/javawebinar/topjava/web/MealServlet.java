package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.repository.MealsRepositoryInMemory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private MealsRepository repository;

    @Override
    public void init() throws ServletException {
        repository = new MealsRepositoryInMemory();
    }

    private static Integer parseAndGetId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");

        switch (action == null ? "getAll" : action) {
            case "delete": {
                repository.delete(parseAndGetId(request));
                response.sendRedirect("meals");
            }
            case "create": {
                Meal meal = new Meal(LocalDateTime.now(), "завтрак", 550);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
            }
            case "edit": {
                Meal meal = repository.getOne(parseAndGetId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
            }
            case "getAll":
            default: {
                request.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAllMeals(), MealsUtil.CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal;
        if (repository.getOne(id) != null) {
            meal = new Meal(id, LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        } else {
            meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        }
        repository.save(meal);
        response.sendRedirect("meals");
    }
}
