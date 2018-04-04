INSERT INTO Account_Role (account_role_code, role_name) VALUES (1, 'USER');
INSERT INTO Account_Role (account_role_code, role_name) VALUES (2, 'ADMIN');

INSERT INTO Account_State (account_state_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Account_State (account_state_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');

INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (1, 'No Degree', 'Puudub');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (2, 'Bachelor', 'Bakalaureuse');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (3, 'Master', 'Magistri');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (4, 'Doctoral', 'Doktori');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (5, 'Applied', 'Rakendus');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (6, 'Engineering', 'Integreeritud');

INSERT INTO Account (username, password, email) VALUES ('mavein', '$2a$10$NJYSvPT1dZuaAZwjLGFET.1vEEqRj.TD8R.hgDZmxIklY49sXbJpS', 'mavein@ttu.ee');
INSERT INTO Account (username, password, email) VALUES ('avlans', '$2a$10$Ogjqnrtcw8f4FFMg4Uj03O/7POLz1T1vVS221EHgjy9lYXeRB6FaW', 'avo.lans@ttu.ee');

/* Test data */
INSERT INTO Account (username, password, email) VALUES ('eriktest', '$2a$10$cyOS8DkYWTXww30jkU1N.O7uNWn9Whm4QWfOTrZ5as1RMc8eZK/d2', 'eriktest@ttu.ee');
INSERT INTO Account (username, password, email) VALUES ('olegtest', '$2a$10$TUGfpgc8wvP.cFsHXshw7uz7CHV1we3V1vQfyVZ7e8eiU9hRd1nGW', 'olegtest@ttu.ee');
INSERT INTO Account (username, password, email) VALUES ('denistest', '$2a$10$waw3JSygHEc5yuxNs.w5VuDA3B0j/y5ksxXHKO2DJ2RFKsx9VCwmW', 'denistest@ttu.ee');


INSERT INTO Person (degree_code, firstname, lastname, uni_id) VALUES (1, 'Mart', 'Vein', 'mavein');
INSERT INTO Person (degree_code, firstname, lastname, uni_id) VALUES (3, 'Avo', 'Läns', 'avlans');

INSERT INTO Person (degree_code, firstname, lastname, uni_id) VALUES (1, 'Erik', 'Priis', 'erpris');
INSERT INTO Person (degree_code, firstname, lastname, uni_id) VALUES (1, 'Oleg', 'Kartašov', 'olkart');
INSERT INTO Person (degree_code, firstname, lastname, uni_id) VALUES (1, 'Denis', 'Rästas', 'derast');

INSERT INTO Person_Account_Owner (person_id, account_id) VALUES (1, 1);
INSERT INTO Person_Account_Owner (person_id, account_id) VALUES (2, 2);

INSERT INTO Person_Account_Owner (person_id, account_id) VALUES (3, 3);
INSERT INTO Person_Account_Owner (person_id, account_id) VALUES (4, 4);
INSERT INTO Person_Account_Owner (person_id, account_id) VALUES (5, 5);

INSERT INTO Role (role_code, en_name, ee_name) VALUES (1, 'Student', 'Tudeng');
INSERT INTO Role (role_code, en_name, ee_name) VALUES (2, 'Curator', 'Õppejõud');

INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (1, 'School of Business and Governance', 'Majandusteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (2, 'School of Engineering', 'Inseneeriteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (3, 'School of Information Technologies', 'Infotehnoloogia teaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (4, 'School of Science', 'Loodusteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (5, 'Estonian Maritime Academy', 'Eesti Mereakadeemia');

INSERT INTO Person_Role (person_id, role_code) VALUES (1, 1);
INSERT INTO Person_Role (person_id, role_code) VALUES (2, 2);

INSERT INTO Person_Role (person_id, role_code) VALUES (3, 1);
INSERT INTO Person_Role (person_id, role_code) VALUES (4, 1);
INSERT INTO Person_Role (person_id, role_code) VALUES (5, 1);

INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (1, 3);
INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (2, 3);

INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (3, 3);
INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (4, 3);
INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (5, 3);

INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');
INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (3, 'Reserved', 'Reserveeritud');

INSERT INTO Thesis (faculty_code, degree_code, ee_title, en_title, ee_description, en_description)
VALUES (3, 2, 'Lõputööde deklareerimise automatiseerimine', 'Thesis declaration', 'PHP Laravel programmeerimine', 'PHP Laravel programming');
INSERT INTO Thesis (faculty_code, degree_code, ee_title, en_title, ee_description, en_description)
VALUES (3, 3, 'Masinõpe ja tehisintellekt', 'Artificial Intellect', 'Tehisintellekti arendamine', 'AI development');

INSERT INTO Thesis_Candidate (thesis_id, candidate_id) VALUES (1, 1);

INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'PostgreSQL');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'Java Spring Boot');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'PHP Laravel');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'Vuejs');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'Plagiarism checker');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (2, 'C#');

INSERT INTO Thesis_Owner (thesis_id, person_id, role_code) VALUES (1, 2, 2);
INSERT INTO Thesis_Owner (thesis_id, person_id, role_code) VALUES (2, 2, 2);
