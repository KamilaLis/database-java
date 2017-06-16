#!/bin/bash

PASS="bd2"
DATABASE="bd2"

# Check if Java is installed
if [ -n `which java` ]; then
   echo "Java already installed."
else
   echo "Installing Java..."
   sudo apt-get install default-jre
fi

# Check if Postgresql is installed
UP=$(service postgresql status)
if [ "$UP" -gt "0" ]; then
  echo "Installing postgresql..."
  sudo apt-get update
  sudo apt-get install postgresql postgresql-contrib
else
  echo "Postgresql already intalled."
fi

# Create database
echo "Creating database in postgresql..."
sudo -u postgres psql --command "CREATE DATABASE $DATABASE;"
sudo -u postgres psql --command "ALTER USER postgres WITH PASSWORD '$PASS';"
sudo -u postgres psql $DATABASE --file tabele.sql


echo "Database created succesfully."
#OPCJONALNIE: Create new role
#sudo -u postgres createuser --interactive \
#"$USER"


