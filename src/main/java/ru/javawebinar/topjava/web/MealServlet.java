package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.repository.InMemoryMealsRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private MealsRepository repository;

    @Override
    public void init() throws ServletException {
        repository = new InMemoryMealsRepository();
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
                break;
            }
            case "create": {
                Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "завтрак", 550);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
                break;
            }
            case "edit": {
                Meal meal = repository.getOne(parseAndGetId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
                break;
            }
            case "getAll":
            default: {
                request.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAllMeals(), MealsUtil.CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal;
        if(request.getParameter("id")!=null && !request.getParameter("id").isEmpty()) {
            int id = parseAndGetId(request);
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
