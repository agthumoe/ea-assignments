package edu.miu.cs544.moe.service;

import edu.miu.cs544.moe.configs.EntityManagerWrapper;
import edu.miu.cs544.moe.entity.DistanceEducation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistanceEducationService extends AbstractService<DistanceEducation> implements Service<DistanceEducation> {
    @Autowired
    public DistanceEducationService(EntityManagerWrapper entityManagerWrapper) {
        super(entityManagerWrapper, DistanceEducation.class);
    }
}
