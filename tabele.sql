CREATE TABLE kierowca(
   id_kierowcy int NOT NULL,
   imie text NOT NULL,
   nazwisko text NOT NULL,
   PESEL bigint,
   kat_prawa_jazdy datatype,
   PRIMARY KEY( id_kierowcy )
);


CREATE TABLE historia_przejazdu(
   id_historii int,
   id_kierowcy int,
   id_zastepcy int,
   id_pojazdu int,
   miejsce_docelowe text,
   liczba_km real,
   cena_zużytego_paliwa real,
   data_startu date,
   data_dotarcia date,
   id_przyczepy int,
   PRIMARY KEY( id_historii )
   FOREIGN KEY ( column [, ... ] ) REFERENCES reftable [ ( refcolumn [, ... ] ) ]
);

CREATE TABLE mandat(
   id_mandatu int NOT NULL,
   id_kierowcy int NOT NULL,
   data date,
   oplata real,
   pkt_karne int,
   id_historii int,
   PRIMARY KEY( id_mandatu )
);

CREATE TABLE table_name(
   column1 datatype,
   column2 datatype,
   column3 datatype,
   .....
   columnN datatype,
   PRIMARY KEY( one or more columns )
);

CREATE TABLE table_name(
   column1 datatype,
   column2 datatype,
   column3 datatype,
   .....
   columnN datatype,
   PRIMARY KEY( one or more columns )
);
