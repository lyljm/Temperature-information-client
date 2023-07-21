package org.com.RDcenter.Service;

import org.com.RDcenter.MQTTv3.Manager;
import org.com.RDcenter.Service.Imp.TempTempRecordService;
import org.com.RDcenter.model.Device;
import org.com.RDcenter.model.IdentifyDev;
import org.com.RDcenter.utils.DataHandler;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;


public class DataHandlerService implements Runnable {
    private static DataHandlerService dataHandlerService = new DataHandlerService();

    private static Map<IdentifyDev, IdentifyDev> pool = new ConcurrentHashMap<IdentifyDev, IdentifyDev>();
    private static Queue<IdentifyDev> queue = new ConcurrentLinkedQueue<IdentifyDev>();

    private final Logger sourceLogger = LoggerFactory.getLogger("SourceLogger");
    private final Logger decodeLogger = LoggerFactory.getLogger("DecodeLogger");
    private final Logger recordLogger = LoggerFactory.getLogger("RecordLogger");
    private final Logger messageLogger = LoggerFactory.getLogger("MessageLogger");

    private DataHandler dataHandler = new DataHandler();

    ITempRecordService tempRecordService = new TempTempRecordService();

    ShowMessage showMessage;

    /**
     * 信息到达后处理
     *
     * @param s
     * @param mqttMessage
     */
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String data = dataHandler.dataVerify(new String(mqttMessage.getPayload()));
        sourceLogger.info(new String(mqttMessage.getPayload()));
        if (data != null) {
            /**
             * 是否需要根据不同
             */
            Device device = dataHandler.parseData(data);
            device.setCreateTime(LocalDateTime.now());
            this.showMsg(DataHandler.deviceTOString(device));

            IdentifyDev identifyDev = new IdentifyDev();
            identifyDev.id = s;
            identifyDev.time = System.currentTimeMillis();
            identifyDev.device = device;
            identifyDev.imei = device.getImei();
            identifyDev.imsi = device.getImsi();

            queue.add(identifyDev);
            decodeLogger.info("{} {}", s, device.toString());
        } else {
            decodeLogger.warn("解码出错： {}", new String(mqttMessage.getPayload()));
        }
    }

    public void setShowMsg(ShowMessage showMessage) {
        this.showMessage = showMessage;
    }

    protected void showMsg(String msg) {
        if(showMessage!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            showMessage.showMsg(LocalDateTime.now().format(formatter)+" "+msg);
        }
    }

    private DataHandlerService() {

    }

    /**
     * 单例急汉
     *
     * @return
     */
    public static DataHandlerService getInstance() {
        return dataHandlerService;
    }

    protected void finalDataHandle(IdentifyDev dev) {
        recordLogger.info("{}", dev.toString());
        if (Manager.sqlConnection) {
            tempRecordService.insertDevice(dev);
        }
    }

    public void run() {
        while (true) {
            long currentTimeMillis = System.currentTimeMillis();
            IdentifyDev dev;
            while ((dev = queue.peek()) != null && currentTimeMillis - 10 > dev.time) {
                queue.poll();
                IdentifyDev pooldev1 = null;
                if ((pooldev1 = pool.get(dev)) != null) {
                    pooldev1.imei = pooldev1.imei + ";" + dev.imei;
                    pooldev1.imsi = pooldev1.imsi + ";" + dev.imsi;
                } else {
                    pool.put(dev, dev);
                }
            }
            messageLogger.warn("======{}====", pool.size());
            for (IdentifyDev d : pool.values()) {
                finalDataHandle(d);
            }
            pool.clear();
            messageLogger.warn("======{}======", pool.size());
            try {
                sleep(1000 * 30);
            } catch (InterruptedException e) {
                messageLogger.error(Thread.currentThread().getName() + " 休眠失败");
                e.printStackTrace();
            }
        }
    }

}