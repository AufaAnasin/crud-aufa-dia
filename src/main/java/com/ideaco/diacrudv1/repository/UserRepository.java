package com.ideaco.diacrudv1.repository;

import com.ideaco.diacrudv1.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


// pake integer karena sesuaikan tipe data dari user id
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    // ini untuk query semua item di dalam tabel aufa
    @Query(value = "SELECT * FROM tab_aufa", nativeQuery = true)
    List<UserModel> findAllUserData();
    // isi list nya return dari query diatas, dan diibuatkan fungsi dari user yang bisa dikustom sesuka hati

    @Query(value = "SELECT i FROM UserModel i WHERE i.userId = :userId")
    Optional<UserModel> findByJPQLUser(int userId);
    // optional kayak bisa pake custom function yang mana saja, value nya tetep yang keluar hasil query yang diatas
    Optional<UserModel> findByUserId(int userId);

    // untuk login
    List<UserModel> findByUserName(String userName);
}
