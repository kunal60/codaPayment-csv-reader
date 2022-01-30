package com.payment.coda.util;

import com.payment.coda.exceptions.InvalidCSVException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtilTest extends TestCase {
    Path resourceDirectory = Paths.get("src", "test", "resources");


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testValidCsvToList() throws InvalidCSVException {
        String absolutePath = resourceDirectory.toFile().getAbsolutePath() + "" + File.separator + "ValidCSV.txt";
        List<Map<String, String>> list = CSVUtil.csvToList(absolutePath);
        Assert.assertEquals(list.size(), 2);
        Map.Entry<String, String> entry = list.get(0).entrySet().iterator().next();
        String key = entry.getKey();
        String value = entry.getValue();
        Assert.assertEquals(key, "first_name");
        Assert.assertEquals(value, "Josephine");
    }

    @Test
    public void testEmptyCsv() {
        try {
            String absolutePath = resourceDirectory.toFile().getAbsolutePath() + "" + File.separator + "EmptyCSV.txt";
            CSVUtil.csvToList(absolutePath);
        } catch (InvalidCSVException e) {
            Assert.assertEquals(e.getMessage(), "There is some error in File Path or in the csv format");
        }
    }


    @Test
    public void testlistToJson() throws InvalidCSVException {
        List<Map<String, Map<String, String>>> list = new ArrayList<>();
        Map innerMap = new HashMap();
        innerMap.put("TestKey", "TestValue");
        Map outerMap = new HashMap();
        outerMap.put("TestOuterKey", innerMap);
        list.add(outerMap);
        String absolutePath = resourceDirectory.toFile().getAbsolutePath() + "" + File.separator + "output.txt";
        CSVUtil.listToJson(list, absolutePath);
        File file = new File(absolutePath);
        //Check File Size Not Zero
        Assert.assertTrue(file.length() != 0);
        //Delete the file
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}