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
public class Metadata {
    public ApplicationProperties applicationProperties;
    public List<Schema> schemas;
}
