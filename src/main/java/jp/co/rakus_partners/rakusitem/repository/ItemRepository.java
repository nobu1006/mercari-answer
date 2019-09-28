package jp.co.rakus_partners.rakusitem.repository;

import jp.co.rakus_partners.rakusitem.controller.ItemConroller;
import jp.co.rakus_partners.rakusitem.entity.Item;
import jp.co.rakus_partners.rakusitem.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Repository
public class ItemRepository {

    private static final RowMapper<Item> ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Item> search(SearchForm searchForm) {

        MapSqlParameterSource param = new MapSqlParameterSource();
        String sql = createSql(searchForm, param, null);
        return namedJdbcTemplate.query(sql, param, ROW_MAPPER);

    }

    public Integer searchCount(SearchForm searchForm) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        String sql = createSql(searchForm, param, "count");
        return namedJdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    private String createSql(SearchForm searchForm, MapSqlParameterSource param, String mode) {

        String sql = "SELECT";
                sql += "  i.id id";
                sql += " , i.name \"name\"";
                sql += " , condition";
                sql += " , category";
                sql += " , brand";
                sql += " , price";
                sql += " , shipping";
                sql += " , description";
                sql += " , name_all";
                sql += " FROM items i";
                sql += " LEFT JOIN category c ON c.id = i.category";
                sql += " WHERE 1 = 1";

        if (!StringUtils.isEmpty(searchForm.getCategoryName())) {
            sql += " AND name_all LIKE :name_all";
            param.addValue("name_all", searchForm.getCategoryName() + "%");
        }

        if (!"count".equals(mode)) {
            Integer offset = ItemConroller.ROW_PAR_PAGE * (searchForm.getPage() - 1);
            sql += " ORDER BY i.id";
            sql += " LIMIT 30 OFFSET " + offset;
        } else {
            sql = sql.replaceFirst("SELECT.+FROM", "SELECT count(*) FROM");
        }
        return sql;
    }


}
