package jp.co.rakus_partners.rakusitem.entity;

public class Item {

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

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Integer id;
    private String name;
    private Integer condition;
    private Integer category;
    private String brand;
    private Integer price;
    private Integer shipping;
    private String description;

    private String nameAll;

    public String getNameAll() {
        return nameAll;
    }

    public void setNameAll(String nameAll) {
        this.nameAll = nameAll;
    }

    public String getDaiCategoryName() {
        return nameAll != null ? nameAll.split("/")[0] : "";
    }

    public String getChuCategoryName() {
        return nameAll != null ? nameAll.split("/")[1] : "";
    }

    public String getSyoCategoryName() {
        return nameAll != null ? nameAll.split("/")[2] : "";
    }

}
