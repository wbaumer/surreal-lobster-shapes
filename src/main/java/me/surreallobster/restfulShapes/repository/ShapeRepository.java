package me.surreallobster.restfulShapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.surreallobster.restfulShapes.domain.BaseShape;

public interface ShapeRepository extends JpaRepository<BaseShape, Long>{

}
