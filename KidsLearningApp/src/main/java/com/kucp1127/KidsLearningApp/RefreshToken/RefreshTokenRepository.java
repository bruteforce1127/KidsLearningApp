package com.kucp1127.KidsLearningApp.RefreshToken;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokens,String> {

}
