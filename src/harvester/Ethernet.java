/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author ReneZ
 */
public class Ethernet {

    private String serialNumber;
    private String humidity;
    private String temperature;
    private String serialInput;
    private double doubleHumidity;
    private double doubleTemperature;

    /**
     *
     */
    public void getEthernet() {

        Socket socket;
        StringBuilder sb = new StringBuilder();

        try {
            socket = new Socket("192.168.200.207", 5000);
            try (PrintStream myOutput = new PrintStream(socket.getOutputStream())) {
                myOutput.println("m");
                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    for (int i = 0; i < 15; i++) {
                        String answer = input.readLine();
                        sb.append(answer);
                    }
                    input.close();
                    myOutput.close();
                    socket.close();

                }
            }
        } catch (IOException ex) {

        }
        String sbString = sb.toString();
        serialInput = sbString.replace(".", "");
    }

    public Ethernet() {
    }

    public void buildFieldsOutOfString() {
        String zwischenString;

        if (serialInput.indexOf("&") > 0) {
            serialNumber = (serialInput.substring(serialInput.indexOf("&") + 2, 19));
            zwischenString = (serialInput.substring(19));
            if (zwischenString.indexOf("&") == 0) {
                humidity = (zwischenString.substring(zwischenString.indexOf("&") + 2, 7));
                doubleHumidity = Double.parseDouble(humidity) / 100;
                humidity = String.valueOf(doubleHumidity);
                zwischenString = (zwischenString.substring(7));
                if (zwischenString.indexOf("&") == 0) {
                    temperature = (zwischenString.substring(zwischenString.indexOf("&") + 3, 7));
                    doubleTemperature = Double.parseDouble(temperature) / 100;
                    temperature = String.valueOf(doubleTemperature);
                }
            }
        }
        doubleHumidity = Double.parseDouble(humidity);
        doubleTemperature = Double.parseDouble(temperature);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public double getDoubleHumidity() {
        return doubleHumidity;
    }

    public void setDoubleHumidity(double doubleHumidity) {
        this.doubleHumidity = doubleHumidity;
    }

    public double getDoubleTemperature() {
        return doubleTemperature;
    }

    public void setDoubleTemperature(double doubleTemperature) {
        this.doubleTemperature = doubleTemperature;
    }

}
