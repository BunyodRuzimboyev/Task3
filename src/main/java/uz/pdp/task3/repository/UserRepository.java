package uz.pdp.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task3.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
