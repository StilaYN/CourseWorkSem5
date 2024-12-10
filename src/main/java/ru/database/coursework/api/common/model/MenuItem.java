package ru.database.coursework.api.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuItem {
    private String displayLabel;
    private String actionUri;
    private List<MenuItem> children;
}
