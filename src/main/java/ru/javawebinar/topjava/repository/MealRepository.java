package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalTime;
import java.util.Collection;
import java.util.function.Predicate;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Meal get(int id, int userId);

    // ORDERED dateTime desc
    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllBetweenHalfOpen(int userId, LocalTime start, LocalTime end);

    public Collection<Meal> getAllByPredicate(int userId, Predicate<Meal> filter);
}
