package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;
import org.example.metadata.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {


    public static int ordinal = 1;
    public static Root root;
    public static boolean primaryKey = true;


    public static Metadata metadata = new Metadata();

    public static List<Table> tables = new ArrayList<>();
    public static List<Schema> schemas = new ArrayList<>();
    public static List<Object> highFrequencyCharData = new ArrayList<>();
    public static List<Object> partitionOrder = new ArrayList<>();
    public static List<Object> sortOrder = new ArrayList<>();





    public static void main(String[] args) throws IOException {

        String filePath = "/Users/p3solution/Downloads/DBO";
        File file = new File(filePath);
        String schemaName = file.getName();
        File[] tablesFolder = file.listFiles();

        for (File tableFile: tablesFolder) {
            String tableName = tableFile.getName();
            File[] parquetFiles = tableFile.listFiles();
            List<Column> columns = new ArrayList<>();
            if(parquetFiles!= null)
            for (File parquetFile: parquetFiles ) {
                if (parquetFile.getName().endsWith(".parquet")) {

                    ParquetFileReader reader = ParquetFileReader.open(HadoopInputFile.fromPath(new Path(parquetFile.getAbsolutePath()), new Configuration()));
                    MessageType schema = reader.getFooter().getFileMetaData().getSchema();
                    List<Type> fields = schema.getFields();

                    for (Type field : fields) {
                        String columnName = field.asPrimitiveType().getName();
                        String primitiveType = field.asPrimitiveType().getPrimitiveTypeName().name();
                        String originalType = null;
                        if (Objects.nonNull(field.getOriginalType()) && StringUtils.isNotBlank(field.getOriginalType().name())) {
                            originalType = field.getOriginalType().name();
                        }

                        String dataType = dataType = getDataType(primitiveType, originalType);

                        columns.add(Column.builder().name(columnName)
                                .ordinal(ordinal)
                                .dataType(dataType)
                                .length(0)
                                .description("")
                                .sorted(false)
                                .partitioned(false)
                                .source(Source.builder()
                                        .origin("SOURCE_SYSTEM")
                                        .name(columnName)
                                        .typeLength(0)
                                        .type(dataType)
                                        .index(true)
                                        .build())
                                .fieldProperties(FieldProperties.builder()
                                        .precision(0)
                                        .scale(0)
                                        .nullable(false)
                                        .autoIncrement(false)
                                        .primaryKey(primaryKey)
                                        .build())
                                .dataProperties(DataProperties.builder()
                                        .analysed(false)
                                        .additional(Additional.builder()
                                                .minValue(0)
                                                .minValue(0)
                                                .whiteSpaceCount(0)
                                                .highFrequencyCharData(highFrequencyCharData)
                                                .nullCount(0)
                                                .distinctRecord(0)
                                                .lengthUniform(false)
                                                .allNumeric(false)
                                                .build())
                                        .build())
                                .encrypt(false).build());
                        ordinal++;
                        primaryKey = false;

                    }
                    break;
                }
            }
            tables.add(Table.builder()
                    .recordCount(0)
                    .name(tableName)
                    .sourceName(tableName)
                    .description("table")
                    .columnCount(columns.size())
                    .partitionOrder(partitionOrder)
                    .sortOrder(sortOrder)
                    .columns(columns)
                    .build());
        }
        schemas.add(Schema.builder()
                .name(schemaName)
                .sourceName(schemaName)
                .sourceDatabaseName("TEST")
                .description("")
                .tableCount(tables.size())
                .tables(tables)
                .build());

        metadata = Metadata.builder()
                .schemas(schemas)
                .build();

        root = Root.builder()
                .metadata(metadata)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("/Users/p3solution/IdeaProjects/ADS-MetaData-Generator/PARAQUET_TO_METADATA/src/main/java/org/example/MetaData.json"), root);

    }


    public static String getDataType(String primitiveType, String originalType) {

        if (primitiveType.toUpperCase().contains("INT96") && originalType == null){
            return "DATETIME";
        }else if (primitiveType.toUpperCase().startsWith("DOUBLE") && originalType==null) {
            return "DECIMAL";
        } else if (primitiveType.toUpperCase().startsWith("BOOLEAN")&& originalType == null) {
            return "BOOLEAN";
        } else if (primitiveType.toUpperCase().contains("FLOAT") && originalType==null) {
            return "DECIMAL";
        }else if(primitiveType.toUpperCase().contains("INT") && (originalType == null||originalType.toUpperCase().contains("INT"))) {
            return "NUMBER";
        } else if (originalType.startsWith("TIME") || originalType.startsWith("DATE")){
            return "DATETIME";
        } else if (primitiveType.toUpperCase().contains("BINARY") && originalType.toUpperCase().contains("UTF8") ) {
            return "STRING";
        }
        else {
            return "STRING";
        }
    }
}
