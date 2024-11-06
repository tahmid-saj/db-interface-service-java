package com.ts.dbinterface.rest.kv;

import java.util.ArrayList;
import java.util.List;

public class DynamoDBTableItem {
    private String primaryKeyName;
    private String primaryKeyValue;
    private List<String[]> extraFields = new ArrayList<>();
}
