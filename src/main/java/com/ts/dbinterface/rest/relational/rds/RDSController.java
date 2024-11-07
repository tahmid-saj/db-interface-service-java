package com.ts.dbinterface.rest.relational.rds;

import com.ts.dbinterface.service.relational.rds.RDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("/relational/rds")
public class RDSController {

    private RDS rds;

    @Autowired
    public RDSController(RDS rds) {
        this.rds = rds;
    }

//    db instance operations
    @GetMapping("/dbs/{dbInstanceIdentifier}")
    public Object describeDBInstance(@PathVariable String dbInstanceIdentifier) {
        return rds.describeDBInstance(dbInstanceIdentifier);
    }

    @PostMapping("/dbs")
    public boolean createDBInstance(@RequestBody Map<String, Object> rdsDBInstance) {
        String dbInstanceIdentifier = (String)rdsDBInstance.get("dbInstanceIdentifier");
        String dbInstanceClass = (String)rdsDBInstance.get("dbInstanceClass");
        String dbEngine = (String)rdsDBInstance.get("dbEngine");
        int dbAllocatedStorage = (Integer)rdsDBInstance.get("dbAllocatedStorage");

        return rds.createDBInstance(
            dbInstanceIdentifier,
            dbInstanceClass,
            dbEngine,
            dbAllocatedStorage
        );
    }

    @PutMapping("/dbs")
    public boolean modifyDBInstance(@RequestBody Map<String, Object> rdsDBInstance) {
        String dbInstanceIdentifier = (String)rdsDBInstance.get("dbInstanceIdentifier");
        String dbInstanceClass = (String)rdsDBInstance.get("dbInstanceClass");
        int dbAllocatedStorage = (Integer)rdsDBInstance.get("dbAllocatedStorage");

        return rds.modifyDBInstance(
            dbInstanceIdentifier,
            dbInstanceClass,
            dbAllocatedStorage
        );
    }

    @DeleteMapping("/dbs/{dbInstanceIdentifier}")
    public boolean deleteDBInstance(@PathVariable String dbInstanceIdentifier) {
        return rds.deleteDBInstance(dbInstanceIdentifier);
    }

    @PutMapping("/dbs/{dbInstanceIdentifier}")
    public boolean rebootDBInstance(@PathVariable String dbInstanceIdentifier) {
        return rds.rebootDBInstance(dbInstanceIdentifier);
    }
}
