package com.ts.dbinterface.samples.relational.rds.ops;

import com.amazonaws.services.rds.model.DBInstance;
import com.ts.dbinterface.samples.relational.rds.entity.RDSDBInstance;
import com.ts.dbinterface.service.relational.rds.RDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RDSDBOps {

    private RDS rds;

    @Autowired
    public RDSDBOps(RDS rds) {
        this.rds = rds;
    }

    public boolean createDBInstance(RDSDBInstance rdsdbInstance) {
        return rds.createDBInstance(
                rdsdbInstance.getRdsDBIdentifier(),
                rdsdbInstance.getRdsInstanceClass(),
                rdsdbInstance.getRdsDBEngine(),
                rdsdbInstance.getRdsDBAllocatedStorage()
        );
    }

    public boolean deleteDBInstance(RDSDBInstance rdsdbInstance) {
        return rds.deleteDBInstance(rdsdbInstance.getRdsDBIdentifier());
    }

    public DBInstance describeDBInstance(RDSDBInstance rdsdbInstance) {
        return rds.describeDBInstance(rdsdbInstance.getRdsDBIdentifier());
    }

    public boolean modifyDBInstance(RDSDBInstance rdsdbInstance) {
        return rds.modifyDBInstance(
                rdsdbInstance.getRdsDBIdentifier(),
                rdsdbInstance.getRdsInstanceClass(),
                rdsdbInstance.getRdsDBAllocatedStorage()
        );
    }

    public boolean rebootDBInstance(RDSDBInstance rdsdbInstance) {
        return rds.rebootDBInstance(rdsdbInstance.getRdsDBIdentifier());
    }
}
