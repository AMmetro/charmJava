package ru.eliseev.charm.back.controller;

import ru.eliseev.charm.back.model.Profile;
import ru.eliseev.charm.back.service.ProfileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProfileController {
    private final ProfileService profileService;
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public String findAll() {
        Profile profileDto = new Profile();
        List<Profile> savedProfile = profileService.findAll();
        return savedProfile.toString();
    }

    public String findById(String id) {
        if (id == null) return Optional.empty().toString();
        Long evaluatedId;
        try {
            evaluatedId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            return "Id is incorrect";
        }
        Optional<Profile> maybeProfile = profileService.findById(evaluatedId);
        if (maybeProfile.isEmpty()){
            return "not found";
        }
        return maybeProfile.get().toString();
    }




    public String updateById(String id) {
        if (id == null) return Optional.empty().toString();
        Long evaluatedId;
        try {
            evaluatedId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            return "Id is incorrect";
        }
        Optional<Profile> maybeProfile = profileService.findById(evaluatedId);
        if (maybeProfile.isEmpty()){
            return "not found";
        }

        return maybeProfile.get().toString();
    }





    public String deleteById(String id) {
        if (id == null) return Optional.empty().toString();
        Long evaluatedId;
        try {
            evaluatedId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            return "Id is incorrect";
        }
        Boolean isDeleted = profileService.delete(evaluatedId);
        return isDeleted.toString();
    }

    public String save(String request) {
        String[] params = request.split(",");
        if (params.length < 4){
            return "bad request";
        }
        Profile profileDto = new Profile();
        System.out.println("requestData");
        profileDto.setEmail(params[0]);
        profileDto.setName(params[0]);
        profileDto.setSurname(params[2]);
        profileDto.setAbout(params[3]);
        Profile savedProfile = profileService.save(profileDto);
        return savedProfile.toString();
    }



}
