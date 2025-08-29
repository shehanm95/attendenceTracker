package com.tec.attendenceTracker.repository;

import com.tec.attendenceTracker.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
