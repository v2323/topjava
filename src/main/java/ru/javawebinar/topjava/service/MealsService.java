package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MealsService implements MealsRepository {
    public static void main(String[] args) {
        MealsRepository repo = new MealsService();

        System.out.println(MealsUtil.filteredByStreams(repo.getAllMeals(), 2000));
    }

    public int mealIdCounter = 0;
    public Map<Integer, Meal> mealsWithId = new ConcurrentHashMap<>();

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal);
        }
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealsWithId.values());
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == 0) {
            meal.setId(mealIdCounter++);
            mealsWithId.put(meal.getId(), meal);
            return meal;
        } else {
            return mealsWithId.computeIfPresent(meal.getId(), (id, mealBeforeChanges) -> mealBeforeChanges = meal);
        }
    }

    @Override
    public boolean delete(int id) {
        return mealsWithId.remove(id) != null;
    }

    @Override
    public Meal getOne(int id) {
        return mealsWithId.get(id);
    }
}
