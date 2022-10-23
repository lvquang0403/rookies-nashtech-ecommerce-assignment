package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ListProductDTO;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.ProductPostDTO;
import com.example.ecommerce.dto.ResponseProductDTO;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.response.ResponseObject;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("products")
    @ResponseBody
    ResponseEntity<ListProductDTO> getProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "productName",defaultValue = "", required = false) String productName,
            @RequestParam(value = "categoryName", defaultValue = "", required = false) String categoryName

    ) {
        return ResponseEntity.ok(productService.findByConditions(pageNumber, pageSize, productName, categoryName));
    }
    @GetMapping("/getById/{productId}")
    @ResponseBody
    ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @GetMapping("/getByCategoryId/{categoryId}")
    @ResponseBody
    ResponseEntity<ListProductDTO> getProductsByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize
    ) {
        return ResponseEntity.ok(productService.findByCategoryId(pageNumber, pageSize, categoryId));
    }

    @PostMapping("createProduct")
    @ResponseBody
    ResponseEntity<ResponseProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("updateProduct/{productId}")
    @ResponseBody
    ResponseEntity<ResponseProductDTO   > updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductDTO productDTO)
    {
        return ResponseEntity.ok(productService.updatedProductById(productDTO,productId));
    }

    @DeleteMapping("deleteProduct/{productId}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok(new ResponseObject("200", "successfully", null));
    }
}
