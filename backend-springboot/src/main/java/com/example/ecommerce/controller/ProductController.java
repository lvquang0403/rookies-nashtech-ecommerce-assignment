package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ProductPostDTO;
import com.example.ecommerce.dto.request.ProductPutDTO;
import com.example.ecommerce.dto.response.DetailProductDTO;
import com.example.ecommerce.dto.response.ListProductViewDTO;
import com.example.ecommerce.dto.response.ResponseProductDTO;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<ListProductViewDTO> getProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "productName",defaultValue = "", required = false) String productName
    ) {
        return ResponseEntity.ok(productService.searchProductByProductName(pageNumber, pageSize, productName));
    }
    @GetMapping("/{productId}")
    ResponseEntity<DetailProductDTO> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @GetMapping()
    ResponseEntity<ListProductViewDTO> getProductsByCategoryId(
            @RequestParam(value = "pageNumber", defaultValue = "0")  int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "categoryId") Long categoryId
    ) {
        return ResponseEntity.ok(productService.findByCategoryId(pageNumber, pageSize, categoryId));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ResponseProductDTO> createProduct(@RequestBody ProductPostDTO productDTO){
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductPutDTO productDTO)
    {
        productService.updatedProductById(productDTO, productId);
        return ResponseEntity.ok("successfully");
    }

    @DeleteMapping("{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok("successfully");
    }
}
