package com.ai.mlops.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.mlops.model.Model;
import com.ai.mlops.services.ModelService;

@RestController
@RequestMapping("/api/models")
public class ModelController {

	private final ModelService service;

	public ModelController(ModelService service) {
		this.service = service;
	}

	// ----- CRUD Endpoints -----

	@GetMapping
	public List<Model> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Model getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	public Model create(@RequestBody Model model) {
		return service.create(model);
	}

	@PutMapping("/{id}")
	public Model update(@PathVariable Long id, @RequestBody Model model) {
		return service.update(id, model);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	// ----- Prediction Endpoint -----
	@GetMapping("/predict")
	public String predict(@RequestParam double feature1, @RequestParam double feature2) {
		try {
			// Use full path to Python in the virtual environment
			String pythonExe = "E:\\Project\\ai_mlops_crud_python_java_app\\ml-python\\venv\\Scripts\\python.exe";

			ProcessBuilder pb = new ProcessBuilder(pythonExe, "ml-python/model_predict.py", String.valueOf(feature1),
					String.valueOf(feature2));

			pb.redirectErrorStream(true);
			Process process = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			StringBuilder output = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				output.append(line);
			}

			process.waitFor();
			return output.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}
}