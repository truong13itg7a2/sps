package edu.txts.sps131025.controller;

import edu.txts.sps131025.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ApiResponse<?> listProducts() {
		return productService.getAllProducts();
	}

	@PostMapping
	public ApiResponse<?> create(@RequestBody ProductRequest req) {
		return productService.createProduct(req);
	}
}

