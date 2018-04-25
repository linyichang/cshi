package com.lin.utils.wx.utils;

import java.util.Arrays;

public class SignUtil {

	/** 
     * ��֤ǩ�� 
     * 
     * @param token ΢�ŷ�����token����env.properties�ļ������õĺ��ڿ������������õı���һ�� 
     * @param signature ΢�ŷ�����������sha1���ܵ�֤��ǩ��
     * @param timestamp ʱ���
     * @param nonce ����� 
     * @return 
     */  
    public static boolean checkSignature(String token,String signature, String timestamp, String nonce) {  
        String[] arr = new String[] { token, timestamp, nonce };  
        // ��token��timestamp��nonce�������������ֵ�������  
        Arrays.sort(arr);  
        
        // �����������ַ���ƴ�ӳ�һ���ַ�������sha1����  
        String tmpStr = SHA1.encode(arr[0] + arr[1] + arr[2]);  
        
        // ��sha1���ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��  
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
}
