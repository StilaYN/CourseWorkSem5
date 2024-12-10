package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.common.model.MenuItem;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.Entity.MenuItemEntity;
import ru.database.coursework.core.mapper.MenuMapper;
import ru.database.coursework.core.repository.MenuRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);
    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    public void findAll() {
        Context.menu = menuRepository.findTopLevelItems()
                .stream()
                .filter(element -> Context.authorityMap.get(element.id()).get("r"))
                .map(menuItemEntity -> menuMapper.map(menuItemEntity, findChild(menuItemEntity)))
                .toList();
        log.info("menu :{}", Context.menu);
    }

    private List<MenuItem> findChild(MenuItemEntity item) {
        List<MenuItemEntity> child = menuRepository.findByParentId(item.id());
        if (child == null || child.isEmpty()) {
            return null;
        }
        return child.stream()
                //.filter(element-> Context.authorityMap.get(element.id()).get("r"))
                .map(menuItemEntity -> menuMapper.map(menuItemEntity, findChild(menuItemEntity)))
                .toList();
    }

}
