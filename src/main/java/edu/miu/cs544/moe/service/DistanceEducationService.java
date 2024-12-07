package edu.miu.cs544.moe.service;

import edu.miu.cs544.moe.configs.EntityManagerWrapper;
import edu.miu.cs544.moe.entity.DistanceEducation;

public class DistanceEducationService extends AbstractService<DistanceEducation> implements Service<DistanceEducation> {
    public DistanceEducationService(EntityManagerWrapper entityManagerWrapper) {
        super(entityManagerWrapper, DistanceEducation.class);
    }
}
