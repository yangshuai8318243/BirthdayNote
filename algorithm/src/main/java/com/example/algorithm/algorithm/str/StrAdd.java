package com.example.algorithm.algorithm.str;

public class StrAdd {
    public String bigNumAdd(String a, String b) {
        StringBuilder strA = new StringBuilder(a).reverse();
        StringBuilder strB = new StringBuilder(b).reverse();

        int lengthA = strA.length();
        int lengthB = strB.length();
        int max = Math.max(lengthA, lengthB);
        int[] ints = new int[max + 1];
        boolean isOver = false;

        int abs = Math.abs(lengthA - lengthB);

        if (lengthA < lengthB) {
            for (int i = 0; i < abs; i++) {
                strA.append("0");
            }
        } else if (lengthA > lengthB) {
            for (int i = 0; i < abs; i++) {
                strB.append("0");
            }
        }

        for (int i = 0; i < max; i++) {
            if (isOver) {
                ints[i] = Integer.parseInt(strA.charAt(i) + "") +
                        Integer.parseInt(strB.charAt(i) + "") + 1;
            } else {
                ints[i] = Integer.parseInt(strA.charAt(i) + "") +
                        Integer.parseInt(strB.charAt(i) + "");
            }
            //处理溢出位
            isOver = handleSumOverTen(ints, i);
        }
        //处理最高位
        if (isOver) {
            ints[max] = 1;
        } else {
            ints[max] = 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            if (i == ints.length - 1) {
                int anInt = ints[i];
                if (anInt > 0) {
                    stringBuilder.append(ints[i]);
                }
            } else {
                stringBuilder.append(ints[i]);
            }
        }
        return stringBuilder.reverse().toString();
    }

    private boolean handleSumOverTen(int[] nSum, int i) {

        boolean flag = false;
        if (nSum[i] >= 10) {
            nSum[i] = nSum[i] - 10;
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

}
