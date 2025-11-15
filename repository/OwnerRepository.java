package com.nurbergenovv.lab7.repository;

import com.nurbergenovv.lab7.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
