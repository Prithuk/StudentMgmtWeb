/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prithu.simw.controller;

import com.prithu.sim.dto.User;
import com.prithu.sim.repository.UserRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class UserControllerWeb implements Serializable {

    private List<User> userList;
    private User user;

    @Inject
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        userList = new ArrayList<>();
        user = new User();
        loadData();

    }

    public void loadData() {
        userList = userRepository.getUserListFromDB();
    }

    public void beforeCreate() {
        user = new User();
        System.out.println(user);
    }

    public void addUser() {
        userRepository.addNewUser(user);
        this.user = new User();
        loadData();
    }

    public void beforeEdit(Long id) {
        user = userRepository.findById(id);
        System.out.println(user);
    }

    public void editUser() {
        userRepository.editUser(user);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage("User is updated successfully"));
        loadData();

    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
        loadData();
    }
}
