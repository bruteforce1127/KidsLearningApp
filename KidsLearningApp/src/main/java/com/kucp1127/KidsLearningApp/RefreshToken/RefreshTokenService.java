package com.kucp1127.KidsLearningApp.RefreshToken;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;



//    public RefreshTokens createRefreshTokens(String username){
//
//
//        return RefreshTokens.builder()
//                .token(UUID.randomUUID().toString())
//                .expiry(Instant.now().plusMillis(5*60*60*10000))
//                .username(username)
//                .build();
//    }
//
//    public RefreshTokens validateRefreshTokens(String refreshToken) throws Exception {
//
//        RefreshTokens refreshTokens = refreshTokenRepository
//                .findById(refreshToken).get()
//                ;
//
//        if(refreshTokens.getExpiry().compareTo(Instant.now())<0){
//            throw new Exception("Refresh Token Expired");
//        }
//
//        return refreshTokens;
//    }


}
