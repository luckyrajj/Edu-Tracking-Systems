package com.jsp.rest.ets.batch;

import org.springframework.stereotype.Component;

@Component
public class BatchMapper {

	public Batch mapToBatchEntity(BatchRequest batchRequest,Batch batch) {
		batch.setTitle(batchRequest.getTitle());
		batch.setSubjects(batchRequest.getSubjects());
		batch.setBeginsAt(batchRequest.getBeginsAt());
		batch.setEndsAt(batchRequest.getEndsAt());
		batch.setStartingDate(batchRequest.getStartingDate());
		return batch;
	}
	
	public BatchResponse mapToBatchResponse(Batch batch) {
		BatchResponse batchResponse=new BatchResponse();
		batchResponse.setBatchId(batch.getBatchId());
		batchResponse.setTitle(batch.getTitle());
		batchResponse.setSubjects(batch.getSubjects());
		batchResponse.setStartingDate(batch.getStartingDate());
		batchResponse.setClosedDate(batch.getClosedDate());
		batchResponse.setBeginsAt(batch.getBeginsAt());
		batchResponse.setEndsAt(batch.getEndsAt());
		batchResponse.setBatchStatus(batch.getBatchStatus());
		return batchResponse;
	}
}
