package ru.database.coursework.api.document_type;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.document_type.model.DocumentTypeCreationRequest;
import ru.database.coursework.api.document_type.model.DocumentTypeFilter;
import ru.database.coursework.api.document_type.model.DocumentTypeUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.DocumentTypeService;

@Controller
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @PostMapping(ApiPaths.DOCUMENT_TYPE_CREATE)
    public String createDocument(DocumentTypeCreationRequest request) {
        documentTypeService.createDocumentType(request);
        return "redirect:/documentType/list";
    }

    @GetMapping(ApiPaths.DOCUMENT_TYPE_CREATE)
    public String getCreateDocumentTypePage(Model model) {
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.document.type")));
        return "document_type/create";
    }

    @GetMapping(ApiPaths.DOCUMENT_TYPE_LIST)
    public String getDocumentTypeList(@ParameterObject DocumentTypeFilter documentFilter, Model model) {
        model.addAttribute("documentList", documentTypeService.getAllDocumentsType(documentFilter));
        model.addAttribute("searchResult", documentFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.document.type")));
        return "document_type/list";
    }

    @PostMapping(ApiPaths.DOCUMENT_TYPE_UPDATE)
    public String updateDocumentType(DocumentTypeUpdateRequest documentUpdateRequest) {
        if (documentUpdateRequest.delete()) {
            documentTypeService.deleteDocumentType(documentUpdateRequest.id());
        } else {
            documentTypeService.updateDocumentType(documentUpdateRequest);
        }
        return "redirect:/documentType/list";
    }

    @GetMapping(ApiPaths.DOCUMENT_TYPE_UPDATE)
    public String getUpdateDocumentTypePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("documentType", documentTypeService.getDocumentTypeById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.document.type")));
        return "document_type/update";
    }

}
