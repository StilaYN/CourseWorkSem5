package ru.database.coursework.api.document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.document.model.FileInfo;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.exception.FileException;
import ru.database.coursework.core.service.DocumentService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping(ApiPaths.DOCUMENT)
    public String getDocument(Model model, FileInfo fileInfo, String sqlQuery) {
        List<Map<String, Object>> result = documentService.getResult(sqlQuery);
        model.addAttribute("query", sqlQuery);
        model.addAttribute("results", result);
        model.addAttribute("keys", result.getFirst().keySet());
        model.addAttribute("menu", Context.menu);
        return "document/list";
    }

    @PostMapping(ApiPaths.DOCUMENT_FILE)
    public String postDocument(String sqlQuery, FileInfo fileInfo, BindingResult result, Model model) {
        log.info(sqlQuery);
        try {
            documentService.export(sqlQuery, fileInfo.fileName());
        } catch (FileException e) {
            ObjectError error = new ObjectError("global", e.getMessage());
            List<Map<String, Object>> resultMap = documentService.getResult(sqlQuery);
            result.addError(error);
            model.addAttribute("query", sqlQuery);
            model.addAttribute("results", resultMap);
            model.addAttribute("keys", resultMap.getFirst().keySet());
            model.addAttribute("menu", Context.menu);
            return "document/list";
        }
        return "redirect:" + ApiPaths.DOCUMENT + "?sqlQuery=" + sqlQuery;
    }

}
