package Distribuidos.PEP1.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Distribuidos.PEP1.model.TemporaryPermit;
import Distribuidos.PEP1.service.TemporaryPermitService;

@CrossOrigin
@RestController
public class TemporaryPermitController {

    private final TemporaryPermitService temporaryPermitService;

    @Autowired
    public TemporaryPermitController(@NonNull TemporaryPermitService temporaryPermitService) {
        this.temporaryPermitService = temporaryPermitService;
    }

    @PostMapping("/api/newTemporaryPermit")
    public Optional<TemporaryPermit> insertTemporaryPermit(@RequestBody TemporaryPermit temporaryPermit) {
        return temporaryPermitService.insertTemporaryPermit(temporaryPermit);
    }

    @GetMapping("/api/allTemporaryPermits")
    public List<TemporaryPermit> selectAllTemporaryPermits() {
        return temporaryPermitService.selectAllTemporaryPermits();
    }

    @GetMapping("/api/temporaryPermitById/{id}")
    public Optional<TemporaryPermit> selectTemporaryPermitById(@PathVariable("id") UUID id) {
        return temporaryPermitService.selectTemporaryPermitById(id);
    }
    
}
