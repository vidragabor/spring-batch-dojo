package hu.vidragabor.springbatchdojo.repository;

import hu.vidragabor.springbatchdojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
