package org.com.RDcenter.utils;

public class CRCUtil {

//    private static int crc16_ccitt_table[] = {
//            0X0000, 0X1189, 0X2312, 0X329B, 0X4624, 0X57AD, 0X6536, 0X74BF,
//            0X8C48, 0X9DC1, 0XAF5A, 0XBED3, 0XCA6C, 0XDBE5, 0XE97E, 0XF8F7,
//            0X1081, 0X0108, 0X3393, 0X221A, 0X56A5, 0X472C, 0X75B7, 0X643E,
//            0X9CC9, 0X8D40, 0XBFDB, 0XAE52, 0XDAED, 0XCB64, 0XF9FF, 0XE876,
//            0X2102, 0X308B, 0X0210, 0X1399, 0X6726, 0X76AF, 0X4434, 0X55BD,
//            0XAD4A, 0XBCC3, 0X8E58, 0X9FD1, 0XEB6E, 0XFAE7, 0XC87C, 0XD9F5,
//            0X3183, 0X200A, 0X1291, 0X0318, 0X77A7, 0X662E, 0X54B5, 0X453C,
//            0XBDCB, 0XAC42, 0X9ED9, 0X8F50, 0XFBEF, 0XEA66, 0XD8FD, 0XC974,
//            0X4204, 0X538D, 0X6116, 0X709F, 0X0420, 0X15A9, 0X2732, 0X36BB,
//            0XCE4C, 0XDFC5, 0XED5E, 0XFCD7, 0X8868, 0X99E1, 0XAB7A, 0XBAF3,
//            0X5285, 0X430C, 0X7197, 0X601E, 0X14A1, 0X0528, 0X37B3, 0X263A,
//            0XDECD, 0XCF44, 0XFDDF, 0XEC56, 0X98E9, 0X8960, 0XBBFB, 0XAA72,
//            0X6306, 0X728F, 0X4014, 0X519D, 0X2522, 0X34AB, 0X0630, 0X17B9,
//            0XEF4E, 0XFEC7, 0XCC5C, 0XDDD5, 0XA96A, 0XB8E3, 0X8A78, 0X9BF1,
//            0X7387, 0X620E, 0X5095, 0X411C, 0X35A3, 0X242A, 0X16B1, 0X0738,
//            0XFFCF, 0XEE46, 0XDCDD, 0XCD54, 0XB9EB, 0XA862, 0X9AF9, 0X8B70,
//            0X8408, 0X9581, 0XA71A, 0XB693, 0XC22C, 0XD3A5, 0XE13E, 0XF0B7,
//            0X0840, 0X19C9, 0X2B52, 0X3ADB, 0X4E64, 0X5FED, 0X6D76, 0X7CFF,
//            0X9489, 0X8500, 0XB79B, 0XA612, 0XD2AD, 0XC324, 0XF1BF, 0XE036,
//            0X18C1, 0X0948, 0X3BD3, 0X2A5A, 0X5EE5, 0X4F6C, 0X7DF7, 0X6C7E,
//            0XA50A, 0XB483, 0X8618, 0X9791, 0XE32E, 0XF2A7, 0XC03C, 0XD1B5,
//            0X2942, 0X38CB, 0X0A50, 0X1BD9, 0X6F66, 0X7EEF, 0X4C74, 0X5DFD,
//            0XB58B, 0XA402, 0X9699, 0X8710, 0XF3AF, 0XE226, 0XD0BD, 0XC134,
//            0X39C3, 0X284A, 0X1AD1, 0X0B58, 0X7FE7, 0X6E6E, 0X5CF5, 0X4D7C,
//            0XC60C, 0XD785, 0XE51E, 0XF497, 0X8028, 0X91A1, 0XA33A, 0XB2B3,
//            0X4A44, 0X5BCD, 0X6956, 0X78DF, 0X0C60, 0X1DE9, 0X2F72, 0X3EFB,
//            0XD68D, 0XC704, 0XF59F, 0XE416, 0X90A9, 0X8120, 0XB3BB, 0XA232,
//            0X5AC5, 0X4B4C, 0X79D7, 0X685E, 0X1CE1, 0X0D68, 0X3FF3, 0X2E7A,
//            0XE70E, 0XF687, 0XC41C, 0XD595, 0XA12A, 0XB0A3, 0X8238, 0X93B1,
//            0X6B46, 0X7ACF, 0X4854, 0X59DD, 0X2D62, 0X3CEB, 0X0E70, 0X1FF9,
//            0XF78F, 0XE606, 0XD49D, 0XC514, 0XB1AB, 0XA022, 0X92B9, 0X8330,
//            0X7BC7, 0X6A4E, 0X58D5, 0X495C, 0X3DE3, 0X2C6A, 0X1EF1, 0X0F78
//    };
//
//
//    /**
//     * 采用查表法，根据数据生成CRC校验码
//     *
//     * @param message  校验值，为需要校验的字节
//     * @return
//     */
//    public static String CRC_16_X25(byte[] message) {
//        int crc_reg = 0xffff;//CRC校验时初值
//        for (int i = 0; i < message.length; i++) {
//            crc_reg = (crc_reg >> 8) ^ crc16_ccitt_table[(crc_reg ^ message[i]) & 0xff];
//        }
//        String res=Integer.toHexString(~crc_reg & 0xffff).toUpperCase();
//        for(int i=res.length();i<4;i++){
//            res="0"+res;
//        }
//        return res;
//    }

