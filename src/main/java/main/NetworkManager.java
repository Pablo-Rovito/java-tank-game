package main;

import java.io.ObjectInputStream;
import java.net.Socket;

public class NetworkManager {

    public NetworkManager() {

    }

    public void createNewConnection() {
        try {
            Socket
                    s = new Socket("scalenzo.duckdns.org", 4444);
            ObjectInputStream
                    objectInputStream = new ObjectInputStream(s.getInputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){

                        }
                    } catch (Exception e) {
                        System.err.println("se pudrió");
                    }
                }
            });
            System.out.println(objectInputStream.readUTF());
            s.close();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
