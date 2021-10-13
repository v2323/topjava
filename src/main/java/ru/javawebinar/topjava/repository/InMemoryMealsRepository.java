package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class InMemoryMealsRepository implements MealsRepository {

    private AtomicInteger mealIdCounter = new AtomicInteger(0);
    private Map<Integer, Meal> mealsWithId = new ConcurrentHashMap<>();

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
        if (meal.getId() == null) {
            meal.setId(mealIdCounter.incrementAndGet());
            mealsWithId.put(meal.getId(), meal);
            return meal;
        } else {
            return mealsWithId.computeIfPresent(meal.getId(), (id, mealBeforeChanges) -> meal);
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
