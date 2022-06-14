package be.thomasmore.gin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Recept {
    @Id
    private Integer id;
    private String name;
    private String Produce;
    private String Condiments;
    private String BakingAndSpices;
    private String Liquids;
    private Double price;
    private boolean water;
    @ManyToMany(mappedBy = "favorites")
    Collection<User> users;
    private boolean sugar;

public Recept(){

}

    public Recept(Integer id, String name,String produce, String condiments, String bakingAndSpices, String liquids) {
        this.id = id;
        this.name = name;
        this.Produce = produce;
        this.Condiments = condiments;
        this.BakingAndSpices = bakingAndSpices;
        this.Liquids = liquids;
    }

    public Double getPrice() {
        return price;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduce() {
        return Produce;
    }

    public void setProduce(String produce) {
        Produce = produce;
    }

    public String getCondiments() {
        return Condiments;
    }

    public void setCondiments(String condiments) {
        Condiments = condiments;
    }

    public String getBakingAndSpices() {
        return BakingAndSpices;
    }

    public void setBakingAndSpices(String bakingAndSpices) {
        BakingAndSpices = bakingAndSpices;
    }

    public String getLiquids() {
        return Liquids;
    }

    public void setLiquids(String liquids) {
        Liquids = liquids;
    }


}
