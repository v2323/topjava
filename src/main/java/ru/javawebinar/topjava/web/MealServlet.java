package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository repository;
    private UserRepository userRepository;

    @Override
    public void init() {
        repository = new InMemoryMealRepository();
        userRepository = new InMemoryUserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")),
                userId
        );

        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        repository.save(meal, userId);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                int userId = getUserId(request);
                log.info("Delete {}", id);
                repository.delete(id, userId);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        repository.get(getId(request), getUserId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "allBetweenHalfOpen":
                log.info("getAllBetweenHalfOpen");
                request.setAttribute("meals",
                        MealsUtil.getTos(repository.getAllBetweenHalfOpen(getUserId(request),getStart(),getEnd()), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        MealsUtil.getTos(repository.getAll(getUserId(request)), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
//                log.info("getAll");
//                if(request.getParameter("userId")!=null && !request.getParameter("userId").isEmpty()){
//                request.setAttribute("meals",
//                        MealsUtil.getTos(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY).stream().filter(mealTo -> mealTo.getUserId()==Integer.parseInt(request.getParameter("userId"))).collect(Collectors.toList()));
//                } else {
//                    request.setAttribute("meals",
//                            MealsUtil.getTos(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
//                }
//                request.getRequestDispatcher("/meals.jsp").forward(request, response);
//                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private int getUserId(HttpServletRequest request) {
        if (request.getParameter("userId") == null) {
            return 1;
        }
        String paramUserId = Objects.requireNonNull(request.getParameter("userId"));
        return Integer.parseInt(paramUserId);
    }

    private LocalTime getStart() {
//        String paramStart = Objects.requireNonNull(request.getParameter("start"));
//        return LocalTime.parse(paramStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        return LocalTime.parse(paramStart);
        return LocalTime.parse("2020-01-30 09:00" ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private LocalTime getEnd() {
//        String paramEnd = Objects.requireNonNull(request.getParameter("end"));
        return LocalTime.parse("2020-01-30 13:30" ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        return LocalTime.parse(paramEnd);
    }
}
