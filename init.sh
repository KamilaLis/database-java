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


#OPCJONALNIE: Create new role
#sudo -u postgres createuser --interactive \
#"$USER"


