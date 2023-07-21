package org.com.RDcenter.utils;

import com.alibaba.fastjson2.JSONObject;
import org.com.RDcenter.model.Device;

/**
 * Device 数据验证和解析
 *
 */
public class DataHandler {
    /**
     * 数据验证
     * @param s
     * @return
     */
    public  String dataVerify(String s) {
        JSONObject jsonObject=null;
        try {
            jsonObject=JSONObject.parse(s);
        }catch (Exception e){
            /**
             * todo
             * 非JSON数据
             */
            return null;
        }
        String data=jsonObject.getString("value");

        if(CRCValidate(data))
            return data;
        else{
            /**
             * todo
             * 校验失败
             */
            return null;
        }
    }

    /**
     * 数据验证后才能进行解析
     * 解析数据value
     * @param data
     * @return
     */
    public  Device parseData(String data) {
        Device device = new Device();
        device.setVersion(data.substring(8, 10));
        device.setFlag(HexTool.hexToInt(data.substring(10,12)));

        device.setImei(HexTool.hexToAscii(data.substring(12, 42)));
        device.setImsi(HexTool.hexToAscii(data.substring(12, 42)));
        //strSrcAddress
        String strSrcAddr = data.substring(42, 50);
        String strcmd = data.substring(50, 52);
        String strCmdData = data.substring(52, data.length() - 8);

        device.setType(HexTool.hexToInt(strCmdData.substring(0, 2)));
        device.setDatanum(HexTool.hexToInt(strCmdData.substring(2, 6)));
        device.setRssi(HexTool.hexToInt(strCmdData.substring(6, 8)));
        device.setFrequency(HexTool.hexToInt(strCmdData.substring(8, 12)));
        device.setCellarsn(HexTool.hexToAscii(strCmdData.substring(12, 22)));
        device.setMac(strCmdData.substring(22, 46));
        device.setVoltage(HexTool.hexToInt(strCmdData.substring(46, 50)));
        device.setQuantity(HexTool.hexToInt(strCmdData.substring(50, 52)));
        device.setWarningcode(HexTool.hexToInt(strCmdData.substring(52, 54)));
        device.setSensornum(HexTool.hexToInt(strCmdData.substring(54, 56)));

        if (strcmd.equals("B1")) {
            switch (device.getSensornum()) {
                case 1:
                case 2:
                case 3:
                    device.setTemp1(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    device.setTemp2(HexTool.hexToSignShort(strCmdData.substring(60, 64)) * 1.0 / 100);
                    device.setTemp3(HexTool.hexToSignShort(strCmdData.substring(64, 68)) * 1.0 / 100);
                    break;
                case 4:
                    device.setTemp(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    break;
                case 5:
                    device.setTemp(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    device.setHumidity(HexTool.hexToSignShort(strCmdData.substring(60, 64))*1.0/100);
                    break;
                case 6:
                    device.setO2(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 10);
                    break;
                case 7:
                    device.setCo2(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    break;
                case 8:
                    device.setH2s(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    break;
                case 10:
                    device.setPressure(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    break;
                case 11:
                    device.setLiquidLevel(HexTool.hexToSignShort(strCmdData.substring(56, 60)) * 1.0 / 100);
                    break;
            }

        } else if (strcmd.equals("B2")) {

        } else if (strcmd.equals("B3")) {

        } else if (strcmd.equals("B4")) {

        } else {
            /**
             * 出错
             */
        }
        return device;
    }

    public static String deviceTOString(Device device){
        StringBuilder result = new StringBuilder();
        switch (device.getFlag()) {
            case 0:
                result.append("请求 | 设备[" + device.getImei() + "]的监控报告");
                break;
            case 1:
                result.append("响应 | 设备[" + device.getImei() + "]的监控报告");
                break;
        }

        result.append("\r\n");
        result.append("Version: " + device.getVersion() + " | ");
        result.append("IMEI: " + device.getImei() + " | ");
        result.append("IMSI: " + device.getImsi() + " | ");
        switch (device.getType()) {
            case 1:
                result.append("类型: 采集包 | ");
                break;
            case 2:
                result.append("类型: 开机包 | ");
                break;
            case 3:
                result.append("类型: 关机包 | ");
                break;
            default:
                result.append("类型: 未知 | ");
                break;
        }
        result.append("数据包编号: " + device.getDatanum() + " | ");
        result.append("信号强度: " + device.getRssi() + "dBm | ");
        result.append("频率: " + device.getFrequency() + "分 | ");
        result.append("窖池编号: " + device.getCellarsn() + " | ");
        result.append("设备编号: " + device.getMac() + " | ");
        result.append("电池电压: " + device.getVoltage() + "mV | ");
        result.append("电量: " + device.getQuantity() + "% | ");
        result.append("告警码: " + String.valueOf(device.getWarningcode())+ " | ");
        result.append("\r\n");
        result.append("传感器类型: " + String.valueOf(device.getSensornum())+ " | ");
        switch (device.getSensornum()) {
            case 1:
                result.append("湿度2: " + device.getTemp2() + "°C");
                break;
            case 2:
                result.append("湿度2: " + device.getTemp2() + "°C | ");
                result.append("湿度3: " + device.getTemp3());
                break;
            case 3:
                result.append("温度1: " + device.getTemp1() + "°C | ");
                result.append("湿度2: " + device.getTemp2() + "°C | ");
                result.append("湿度3: " + device.getTemp3() + "°C");
                break;
            case 4:
                result.append("温度: " + device.getTemp() + "°C");
                break;
            case 5:
                result.append("温度: " + device.getTemp() + "°C | ");
                result.append("湿度: " + device.getHumidity() + "%");
                break;
            case 6:
                result.append("氧气: " + device.getO2() + "%vol");
                break;
            case 7:
                result.append("二氧化碳: " + device.getCo2() + "%vol");
                break;
            case 8:
                result.append("硫化氢: " + device.getH2s() + "ppm");
                break;
            case 10:
                result.append("气压: " + device.getPressure() + "Kpa");
                break;
            case 11:
                result.append("液位: " + device.getLiquidLevel() + "m");
                break;
        }
        return result.toString();
    }

    /**
     * CRC16-x25 校验
     * @param data
     * @return
     */
      protected boolean CRCValidate(String data) {
        String CRC = data.substring(data.length() - 8, data.length() - 4);
        data = data.substring(0, data.length() - 8);
        byte[] culCRC = new byte[data.length() / 2];
        for (int i = 0; i < data.length(); i += 2) {
            culCRC[i / 2] = (byte) Integer.parseInt(data.substring(i, i + 2), 16);
        }
        return CRCUtil.getCrc16x25(culCRC).equals(CRC);
    }
}
