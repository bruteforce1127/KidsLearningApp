package com.kucp1127.KidsLearningApp.Configuration.Controller;


import com.kucp1127.KidsLearningApp.Configuration.SecurityConfiguration.JwtService;
import com.kucp1127.KidsLearningApp.Configuration.Service.ChildService;
import com.kucp1127.KidsLearningApp.Configuration.Service.ParentService;
import com.kucp1127.KidsLearningApp.Configuration.Model.Child;
import com.kucp1127.KidsLearningApp.Configuration.Model.ParentClass;
import com.kucp1127.KidsLearningApp.Configuration.Model.Parents;
import com.kucp1127.KidsLearningApp.Configuration.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private ParentService parentService;

    @Autowired
    private ChildService childService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(12);

    @GetMapping("/parents")
    public ResponseEntity<?> getAllParents(){
        Optional<List<Parents>> parents = parentService.getAllParent();
        if(parents.isPresent()) return new ResponseEntity<>(parents.get(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/child")
    public ResponseEntity<?> getAllChild(){
        Optional<List<Child>> children = childService.getAllChild();
        if(children.isPresent()) return new ResponseEntity<>(children.get(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/child/{id}")
    public ResponseEntity<?> getChildByUserName(@PathVariable("id")  String username){
        Optional<Child> child = childService.getChildByUserName(username);
        if(child.isPresent()) return new ResponseEntity<>(child.get(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/parents/{id}")
    public ResponseEntity<?> getParentByUserName(@PathVariable("id")  String username){
        Optional<Parents> parent = parentService.getParentByUserName(username);
        if(parent.isPresent()) return new ResponseEntity<>(parent.get(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PostMapping("/register")
    public void register(@RequestBody ParentClass parent){
        Parents parents = new Parents();
        parents.setEmail(parent.getEmail());
        parents.setUsername(parent.getUsername());
        parents.setFullName(parent.getFullName());
        parents.setPassword(bCryptPasswordEncoder.encode(parent.getPassword()));
        List<String> userNames = new ArrayList<>();

        List<Child> children = parent.getChildren();

        for(Child ele : children){
            childService.postChild(ele);
            userNames.add(ele.getUsername());
        }
        parents.setChildren(userNames);
        parentService.postParent(parents);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()) {
            Optional<Parents> parent = parentService.getParentByUserName(user.getUsername());
            if(parent.isPresent()){
//                Some work will be done
            }
            else{
                Optional<List<Child>> children = childService.getAllChild();
                if(children.isPresent()){
//                    Some work will be done
                }
            }
            return jwtService.generateToken(user.getUsername());
        }
        return "failure";
    }


}
