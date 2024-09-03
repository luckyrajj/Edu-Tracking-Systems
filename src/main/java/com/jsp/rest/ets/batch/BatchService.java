package com.jsp.rest.ets.batch;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatchService {

	private BatchRepository batchRepository;
	private BatchMapper batchMapper;
	
	public BatchResponse saveBatch(BatchRequest batchRequest) {
		Batch batch=batchMapper.mapToBatchEntity(batchRequest, new Batch());
		batch.setBatchStatus(BatchStatus.CREATED);
		batch =batchRepository.save(batch);
		return batchMapper.mapToBatchResponse(batch);
	}

	
}
