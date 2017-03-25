package com.gdut.ipdivider.tool;

public class IPTool
{
    public static String IPArraytoString(final int[] array) {
        return String.valueOf(array[0]) + "." + array[1] + "." + array[2] + "." + array[3];
    }

    public static int[] IPSub(final int[] array, int n) {
        final int[] array2 = array.clone();
        Boolean b = true;
        final int n2 = 3;
        int n3 = n;
        for (n = n2; b && n >= 0; --n) {
            if ((array2[n] -= n3) < 0) {
                array2[n] = 256 - n3;
                n3 = 1;
            }
            else {
                b = false;
            }
        }
        return array2;
    }

    public static int calBlockNeedCount(final int n) {
        int n2 = 1;
        int n3;
        do {
            n3 = n2 * 2;
        } while ((n2 = n3) < n);
        return n3;
    }

    public static int[] ipAdd(final int[] array, int n) {
        int n2 = n;
        final int[] array2 = array.clone();
        Boolean b;
        for (b = true, n = 3; b && n >= 0; --n) {
            if ((array2[n] += n2) > 255) {
                n2 = array2[n] / 256;
                array2[n] %= 256;
            }
            else {
                b = false;
            }
        }
        return array2;
    }

    public static int log(final double n, final double n2) {
        return (int)(Math.log(n) / Math.log(n2));
    }

    public static String maskParse2Ip(final int n) {
        return maskParse2Ip(String.valueOf(n));
    }

    public static String maskParse2Ip(final String s) {
        int int1 = 0;
        if (!s.equals("")) {
            int1 = Integer.parseInt(s);
        }
        int n = int1 / 8;
        int n2 = int1 % 8;
        final StringBuilder sb = new StringBuilder("");
        for (int i = 4; i > 0; --i, --n) {
            if (n > 0) {
                sb.append("255");
            }
            else {
                int n3 = 0;
                for (int j = 8; j > 0; --j, --n2) {
                    int n4 = n3;
                    if (n2 > 0) {
                        n4 = n3 + 1;
                    }
                    n3 = n4 << 1;
                }
                sb.append(n3 >> 1);
            }
            if (i != 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }
}
