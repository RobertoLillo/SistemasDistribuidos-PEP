package Distribuidos.PEP1.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import Distribuidos.PEP1.model.TemporaryPermit;

public interface TemporaryPermitDao {

    default Optional<TemporaryPermit> insertTemporaryPermit(TemporaryPermit temporaryPermit) {
        UUID id = UUID.randomUUID();
        return insertTemporaryPermit(id, temporaryPermit);
    }

    Optional<TemporaryPermit> insertTemporaryPermit(UUID id, TemporaryPermit temporaryPermit);

    List<TemporaryPermit> selectAllTemporaryPermits();

    Optional<TemporaryPermit> selectTemporaryPermitById(UUID id);
    
}
