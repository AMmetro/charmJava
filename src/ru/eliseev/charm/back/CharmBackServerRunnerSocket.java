package ru.eliseev.charm.back;

import ru.eliseev.charm.back.controller.ProfileController;
import ru.eliseev.charm.back.dao.ProfileDao;
import ru.eliseev.charm.back.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CharmBackServerRunnerSocket {

    public static void main(String[] args) throws IOException {


        ProfileController profileController = new ProfileController(new ProfileService(new ProfileDao()));

        System.out.println(" ====== S e r v e r =======");

        try (ServerSocket serverSocket = new ServerSocket( 8080);
            Socket socket = serverSocket.accept();
            DataOutputStream rsStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream rqStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in)
        ) {

            String request = rqStream.readUTF();
            String response;

            while (!"stop".equals(request)){

                if (request.startsWith("save ")){
                    response = profileController.save(request.split("save ")[1]);
                } else if (request.startsWith("findAll")){
                    response = profileController.findAll();
                }  else if (request.startsWith("findById ")){
                    response = profileController.findById(request.split("findById ")[1]);
                } else if (request.startsWith("updateById ")){
                    response = profileController.updateById(request.split("updateById ")[1]);
                } else if (request.startsWith("deleteById ")){
                    response = profileController.deleteById(request.split("deleteById ")[1]);
                } else {
                    response = "unsupported operation";
                }

                rsStream.writeUTF(response);
                request = rqStream.readUTF();
            }
        }


    }

}
