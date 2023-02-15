package org.example.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Source {
    public String origin;

    public String name;
    public int typeLength;
    public String type;
    public boolean index;
}
