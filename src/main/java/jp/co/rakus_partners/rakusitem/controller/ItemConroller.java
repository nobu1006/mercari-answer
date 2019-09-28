package jp.co.rakus_partners.rakusitem.controller;

import jp.co.rakus_partners.rakusitem.entity.Category;
import jp.co.rakus_partners.rakusitem.form.SearchForm;
import jp.co.rakus_partners.rakusitem.service.CategoryService;
import jp.co.rakus_partners.rakusitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemConroller {

    public static final int ROW_PAR_PAGE = 30;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    private SearchForm setupForm() {
        return new SearchForm();
    }

    @RequestMapping("/search")
    public String search(SearchForm searchForm, Model model) {
        if (searchForm.getPage() == null) {
            searchForm.setPage(1);
        }
        Integer searchCount = itemService.searchCount(searchForm);
        int maxPage = searchCount / ROW_PAR_PAGE;
        if (searchCount % ROW_PAR_PAGE != 0) {
            maxPage++;
        }
        setCategoryIds(searchForm, categoryService.findAllCategories());

        model.addAttribute("itemList", itemService.search(searchForm));
        model.addAttribute("maxPage", maxPage);
        return "list.html";
    }

    @RequestMapping("/categories")
    @ResponseBody
    public List<Category> categories() {
        return categoryService.findAllCategories();
    }


    /**
     * 検索完了時、カテゴリーのプルダウンを維持するために
     * categoryNameから、daiCategoryId, chuCategoryId, syoCategoryId を求めてフォームにセットする.
     *
     * @param searchForm
     * @param categories
     */
    private void setCategoryIds(SearchForm searchForm, List<Category> categories) {
        // 一旦全てクリアーする
        searchForm.setDaiCategoryId(null);
        searchForm.setChuCategoryId(null);
        searchForm.setSyoCategoryId(null);

        String[] categoryArray = searchForm.getCategoryName().split("/");
        if (categoryArray.length >= 1 && !"".equals(categoryArray[0])) {
            Category daiCategory = getCategoryByName(categories, categoryArray[0]);
            searchForm.setDaiCategoryId(daiCategory.getId());
            if (categoryArray.length >= 2) {
                Category chuCategory = getCategoryByName(daiCategory.getChildCategories(), categoryArray[1]);
                searchForm.setChuCategoryId(chuCategory.getId());
                if (categoryArray.length >= 3) {
                    Category syoCategory = getCategoryByName(chuCategory.getChildCategories(), categoryArray[2]);
                    searchForm.setSyoCategoryId(syoCategory.getId());
                }
            }
        }
    }

    private Category getCategoryByName(List<Category> categories, String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

}
