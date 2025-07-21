package ru.eliseev.charm.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CharmBackClientRunnerSocket {

    public static void main(String[] args) throws IOException {

        System.out.println("====== C l i e n t =======");
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream rqStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream rsStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                rqStream.writeUTF(request);
                String response = rsStream.readUTF();
                System.out.println("response: " + response);
            }
        }
    }


}