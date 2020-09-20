package com.raketlabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.raketlabs.model.User;

@Repository
@Component
public interface UserInfoRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String userName);
	
	boolean existsByUserName(String userName);
}
