package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.CategoryDTO;
import am.itspace.productcategoryservice.mapper.CategoryMapper;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.model.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryEndpoint {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @GetMapping("")
    public List<CategoryDTO> showAllCategories() {
        return categoryMapper.map(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showCategoryById(@PathVariable(name = "id") int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoryMapper.map(byId.get()));
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDto) {
        Category category = categoryMapper.map(categoryDto);
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.map(category));
    }

    @PutMapping("")
    public ResponseEntity<?> changeCategory(@RequestBody Category category) {
        if (category.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") int id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}