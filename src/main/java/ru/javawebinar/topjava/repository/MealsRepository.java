package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsRepository {

    List<Meal> getAllMeals();

    Meal save(Meal meal);

    boolean delete(int id);

    Meal getOne(int id);
}
