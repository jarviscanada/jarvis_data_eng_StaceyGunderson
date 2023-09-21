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

# Save variables for machine statistics MB and hostname
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)


# Get hardware specifications to variables
# xargs used to trim leading and trailing spaces
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}'| tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}'| tail -n1 | xargs)
disk_io=$(vmstat -d | awk '{print $10}' | tail -n1 | xargs)
disk_available=$(df -BM | awk '{print $4}' | tail -n1 | head -c-2 | xargs)

# Saves timestamp of current specs
timestamp=$(vmstat -t | awk '{print $18 " " $19}' | tail -n1)

# Query db to find matching id in host_info table
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')"

# PSQL insert information about server usage into the host_usage table
insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
 VALUES('$timestamp', $host_id, $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available)"

#Creates environment variable for psql for password
export PGPASSWORD=$psql_password

# Adds
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

# Exit successful if no errors
exit 0
