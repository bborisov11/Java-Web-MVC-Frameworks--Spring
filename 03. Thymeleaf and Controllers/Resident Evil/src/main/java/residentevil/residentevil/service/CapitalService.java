package residentevil.residentevil.service;

import org.springframework.stereotype.Service;
import residentevil.residentevil.domain.models.service.CapitalServiceModel;

import java.util.List;

public interface CapitalService {
    List<CapitalServiceModel> findAllCapitals();
}
