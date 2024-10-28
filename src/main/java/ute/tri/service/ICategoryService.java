package ute.tri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ute.tri.entity.Category;
import ute.tri.entity.CategoryEntity;

public interface ICategoryService {

	List<CategoryEntity> findAl1();

	<S extends CategoryEntity> S save(S entity);

	Optional<CategoryEntity> findById(Long id);

	Page<CategoryEntity> findAll(Pageable pageable);

	List<CategoryEntity> findAll();

	Optional<CategoryEntity> findById(Object object);

	List<CategoryEntity> findAll(Sort sort);

	List<CategoryEntity> findAllById(Iterable<Long> ids);

	<S extends CategoryEntity> Optional<S> findOne(Example<S> example);

	Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);

	void deleteById(Long categoryId);

	long count();

	void delete(CategoryEntity entity);

	void deleteAll();

	Page<CategoryEntity> findByNameContaining(String name);

}
