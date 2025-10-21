package edu.txts.sps131025.controller;

import edu.txts.sps131025.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@PostMapping("/upload")
	public ApiResponse<?> uploadFile(@RequestParam("file") MultipartFile file) {
		// Gọi service xử lý
		return ApiResponse.success("Upload thành công");
	}

	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		// Xử lý tải file
	}
}

