package com.example.algorithm.algorithm.str;

public class StrFindIntersection {


    public String intersection(String a, String b) {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < charsA.length; i++) {
            char cA = charsA[i];
            for (int j = 0; j < charsB.length; j++) {
                char cB = charsB[j];
                if (cA == cB) {
                    stringBuilder.append(cB);
                }
            }
        }
        return stringBuilder.toString();
    }

    private void test(String s){
        s.indexOf("aaa");
    }
}
