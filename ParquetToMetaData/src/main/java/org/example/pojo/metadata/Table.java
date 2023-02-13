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
public class Table {
    public int recordCount;

    public String name;
    public String sourceName;
    public String description;
    public int columnCount;
    public List<Object> partitionOrder;
    public List<Object> sortOrder;

    public List<Column> columns;

}
