CREATE TABLE kierowca(
   id_kierowcy int PRIMARY KEY NOT NULL,
   imie text NOT NULL,
   nazwisko text NOT NULL,
   PESEL bigint NOT NULL,
   id_zastepcy int NOT NULL,
   id_kat_prawa_jazdy int NOT NULL REFERENCES kategoria_prawa_jazdy,
   id_pojazdu int NOT NULL REFERENCES pojazd
);

CREATE TABLE historia_przejazdu(
   id_historii int  PRIMARY KEY NOT NULL,
   id_kierowcy int NOT NULL REFERENCES kierowca,
   id_pojazdu int NOT NULL REFERENCES pojazd,
   miejsce_startu text NOT NULL,
   miejsce_docelowe text NOT NULL,
   liczba_km real NOT NULL,
   srednie_zuzycie_paliwa NOT NULL,
   cena_zuzytego_paliwa real NOT NULL,
   data date NOT NULL,
   przyczepa boolean NOT NULL,
   id_przyczepy int REFERENCES przyczepa,
);

CREATE TABLE mandat(
   id_mandatu PRIMARY KEY int NOT NULL,
   id_kierowcy int NOT NULL REFERENCES kierowca,
   data date NOT NULL,
   oplata real NOT NULL,
   pkt_karne int NOT NULL,
   id_historii int REFERENCES historia_przejazdu
);

CREATE TABLE kategoria_prawa_jazdy(
   id_kat_prawa_jazdy int PRIMARY KEY NOT NULL,
   opis_kat char(10) NOT NULL
);

CREATE TABLE ladunek(
   id_ladunku int  PRIMARY KEY NOT NULL,
   id_rodzaju int NOT NULL REFERENCES rodzaj_ladunku,
   waga real NOT NULL,
   id_historii int  NOT NULL REFERENCES historia_przejazdu
);

CREATE TABLE rodzaj_ladunku(
   id_rodzaju int PRIMARY KEY NOT NULL,
   opis_rodzaju text
);

CREATE TABLE przyczepa(
   id_przyczepy int PRIMARY KEY NOT NULL,
   id_marki NOT NULL REFERENCES marka,
   id_modelu NOT NULL REFERENCES model,
   nr_rejestr char(10) NOT NULL
);

CREATE TABLE pojazd(
   id_pojazdu int PRIMARY KEY NOT NULL,
   id_typ_pojazdu int REFERENCES typ_pojazdu,
   id_marki NOT NULL REFERENCES marka,
   id_modelu NOT NULL REFERENCES model,
   wymagana_kat_p_j char(10) NOT NULL,
   przebieg real NOT NULL,
   rodz_paliwa char(20) NOT NULL,
   nr_rejestr char(10) NOT NULL
);

CREATE TABLE typ_pojazdu(
   id_typ_pojazdu int PRIMARY KEY NOT NULL,
   opis_typ char(50) NOT NULL
);

CREATE TABLE marka(
   id_marka int PRIMARY KEY NOT NULL,
   opis_marki char(50) NOT NULL
);

CREATE TABLE model(
   id_model int PRIMARY KEY NOT NULL,
   opis_modelu char(50) NOT NULL
);

CREATE TABLE serwis(
   id_serwisu int PRIMARY KEY NOT NULL,
   id_pojazdu int NOT NULL REFERENCES pojazd,
   data date NOT NULL,
   id_przedmiotu int NOT NULL REFERENCES czesc_samochodowa,
   id_czynnosciint NOT NULL REFERENCES czynnosc,
   miejsce_serwisu char(50) NOT NULL,
   cena real NOT NULL,
);
CREATE TABLE czesc_samochodowa(
   id_czesci int PRIMARY KEY NOT NULL,
   opis_czesci char(50) NOT NULL
);
CREATE TABLE czynnosc(
   id_czynnosci int PRIMARY KEY NOT NULL,
   opis_czynnosci char(50) NOT NULL
);

CREATE TABLE materialy_eksploatacyjne(
   id_materialu int PRIMARY KEY NOT NULL,
   id_rodzaj_materialu int NOT NULL REFERENCES rodzaj_materialu,
   cena real NOT NULL,
   data_zakupu date NOT NULL,
   litry real NOT NULL
);

CREATE TABLE rodzaj_materialu(
   id_rodzaj_materialu int PRIMARY KEY NOT NULL,
   opis_rodzaju char(50) NOT NULL
);

