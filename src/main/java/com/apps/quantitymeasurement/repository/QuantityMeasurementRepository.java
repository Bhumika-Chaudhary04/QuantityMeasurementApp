package com.apps.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.entity.User;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

	List<QuantityMeasurementEntity> findByUser(User user);

	List<QuantityMeasurementEntity> findByUserAndOperationIgnoreCase(User user, String operation);

	@Modifying
	@Transactional
	@Query("DELETE FROM QuantityMeasurementEntity q WHERE q.user = :user")
	void deleteAllByUser(User user);
}