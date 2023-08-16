package com.hius.utils;

import org.apache.commons.validator.routines.EmailValidator;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class StringUtil {
    public static final String EMPTY = "";
    private static final Pattern PATTERN_IP = Pattern.compile(("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)"));
    private static final Pattern LETTER_PATTERN = Pattern.compile("[a-z A-Z]");

    public static boolean isEmpty(Long num) { return num == null; }

    public static boolean isEmpty(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }

        if("null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str)){
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str){
        if(isEmpty(str)){
            return false;
        }

        int len = str.length();
        for (int i = 0; i < len; i++) {
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }

        return true;
    }

    public static boolean isLetter(char c){
        return LETTER_PATTERN.matcher(String.valueOf(c)).matches();
    }

    public static boolean isMobileNumber(String mobileNo){
        if(isEmpty(mobileNo)){
            return false;
        }

        //validate phone numbers of format"0123456789"
        if(mobileNo.matches("\\d{10}"))
            return true;

        //validate phone number with -, . or space
        if(mobileNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;

            //validating phone number with extension length from 3 to 5
        if(mobileNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;

            //validating phone number where area code is in braces ()
        if(mobileNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;

        return false;
    }

    public static boolean isEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean hasBlank(String... strs){
        if(CollectionKit.isEmpty(strs)){
            return true;
        }

        for (String str: strs) {
            if(isEmpty(str))
                return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String str){
        return (!isEmpty(str));
    }

    public static String stringOrEmpty(Object o) {
        return o == null ? "" : o.toString();
    }

    public static String format(String template, Object... values){
        if(CollectionKit.isEmpty(values) || isEmpty(template)) {
            return template;
        }

        final StringBuilder sb = new StringBuilder();
        final int len = template.length();

        int valueIndex = 0;
        char currChar;
        for (int i = 0; i < len; i++) {
            if(valueIndex >= values.length){
                sb.append(template.substring(i, len));
                break;
            }
            currChar = template.charAt(i);
            if (currChar == '{') {
                final char nextChar = template.charAt(++i);
                if (nextChar == '}') {
                    sb.append(values[valueIndex++]);
                } else {
                    sb.append('{').append(nextChar);
                }
            } else {
                sb.append(currChar);
            }
        }

        return sb.toString();
    }

    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    public static String removePrefix(String str, String prefix) {
        if(isEmpty(str) || isEmpty(prefix)){
            return str;
        }

        if (str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }

    public static String removeSuffix(String str, String suffix) {
        if(isEmpty(str) || isEmpty(suffix)){
            return str;
        }

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    public static byte[] getBytes(String str, Charset charset){
        if(null == str){
            return null;
        }
        return null == charset ? str.getBytes() : str.getBytes(charset);
    }

    public static Long[] splitForLong(String str, String delimiter){
        String[] arr = str.split(delimiter);
        if(CollectionKit.isNotEmpty(arr)){
            Long[] ret = new Long[arr.length];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = Long.valueOf(arr[i]);
            }
            return ret;
        }
        return null;
    }

    public static String camelToSnake(String str){
        // Empty String
        String result = "";

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);

        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result = result + ch;
            }
        }

        // return the result
        return result;
    }

    public static String getFirstName(String fullName){
        if(isNotEmpty(fullName) && fullName.contains(" ")){
            int pos = fullName.indexOf(" ");
            return fullName.substring(0, pos);
        }
        return fullName;
    }

    public static String getLastName(String fullName){
        if(isNotEmpty(fullName) && fullName.contains(" ")){
            int pos = fullName.indexOf(" ");
            return fullName.substring(pos + 1);
        }
        return fullName;
    }

    public static String[] split(String str, String delimiter) {
        if (str == null) {
            return null;
        }
        if (str.trim().length() == 0) {
            return new String[] { str };
        }

        int delLeng = delimiter.length();
        int maxparts = (str.length() / delLeng) + 2;
        int[] positions = new int[maxparts];

        int i, j = 0;
        int count = 0;
        positions[0] = -delLeng;
        while ((i = str.indexOf(delimiter, j)) != -1) {
            count++;
            positions[count] = i;
            j = i + delLeng;
        }
        count++;
        positions[count] = str.length();

        String[] result = new String[count];

        for (i = 0; i < count; i++) {
            result[i] = str.substring(positions[i] + delLeng, positions[i + 1]);
        }
        return result;
    }
}
