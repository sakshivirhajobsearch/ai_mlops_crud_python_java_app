package com.ai.mlops.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.mlops.model.Model;
import com.ai.mlops.repository.ModelRepository;

@Service
public class ModelService {
	private final ModelRepository repository;

	public ModelService(ModelRepository repository) {
		this.repository = repository;
	}

	public List<Model> getAll() {
		return repository.findAll();
	}

	public Model getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Model create(Model model) {
		return repository.save(model);
	}

	public Model update(Long id, Model model) {
		Model existing = repository.findById(id).orElse(null);
		if (existing != null) {
			existing.setName(model.getName());
			existing.setDescription(model.getDescription());
			repository.save(existing);
		}
		return existing;
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}