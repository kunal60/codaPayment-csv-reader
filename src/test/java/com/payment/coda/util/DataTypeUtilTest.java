package com.payment.coda.util;


import org.junit.Assert;
import org.junit.Test;

public class DataTypeUtilTest {

    public DataTypeUtilTest() {

    }

    @Test
    public void getDataTypeTest() {
        Assert.assertEquals(DataTypeUtil.getDataType("kunalmalhotra9322@gmail.com"), DataType.EMAIL_ADDRESS);
        Assert.assertEquals(DataTypeUtil.getDataType(""), DataType.UNDEFINED);
        Assert.assertEquals(DataTypeUtil.getDataType("https://www.linkedin.com/in/officialkunal/"), DataType.STRING);
        Assert.assertEquals(DataTypeUtil.getDataType("810-292-9388"), DataType.PHONE_NUMBER);
        Assert.assertEquals(DataTypeUtil.getDataType("123"), DataType.INTEGER);
        Assert.assertEquals(DataTypeUtil.getDataType("kunal"), DataType.STRING);
        Assert.assertEquals(DataTypeUtil.getDataType(null), DataType.UNDEFINED);

    }
}