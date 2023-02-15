package org.example.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Additional {
    public int minValue;
    public int maxValue;
    public int whiteSpaceCount;
    public List<Object> highFrequencyCharData;
    public int nullCount;
    public int distinctRecord;
    public boolean lengthUniform;
    public boolean allNumeric;
}
