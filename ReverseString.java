/*
Given a string, reverse each word in the string but not
the letters of each word. Example: Given "Hello there how are you" return "olleh ereht woh era uoy"
*/



package com.company;


import com.sun.xml.internal.ws.util.StringUtils;

public class StringManipulations {
    public static void main(String args[]){
        String str1="Hello there how are you";
        String[] strarr=str1.split(" ");
        for(int i=0;i<strarr.length;i++){
                    String re=strarr[i];
                    byte [] strAsByteArray = re.getBytes();
                    byte [] result = new byte[strAsByteArray.length];
                        for(int j=0;j<strAsByteArray.length;j++){
                            result[j]=strAsByteArray[strAsByteArray.length-j-1];
                        }
                        System.out.println(new String(result));
        }

    }
}
