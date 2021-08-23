package com.boot.spring.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.spring.login.entity.Manager;
import com.boot.spring.login.entity.Slot;
import com.boot.spring.login.entity.User;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{
    
	@Query("SELECT id from User u where u.userName=:s")
	int getManId(@Param("s") String s);

	@Query("SELECT m from Manager m where m.user.id= :id")
	Manager getMan(@Param("id") int id);

	@Query("SELECT s from Slot s where s.docId= :docId")
	Slot getSlot(@Param("docId") int docId);

	@Query("SELECT s from Slot s where s.docId= :docId")
	List<Slot> getAllSlot(@Param("docId") int docId);
    
	@Query("SELECT s from Slot s where s.id = :id")
	Slot getUpdateSlot(int id);
	
	
	
}
