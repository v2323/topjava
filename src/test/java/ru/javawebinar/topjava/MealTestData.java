package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 999999;
    public static final LocalDate START_DATE = LocalDate.of(2020, 1, 30);
    public static final LocalDate END_DATE = LocalDate.of(2020, 1, 30);
    public static final LocalTime START_TIME = LocalTime.of(9, 0, 0);
    public static final LocalTime END_TIME = LocalTime.of(15, 0, 0);
    public static final int CALORIES_PER_DAY = 2000;

    public static final Meal userMeal1 = new Meal(MEAL_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак юзера", 500);
    public static final Meal userMeal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед юзера", 1000);
    public static final Meal userMeal3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2020, Month.JANUARY, 30, 19, 0), "Ужин юзера", 500);
    public static final Meal userMeal4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак юзера", 600);
    public static final Meal userMeal5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед юзера", 1100);
    public static final Meal userMeal6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2020, Month.JANUARY, 31, 19, 0), "Ужин юзера", 400);
    public static final Meal adminMeal1 = new Meal(MEAL_ID + 6, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак админа", 700);
    public static final Meal adminMeal2 = new Meal(MEAL_ID + 7, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед админа", 1200);
    public static final Meal adminMeal3 = new Meal(MEAL_ID + 8, LocalDateTime.of(2020, Month.JANUARY, 30, 19, 0), "Ужин админа", 800);
    public static final Meal adminMeal4 = new Meal(MEAL_ID + 9, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак админа", 700);
    public static final Meal adminMeal5 = new Meal(MEAL_ID + 10, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед админа", 900);
    public static final Meal adminMeal6 = new Meal(MEAL_ID + 11, LocalDateTime.of(2020, Month.JANUARY, 31, 19, 0), "Ужин админа", 450);

    public static final MealTo userMealTo1 = new MealTo(MEAL_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак юзера", 500, false);
    public static final MealTo userMealTo2 = new MealTo(MEAL_ID + 1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед юзера", 1000, false);

    public static final List<Meal> meals = Arrays.asList(userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);
    public static final List<Meal> mealsBetweenInclusiveDates = Arrays.asList(userMeal3, userMeal2, userMeal1);
    public static final List<MealTo> mealsBetweenInclusiveDateTime = Arrays.asList(userMealTo2, userMealTo1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0), "Полдник", 850);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        updated.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0));
        updated.setDescription("Ужин");
        updated.setCalories(350);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
