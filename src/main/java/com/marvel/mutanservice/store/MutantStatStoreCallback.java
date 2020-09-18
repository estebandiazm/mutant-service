package com.marvel.mutanservice.store;

import com.google.api.core.ApiFutureCallback;
import com.google.cloud.firestore.WriteResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MutantStatStoreCallback implements ApiFutureCallback<WriteResult>{
    @Override public void onFailure(Throwable t) {
        log.error("DNA Stat store failed", t);
    }

    @Override public void onSuccess(WriteResult result) {
        log.info("DAN Stat store success {}", result.getUpdateTime());

    }
}
