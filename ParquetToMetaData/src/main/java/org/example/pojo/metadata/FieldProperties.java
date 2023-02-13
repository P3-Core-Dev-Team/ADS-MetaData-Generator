package org.example.pojo.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldProperties {
    public int precision;
    public int scale;
    public boolean nullable;
    public boolean autoIncrement;
    public boolean primaryKey;
}
