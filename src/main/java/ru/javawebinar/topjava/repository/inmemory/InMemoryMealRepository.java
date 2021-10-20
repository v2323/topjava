package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
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
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> fillableRepository = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            fillableRepository.put(meal.getId(), meal);
//            repository.put(userId, fillableRepository);
            return meal;
        }
        // handle case: update, but not present in storage
        repository.computeIfPresent(userId,
                (uId, updatableRepository) -> {
                    updatableRepository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
                    return updatableRepository;
                });
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> mealsGetByUserId = repository.get(userId);
        return mealsGetByUserId != null && mealsGetByUserId.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> mealsGetByUserId = repository.get(userId);
        return mealsGetByUserId != null ? mealsGetByUserId.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getAllByPredicate(userId, meal -> true);
    }
    public Collection<Meal> getAllBetweenHalfOpen(int userId, LocalTime start, LocalTime end) {
        return getAllByPredicate(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), start, end));
    }

    @Override
    public Collection<Meal> getAllByPredicate(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}

