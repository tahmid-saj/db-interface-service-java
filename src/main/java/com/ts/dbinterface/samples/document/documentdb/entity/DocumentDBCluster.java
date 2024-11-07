package com.ts.dbinterface.samples.document.documentdb.entity;

import com.ts.dbinterface.validation.document.documentdb.DocumentDBClusterName;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class DocumentDBCluster {

    @DocumentDBClusterName
    private String clusterName = "default-cluster";

    public DocumentDBCluster() {

    }

    public DocumentDBCluster(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    @Override
    public String toString() {
        return "DocumentDBCluster{" +
                "clusterName='" + clusterName + '\'' +
                '}';
    }

    @PostConstruct
    public void startDocumentDBCluster() {
        System.out.println("New instance of documentdb cluster initialized : " + getClusterName());
    }

    @PreDestroy
    public void closeDocumentDBCluster() {
        System.out.println("Closing instance of documentdb cluster : " + getClusterName());
    }
}
