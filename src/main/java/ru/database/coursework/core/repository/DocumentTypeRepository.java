package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.document_type.model.DocumentType;
import ru.database.coursework.api.document_type.model.DocumentTypeCreationRequest;
import ru.database.coursework.api.document_type.model.DocumentTypeUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(DocumentType.class)
public interface DocumentTypeRepository {

    @SqlUpdate("""
            INSERT INTO document_types (document_type_name) VALUES (:document.documentTypeName)
            """)
    void save(@BindMethods(value = "document") DocumentTypeCreationRequest document);

    @SqlQuery("""
            SELECT * FROM document_types WHERE id = :document_type_id;
            """)
    DocumentType findById(@Bind("document_type_id") int documentTypeId);

    @SqlQuery("""
            SELECT * FROM document_types WHERE document_type_name ILIKE coalesce(:document_type_name, document_type_name) ORDER BY id
            """)
    List<DocumentType> findAll(@Bind("document_type_name") String documentTypeName);

    @SqlUpdate("""
            UPDATE document_types SET document_type_name=:document.documentTypeName WHERE id=:document.id;
            """)
    void update(@BindMethods("document") DocumentTypeUpdateRequest city);

    @SqlUpdate("""
            DELETE FROM document_types WHERE id = :id;
            """)
    void delete(@Bind("id") Integer id);
}
