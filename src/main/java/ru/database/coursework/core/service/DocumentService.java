package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.database.coursework.core.exception.FileException;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getResult(String query) {
        try {
            if (query == null || query.isEmpty()) {
                return List.of(Map.of("result", "none"));
            }
            List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
            if (result.isEmpty()) {
                return List.of(Map.of("result", "none"));
            }
            return result;
        } catch (Exception e) {
            return List.of(Map.of("message", e.getMessage()));
        }
    }

    public void export(String query, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            List<Map<String, Object>> resultData = getResult(query);
            String res = "";
            for (String key : resultData.getFirst().keySet()) {
                res += key  + "\t";
            }
            writer.println(res);
            for (Map<String, Object> result : getResult(query)) {
                res = "";
                for (String key : result.keySet()) {
                    res += result.get(key) + "\t";
                }
                writer.println(res);
            }

        } catch (Exception e) {
            throw new FileException("exception.file");
        }
    }

}
