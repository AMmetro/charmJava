package ru.eliseev.charm.back;

import ru.eliseev.charm.back.controller.ProfileController;
import ru.eliseev.charm.back.dao.ProfileDao;
import ru.eliseev.charm.back.service.ProfileService;

import java.io.IOException;

public class CharmHttpServerRunner {

    public static void main(String[] args) {
        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));
        CharmHttpServer charmHttpServer = new CharmHttpServer(8080, 5, controller);
        charmHttpServer.run();
    }

}
