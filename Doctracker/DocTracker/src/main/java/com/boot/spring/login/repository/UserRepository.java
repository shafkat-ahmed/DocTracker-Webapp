package com.boot.spring.login.repository;

import com.boot.spring.login.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

    @Query("SELECT id from User u where u.userName=:s")
    int getUserId(@Param("s") String s);

    @Query("SELECT u from User u where u.userName=:s")
    public List<User> checkUnique(@Param("s") String s);

    @Query("SELECT p from Patient p where p.user.id= :id")
    Patient getPatient(@Param("id") int id);

    @Query("SELECT s from Slot s where s.id=:id")
    Slot getSlot(@Param("id") int id);

    @Query("SELECT d from Doctor d where d.id=:id")
    Doctor getDoctor(@Param("id") int id);
    
    @Query("select field from Doctor d")
    public List<String> getFieldList();

    @Query("select s from Slot s where s.docId =:docId AND s.startTime >:dateTime AND s.patientCount > 0")
    public List<Slot> getSlots(@Param("docId") int docId,@Param("dateTime") LocalDateTime dateTime);
    
    @Query("SELECT d from Doctor d where d.field =:field ") 
    List<Doctor> findByField(@Param("field") String field);
    
    @Query("SELECT d from Doctor d where d.field =:field AND d.lat >:latlower AND d.lat <:latupper AND d.lng >:lnglower AND d.lng <:lngupper ") 
    List<Doctor> findByLoc(@Param("field") String field, @Param("latlower") float latlower, @Param("latupper") float latupper, @Param("lnglower") float lnglower, @Param("lngupper") float lngupper);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.firstName =:fn , u.lastName =:ln , u.userName =:email , u.password =:pass WHERE u.id=:id")
    void updateUser(@Param("fn") String fn,@Param("ln") String ln,@Param("email") String email,@Param("pass") String pass,@Param("id") int id);
}
