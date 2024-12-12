package ru.database.coursework.api.document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.DocumentService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping(ApiPaths.DOCUMENT)
    public String getDocument(Model model, String sqlQuery) {
        List<Map<String, Object>> result = documentService.getResult(sqlQuery);
        model.addAttribute("query", sqlQuery);
        model.addAttribute("results", result);
        model.addAttribute("keys", result.getFirst().keySet());
        model.addAttribute("menu", Context.menu);
        return "document/list";
    }

    @PostMapping(ApiPaths.DOCUMENT_FILE)
    public String postDocument(String sqlQuery, String fileName) {
        log.info(sqlQuery);
        documentService.export(sqlQuery, fileName);
        return "redirect:" + ApiPaths.DOCUMENT + "?sqlQuery=" + sqlQuery;
    }

}
