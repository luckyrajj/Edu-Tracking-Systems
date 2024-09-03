package com.jsp.rest.ets.batch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BatchController {

	private BatchService batchService;
	private AppResponseBuilder responseBuilder;
	
	
	@PostMapping("/batches")
	public ResponseEntity<ResponseStructure<BatchResponse>> saveBatch(@RequestBody @Valid BatchRequest batchRequest){
		  BatchResponse batchResponse=batchService.saveBatch(batchRequest);
		  return responseBuilder.success(HttpStatus.OK, "batch created successfully", batchResponse);
	}
	
	
	
}
