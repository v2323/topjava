package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Meal get(int id, int userId);

    // ORDERED dateTime desc
    List<Meal> getAll(int userId);

    List<Meal> getAllBetweenHalfOpen(int userId, LocalTime start, LocalTime end);

    List<Meal> getAllBetweenDates(int userId, LocalDate start, LocalDate end);

    List<Meal> getAllByPredicate(int userId, Predicate<Meal> filter);
}
