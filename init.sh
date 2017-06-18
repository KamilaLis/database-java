#!/bin/bash

# Read variables
#echo "Database username:"
#read USER
#echo "Database password:"
#read PASS
#echo "Database name:"
#read DATABASE
USER="bd2"
PASS="bd2"
DATABASE="bd2"

# Check if Java is installed
program="java"
condition=$(which $program 2>/dev/null | grep -v "not found" | wc -l)
if [ $condition -eq 0 ] ; then
    echo "$program is not installed"
    sudo apt-get install default-jre
else
    echo "$program is installed"
fi


# Check if Postgresql is installed
program="psql"
condition=$(which $program 2>/dev/null | grep -v "not found" | wc -l)
if [ $condition -eq 0 ] ; then
    echo "$program is not installed"
    sudo apt-get install postgresql postgresql-contrib
    #sudo apt-get install postgresql-9.6
else
    echo "$program is installed"
fi

# Create database
service postgresql start
echo "Creating database in postgresql..."
sudo -u postgres psql --command "CREATE DATABASE $DATABASE;"
sudo -u postgres psql $DATABASE --command "ALTER USER postgres WITH PASSWORD '$PASS';"
sudo -u postgres psql $DATABASE --file init.sql

# Import CSV file into table
echo "Importing data from CSV files..."
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
sudo -u postgres psql $DATABASE --command "COPY marka(opis_marki) 
FROM '$DIR/csvki/marka.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY model(opis_modelu) 
FROM '$DIR/csvki/model.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY typ_pojazdu(opis_typ) 
FROM '$DIR/csvki/typ_pojazdu.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY kategoria_prawa_jazdy(opis_kat) 
FROM '$DIR/csvki/kategoria_prawa_jazdy.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY rodzaj_ladunku(opis_rodzaju) 
FROM '$DIR/csvki/rodzaj_ladunku.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY czesc_samochodowa(opis_czesci) 
FROM '$DIR/csvki/czesc_samochodowa.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY rodzaj_materialu(opis_rodzaju) 
FROM '$DIR/csvki/rodzaj_materialu.csv' DELIMITER ',' CSV HEADER;"
sudo -u postgres psql $DATABASE --command "COPY czynnosc(opis_czynnosci) 
FROM '$DIR/csvki/czynnosc.csv' DELIMITER ',' CSV HEADER;"



#OPCJONALNIE: Create new role
#sudo -u postgres createuser --interactive \
#"$USER"


