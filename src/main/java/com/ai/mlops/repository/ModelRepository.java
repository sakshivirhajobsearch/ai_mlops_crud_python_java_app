package com.ai.mlops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.mlops.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
