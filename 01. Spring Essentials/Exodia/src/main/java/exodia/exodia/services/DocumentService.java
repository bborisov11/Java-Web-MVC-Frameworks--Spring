package exodia.exodia.services;


import exodia.exodia.models.service.DocumentServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;
public interface DocumentService {

    DocumentServiceModel createDocument(DocumentServiceModel userServiceModel);

    List<DocumentServiceModel> getAllDocuments();

    DocumentServiceModel getById(String id);

    void removeDocument(String id);

    String getByTitle(String title);
}
