package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.document_type.model.DocumentType;
import ru.database.coursework.api.document_type.model.DocumentTypeCreationRequest;
import ru.database.coursework.api.document_type.model.DocumentTypeFilter;
import ru.database.coursework.api.document_type.model.DocumentTypeUpdateRequest;
import ru.database.coursework.core.repository.DocumentTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public void createDocumentType(DocumentTypeCreationRequest document) {
        documentTypeRepository.save(document);
    }

    public DocumentType getDocumentTypeById(int id) {
        return documentTypeRepository.findById(id);
    }

    public List<DocumentType> getAllDocumentsType(DocumentTypeFilter documentTypeFilter) {
        String template = (documentTypeFilter == null || documentTypeFilter.template() == null) ?
                null : "%" + documentTypeFilter.template() + "%";
        return documentTypeRepository.findAll(template);
    }

    public void updateDocumentType(DocumentTypeUpdateRequest document) {
        documentTypeRepository.update(document);
    }

    public void deleteDocumentType(int id) {
        documentTypeRepository.delete(id);
    }
}
