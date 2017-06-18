COPY marka(opis_marki) 
FROM 'marka.csv' DELIMITER ',' CSV HEADER;
COPY model(opis_modelu) 
FROM 'model.csv' DELIMITER ',' CSV HEADER;
COPY typ_pojazdu(opis_typ) 
FROM 'typ_pojazdu.csv' DELIMITER ',' CSV HEADER;
