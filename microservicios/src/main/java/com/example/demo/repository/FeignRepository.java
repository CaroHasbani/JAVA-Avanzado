import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;

import java.util.List;

@FeignClient("session.four.eureka.client2")
public interface FeignRepository {

	@RequestMapping("/products")
    List<Product> getProducts();

    @RequestMapping("/products/{productId}")
    Product getProductById(@PathVariable Long productId);

    @RequestMapping("/products/add")
    Product addProduct(Product product);

    @DeleteMapping("/products/delete/{id}")
    void deleteProduct(@PathVariable Long id);

    @PutMapping("/products/update/{id}")
    Product updateProduct(@PathVariable Long id,  Product newProduct);
    
}