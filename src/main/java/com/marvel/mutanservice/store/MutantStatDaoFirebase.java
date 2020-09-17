package com.marvel.mutanservice.store;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.marvel.mutanservice.models.DnaInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class MutantStatDaoFirebase implements MutantStatDao {

    public static final String MUTANT_STATS = "mutantStats";
    private Firestore db;

    @SneakyThrows @PostConstruct
    private void construct() {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("mutant-service")
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .build();
        db = firestoreOptions.getService();
    }

    @Override public List<DnaInfo> getMutantStats() {

        List<DnaInfo> dnaInfos = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> query = db.collection(MUTANT_STATS).get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                dnaInfos.add(
                        new DnaInfo()
                        .withDna(document.getId())
                        .withMutant(Optional.ofNullable(document.getBoolean("isMutant")).orElse(false))
                );
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error getting mutants stats", e);
        }
        return dnaInfos;
    }

    @Override public void insertMutantStat(DnaInfo dnaInfo) {

        DocumentReference docRef = db.collection("mutantStats").document(dnaInfo.getDna());
        Map<String, Object> data = new HashMap<>();
        data.put("isMutant", dnaInfo.isMutant());
        ApiFuture<WriteResult> result = docRef.set(data);

        ApiFutures.addCallback(result, new ApiFutureCallback<>() {
            @Override public void onFailure(Throwable t) {
                log.error("DNA Stat store failed", t);
            }

            @Override public void onSuccess(WriteResult result) {
                log.info("DAN Stat store success {}", result.getUpdateTime());
            }
        }, Executors.newSingleThreadExecutor());
    }
}
