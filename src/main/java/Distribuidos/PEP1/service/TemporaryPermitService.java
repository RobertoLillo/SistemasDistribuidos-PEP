package Distribuidos.PEP1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import Distribuidos.PEP1.dao.TemporaryPermitDao;
import Distribuidos.PEP1.model.TemporaryPermit;

@Service
public class TemporaryPermitService {
    
    private final TemporaryPermitDao temporaryPermitDao;

    @Autowired
    public TemporaryPermitService(@Qualifier("postgresTemporaryPermit") TemporaryPermitDao temporaryPermitDao) {
        this.temporaryPermitDao = temporaryPermitDao;
    }

    public Optional<TemporaryPermit> insertTemporaryPermit(TemporaryPermit temporaryPermit) {
        return temporaryPermitDao.insertTemporaryPermit(temporaryPermit);
    }

    public List<TemporaryPermit> selectAllTemporaryPermits() {
        return temporaryPermitDao.selectAllTemporaryPermits();
    }

    public Optional<TemporaryPermit> selectTemporaryPermitById(UUID id) {
        return temporaryPermitDao.selectTemporaryPermitById(id);
    }


}
