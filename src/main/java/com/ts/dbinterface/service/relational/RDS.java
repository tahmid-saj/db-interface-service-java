package com.ts.dbinterface.service.relational;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.rds.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RDS implements RelationalDB {

    private final AmazonRDS rds;

    // Constructor injection for AmazonRDS
    @Autowired
    public RDS() {
        AmazonRDS client = null;
        try {
            client = AmazonRDSClientBuilder.defaultClient();
        } catch (Exception e) {
            System.err.println("Error initializing AmazonRDS client: " + e.getMessage());
            throw e; // rethrow to let Spring know that bean creation failed
        }
        this.rds = client;
    }

    // DB instance operations

    // Create RDS instance
    public boolean createDBInstance(String dbInstanceIdentifier, String dbInstanceClass, String engine, int allocatedStorage) {
        CreateDBInstanceRequest request = new CreateDBInstanceRequest()
                .withDBInstanceIdentifier(dbInstanceIdentifier)
                .withDBInstanceClass(dbInstanceClass)
                .withEngine(engine)
                .withAllocatedStorage(allocatedStorage);

        try {
            DBInstance dbInstance = rds.createDBInstance(request);
            System.out.println("Created DB instance: " + dbInstance.getDBInstanceIdentifier());
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to create DB instance: " + e.getErrorMessage());
            return false;
        }
    }

    // Delete RDS instance
    public boolean deleteDBInstance(String dbInstanceIdentifier) {
        DeleteDBInstanceRequest request = new DeleteDBInstanceRequest()
                .withDBInstanceIdentifier(dbInstanceIdentifier)
                .withSkipFinalSnapshot(true); // Skips final snapshot for quick deletion

        try {
            rds.deleteDBInstance(request);
            System.out.println("Deleted DB instance: " + dbInstanceIdentifier);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to delete DB instance: " + e.getErrorMessage());
            return false;
        }
    }

    // Describe RDS instance
    public DBInstance describeDBInstance(String dbInstanceIdentifier) {
        DescribeDBInstancesRequest request = new DescribeDBInstancesRequest()
                .withDBInstanceIdentifier(dbInstanceIdentifier);

        try {
            DescribeDBInstancesResult result = rds.describeDBInstances(request);
            DBInstance dbInstance = result.getDBInstances().get(0);
            System.out.println("DB instance: " + dbInstance.getDBInstanceIdentifier());
            return dbInstance;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to describe DB instance: " + e.getErrorMessage());
            return null;
        }
    }

    // Modify RDS instance
    public boolean modifyDBInstance(String dbInstanceIdentifier, String newDBInstanceClass, int newAllocatedStorage) {
        ModifyDBInstanceRequest request = new ModifyDBInstanceRequest()
                .withDBInstanceIdentifier(dbInstanceIdentifier)
                .withDBInstanceClass(newDBInstanceClass)
                .withAllocatedStorage(newAllocatedStorage);

        try {
            rds.modifyDBInstance(request);
            System.out.println("Modified DB instance: " + dbInstanceIdentifier);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to modify DB instance: " + e.getErrorMessage());
            return false;
        }
    }

    // Reboot RDS instance
    public boolean rebootDBInstance(String dbInstanceIdentifier) {
        RebootDBInstanceRequest request = new RebootDBInstanceRequest()
                .withDBInstanceIdentifier(dbInstanceIdentifier);

        try {
            rds.rebootDBInstance(request);
            System.out.println("Rebooted DB instance: " + dbInstanceIdentifier);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to reboot DB instance: " + e.getErrorMessage());
            return false;
        }
    }
}
