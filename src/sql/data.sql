INSERT INTO Person_state (person_state_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Person_state (person_state_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');

INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (1, 'School of Business and Governance', 'Majandusteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (2, 'School of Engineering', 'Inseneeriteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (3, 'School of Information Technologies', 'Infotehnoloogia teaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (4, 'School of Science', 'Loodusteaduskond');
INSERT INTO Faculty (faculty_code, en_name, ee_name) VALUES (5, 'Estonian Maritime Academy', 'Eesti Mereakadeemia');

INSERT INTO Role (role_code, en_name, ee_name) VALUES (1, 'Student', 'Tudeng');
INSERT INTO Role (role_code, en_name, ee_name) VALUES (2, 'Lecturer', 'Õppejõud');

INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (1, 1, 1);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (2, 1, 2);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (3, 1, 3);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (4, 1, 4);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (5, 1, 5);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (6, 2, 1);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (7, 2, 2);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (8, 2, 3);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (9, 2, 4);
INSERT INTO Role_Faculty (role_faculty_code, role_code, faculty_code) VALUES (10, 2, 5);

INSERT INTO Grade (grade_code, en_name, ee_name) VALUES (1, 'No Grade', 'Puudub');
INSERT INTO Grade (grade_code, en_name, ee_name) VALUES (2, 'Bachelor', 'Bakalaureuse');
INSERT INTO Grade (grade_code, en_name, ee_name) VALUES (3, 'Master', 'Magistri');
INSERT INTO Grade (grade_code, en_name, ee_name) VALUES (4, 'Doctoral', 'Doktori');
INSERT INTO Grade (grade_code, en_name, ee_name) VALUES (5, 'Applied Higher Education', 'Rakenduskõrgharidus');

INSERT INTO Person (grade_code, firstname, lastname, uniid, email, password) VALUES (1, 'Firstname', 'Lastname', 'filast', 'filast@ttu.ee', 'test');
INSERT INTO Person (grade_code, firstname, lastname, uniid, email, password) VALUES (3, 'Oppejoud', 'Lastname', 'oplast', 'oppejoud.lastname@ttu.ee', 'test');

INSERT INTO Role_Faculty_Owner (person_id, role_faculty_code) VALUES (1, 3);
INSERT INTO Role_Faculty_Owner (person_id, role_faculty_code) VALUES (2, 8);

INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');
INSERT INTO Thesis_State (thesis_state_code, en_name, ee_name) VALUES (3, 'Reserved', 'Reserveeritud');

INSERT INTO Thesis (curator_id, faculty_code, ee_title, en_title, ee_description, en_description) VALUES (2, 3, 'Estonian', 'English', 'Java', 'Postgres');

/* INSERT INTO Thesis_Candidate (thesis_id, person_id) VALUES (1, 1) */
