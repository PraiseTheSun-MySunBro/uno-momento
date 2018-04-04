CREATE DOMAIN d_name VARCHAR(128) NOT NULL
CONSTRAINT CK_d_name CHECK (TRIM(VALUE) != '' and VALUE !~ '^[[:digit:]]+$');

CREATE TABLE Account_State (
  account_state_code SMALLINT NOT NULL,
  ee_name d_name,
  en_name d_name,
  CONSTRAINT PK_Account_State_account_state_code PRIMARY KEY (account_state_code),
  CONSTRAINT AK_Account_State_ee_name UNIQUE (ee_name),
  CONSTRAINT AK_Account_State_en_name UNIQUE (en_name)
);

CREATE TABLE Degree (
  degree_code SMALLINT NOT NULL,
  ee_name d_name,
  en_name d_name,
  CONSTRAINT PK_Degree_degree_code PRIMARY KEY (degree_code),
  CONSTRAINT AK_Degree_ee_name UNIQUE (ee_name),
  CONSTRAINT AK_Degree_en_name UNIQUE (en_name)
);

COMMENT ON TABLE Degree IS '1 -- No Degree, 2 -- Bachelor, 3 -- Master, 4 -- Doctoral, 5 -- Applied Higher Education';

CREATE TABLE Account_Role (
  account_role_code SMALLINT NOT NULL,
  role_name d_name,
  CONSTRAINT PK_Account_Role_account_role_code PRIMARY KEY (account_role_code),
  CONSTRAINT AK_Account_Role_role_name UNIQUE (role_name)
);

CREATE TABLE Account (
  account_id BIGSERIAL NOT NULL,
  username VARCHAR(32) NOT NULL,
  password VARCHAR(72) NOT NULL,
  email VARCHAR(256) NOT NULL, /* TODO: make trigger uniid || '@ttu.ee' */
  account_state_code SMALLINT NOT NULL DEFAULT 1,
  account_role_code SMALLINT NOT NULL DEFAULT 1,
  reg_time TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT PK_Account_account_id PRIMARY KEY (account_id),
  CONSTRAINT FK_Account_account_state_code FOREIGN KEY (account_state_code) REFERENCES Account_State (account_state_code) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT FK_Account_account_role_code FOREIGN KEY (account_role_code) REFERENCES Account_Role (account_role_code) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT CK_Account_username CHECK (username ~ '^\w{3,}$'),
  CONSTRAINT CK_Account_password CHECK (TRIM(password) != ''),
  CONSTRAINT CK_Account_email CHECK (email ~ '^[a-z0-9!#$%&''*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&''*+\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$'),
  CONSTRAINT CK_Account_reg_time CHECK (reg_time <= now())
);

CREATE UNIQUE INDEX IXAK_Account_email ON Account (LOWER(email) ASC);
CREATE UNIQUE INDEX IXAK_Account_username ON Account (LOWER(username) ASC);
CREATE INDEX IXFK_Account_account_state_code ON Account (account_state_code ASC);
CREATE INDEX IXFK_Account_account_role_code ON Account (account_role_code ASC);

CREATE TABLE Person (
  person_id BIGSERIAL NOT NULL,
  uni_id VARCHAR(6) NOT NULL, /* TODO: create by function */
  degree_code SMALLINT NOT NULL,
  firstname VARCHAR(1000) NOT NULL,
  lastname VARCHAR(1000) NOT NULL,
  person_state_code SMALLINT NOT NULL DEFAULT 1,
  CONSTRAINT PK_Person_person_id PRIMARY KEY (person_id),
  CONSTRAINT FK_Person_degree_code FOREIGN KEY (degree_code) REFERENCES Degree (degree_code) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT CK_Person_firstname CHECK (TRIM(firstname) != '' AND firstname !~ '^[[:digit:]]+$'),
  CONSTRAINT CK_Person_lastname CHECK (TRIM(lastname) != '' AND lastname !~ '^[[:digit:]]+$'),
  CONSTRAINT CK_Person_uni_id CHECK (uni_id ~ '^[a-z]{6}$')
);

