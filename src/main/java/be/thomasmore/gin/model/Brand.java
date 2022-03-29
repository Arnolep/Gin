package be.thomasmore.gin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Brand {
    @Id
    private Integer id;
    private String name;
    private boolean londondry;
    private boolean spiced;
    @Column(length = 800)
    private String description;
    @ManyToOne
    private Recept recept;

    public Brand(){

    }

    public Brand(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    //many to one

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }

    public boolean isLondondry() {
        return londondry;
    }

    public void setLondondry(boolean londondry) {
        this.londondry = londondry;
    }

    public boolean isSpiced() {
        return spiced;
    }

    public void setSpiced(boolean spiced) {
        this.spiced = spiced;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
