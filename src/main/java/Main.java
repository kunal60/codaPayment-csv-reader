import com.payment.coda.exceptions.InvalidArgumentException;
import com.payment.coda.exceptions.InvalidCSVException;
import com.payment.coda.util.CSVUtil;
import com.payment.coda.util.PropertyUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @author Kunal Malhotra
 */
public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InvalidCSVException, InvalidArgumentException {
        log.info("Reading file path from properties file");
        String csvFilePath = PropertyUtil.getPropertyValue("input.csv.file.path");
        String jsonOutputPath = PropertyUtil.getPropertyValue("output.json.file.path");

        log.info("Generating json for thi csv:" + csvFilePath);
        log.info("Generating json in this file:" + jsonOutputPath);
        //I have attached 2 samples in the resources directory which i created for my test
        printJsonToFile(csvFilePath, jsonOutputPath);
        log.info("Json File generated on this path:" + jsonOutputPath);
    }


    /**
     * This method prints json in the file
     *
     * @param csvPath
     * @param jsonOutputPath
     * @throws InvalidCSVException
     */
    private static void printJsonToFile(String csvPath, String jsonOutputPath) throws InvalidCSVException {
        List<Map<String, String>> list = CSVUtil.csvToList(csvPath);
        CSVUtil.listToJson(CSVUtil.appendDataTypetoRecords(list), jsonOutputPath);
    }


}
