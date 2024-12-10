package ru.database.coursework.core.mapper;

import org.mapstruct.Mapper;
import ru.database.coursework.api.common.model.MenuItem;
import ru.database.coursework.core.Entity.MenuItemEntity;

import java.util.List;

@Mapper
public interface MenuMapper {

    MenuItem map(MenuItemEntity menuItem, List<MenuItem> children);
}
