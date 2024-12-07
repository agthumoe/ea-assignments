package edu.miu.cs544.moe.service;

import edu.miu.cs544.moe.configs.EntityManagerWrapper;
import edu.miu.cs544.moe.entity.OnCampus;

public class OnCampusService extends AbstractService<OnCampus> implements Service<OnCampus> {
    public OnCampusService(EntityManagerWrapper entityManagerWrapper) {
        super(entityManagerWrapper, OnCampus.class);
    }
}
