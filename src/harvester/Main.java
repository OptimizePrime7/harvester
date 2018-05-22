/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author ReneZ
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Database db = new Database();
        Ethernet sensor = new Ethernet();

        Runnable r;
        r = () -> {
            while (true) {

                sensor.getEthernet();
                sensor.buildFieldsOutOfString();

                db.insertDataToDB(sensor.getDoubleHumidity(), sensor.getDoubleTemperature());
                try {
                    TimeUnit.SECONDS.sleep(30);
                    System.out.println("wake!");
                } catch (InterruptedException ex) {
                    System.out.println("error");
                }
            }
        };
        new Thread(r).start();
    }
}
