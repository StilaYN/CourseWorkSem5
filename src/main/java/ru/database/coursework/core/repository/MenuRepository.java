package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.database.coursework.core.Entity.MenuItemEntity;

import java.util.List;

@RegisterConstructorMapper(MenuItemEntity.class)
public interface MenuRepository {

    @SqlQuery("""
            SELECT id, name as display_label, uri as action_uri FROM menu WHERE parent_id IS NULL ORDER BY sequence;
            """)
    List<MenuItemEntity> findTopLevelItems();

    @SqlQuery("""
            SELECT id, name as display_label, uri as action_uri FROM menu WHERE parent_id =:parent_id ORDER BY sequence;
            """)
    List<MenuItemEntity> findByParentId(@Bind("parent_id") int parentId);
}
