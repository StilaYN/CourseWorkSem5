package ru.database.coursework.core.mapper;

import org.mapstruct.Mapper;
import ru.database.coursework.core.entity.UserPrivileges;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface AuthMapper {

    default Map<Integer, Map<String, Boolean>> map(List<UserPrivileges> userPrivileges) {
        Map<Integer, Map<String, Boolean>> map = new HashMap<>();
        userPrivileges.forEach(userPrivilege -> {
            map.put(userPrivilege.menuId(), Map.of(
                            "r", userPrivilege.read(),
                            "w", userPrivilege.write(),
                            "e", userPrivilege.edit(),
                            "d", userPrivilege.delete()
                    )
            );
        });
        return map;
    }

    default Map<String, Integer> mapToTableName(List<UserPrivileges> userPrivileges) {
        Map<String, Integer> map = new HashMap<>();
        userPrivileges.forEach(userPrivilege -> {
            map.put(userPrivilege.name(), userPrivilege.menuId());
        });
        return map;
    }
}
