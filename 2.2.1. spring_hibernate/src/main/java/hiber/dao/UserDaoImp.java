package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    // @SuppressWarnings("unchecked")
    public void truncateUsersTable() {
        List<User> users = listUsers();
        for (User user : users) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    @Transactional
    public void truncateCarTable() {
        TypedQuery<Car> carQuery = sessionFactory.getCurrentSession().createQuery("from Car");
        List<Car> carList = carQuery.getResultList();
        for (Car car : carList) {
            sessionFactory.getCurrentSession().delete(car);
        }
    }


    @Override
    @Transactional
    public User getCarsUser(String model, int series) {
        TypedQuery<Car> seekedCarQuery = sessionFactory.getCurrentSession().createQuery
                ("from Car where model = :model and series = :series")
                .setParameter("model", model)
                .setParameter("series", series);
        List<Car> seekedCarList = seekedCarQuery.getResultList();
        if (!seekedCarList.isEmpty()) {
            Car seekedCar = seekedCarList.get(0);
            List<User> userList = listUsers();
            User seekedUser = null;
            for (User user : userList) {
                if (user.getCar().equals(seekedCar)) {
                    seekedUser = user;
                    break;
                }
            }
            return seekedUser;
        }
        return null;
    }

}