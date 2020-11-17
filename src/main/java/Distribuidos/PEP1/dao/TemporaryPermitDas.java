package Distribuidos.PEP1.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import Distribuidos.PEP1.model.TemporaryPermit;

@Repository("postgresTemporaryPermit")
public class TemporaryPermitDas implements TemporaryPermitDao {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Optional<TemporaryPermit> insertTemporaryPermit(UUID id, TemporaryPermit temporaryPermit) {
        final String sql1 = "INSERT INTO temporary_permit (id, rut, name, address, reason, request_time, valid_from, valid_until) ";
        final String sql2 = "VALUES (:id, :rut, :name, :address, :reason, ";
        final String sql3 = "current_timestamp, ";  // Tiempo en que se pide el permiso.
        final String sql4 = "current_timestamp + (10 * interval '1 minute'), "; // Espacio de 10 minutos desde que se pide el permiso.
        final String sql5 = "current_timestamp + (130 * interval '1 minute'))"; // Espacio de 2 horas para utlizar el permiso.
        final String sql = sql1 + sql2 + sql3 + sql4 + sql5;

        try (Connection db = sql2o.open()) {
            db.createQuery(sql)
                .addParameter("id", id)
                .addParameter("rut", temporaryPermit.getRut())
                .addParameter("name", temporaryPermit.getName())
                .addParameter("address", temporaryPermit.getAddress())
                .addParameter("reason", temporaryPermit.getReason())
                .executeUpdate();
            
            return selectTemporaryPermitById(id);
        }
    }

	@Override
	public List<TemporaryPermit> selectAllTemporaryPermits() {
        final String sql = "SELECT * FROM temporary_permit";
        try (Connection db = sql2o.open()) {
            return db.createQuery(sql)
                .executeAndFetch(TemporaryPermit.class);
        }
	}

    @Override
    public Optional<TemporaryPermit> selectTemporaryPermitById(UUID id) {
        final String sql1 = "SELECT * FROM temporary_permit ";
        final String sql2 = "WHERE id = :searchId";
        final String sql = sql1 + sql2;

        try (Connection db = sql2o.open()) {
            return db.createQuery(sql)
                .addParameter("searchId", id)
                .executeAndFetch(TemporaryPermit.class)
                .stream()
                .findFirst();
        }
    }
    
}
