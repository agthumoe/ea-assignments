package edu.miu.cs544.moe.service;

import edu.miu.cs544.moe.configs.EntityManagerWrapper;
import edu.miu.cs544.moe.entity.OnCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnCampusService extends AbstractService<OnCampus> implements Service<OnCampus> {
    @Autowired
    public OnCampusService(EntityManagerWrapper entityManagerWrapper) {
        super(entityManagerWrapper, OnCampus.class);
    }
}
