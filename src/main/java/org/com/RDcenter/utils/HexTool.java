package org.com.RDcenter.utils;

/**
 * 16进制工具
 */
public class HexTool {

    /**
     * 16进制串转10进制串
     * @param data
     * @return
     */
    public static String hexToDec(String data){
        int temp= Integer.parseInt(data,16);
        return String.valueOf(temp);
    }

    public static String hexToAscii(String data){
        String res="";
        for(int i=0;i<data.length();i+=2){
            int temp=Integer.parseInt(data.substring(i,i+2),16);
            res+=(char)temp;
        }
        return res;
    }

    public static byte[] hexToBytes(String data){
        byte[] res = new byte[data.length() / 2];
        for (int i = 0; i < data.length(); i += 2) {
            res[i / 2] = (byte)Integer.parseInt(data.substring(i, i + 2), 16);
        }
        return res;
    }

    public static int hexToInt(String hex){
        return Integer.parseInt(hexToDec(hex));
    }

    public static short hexToSignShort(String hex){
        return Short.parseShort(hexToDec(hex));
    }
}
