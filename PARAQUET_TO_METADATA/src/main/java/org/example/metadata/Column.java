package org.example.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Column {
    public String name;
    public int ordinal;
    public String dataType;
    public int length;
    public String description;
    public boolean sorted;
    public boolean partitioned;
    public Source source;
    public FieldProperties fieldProperties;
    public DataProperties dataProperties;
    public boolean encrypt;
}
