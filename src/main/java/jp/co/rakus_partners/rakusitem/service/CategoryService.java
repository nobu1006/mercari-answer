package jp.co.rakus_partners.rakusitem.service;

import jp.co.rakus_partners.rakusitem.entity.Category;
import jp.co.rakus_partners.rakusitem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        List<Category> daiCategories = categoryRepository.findByParenId(null);
        for (Category daiCategory : daiCategories) {
            List<Category> chuCategories = categoryRepository.findByParenId(daiCategory.getId());
            daiCategory.setChildCategories(chuCategories);
            for (Category chuCategory : chuCategories) {
                List<Category> syoCategories = categoryRepository.findByParenId(chuCategory.getId());
                chuCategory.setChildCategories(syoCategories);
            }
        }
        return daiCategories;
    }


}
