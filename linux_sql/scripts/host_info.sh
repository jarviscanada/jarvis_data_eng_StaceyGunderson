#!/bin/bash

# Get arguments from CLI and create variables for later use
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Warn and exit if incorrect number of CLI arguments are given
if [ "$#" -ne 5 ]; then
  echo "Incorrect number of arguments, requires host, port, name, username and password."
  exit 1
fi

# Save variables for mach/s ine statistics MB and hostname
vmstat_mb=$(vmstat --unit M)
lscpu_out=$(lscpu)
hostname=$(hostname -f)

# Get hardware specifications to variables
# xargs used to trim leading and trailing spaces
id=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
hostname=$(echo "$vmstat_mb" | awk '{print $15}'| tail -n1 | xargs)
cpu_number=$(echo "$vmstat_mb" | awk '{print $14}'| tail -n1 | xargs)
cpu_architecture=$(vmstat -d | awk '{print $10}' | tail -n1 | xargs)
cpu_model=$(df -BM | awk '{print $4}' | tail -n1 | head -c-2 | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | head -c-2 | xargs)
total_mem=$(vmstat --unit M | tail -1 | awk '{print $4}')

# Saves timestamp of current specs
timestamp=$(vmstat -t | awk '{print $18 " " $19}' | tail -n1)

# PSQL insert information about server usage into the host_usage table
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, timestamp, total_mem)
 VALUES('$hostname', $cpu_number, $cpu_architecture, $cpu_model, $cpu_mhz, $l2_cache, '$timestamp', $total_mem)"

#Creates environment variable for psql for password
export PGPASSWORD=$psql_password

# Adds
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

# Exit successful if no errors
exit 0