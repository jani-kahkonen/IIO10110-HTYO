package fi.jamk.UDP_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jani
 */
public class ClientThread extends Thread{
    private DatagramSocket socket;
    private InetAddress sAddress;
    private DatagramPacket inputPacket;
    ClientThread(String sAddress) throws SocketException, UnknownHostException{
        //Luodaan datagram socket portti:oletus
        this.socket = new DatagramSocket();
        //Haetaan localhost:in/Server:in osoite
        this.sAddress = InetAddress.getByName(sAddress);
    }
    @Override
    public void run(){
        try {
            System.out.println("CLIENT > [Käynnistetty]");
            //Luodaan puskuri Byte(8b/1t) tietotyypillä. Taulukko(1kt/1024t)
            byte[] buffer = new byte[1024];
            //Vastanotetaan
            output("Ping".getBytes());
            //Lähetetään
            input(buffer);
            String strMsg = new String(buffer, 0, inputPacket.getLength());
            System.out.printf("CLIENT > [" + inputPacket.getAddress().getHostAddress() + " : " + inputPacket.getPort() + "]>" + strMsg + "\n");
            socket.close();
        } catch (IOException e) {}
    }//Lähetettää
    public void output( byte[] buffer ) throws IOException{
        DatagramPacket outputPacket = new DatagramPacket(buffer, buffer.length, sAddress, 7500);
        socket.send(outputPacket);
    }//Vastaanottaa
    public void input( byte[] buffer ) throws IOException{
        inputPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(inputPacket);
    }//Käynnistää Client:in
    public static void main(String[] args) throws SocketException, UnknownHostException{
        ClientThread T2 = new ClientThread("localhost");
        T2.start();
    }
}
