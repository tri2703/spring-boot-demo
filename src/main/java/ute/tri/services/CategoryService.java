package ute.tri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ute.tri.entity.Category;
import ute.tri.repository.CategoryRepository;

public interface CategoryService {

	void deleteById(Long id);

	long count();

	Optional<Category> findById1(Long id);

	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll(Sort sort);

	<S extends Category> S save(S entity);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	Optional<Category> findByName(String name);
	
	 public static final CategoryRepository categoryRepository = null;

}
