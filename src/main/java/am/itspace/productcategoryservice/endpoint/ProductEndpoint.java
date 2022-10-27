package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.CreateProductDTO;
import am.itspace.productcategoryservice.dto.ResponseProductDTO;
import am.itspace.productcategoryservice.mapper.ProductMapper;
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
@RequestMapping("/products")
public class ProductEndpoint {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("")
    public List<ResponseProductDTO> showAllProducts() {
        return productMapper.map(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> showProductById(@PathVariable(name = "id") int id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productMapper.map(byId.get()));
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDTO createProductDto) {
        Product product = productMapper.map(createProductDto);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<?> changeProduct(@RequestBody Product product) {
        if (product.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") int id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byCategory/{id}")
    public ResponseEntity<List<ResponseProductDTO>> getProductsByCategoriesId(@PathVariable(name = "id") int id) {
        List<ResponseProductDTO> productByCategory_id = productMapper.map(productRepository.findProductByCategory_Id(id));
        return ResponseEntity.ok(productByCategory_id);
    }
}