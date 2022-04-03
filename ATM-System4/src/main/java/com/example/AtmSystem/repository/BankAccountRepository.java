package com.example.AtmSystem.repository;

import com.example.AtmSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<User, Long> {

    @Query("select b from User b where b.cardNumber = :сard_number")
    User findByCardNumber(@Param("сard_number") String cardNumber);

    @Query("select b from User b where b.cardNumber = :сard_number and b.password = :password")
    User verification(@Param("сard_number") String cardNumber, @Param("password") String password);

}
