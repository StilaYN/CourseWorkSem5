package ru.database.coursework.api.common.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class TableData {

    List<String> columnNames;

    List<Map<String, String>> data;
}
