package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID,USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID,USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
    }

//    @Test
//    public void getAll() {
//    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        int id = updated.getId();
        assertMatch(service.get(id,USER_ID),getUpdated());
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(),USER_ID);
        Integer newId = created.getId();
        Meal newUser = getNew();
        newUser.setId(newId);
        assertMatch(created, newUser);
        assertMatch(service.get(newId,USER_ID), newUser);
    }
//
    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, meals);
    }
}