package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_ID = 1;
    public static final int NOT_FOUND = 999999;

    public static final Meal userMeal1 = new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal userMeal2 = new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal userMeal3 = new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 19, 0), "Ужин", 500);
    public static final Meal userMeal4 = new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 600);
    public static final Meal userMeal5 = new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1100);
    public static final Meal userMeal6 = new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 19, 0), "Ужин", 400);
    public static final Meal adminMeal1 = new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 700);
    public static final Meal adminMeal2 = new Meal(8, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1200);
    public static final Meal adminMeal3 = new Meal(9, LocalDateTime.of(2020, Month.JANUARY, 30, 19, 0), "Ужин", 800);
    public static final Meal adminMeal4 = new Meal(10, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 700);
    public static final Meal adminMeal5 = new Meal(11, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 900);
    public static final Meal adminMeal6 = new Meal(12, LocalDateTime.of(2020, Month.JANUARY, 31, 19, 0), "Ужин", 450);

    public static final List<Meal> meals = Arrays.asList(userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0), "Полдник", 850);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        updated.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0));
        updated.setDescription("Ужин");
        updated.setCalories(350);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingComparatorForFields((obj1, obj2) -> 0, "dateTime", "description", "calories").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().comparingOnlyFields("dateTime", "description", "calories").isEqualTo(expected);
    }
}
