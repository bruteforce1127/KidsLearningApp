package com.kucp1127.KidsLearningApp.Configuration.SecurityConfiguration;

import com.kucp1127.KidsLearningApp.Configuration.Repository.ParentRepository;
import com.kucp1127.KidsLearningApp.Configuration.Service.ParentService;
import com.kucp1127.KidsLearningApp.Configuration.Model.Parents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private ParentRepository userRepository;

    @Autowired
    private ParentService parentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Parents> users = parentService.getParentByUserName(username);
        if(users.isEmpty()){
            System.out.println("Not found");
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(users.get());
    }
}
