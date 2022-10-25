package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.CreateCategoryDTO;
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
public class CategoryEndpoint {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @GetMapping("categories")
    public List<Category> showAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> showCategoryById(@PathVariable(name = "id") int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryDTO createCategoryDto) {
        Category category = Category.builder()
                .name(createCategoryDto.getName())
                .build();
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/categories")
    public ResponseEntity<?> changeCategory(@RequestBody Category category) {
        if (category.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") int id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}