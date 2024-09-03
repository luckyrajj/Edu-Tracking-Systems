package com.jsp.rest.ets.batch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.rest.ets.exception.BatchNotFoundByIdException;
import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BatchController {

	private BatchService batchService;
	private AppResponseBuilder responseBuilder;
	
	@Operation(description = "This API endpoint is meant for creating the batches and store it in the specific database. In order to "
			+ "create a new batch,  the object of BatchRequest  is attached along with the request "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "200",description = "batch created"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("/batches")
	public ResponseEntity<ResponseStructure<BatchResponse>> saveBatch(@RequestBody @Valid BatchRequest batchRequest){
		  BatchResponse batchResponse=batchService.saveBatch(batchRequest);
		  return responseBuilder.success(HttpStatus.OK, "batch created successfully", batchResponse);
	}
	
	@Operation(description = "This API endpoint is meant for updating the Batch details and store it in the specific database. In order to "
			+ "update the Batch, the unique identifier as path variable and the object of BatchRequest is attached along with the request coming from the UI "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "302",description = "updated batch details"),
					@ApiResponse(responseCode = "404",description = "failed to update the batch",content = {
							@Content(schema = @Schema(anyOf = BatchNotFoundByIdException.class))
					})
			})
	@PutMapping("/batches/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatchDetails(@RequestBody BatchRequest batchRequest,@PathVariable String batchId) {
		BatchResponse batchResponse=batchService.updateBatchDetails(batchRequest,batchId);
		
		return responseBuilder.success(HttpStatus.OK, "batch updated", batchResponse);
	}

	@Operation(description = "This API endpoint is meant for Cancelling the batch . In order to "
			+ "cancel the Batch, the unique identifier as path variable  is attached along with the request coming from the UI "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "302",description = "batch cancelled"),
					@ApiResponse(responseCode = "404",description = "failed to cancel the batch",content = {
							@Content(schema = @Schema(anyOf = BatchNotFoundByIdException.class))
					})
			})
	@PatchMapping("/batches/{batchId}/cancel")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatchStatusToCancel(@PathVariable String batchId){
		BatchResponse batchResponse= batchService.updateBatchStatusToCancel(batchId);
		return responseBuilder.success(HttpStatus.OK, "batch cancelled", batchResponse);
	}
	
	@Operation(description = "This API endpoint is meant for closing the batch  and store it in the specific database. In order to "
			+ "close the Batch, the unique identifier as path variable  is attached along with the request coming from the UI "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "302",description = "batch closed"),
					@ApiResponse(responseCode = "404",description = "failed to close the batch",content = {
							@Content(schema = @Schema(anyOf = BatchNotFoundByIdException.class))
					})
			})
	@PatchMapping("/batches/{batchId}/close")
	public ResponseEntity<ResponseStructure<BatchResponse>>updateBatchStatusToClosed(@PathVariable String batchId){
		BatchResponse batchResponse=batchService.updateBatchStatusToClose(batchId);
		return responseBuilder.success(HttpStatus.OK, "batch Closed", batchResponse);
	}
	
}
