package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query(value = "Select u.username from users u where email is NOT NULL and sentiment_analysis = true", nativeQuery = true)
    List<String> getUserNamesForSA();

    @Query("Select u from User u where email is NOT NULL and sentimentAnalysis = true")
    List<User> getUsersForSA();

    @Query("Select u from User u where u.username = :userName and u.email = :email")
    List<User> getUsersByUserNameAndEmail(@Param("userName") String userName, @Param("email")String email);

}
