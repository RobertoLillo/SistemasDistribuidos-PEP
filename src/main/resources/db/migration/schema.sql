DROP TABLE IF EXISTS temporary_permit;

CREATE TABLE temporary_permit
(
    id UUID NOT NULL,
    rut VARCHAR(10),
    name VARCHAR(100),
    address VARCHAR(100),
    mail VARCHAR(100),
    reason VARCHAR(512),
    request_time TIMESTAMP,
    valid_from TIMESTAMP,
    valid_until TIMESTAMP,

    PRIMARY KEY(id)
);

