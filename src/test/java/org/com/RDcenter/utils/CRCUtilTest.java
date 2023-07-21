package org.com.RDcenter.utils;

import org.junit.Assert;
import org.junit.Test;

public class CRCUtilTest {
    DataHandler dataHandler=new DataHandler();
    @Test
    public void testCRCUTils(){
        String orgin="FDFD003CA32038363932393830353837313937303900000000B10108A70A00783939393939AAAAAAAA720100001434320C0F6E5101040064";
        byte[] culCRC = new byte[orgin.length()/2];
        for (int i = 0; i < orgin.length(); i += 2) {
            culCRC[i / 2] = (byte) Integer.parseInt(orgin.substring(i, i + 2), 16);
        }
        String actual = CRCUtil.getCrc16x25(culCRC);
        String expect="C5EA";
        Assert.assertEquals(expect,actual);
    }
}