    /**
     * 计算给定长度数据的16位CRC。
     *
     * @param pData
     * @return
     */
    public static String getCrc16x25(byte[] pData) {
        short fcs = (short) 0xffff;   // 初始化
        int i;
        for (i = 0; i < pData.length; i++) {
            fcs = (short) (((fcs & 0xFFFF) >>> 8) ^ crctab16[(fcs ^ pData[i]) & 0xff]);
        }
        String res = Integer.toHexString((short) ~fcs & 0xffff).toUpperCase();

        for (int t = res.length(); t < 4; t++) {
            res = "0" + res;
        }
        return res;
//            return (short) ~fcs;
    }


    private static short crctab16[] =
            {
                    (short) 0x0000, (short) 0x1189, (short) 0x2312, (short) 0x329b, (short) 0x4624, (short) 0x57ad, (short) 0x6536, (short) 0x74bf,
                    (short) 0x8c48, (short) 0x9dc1, (short) 0xaf5a, (short) 0xbed3, (short) 0xca6c, (short) 0xdbe5, (short) 0xe97e, (short) 0xf8f7,
                    (short) 0x1081, (short) 0x0108, (short) 0x3393, (short) 0x221a, (short) 0x56a5, (short) 0x472c, (short) 0x75b7, (short) 0x643e,
                    (short) 0x9cc9, (short) 0x8d40, (short) 0xbfdb, (short) 0xae52, (short) 0xdaed, (short) 0xcb64, (short) 0xf9ff, (short) 0xe876,
                    (short) 0x2102, (short) 0x308b, (short) 0x0210, (short) 0x1399, (short) 0x6726, (short) 0x76af, (short) 0x4434, (short) 0x55bd,
                    (short) 0xad4a, (short) 0xbcc3, (short) 0x8e58, (short) 0x9fd1, (short) 0xeb6e, (short) 0xfae7, (short) 0xc87c, (short) 0xd9f5,
                    (short) 0x3183, (short) 0x200a, (short) 0x1291, (short) 0x0318, (short) 0x77a7, (short) 0x662e, (short) 0x54b5, (short) 0x453c,
                    (short) 0xbdcb, (short) 0xac42, (short) 0x9ed9, (short) 0x8f50, (short) 0xfbef, (short) 0xea66, (short) 0xd8fd, (short) 0xc974,
                    (short) 0x4204, (short) 0x538d, (short) 0x6116, (short) 0x709f, (short) 0x0420, (short) 0x15a9, (short) 0x2732, (short) 0x36bb,
                    (short) 0xce4c, (short) 0xdfc5, (short) 0xed5e, (short) 0xfcd7, (short) 0x8868, (short) 0x99e1, (short) 0xab7a, (short) 0xbaf3,
                    (short) 0x5285, (short) 0x430c, (short) 0x7197, (short) 0x601e, (short) 0x14a1, (short) 0x0528, (short) 0x37b3, (short) 0x263a,
                    (short) 0xdecd, (short) 0xcf44, (short) 0xfddf, (short) 0xec56, (short) 0x98e9, (short) 0x8960, (short) 0xbbfb, (short) 0xaa72,
                    (short) 0x6306, (short) 0x728f, (short) 0x4014, (short) 0x519d, (short) 0x2522, (short) 0x34ab, (short) 0x0630, (short) 0x17b9,
                    (short) 0xef4e, (short) 0xfec7, (short) 0xcc5c, (short) 0xddd5, (short) 0xa96a, (short) 0xb8e3, (short) 0x8a78, (short) 0x9bf1,
                    (short) 0x7387, (short) 0x620e, (short) 0x5095, (short) 0x411c, (short) 0x35a3, (short) 0x242a, (short) 0x16b1, (short) 0x0738,
                    (short) 0xffcf, (short) 0xee46, (short) 0xdcdd, (short) 0xcd54, (short) 0xb9eb, (short) 0xa862, (short) 0x9af9, (short) 0x8b70,
                    (short) 0x8408, (short) 0x9581, (short) 0xa71a, (short) 0xb693, (short) 0xc22c, (short) 0xd3a5, (short) 0xe13e, (short) 0xf0b7,
                    (short) 0x0840, (short) 0x19c9, (short) 0x2b52, (short) 0x3adb, (short) 0x4e64, (short) 0x5fed, (short) 0x6d76, (short) 0x7cff,
                    (short) 0x9489, (short) 0x8500, (short) 0xb79b, (short) 0xa612, (short) 0xd2ad, (short) 0xc324, (short) 0xf1bf, (short) 0xe036,
                    (short) 0x18c1, (short) 0x0948, (short) 0x3bd3, (short) 0x2a5a, (short) 0x5ee5, (short) 0x4f6c, (short) 0x7df7, (short) 0x6c7e,
                    (short) 0xa50a, (short) 0xb483, (short) 0x8618, (short) 0x9791, (short) 0xe32e, (short) 0xf2a7, (short) 0xc03c, (short) 0xd1b5,
                    (short) 0x2942, (short) 0x38cb, (short) 0x0a50, (short) 0x1bd9, (short) 0x6f66, (short) 0x7eef, (short) 0x4c74, (short) 0x5dfd,
                    (short) 0xb58b, (short) 0xa402, (short) 0x9699, (short) 0x8710, (short) 0xf3af, (short) 0xe226, (short) 0xd0bd, (short) 0xc134,
                    (short) 0x39c3, (short) 0x284a, (short) 0x1ad1, (short) 0x0b58, (short) 0x7fe7, (short) 0x6e6e, (short) 0x5cf5, (short) 0x4d7c,
                    (short) 0xc60c, (short) 0xd785, (short) 0xe51e, (short) 0xf497, (short) 0x8028, (short) 0x91a1, (short) 0xa33a, (short) 0xb2b3,
                    (short) 0x4a44, (short) 0x5bcd, (short) 0x6956, (short) 0x78df, (short) 0x0c60, (short) 0x1de9, (short) 0x2f72, (short) 0x3efb,
                    (short) 0xd68d, (short) 0xc704, (short) 0xf59f, (short) 0xe416, (short) 0x90a9, (short) 0x8120, (short) 0xb3bb, (short) 0xa232,
                    (short) 0x5ac5, (short) 0x4b4c, (short) 0x79d7, (short) 0x685e, (short) 0x1ce1, (short) 0x0d68, (short) 0x3ff3, (short) 0x2e7a,
                    (short) 0xe70e, (short) 0xf687, (short) 0xc41c, (short) 0xd595, (short) 0xa12a, (short) 0xb0a3, (short) 0x8238, (short) 0x93b1,
                    (short) 0x6b46, (short) 0x7acf, (short) 0x4854, (short) 0x59dd, (short) 0x2d62, (short) 0x3ceb, (short) 0x0e70, (short) 0x1ff9,
                    (short) 0xf78f, (short) 0xe606, (short) 0xd49d, (short) 0xc514, (short) 0xb1ab, (short) 0xa022, (short) 0x92b9, (short) 0x8330,
                    (short) 0x7bc7, (short) 0x6a4e, (short) 0x58d5, (short) 0x495c, (short) 0x3de3, (short) 0x2c6a, (short) 0x1ef1, (short) 0x0f78
            };
}
