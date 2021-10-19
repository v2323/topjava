package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 2));
        save(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 1000), 1);
        save(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 13, 0), "Обед", 800), 1);
        save(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 18, 0), "Ужин", 600), 1);
        save(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 2, 10, 0), "Завтрак", 900), 3);
        save(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 2, 13, 0), "Обед", 1200), 3);
        save(new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 2, 18, 0), "Ужин", 700), 3);
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        Map<Integer, Meal> repositoryWithoutUserId = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repositoryWithoutUserId.put(meal.getId(), meal);
            repository.put(userId, repositoryWithoutUserId);
            return meal;
        }
        // handle case: update, but not present in storage
        repository.computeIfPresent(userId,
                (uId, repositoryWithoutUId) -> {
                    repositoryWithoutUId.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
                    return repositoryWithoutUId;
                });

        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId) != null ? repository.get(userId).get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals.values().stream().
                sorted(Comparator.comparing(Meal::getDate).reversed()).
                collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    public Collection<Meal> getAllBetweenHalfOpen(int userId, LocalTime start, LocalTime end) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals.values().stream().filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), start, end)).
                sorted(Comparator.comparing(Meal::getDate).reversed()).
                collect(Collectors.toList()) : new ArrayList<>();
    }
}

