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

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Create database
#service postgresql start
cd ~postgres/
sudo -u postgres createuser --superuser --createdb --no-createrole $DATABASE
sudo -u postgres createdb -O $USER $DATABASE
sudo -u postgres psql -c "alter user $USER with encrypted password '$PASS';"
echo "Creating database in postgresql..."
#sudo -u postgres psql --command "CREATE DATABASE $DATABASE;"
#sudo -u postgres psql $DATABASE --command "ALTER USER postgres WITH PASSWORD '$PASS';"
path="$DIR/init.sql"
chmod a+rwx "$path"
psql $DATABASE -U $USER --file $path
#sudo -u postgres psql $DATABASE --file $path

# Import CSV file into table
echo "Importing data from CSV files..."
chmod a+rwx "$DIR/csvki/marka.csv"
chmod a+rwx "$DIR/csvki/model.csv"
chmod a+rwx "$DIR/csvki/typ_pojazdu.csv"
chmod a+rwx "$DIR/csvki/kategoria_prawa_jazdy.csv"
chmod a+rwx "$DIR/csvki/rodzaj_ladunku.csv"
chmod a+rwx "$DIR/csvki/czesc_samochodowa.csv"
chmod a+rwx "$DIR/csvki/rodzaj_materialu.csv"
chmod a+rwx "$DIR/csvki/pojazd.csv"
chmod a+rwx "$DIR/csvki/czynnosc.csv"
echo "\copy marka(opis_marki) FROM $DIR/csvki/marka.csv DELIMITER ',' CSV;
\copy model(opis_modelu) FROM $DIR/csvki/model.csv DELIMITER ',' CSV;
\copy typ_pojazdu(opis_typ) FROM $DIR/csvki/typ_pojazdu.csv DELIMITER ',' CSV;
\copy kategoria_prawa_jazdy(opis_kat) FROM $DIR/csvki/kategoria_prawa_jazdy.csv DELIMITER ',' CSV;
\copy rodzaj_ladunku(opis_rodzaju) FROM $DIR/csvki/rodzaj_ladunku.csv DELIMITER ',' CSV;
\copy czesc_samochodowa(opis_czesci) FROM $DIR/csvki/czesc_samochodowa.csv DELIMITER ',' CSV;
\copy rodzaj_materialu(opis_rodzaju) FROM $DIR/csvki/rodzaj_materialu.csv DELIMITER ',' CSV;
\copy czynnosc(opis_czynnosci) FROM $DIR/csvki/czynnosc.csv DELIMITER ',' CSV;
\copy pojazd(id_typ_pojazdu,id_marki,id_modelu,id_kat_prawa_jazdy,przebieg,rodz_paliwa,nr_rejestr) FROM $DIR/csvki/pojazd.csv DELIMITER ',' CSV" | psql $DATABASE -U $USER


#OPCJONALNIE: Create new role
#sudo -u postgres createuser --interactive \
#"$USER"


