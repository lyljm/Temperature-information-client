package org.com.RDcenter.utils;

@Deprecated
public class HandleData {
    public static String handRecData(String data) {

        return null;
    }

    public static String parseVersion(String data) {
        Integer v = Integer.parseInt(data.substring(8, 10), 16);
        String version = null;
        switch (v) {
            case 0xA3:
                version = "4G";
                break;
            case 0xA4:
                version = "WIFI";
                break;
            default:
                version = "can't resolve";
                break;
        }
        return version;
    }

    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static String hexToBinary(String hex) {
        int len = hex.length() * 4;
        Integer interger = Integer.parseInt(hex, 16);
        String binary = Integer.toBinaryString(interger);
        for (int i = binary.length(); i < len; i++) {
            binary = "0" + binary;
        }
        return binary;
    }

    public static int parseLen(String data) {
        return Integer.parseInt(data.substring(4, 8), 16);
    }

    public static String parseFlag(String data) {
        String f = hexToBinary(data.substring(10, 12));
        String res = "";
        if (f.indexOf(0) == '0') {
            res += "请求";
        } else res += "应答";
        if (f.indexOf(4) == '0') {
            res += " 实时数据";
        } else res += " 历史数据";
        if (f.indexOf(5) == '0') {
            res += " 设备时间未校准";
        } else res += " 设备时间已校准";
        return res;
    }

    public static String parseImei(String data) {
        return data.substring(12, 42);
    }

    public static String parseSrcAddress(String data) {
        return data.substring(42, 50);
    }

    public static boolean isValidate(String data) {
        String CRC = data.substring(data.length() - 8, data.length() - 4);
        data = data.substring(0, data.length() - 8);
        byte[] culCRC = new byte[data.length() / 2];
        for (int i = 0; i < data.length(); i += 2) {
            culCRC[i / 2] = (byte) Integer.parseInt(data.substring(i, i + 2), 16);
        }
        return CRCUtil.getCrc16x25(culCRC).equals(CRC);
    }
}
