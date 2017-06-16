#!/bin/bash

PASS="bd2"
DATABASE="bd2"

# Check if Postgresql is installed
UP=$(service postgresql status)
if [ "$UP" -gt "0" ]; then
  echo "Installing postgresql..."
  sudo apt-get update
  sudo apt-get install postgresql postgresql-contrib
else
  echo "Postgresql intalled."
fi

# Create database
echo "Creating database in postgresql..."
sudo -u postgres psql --command "CREATE DATABASE $DATABASE;"
sudo -u postgres psql --command "ALTER USER postgres WITH PASSWORD '$PASS';"
sudo -u postgres psql $DATABASE --file tabele.sql

#OPCJONALNIE: Create new role
#sudo -u postgres createuser --interactive \
#"$USER"


