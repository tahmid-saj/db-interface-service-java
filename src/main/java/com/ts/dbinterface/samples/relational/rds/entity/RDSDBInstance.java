package com.ts.dbinterface.samples.relational.rds.entity;

import com.ts.dbinterface.validation.relational.rds.RDSDBName;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class RDSDBInstance {

    @RDSDBName
    private String rdsDBIdentifier = "test-db";

    private String rdsInstanceClass;

    private String rdsDBEngine;

    private int rdsDBAllocatedStorage;

    public RDSDBInstance() {

    }

    public RDSDBInstance(String rdsDBIdentifier, String rdsInstanceClass, String rdsDBEngine, int rdsDBAllocatedStorage) {
        this.rdsDBIdentifier = rdsDBIdentifier;
        this.rdsInstanceClass = rdsInstanceClass;
        this.rdsDBEngine = rdsDBEngine;
        this.rdsDBAllocatedStorage = rdsDBAllocatedStorage;
    }

    public String getRdsDBIdentifier() {
        return rdsDBIdentifier;
    }

    public void setRdsDBIdentifier(String rdsDBIdentifier) {
        this.rdsDBIdentifier = rdsDBIdentifier;
    }

    public String getRdsInstanceClass() {
        return rdsInstanceClass;
    }

    public void setRdsInstanceClass(String rdsInstanceClass) {
        this.rdsInstanceClass = rdsInstanceClass;
    }

    public String getRdsDBEngine() {
        return rdsDBEngine;
    }

    public void setRdsDBEngine(String rdsDBEngine) {
        this.rdsDBEngine = rdsDBEngine;
    }

    public int getRdsDBAllocatedStorage() {
        return rdsDBAllocatedStorage;
    }

    public void setRdsDBAllocatedStorage(int rdsDBAllocatedStorage) {
        this.rdsDBAllocatedStorage = rdsDBAllocatedStorage;
    }

    @PostConstruct
    public void startRDSDB() {
        System.out.println("New instance of rds db initialized : " + getRdsDBIdentifier());
    }

    @PreDestroy
    public void closeRDSDB() {
        System.out.println("Closing instance of rds db : " + getRdsDBIdentifier());
    }
}
