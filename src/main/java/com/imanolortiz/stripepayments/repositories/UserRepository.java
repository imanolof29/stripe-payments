package com.imanolortiz.stripepayments.repositories;

import com.imanolortiz.stripepayments.commons.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
