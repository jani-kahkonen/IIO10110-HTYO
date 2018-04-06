package fi.jamk.UDP_2;

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
//Testaus luokka Server:lle ja Client:lle
public class Testi {
        //Käynnistää Server:in ja Client:in
        public static void main(String[] args) throws SocketException, UnknownHostException{
        ServerThread T1 = new ServerThread();
        T1.start();
        ClientThread T2 = new ClientThread("localhost");
        T2.start();
    }
}
