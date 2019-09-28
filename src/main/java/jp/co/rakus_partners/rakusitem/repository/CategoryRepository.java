package jp.co.rakus_partners.rakusitem.repository;

import jp.co.rakus_partners.rakusitem.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private static final RowMapper<Category> ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Category> findByParenId(Integer parentId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        String sql = "SELECT id, name FROM category";
        if (parentId != null) {
            sql += " WHERE parent = :parent";
            param.addValue("parent", parentId);
        } else {
            sql += " WHERE parent is null";
        }
        sql += " ORDER BY id";
        return namedJdbcTemplate.query(sql, param, ROW_MAPPER);
    }
}
