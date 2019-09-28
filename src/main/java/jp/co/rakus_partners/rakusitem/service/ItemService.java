package jp.co.rakus_partners.rakusitem.service;

import jp.co.rakus_partners.rakusitem.entity.Item;
import jp.co.rakus_partners.rakusitem.form.SearchForm;
import jp.co.rakus_partners.rakusitem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> search(SearchForm searchForm) {
        return itemRepository.search(searchForm);
    }

    public Integer searchCount(SearchForm searchForm) {
        return itemRepository.searchCount(searchForm);
    }
}


