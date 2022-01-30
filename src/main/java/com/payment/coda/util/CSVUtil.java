package com.payment.coda.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.payment.coda.exceptions.InvalidCSVException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Kunal Malhotra
 */
public class CSVUtil {


    private static final Logger log = LogManager.getLogger(CSVUtil.class);

    private CSVUtil() {
    }

    /**
     * This method converts csv to List
     *
     * @param csvPath
     * @return
     */
    public static List<Map<String, String>> csvToList(String csvPath) throws InvalidCSVException {
        log.info("converting csv to List");
        File input = new File(csvPath);
        List<Map<String, String>> list;
        try {
            CsvSchema csv = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Map<String, String>> mappingIterator = csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            list = mappingIterator.readAll();
        } catch (Exception e) {
            log.error(e);
            throw new InvalidCSVException("There is some error in File Path or in the csv format");
        }
        log.info("Json List Created");
        return list;
    }

    /**
     * This method appends DataType to the row records
     *
     * @param input
     * @return
     */
    public static List<Map<String, Map<String, String>>> appendDataTypetoRecords(List<Map<String, String>> input) {
        log.info("appending DataType to List");
        List<Map<String, Map<String, String>>> list = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Map<String, String> oldMap = input.get(i);
            Map<String, Map<String, String>> newMap = addType(oldMap);
            list.add(newMap);
        }
        log.info("DataType appended to List");
        return list;


    }

    /**
     * @param oldMap
     * @return
     */
    private static Map<String, Map<String, String>> addType(Map<String, String> oldMap) {
        Map<String, Map<String, String>> newMap = new HashMap<>();
        for (Map.Entry<String, String> entry : oldMap.entrySet()) {
            Map<String, String> mapRecord = new HashMap<>();
            mapRecord.put("Type(" + DataTypeUtil.getDataType(entry.getValue()) + ")", entry.getValue());
            newMap.put(entry.getKey(), mapRecord);
        }
        return newMap;
    }

    /**
     * This method prints the list as json
     *
     * @param list
     * @param jsonOutputPath
     * @throws InvalidCSVException
     */
    public static void listToJson(List<Map<String, Map<String, String>>> list, String jsonOutputPath) throws InvalidCSVException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(jsonOutputPath), list);

        } catch (IOException e) {
            log.error(e);
            throw new InvalidCSVException("Some Error in the csv format");
        }
    }


}
