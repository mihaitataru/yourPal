package com.fourengineering.yourpal.Repositories;

import com.fourengineering.yourpal.Entities.User;
import com.fourengineering.yourpal.Misc.BadHabits;
import com.fourengineering.yourpal.Misc.Hobbies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByHobbies(Hobbies hobby);
    public List<User> findByBadHabits(BadHabits badHabits);
    public User findByEmail(String email);
}
