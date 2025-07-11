package com.rtc.app.account.repository;

import com.rtc.app.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
