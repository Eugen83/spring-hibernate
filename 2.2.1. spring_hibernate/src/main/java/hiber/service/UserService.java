package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    Car add(User user);
    List<User> listUsers();
}
