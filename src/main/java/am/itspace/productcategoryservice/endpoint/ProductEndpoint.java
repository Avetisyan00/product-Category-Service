package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.CreateProductDTO;
import am.itspace.productcategoryservice.model.CategoryRepository;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductEndpoint {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> showProductById(@PathVariable(name = "id") int id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDTO createProductDto) {
        Product product = Product.builder()
                .title(createProductDto.getTitle())
                .count(createProductDto.getCount())
                .price(createProductDto.getPrice())
                .category(createProductDto.getCategory())
                .build();
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/products")
    public ResponseEntity<?> changeProduct(@RequestBody Product product) {
        if (product.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") int id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/byCategory/{id}")
    public ResponseEntity<List<Product>> getProductsByCategoriesId(@PathVariable(name = "id") int id) {
        List<Product> productByCategory_id = productRepository.findProductByCategory_Id(id);
        return ResponseEntity.ok(productByCategory_id);
    }
}