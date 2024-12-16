package com.example.ICE.service;

import Model.User;
import Persistens.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private List<User> users = UserRepo.loadUsers();

    public List<User> allUsers(){
        return users;
    }
}
