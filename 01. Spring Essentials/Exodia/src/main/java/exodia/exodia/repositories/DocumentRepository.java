package exodia.exodia.repositories;


import exodia.exodia.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

    Document save(Document entity);

    List<Document> findAll();

    void removeDocumentById(String id);

    Document findByTitle(String title);
}
