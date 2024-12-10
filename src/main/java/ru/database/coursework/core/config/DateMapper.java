package ru.database.coursework.core.config;

import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateMapper implements ColumnMapper<Date> {

    @Override
    public Date map(ResultSet r, String columnLabel, StatementContext ctx) throws SQLException {
        return Date.valueOf(r.getString(columnLabel));
    }

    @Override
    public void init(ConfigRegistry registry) {
        ColumnMapper.super.init(registry);
    }

    @Override
    public Date map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        return Date.valueOf(r.getString(columnNumber));
    }
}
