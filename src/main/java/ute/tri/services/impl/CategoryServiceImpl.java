package ute.tri.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ute.tri.entity.CategoryEntity;
import ute.tri.repository.CategoryRepository;
import ute.tri.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public <S extends CategoryEntity> S save(S entity) {
        if (entity.getCategoryId() == null) {
            return categoryRepository.save(entity);
        } else {
            Optional<CategoryEntity> opt = categoryRepository.findById((Long) entity.getCategoryId());
            if (opt.isPresent()) {
                if (StringUtils.isEmpty(entity.getName())) {
                    entity.setName(opt.get().getName());
                }
                return categoryRepository.save(entity);
            }
        }
        return null;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<CategoryEntity> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(CategoryEntity entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public Page<CategoryEntity> findByNameContaining(String name, Pageable pageable) {
        return categoryRepository.findByNameContaining(name, pageable);
    }

    @Override
    public <S extends CategoryEntity> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

	@Override
	public List<CategoryEntity> findAl1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryEntity> findById(Object object) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<CategoryEntity> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CategoryEntity> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
