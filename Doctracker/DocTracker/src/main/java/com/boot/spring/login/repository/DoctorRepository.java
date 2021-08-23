package com.boot.spring.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.spring.login.entity.Doctor;
import com.boot.spring.login.entity.Slot;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	@Query("SELECT id from User u where u.userName=:name")
	int getId(@Param("name") String name);

	@Query("SELECT id from Doctor d where d.user.id=:id")
	int getDocId(@Param("id") int id);

	
}
