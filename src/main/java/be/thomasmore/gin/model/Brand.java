package be.thomasmore.gin.model;

import javax.persistence.*;

@Entity
public class Brand {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    @SequenceGenerator(name="brand_generator",sequenceName = "brand_seq",allocationSize = 1)
    @Id
    private Integer id;
    private String name;
    private Integer introduced;
    @Column(length = 800)
    private String description;
    @ManyToOne
    private Recept recept;

    public Brand(){

    }

    public Brand(Integer id,Integer introduced, String name, String description) {
        this.id = id;
        this.introduced = introduced;
        this.name = name;
        this.description = description;
    }
    //many to one

    public Integer getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Integer introduced) {
        this.introduced = introduced;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
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
