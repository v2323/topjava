package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import javax.persistence.NamedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id,@Param("userId") int userId );

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(@Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime>=:startDateTime AND m.dateTime<:endDateTime ORDER BY m.dateTime DESC")
    List<Meal> getBetweenHalfOpen(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("userId") int userId);

//    @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
//    @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
//    @NamedQuery(name = Meal.GET_BETWEEN, query = """
//                    SELECT m FROM Meal m
//                    WHERE m.user.id=:userId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime ORDER BY m.dateTime DESC
//                """),
//    @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
//    @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id=?1 AND m.user.id=?2"),
//    @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
//    @NamedQuery(name = Meal.GET_BETWEEN, query = "SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime>=?1 AND m.dateTime<?2 ORDER BY m.dateTime DESC"),
}
