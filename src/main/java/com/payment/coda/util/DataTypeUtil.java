package com.payment.coda.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kunal Malhotra
 */
public class DataTypeUtil {

    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String PHONE_REGEX = "^(\\d{3}[- .]?){2}\\d{4}$";

    private DataTypeUtil() {

    }

    /**
     * This method gets the relevant dataType of the csv field
     *
     * @param value
     * @return
     */
    public static DataType getDataType(String value) {
        if (value == null || value.length() == 0) {
            return DataType.UNDEFINED;
        } else if (isEmailAddress(value)) {
            return DataType.EMAIL_ADDRESS;
        } else if (isPhoneNumber(value)) {
            return DataType.PHONE_NUMBER;
        } else if (isNumeric(value)) {
            return DataType.INTEGER;
        } else {
            return DataType.STRING;
        }
    }


    /**
     * @param email
     * @return
     */
    private static boolean isEmailAddress(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * @param phoneNumber
     * @return
     */
    private static boolean isPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * @param strNum
     * @return
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
