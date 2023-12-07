package com.vibhanshu.qpassessment;

import com.vibhanshu.qpassessment.entities.Role;
import com.vibhanshu.qpassessment.entities.User;
import com.vibhanshu.qpassessment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "com.vibhanshu.*")
public class QpAssessmentApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(QpAssessmentApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User adminUser = userRepository.findByRole(Role.ADMIN);

        if(adminUser == null) {
            User user = new User();
            user.setUsername("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }

    }
}
