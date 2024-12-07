package edu.miu.cs544.moe.configs;

import edu.miu.cs544.moe.service.DistanceEducationService;
import edu.miu.cs544.moe.service.OnCampusService;
import edu.miu.cs544.moe.service.StudentService;
import org.springframework.context.annotation.Bean;

public class JavaConfigs {
    @Bean
    public EntityManagerWrapper entityManagerWrapper() {
        return new EntityManagerWrapper("my-pu");
    }

    @Bean
    public StudentService studentService() {
        return new StudentService(entityManagerWrapper());
    }

    @Bean
    public OnCampusService onCampusService() {
        return new OnCampusService(entityManagerWrapper());
    }

    @Bean
    public DistanceEducationService distanceEducationService() {
        return new DistanceEducationService(entityManagerWrapper());
    }

    @Bean
    public Seeder seeder() {
        Seeder seeder = new Seeder();
        seeder.setStudentService(studentService());
        seeder.setOnCampusService(onCampusService());
        seeder.setDistanceEducationService(distanceEducationService());
        return seeder;
    }
}
