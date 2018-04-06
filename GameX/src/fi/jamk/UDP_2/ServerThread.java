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
public class ServerThread extends Thread{
    private DatagramSocket socket;
    private DatagramPacket inputPacket;
    ServerThread() throws SocketException{
        //Luodaan datagram socket portti:7500
        this.socket = new DatagramSocket(7500);
    }
    @Override
    public void run(){
        try {
            System.out.println("SERVER > [Käynnistetty]");
            //Luodaan puskuri Byte(8b/1t) tietotyypillä. Taulukko(1kt/1024t)
            byte[] buffer = new byte[1024];
            //Vastanotetaan
            input(buffer);
            //Lähetetään
            output("pong".getBytes(), inputPacket.getAddress(), inputPacket.getPort());
            String strMsg = new String(buffer, 0, inputPacket.getLength());
            System.out.printf("SERVER > [" + inputPacket.getAddress().getHostAddress() + " : " + inputPacket.getPort() + "]>" + strMsg + "\n");
            socket.close();
        } catch (IOException e) {}
    }//Lähettää
    public void output( byte[] buffer, InetAddress sAddress, int port ) throws IOException{
        DatagramPacket outputPacket = new DatagramPacket(buffer, buffer.length, sAddress, port);
        socket.send(outputPacket);
    }//Vastaanottaa
    public void input( byte[] buffer ) throws IOException{
        inputPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(inputPacket);
    }//Käynnistää Server:in
    public static void main(String[] args) throws SocketException, UnknownHostException{
        ServerThread T1 = new ServerThread();
        T1.start();
    }
}
