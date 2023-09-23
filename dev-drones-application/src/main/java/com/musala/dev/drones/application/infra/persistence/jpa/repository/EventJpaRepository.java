package com.musala.dev.drones.application.infra.persistence.jpa.repository;

import com.musala.dev.drones.application.infra.persistence.jpa.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {
}
