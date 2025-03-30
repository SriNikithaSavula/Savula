package gaderning.example.savula.Controller;

import gaderning.example.savula.model.Product;
import gaderning.example.savula.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<Product> getProducts(){
        return productService.getFromProduct();
    }

    // Create Product
    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Get All Products with Pagination and Sorting
    @GetMapping("/get")
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getAllProducts(page, size, sortBy);
    }

    // Get Product by ID
    @GetMapping("get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Product
    @PutMapping("put/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, product));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Product
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Get Products by Category (Paginated)
    @GetMapping("/category/{category}")
    public Page<Product> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getProductsByCategory(category, page, size); // Ensure service method supports pagination
    }

    // Get Expensive Products (Paginated)
    @GetMapping("/expensive")
    public Page<Product> getExpensiveProducts(
            @RequestParam Double price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getExpensiveProducts(price, page, size); // Ensure service method supports pagination
    }
     @GetMapping("/findLesserPrice/{Price}")
    public List<Product> findPrice(@PathVariable int Price){
        return productService.findLesserprice(Price);
    }
}