CREATE INDEX IXFK_Person_degree_code ON Person (degree_code ASC);
CREATE UNIQUE INDEX IXAK_Person_uni_id ON Person (LOWER(uni_id) ASC);

CREATE TABLE Person_Account_Owner (
  account_id BIGINT NOT NULL,
  person_id BIGINT NOT NULL,
  CONSTRAINT PK_Person_Account_Owner_account_id PRIMARY KEY (account_id),
  CONSTRAINT FK_Person_Account_Owner_person_id FOREIGN KEY (person_id) REFERENCES Person (person_id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_Person_Account_Owner_account_id FOREIGN KEY (account_id) REFERENCES Account (account_id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE INDEX IXFK_Person_Account_Owner_person_id ON Person_Account_Owner (person_id ASC);

CREATE TABLE Role (
  role_code SMALLINT NOT NULL,
  en_name d_name,
  ee_name d_name,
  CONSTRAINT PK_Role_person_role_code PRIMARY KEY (role_code),
  CONSTRAINT AK_Role_ee_name UNIQUE (ee_name),
  CONSTRAINT AK_Role_en_name UNIQUE (en_name)
);

COMMENT ON TABLE Role IS '1 -- Student, 2 -- Curator';

CREATE TABLE Faculty (
  faculty_code SMALLINT NOT NULL,
  ee_name d_name,
  en_name d_name,
  CONSTRAINT PK_Faculty_faculty_code PRIMARY KEY (faculty_code),
  CONSTRAINT AK_Faculty_ee_name UNIQUE (ee_name),
  CONSTRAINT AK_Faculty_en_name UNIQUE (en_name)
);

COMMENT ON TABLE Faculty IS '1 -- School of Business and Governance, 2 -- School of Engineering, 3 -- School of Information Technologies, 4 -- School of Science, 5 -- Estonian Maritime Academy';

CREATE TABLE Person_Role (
  person_id BIGINT NOT NULL,
  role_code SMALLINT NOT NULL,
  CONSTRAINT PK_Person_Role_person_id_role_code PRIMARY KEY (person_id, role_code),
  CONSTRAINT FK_Person_Role_person_id FOREIGN KEY (person_id) REFERENCES Person (person_id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_Person_Role_role_code FOREIGN KEY (role_code) REFERENCES Role (role_code) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IXFK_Person_Role_role_code ON Person_Role (role_code ASC);

CREATE TABLE Person_Faculty (
  person_id BIGINT NOT NULL,
  faculty_code SMALLINT NOT NULL,
  CONSTRAINT PK_Person_Faculty_person_id_faculty_code PRIMARY KEY (person_id, faculty_code),
  CONSTRAINT FK_Person_Faculty_person_id FOREIGN KEY (person_id) REFERENCES Person (person_id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_Person_Faculty_faculty_code FOREIGN KEY (faculty_code) REFERENCES Faculty (faculty_code) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IXFK_Person_Faculty_faculty_code ON Person_Faculty (faculty_code ASC);

CREATE TABLE Thesis_State (
  thesis_state_code SMALLINT NOT NULL,
  ee_name d_name,
  en_name d_name,
  CONSTRAINT PK_Thesis_State_thesis_state_code PRIMARY KEY (thesis_state_code),
  CONSTRAINT AK_Thesis_State_en_name UNIQUE (en_name),
  CONSTRAINT AK_Thesis_State_ee_name UNIQUE (ee_name)
);

COMMENT ON TABLE Thesis_State IS '1 -- Active, 2 - Inactive, 3 - Reserved';

CREATE TABLE Thesis (
  thesis_id BIGSERIAL NOT NULL,
  supervisor_name VARCHAR(1000),
  faculty_code SMALLINT NOT NULL,
  thesis_state_code SMALLINT NOT NULL DEFAULT 1,
  degree_code SMALLINT NOT NULL,
  ee_title VARCHAR(128),
  en_title VARCHAR(128),
  ee_description VARCHAR(1000),
  en_description VARCHAR(1000),
  reg_time TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT PK_Thesis_thesis_id PRIMARY KEY (thesis_id),
  CONSTRAINT CK_Thesis_supervisor_name CHECK (supervisor_name ~ '^[[:alpha:]]+$'),
  CONSTRAINT FK_Thesis_faculty_code FOREIGN KEY (faculty_code) REFERENCES Faculty (faculty_code) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_Thesis_thesis_state_code FOREIGN KEY (thesis_state_code) REFERENCES Thesis_State (thesis_state_code) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT FK_Thesis_degree_code FOREIGN KEY (degree_code) REFERENCES Degree (degree_code) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT CK_Thesis_must_contain_one_or_more_titles_and_descriptions CHECK (ee_title IS NOT NULL AND ee_description IS NOT NULL OR en_title IS NOT NULL AND en_description IS NOT NULL),
  CONSTRAINT CK_Thesis_reg_time CHECK (reg_time <= now())
);

CREATE UNIQUE INDEX IXAK_Thesis_en_title ON Thesis (en_title ASC);
CREATE UNIQUE INDEX IXAK_Thesis_ee_title ON Thesis (ee_title ASC);
CREATE INDEX IXFK_Thesis_faculty_code ON Thesis (faculty_code ASC);
CREATE INDEX IXFK_Thesis_State_thesis_state_code ON Thesis_State (thesis_state_code ASC);

CREATE TABLE Thesis_Candidate (
  thesis_id BIGINT NOT NULL,
  candidate_id BIGINT NOT NULL,
  CONSTRAINT PK_Thesis_Candidate_thesis_id_candidate_id PRIMARY KEY (thesis_id, candidate_id),
  CONSTRAINT FK_Thesis_Candidate_thesis_id FOREIGN KEY (thesis_id) REFERENCES Thesis (thesis_id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_Thesis_Candidate_candidate_id FOREIGN KEY (candidate_id) REFERENCES Person (person_id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE INDEX IXFK_Thesis_Candidate_candidate_id ON Thesis_Candidate (candidate_id ASC);

CREATE TABLE Thesis_Tag (
  thesis_id BIGINT NOT NULL,
  tag_name VARCHAR(32) NOT NULL,
  CONSTRAINT PK_Thesis_Tag_thesis_id_tag_name PRIMARY KEY (thesis_id, tag_name),
  CONSTRAINT FK_Thesis_Tag_thesis_id FOREIGN KEY (thesis_id) REFERENCES Thesis (thesis_id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE Thesis_Owner (
  thesis_id BIGINT NOT NULL,
  person_id BIGINT NOT NULL,
  role_code SMALLINT NOT NULL,
  CONSTRAINT PK_Thesis_Owner_thesis_id_person_id PRIMARY KEY (thesis_id, person_id),
  CONSTRAINT FK_Thesis_Owner_thesis_id FOREIGN KEY (thesis_id) REFERENCES Thesis (thesis_id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_Thesis_Owner_person_id FOREIGN KEY (person_id) REFERENCES Person (person_id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_Thesis_Owner_role_code FOREIGN KEY (role_code) REFERENCES Role (role_code) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE INDEX IXFK_Thesis_Owner_person_id ON Thesis_Owner (person_id ASC);
CREATE INDEX IXFK_Thesis_Owner_role_code ON Thesis_Owner (role_code ASC);

CREATE OR REPLACE VIEW curators_with_theses WITH (security_barrier) AS
 SELECT p.person_id,
  p.uni_id,
  p.degree_code,
  p.firstname,
  p.lastname,
  p.person_state_code,
  array_to_json(array_agg(r.*)) AS theses
  FROM person p
    INNER JOIN thesis_owner tho USING (person_id)
    INNER JOIN person_role pr USING (person_id)
    INNER JOIN (
      SELECT t.*, d.en_name AS en_degree, d.ee_name AS ee_degree
      FROM thesis t
        INNER JOIN degree d ON t.degree_code = d.degree_code) r ON r.thesis_id = tho.thesis_id
WHERE (pr.role_code = 2)
GROUP BY p.person_id;
