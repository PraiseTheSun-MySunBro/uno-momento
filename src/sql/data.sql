INSERT INTO Person_State (person_state_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Person_State (person_state_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');

INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (1, 'No Degree', 'Puudub');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (2, 'Bachelor', 'Bakalaureuse');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (3, 'Master', 'Magistri');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (4, 'Doctoral', 'Doktori');
INSERT INTO Degree (degree_code, en_name, ee_name) VALUES (5, 'Applied Higher Education', 'Rakenduskõrgharidus');

INSERT INTO Person (degree_code, firstname, lastname, uni_id, email, password) VALUES (1, 'Mart', 'Vein', 'mavein', 'mavein@ttu.ee', 'test');
INSERT INTO Person (degree_code, firstname, lastname, uni_id, email, password) VALUES (3, 'Avo', 'Läns', 'avlans', 'avo.lans@ttu.ee', 'test123');

INSERT INTO Role (role_code, en_name, ee_name) VALUES (1, 'Student', 'Tudeng');
INSERT INTO Role (role_code, en_name, ee_name) VALUES (2, 'Curator', 'Õppejõud');

INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (1, 'School of Business and Governance', 'Majandusteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (2, 'School of Engineering', 'Inseneeriteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (3, 'School of Information Technologies', 'Infotehnoloogia teaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (4, 'School of Science', 'Loodusteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (5, 'Estonian Maritime Academy', 'Eesti Mereakadeemia');

INSERT INTO Person_Role (person_id, role_code) VALUES (1, 1);
INSERT INTO Person_Role (person_id, role_code) VALUES (2, 2);

INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (1, 3);
INSERT INTO Person_Faculty (person_id, faculty_code) VALUES (2, 3);

INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');
INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (3, 'Reserved', 'Reserveeritud');

INSERT INTO Thesis (faculty_code, ee_title, en_title, ee_description, en_description)
  VALUES (3, 'Lõputööde deklareerimise automatiseerimine', 'Thesis declaration', 'PHP Laravel programmeerimine', 'PHP Laravel programming');
INSERT INTO Thesis (faculty_code, ee_title, en_title, ee_description, en_description)
  VALUES (3, 'Masinõpe ja tehisintellekt', 'Artificial Intellect', 'Tehisintellekti arendamine', 'AI development');

INSERT INTO Thesis_Candidate (thesis_id, candidate_id) VALUES (1, 1);

INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'PostgreSQL');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'Java Spring Boot');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'PHP Laravel');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'Vuejs');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (1, 'Plagiarism checker');
INSERT INTO Thesis_Tag (thesis_id, tag_name) VALUES (2, 'C#');

INSERT INTO Thesis_Owner (thesis_id, person_id, role_code) VALUES (1, 2, 2);
INSERT INTO Thesis_Owner (thesis_id, person_id, role_code) VALUES (2, 2, 2);
