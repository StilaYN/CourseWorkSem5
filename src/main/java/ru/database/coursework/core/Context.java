package ru.database.coursework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.database.coursework.api.common.model.MenuItem;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Context {

    public static String message;

    public static List<MenuItem> menu;

    public static Map<String, Integer> tablesName;

    public static Map<Integer, Map<String, Boolean>> authorityMap;
}
