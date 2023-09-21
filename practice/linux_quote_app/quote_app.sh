#!/bin/bash

 ## Get arguments from CLI and create variables for later use
 api_key=$1
 psql_host=$2
 psql_port=$3
 db_name=$4
 psql_user=$5
 psql_password=$6
 symbols=$7

 ## Warn and exit if incorrect number of CLI arguments are given
 if [ "$#" -lt 7 ]; then
  echo "Incorrect number of arguments, requires api key, host, port, name, username, password and symbols."
   exit 1
 fi

 #Creates environment variable for psql for password
 export PGPASSWORD=$psql_password

# Use jq to grab api information


# Assign variables from pulled information
 id=1
 $open=1
 $high=1
 $low=3
 $price=4
 $volume=3

# Insert values to psql database
 insert_stmt="INSERT INTO jrvs_test(id, symbol, open, high, low, price, volume) VALUES ($id,$symbols,$open,$high,$low,$price,$volume)"

 # Adds
 psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

 # Exit successful if no errors
 exit 0