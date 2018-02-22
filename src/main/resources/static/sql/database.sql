CREATE DOMAIN d_name VARCHAR(128) NOT NULL
CONSTRAINT CK_d_name CHECK (TRIM(VALUE) != '' and VALUE !~ '^[[:digit:]]+$');

CREATE TABLE Person_state_type (
  person_state_type_code INTEGER NOT NULL,
  name d_name,
  CONSTRAINT PK_Person_state_type_person_state_type_code PRIMARY KEY (person_state_type_code),
  CONSTRAINT AK_Person_state_type_name UNIQUE (name)
);

CREATE TABLE Person (
  person_id BIGSERIAL NOT NULL,
  firstname VARCHAR(1000) NOT NULL,
  lastname VARCHAR(1000) NOT NULL,
  person_state_type_code INTEGER NOT NULL DEFAULT 1,
  uni_id VARCHAR(6) NOT NULL,
  /*
  id_code VARCHAR(11) NOT NULL,
  address VARCHAR(256) NOT NULL,
   */
  email VARCHAR(256) NOT NULL, /* TODO: make trigger uni_id || '@ttu.ee' */
  password VARCHAR(72) NOT NULL,
  reg_time TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT PK_Person_person_id PRIMARY KEY (person_id),
  CONSTRAINT AK_Person_uni_id UNIQUE (uni_id),
  CONSTRAINT CK_Person_firstname CHECK (TRIM(firstname) != '' AND firstname !~ '^[[:digit:]]+$'),
  CONSTRAINT CK_Person_lastname CHECK (TRIM(lastname) != '' AND lastname !~ '^[[:digit:]]+$'),
  CONSTRAINT CK_Person_uni_id CHECK (uni_id ~ '^[a-z]{6}$'),
  CONSTRAINT CK_Person_email CHECK (email~'^[a-z0-9!#$%&''*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&''*+\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$'),
  CONSTRAINT CK_Person_reg_time CHECK (reg_time = now()),
  CONSTRAINT FK_Person_person_state_type_code FOREIGN KEY (person_state_type_code) REFERENCES Person_state_type (person_state_type_code) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IXFK_Person_person_state_type_code ON Person (person_state_type_code ASC)
;
CREATE UNIQUE INDEX IXAK_Person_email ON Person (lower(email))
;
