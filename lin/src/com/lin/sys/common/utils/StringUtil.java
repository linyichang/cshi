package com.lin.sys.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
     * ��content�ַ����е��ַ���ȫ���滻Ϊָ���ַ���
     * @param content
     * @param compileValue
     * @param replacement
     * @return
     */
    public static String replace(String content, String compileValue, String replacement){
        Pattern pattern =  Pattern.compile(compileValue);
        Matcher matcher = pattern.matcher(content);
        return matcher.replaceAll(replacement);
    }
}
