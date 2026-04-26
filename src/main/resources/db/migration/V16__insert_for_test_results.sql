SET client_encoding = 'UTF8';

INSERT INTO judo_moldova.athlete (name, club, birth_year, weight_kg, belt) VALUES
   ('Maria Popescu',    'CS Olimpia Chisinau',  2007, 40.0,  'Centura Albastra'),
   ('Ana Cojocaru',     'SC Judo Balti',        2007, 44.0,  'Centura Albastra'),
   ('Elena Rusu',       'CS Olimpia Chisinau',  2007, 48.0,  'Centura Maro'),
   ('Irina Botnaru',    'SC Judo Tiraspol',     2008, 52.0,  'Centura Albastra'),
   ('Cristina Lungu',   'CS Steaua Cahul',      2007, 57.0,  'Centura Maro'),
   ('Natalia Grosu',    'SC Judo Balti',        2007, 63.0,  'Centura Maro'),
   ('Tatiana Munteanu', 'CS Olimpia Chisinau',  2008, 68.0,  'Centura Albastra'),
   ('Alina Vrabie',     'SC Judo Tiraspol',     2007, 72.0,  'Centura Maro'),
   ('Valentina Ciobanu','CS Steaua Cahul',      2007, 75.0,  'Centura Maro'),
   ('Diana Moraru',     'CS Olimpia Chisinau',  2008, 80.0,  'Centura Albastra'),
   ('Ion Pascari',      'CS Olimpia Chisinau',  2007, 50.0,  'Centura Maro'),
   ('Andrei Lungu',     'SC Judo Balti',        2007, 55.0,  'Centura Albastra'),
   ('Vlad Cebanu',      'CS Steaua Cahul',      2007, 60.0,  'Centura Maro'),
   ('Mihai Rotaru',     'SC Judo Tiraspol',     2008, 66.0,  'Centura Maro'),
   ('Sergiu Apostol',   'CS Olimpia Chisinau',  2007, 73.0,  'Centura Neagra'),
   ('Dumitru Gavrilut', 'SC Judo Balti',        2007, 81.0,  'Centura Maro'),
   ('Alexandru Boicu',  'CS Steaua Cahul',      2008, 90.0,  'Centura Albastra'),
   ('Radu Stratan',     'SC Judo Tiraspol',     2007, 95.0,  'Centura Maro'),
   ('Petru Manole',     'CS Olimpia Chisinau',  2007, 100.0, 'Centura Maro'),
   ('Victor Creciun',   'SC Judo Balti',        2008, 110.0, 'Centura Albastra'),
   ('Tudor Arnaut',     'CS Olimpia Chisinau',  2007, 50.0,  'Centura Maro'),
   ('Bogdan Diaconu',   'SC Judo Balti',        2008, 55.0,  'Centura Albastra'),
   ('Calin Frunza',     'CS Steaua Cahul',      2007, 60.0,  'Centura Albastra'),
   ('Octavian Negru',   'SC Judo Tiraspol',     2007, 66.0,  'Centura Maro');
INSERT INTO judo_moldova.athlete_result (athlete_id, category_result_id, place) VALUES
    (1, 1, 1), (2, 1, 2), (3, 1, 3), (4, 1, 4),
    (5, 2, 1), (6, 2, 2), (7, 2, 3),
    (8, 3, 1), (9, 3, 2), (10, 3, 3),
    (1, 4, 1), (3, 4, 2), (5, 4, 3),
    (2, 5, 1), (4, 5, 2), (6, 5, 3),
    (7, 6, 1), (8, 6, 2), (9, 6, 3),
    (10, 7, 1), (1, 7, 2), (2, 7, 3),
    (3, 8, 1), (4, 8, 2),
    (5, 9, 1), (6, 9, 2), (7, 9, 3),
    (8, 10, 1), (9, 10, 2),
    (11, 11, 1), (12, 11, 2), (21, 11, 3),
    (13, 12, 1), (14, 12, 2), (22, 12, 3),
    (15, 13, 1), (16, 13, 2), (23, 13, 3),
    (17, 14, 1), (18, 14, 2), (24, 14, 3),
    (19, 15, 1), (20, 15, 2), (11, 15, 3),
    (12, 16, 1), (13, 16, 2), (14, 16, 3),
    (15, 17, 1), (16, 17, 2), (21, 17, 3),
    (17, 18, 1), (22, 18, 2),
    (18, 19, 1), (23, 19, 2), (19, 19, 3),
    (20, 20, 1), (24, 20, 2);

INSERT INTO judo_moldova.category_result (category_id, result_id) VALUES
  (3, 3), (4, 3), (5, 3), (6, 3), (7, 3),
  (13, 4), (14, 4), (15, 4), (16, 4), (17, 4)
ON CONFLICT DO NOTHING;

SELECT setval(
   pg_get_serial_sequence('judo_moldova.category_result', 'category_result_id'),
   (SELECT MAX(category_result_id) FROM judo_moldova.category_result));