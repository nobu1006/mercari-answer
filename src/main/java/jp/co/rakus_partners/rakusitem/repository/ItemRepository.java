package jp.co.rakus_partners.rakusitem.repository;

import jp.co.rakus_partners.rakusitem.controller.ItemConroller;
import jp.co.rakus_partners.rakusitem.entity.Item;
import jp.co.rakus_partners.rakusitem.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ItemRepository.class);

    private static final RowMapper<Item> ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Item> search(SearchForm searchForm) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = createSql(searchForm, params, null);
        return namedJdbcTemplate.query(sql, params, ROW_MAPPER);

    }

    public Integer searchCount(SearchForm searchForm) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = createSql(searchForm, params, "count");
        return namedJdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    private String createSql(SearchForm searchForm, MapSqlParameterSource params, String mode) {

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

        // カテゴリー
        if (!StringUtils.isEmpty(searchForm.getCategoryName())) {
            sql += " AND name_all LIKE :name_all";
            params.addValue("name_all", searchForm.getCategoryName() + "%");
        }
        // 商品名（あいまい検索）
        if (!StringUtils.isEmpty(searchForm.getItemKeyword())) {
            sql += " AND i.name LIKE :name";
            params.addValue("name", "%" + searchForm.getItemKeyword() + "%");
        }
        // ブランド名
        if (!StringUtils.isEmpty(searchForm.getBrand())) {
            sql += " AND brand = :brand";
            params.addValue("brand", searchForm.getBrand());
        }
        if ("count".equals(mode)) {
            sql = sql.replaceFirst("SELECT.+FROM", "SELECT count(*) FROM");
        } else {
            sql += " ORDER BY i.id";
            sql += " LIMIT 30 OFFSET " + ItemConroller.ROW_PAR_PAGE * (searchForm.getPage() - 1);
        }
        logger.info("sql = " + sql);
        for (String paramName : params.getParameterNames()) {
            logger.info(paramName + " = " + params.getValue(paramName));
        }
        return sql;
    }


}
