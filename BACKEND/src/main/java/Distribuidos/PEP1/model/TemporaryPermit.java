package Distribuidos.PEP1.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemporaryPermit {

    private final UUID id;
    private final String rut;
    private final String name;
    private final String address;
    private final String mail;
    private final String reason;
    private final Timestamp request_time;
    private final Timestamp valid_from;
    private final Timestamp valid_until;

    public TemporaryPermit(
                @JsonProperty("id") UUID id,
                @JsonProperty("rut") String rut,
                @JsonProperty("name") String name,
                @JsonProperty("address") String address,
                @JsonProperty("mail") String mail,
                @JsonProperty("reason") String reason,
                @JsonProperty("request_time") Timestamp request_time,
                @JsonProperty("valid_from") Timestamp valid_from,
                @JsonProperty("valid_until") Timestamp valid_until) {
        this.id = id;
        this.rut = rut;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.reason = reason;
        this.request_time = request_time;
        this.valid_from = valid_from;
        this.valid_until = valid_until;
    };

    public UUID getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public String getReason() {
        return reason;
    }

    public Timestamp getRequestTime() {
        return request_time;
    }

    public Timestamp getValidFrom() {
        return valid_from;
    }

    public Timestamp getValidUntil() {
        return valid_until;
    }
    
}