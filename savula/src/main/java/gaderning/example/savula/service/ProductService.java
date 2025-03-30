package gaderning.example.savula.service;

import gaderning.example.savula.model.Product;
import gaderning.example.savula.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // ✅ Constructor-based injection (Recommended)
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ✅ Create Product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // ✅ Get All Products with Pagination and Sorting
    public Page<Product> getAllProducts(int page, int size, String sortBy) {
        // Default sorting field if sortBy is null or empty
        sortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageable);
    }

    // ✅ Get Product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // ✅ Update Product
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setCategory(updatedProduct.getCategory());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // ✅ Delete Product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    // ✅ Find Products by Category (Paginated)
    public Page<Product> getProductsByCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategory(category, pageable);
    }

    // ✅ Find Expensive Products (Paginated)
    public Page<Product> getExpensiveProducts(Double price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findExpensiveProducts(price, pageable);
    }

    public List<Product> getFromProduct(){
        return productRepository.findAll();
    }
    public List<Product> findLesserprice(int price)
    {
        return productRepository.findLesserprice(price);
    }
}