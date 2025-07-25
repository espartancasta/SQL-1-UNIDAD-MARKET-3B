package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= "*")
//Le dice a Spring que a va a se el controlador de una API REST
@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Manage products in the store")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /products
    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Return a list of all available products"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    // GET /products/{productId}
    @GetMapping("/{productId}")
    @Operation(
            summary = "Get product by Id",
            description = "Return a product by its ID if exists"
    )
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> getProduct(
            @Parameter(description="Id of the product to be retrieved",
            example = "7",required = true)
            @PathVariable("productId") int productId) {
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // GET /products/category/{categoryId}
    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get product by Category",
            description = "Return a products in a specific category"
    )
    @ApiResponse(responseCode = "200", description = "Products found in the category")
    @ApiResponse(responseCode = "204", description = "No products found in the category")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description="Category ID",
                    example = "7",required = true)
            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /products
    @PostMapping
    @Operation(
            summary = "Save a new product",
            description = "Registsters a new product and returns the created product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content=@Content(
                            examples =@ExampleObject(
                                    name= "Example Product",
                                    value= """
                                            {
                                              "name": "cafe",
                                              "categoryId": 0,
                                              "price": 123,
                                              "stock": 1,
                                              "active": true,
                                              "category": {
                                                "categoryId": 8,
                                                "category": "despensa",
                                                "active": true
                                              }
                                            }
                                            """
                            )
                    )
            )

    )
    @ApiResponse(responseCode = "200", description = "Product post")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    // DELETE /products/{productId}
    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product by ID", description = "Deletes a product if it exists")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(@PathVariable("productId") int productId) {
        if (productService.delete(productId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}