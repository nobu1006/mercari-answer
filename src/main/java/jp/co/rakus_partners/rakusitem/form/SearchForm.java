package jp.co.rakus_partners.rakusitem.form;

public class SearchForm {

    private Integer page;

    private String itemKeyword;

    private String brand;

    // 検索には使用しない
    // 検索完了時、プルダウンの状態を維持するのにJSから使用する
    private Integer daiCategoryId;
    private Integer chuCategoryId;
    private Integer syoCategoryId;

    // こちらを使用してカテゴリ検索
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemKeyword() {
        return itemKeyword;
    }

    public void setItemKeyword(String itemKeyword) {
        this.itemKeyword = itemKeyword;
    }

    public Integer getDaiCategoryId() {
        return daiCategoryId;
    }

    public void setDaiCategoryId(Integer daiCategoryId) {
        this.daiCategoryId = daiCategoryId;
    }

    public Integer getChuCategoryId() {
        return chuCategoryId;
    }

    public void setChuCategoryId(Integer chuCategoryId) {
        this.chuCategoryId = chuCategoryId;
    }

    public Integer getSyoCategoryId() {
        return syoCategoryId;
    }

    public void setSyoCategoryId(Integer syoCategoryId) {
        this.syoCategoryId = syoCategoryId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
