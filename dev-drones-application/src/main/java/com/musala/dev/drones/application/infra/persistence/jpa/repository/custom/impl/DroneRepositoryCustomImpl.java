package com.musala.dev.drones.application.infra.persistence.jpa.repository.custom.impl;

import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.custom.DroneRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DroneRepositoryCustomImpl implements DroneRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DroneEntity> findByFilter(DroneFilter filter) {
        final String sql = "SELECT d FROM DroneEntity d " +
                "WHERE 1 = 1 " +
                (filter.minBatteryLevel() != null
                        ? "AND d.batteryLevel >= :minBatteryLevel " : "") +
                (filter.states() != null
                        ? "AND d.state IN :states " : "");

        final var query = em.createQuery(sql, DroneEntity.class);

        if (filter.minBatteryLevel() != null) {
            query.setParameter("minBatteryLevel", filter.minBatteryLevel());
        }
        if (filter.states() != null) {
            query.setParameter("states", filter.states());
        }
        return query.getResultList();
    }
}
