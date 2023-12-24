package org.practice.module4practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements ApplicationRunner {

    @Autowired
    private UniversityService us;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        us.getAndSaveUniversities().subscribe();
    }
}
