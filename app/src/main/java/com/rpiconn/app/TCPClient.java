package com.rpiconn.app;
import android.util.Log;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import android.widget.CheckBox;

public class TCPClient {

    public String SERVERIP;
    public int SERVERPORT = 4321;

    PrintWriter out;
    BufferedReader in;

    public TCPClient(String serverip) {
        SERVERIP = serverip;
    }

    public void sendMessage(String message){
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }

    public boolean run() {
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.e("TCP Client", "C: Connecting...");

            //create a socket to make the connection with the server
            Socket socket = new Socket(serverAddr, SERVERPORT);

            try {

                //send the message to the server
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                Log.e("TCP Client", "C: Connected.");

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("Gogogo");
                out.flush();

                return true;

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
            }

        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);

        }
        return false;
    }
}