package org.example.pojo.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schema {
    public String name;
    public String sourceName;
    public String sourceDatabaseName;
    public String description;
    public int tableCount;
    public List<Table> tables;
}
