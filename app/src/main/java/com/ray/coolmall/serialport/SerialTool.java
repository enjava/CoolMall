package com.ray.coolmall.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 串口服务类，提供打开、关闭串口，读取、发送串口数据等服务（采用单例设计模式）
 * @author zhong
 *
 */
public class SerialTool {

    private static SerialTool serialTool = null;

    static {
        //在该类被ClassLoader加载时就初始化一个SerialTool对象
        if (serialTool == null) {
            serialTool = new SerialTool();
        }
    }

    //私有化SerialTool类的构造方法，不允许其他类生成SerialTool对象
    private SerialTool() {}

    /**
     * 获取提供服务的SerialTool对象
     * @return serialTool
     */
    public static SerialTool getSerialTool() {
        if (serialTool == null) {
            serialTool = new SerialTool();
        }
        return serialTool;
    }
public static int anInt=0;



    /**
     * 打开串口
     * @param portName 端口名称
     * @param baudrate 波特率
     * @return 串口对象

     */
    public static final SerialPort openPort(String portName, int baudrate) throws Exception {

        try {
//            //通过端口名识别端口
//            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
//            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
//            CommPort commPort = portIdentifier.open(portName, 2000);
//
//            //判断是不是串口
//            if (commPort instanceof SerialPort) {
//
//                SerialPort serialPort = (SerialPort) commPort;
//
//                try {
//
//                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//                } catch (UnsupportedCommOperationException e) {
//                    throw new SerialPortParameterFailure();
//                }
//
//               System.out.println("Open " + portName + " sucessfully !");
//                return serialPort;

//            }
//            else {
//                //不是串口
//                throw new Exception();
//            }
        } catch (Exception e2) {
            throw new Exception();
        }
        return null;
    }

    /**
     * 关闭串口
     //* @param serialport 待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 往串口发送数据
     * @param serialPort 串口对象
     * @param order	待发送数据

     */
    public static void sendToPort(SerialPort serialPort, byte[] order) throws IOException {

        OutputStream out = null;

        try {
            //order[0]=0x39;
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();


        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                throw new IOException();
            }
        }

    }

    /**
     * 从串口读取数据
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据

     */
    public static byte[] readFromPort(SerialPort serialPort) throws IOException {

        InputStream in = null;
        byte[] bytes = null;

        try {

            in = serialPort.getInputStream();
            int bufflenth = in.available();		//获取buffer里的数据长度

            while (bufflenth != 0) {
                bytes = new byte[bufflenth];	//初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch(IOException e) {
                throw new IOException();
            }

        }

        return bytes;

    }

    /**
     * 添加监听器
     * @param port     串口对象
     * @param listener 串口监听器

     */
//    public static void addListener(SerialPort port, SerialPortEventListener listener) throws Exception {
//
//        try {
//
//            //给串口添加监听器
//            port.addEventListener(listener);
//            //设置当有数据到达时唤醒监听接收线程
//            port.notifyOnDataAvailable(true);
//            //设置当通信中断时唤醒中断线程
//            port.notifyOnBreakInterrupt(true);
//
//        } catch (TooManyListenersException e) {
//            throw new TooManyListeners();
//        }
//    }

//    void listPortChoices(){
//        CommPortIdentifier portid;
//        Enumeration en= CommPortIdentifier.getPortIdentifiers();
//        while(en.hasMoreElements()){
//            portid= (CommPortIdentifier) en.nextElement();
//            if (portid.getPortType()==CommPortIdentifier.PORT_PARALLEL){
//                listPortChoices();
//            }
//        }
//    }
//    void listPortChoices() { 
//            CommPortIdentifier portId; 
//            Enumeration en = CommPortIdentifier.getPortIdentifiers();             // iterate through the ports. 
//            while (en.hasMoreElements()) { 
//                portId = (CommPortIdentifier) en.nextElement(); 
//                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {                     System.out.println(portId.getName());                 }             } 
//            portChoice.select(parameters.getPortName());         } 
}
