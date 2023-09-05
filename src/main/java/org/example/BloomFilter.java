package org.example;

public class BloomFilter {

    private int filter_len;
    private int [] tableResult;

    private int get_addValue_status; // статус запроса add()

    // интерфейс класса, реализующий АТД BloomFilter
    public static final int ADD_VALUE_OK = 1;
    public static final int ADD_VALUE_ER = 2;


    public BloomFilter(int f_len)
    {
        filter_len = f_len;
        // create byte array length f_len ...
        tableResult = new int[filter_len];
    }

    // hash-func
    public int hash1(String str1)
    {
        // 17
        int resultHash1 = 0;
        for(int i=0; i<str1.length(); i++)
        {
            int code = str1.charAt(i);
            resultHash1 = ((resultHash1+ code) * 17 ) % filter_len;
        }
        return resultHash1;
    }
    public int hash2(String str1)
    {
        // 223
        int resultHash2 = 0;
        for(int i=0; i<str1.length(); i++)
        {
            int code = str1.charAt(i);
            resultHash2 = (resultHash2 * 223 + code) % filter_len;
        }
        return resultHash2;
    }

    public void add(String str1)
    {
        // add string to filter
        if (isValue(str1.trim())){
            get_addValue_status = ADD_VALUE_ER;
        }
        int buff1 = hash1(str1.trim());
        int buff2 = hash2(str1.trim());
        tableResult [buff1] = 1;
        tableResult [buff2] = 1;
        get_addValue_status = ADD_VALUE_OK;
    }

    public boolean isValue(String str1)
    {
        // check - contain filter this string
        int buff1 = hash1(str1.trim());
        int buff2 = hash2(str1.trim());
        if (tableResult [buff1] == 1 && tableResult [buff2] == 1)
            return true;
        return false;
    }
}
