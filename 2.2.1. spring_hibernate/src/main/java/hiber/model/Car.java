package hiber.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;



@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private long id;

    @Column (name = "model")
    private String model;

    @Column (name = "series")
    private int series;


    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car(){}

    public Car(String model, int series){
        this.model = model;
        this.series = series;

    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }



    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
