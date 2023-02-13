package org.example.pojo.metadata;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationProperties {
    public String system ;
    public List<String> subSystem ;
    public String version;
    public List<String> cannedReports;


}
