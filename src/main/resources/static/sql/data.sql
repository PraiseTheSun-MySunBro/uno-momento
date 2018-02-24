INSERT INTO Person_state_type (person_state_type_code, en_name, ee_name) VALUES (1, 'Active', 'Aktiivne');
INSERT INTO Person_state_type (person_state_type_code, en_name, ee_name) VALUES (2, 'Inactive', 'Mitteaktiivne');

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