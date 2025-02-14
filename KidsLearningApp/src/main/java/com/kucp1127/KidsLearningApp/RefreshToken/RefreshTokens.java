package com.kucp1127.KidsLearningApp.RefreshToken;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.Instant;


//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokens {

    @Id
    private String token;
    private Instant expiry;
    private String username;

}
