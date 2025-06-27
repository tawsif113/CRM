#!/bin/bash

# Prompt to get the script name
read -p "Enter the migration script name: " script_name

# Prompt to get the author name
read -p "Enter author name: " author_name

# Generate Flyway migration script version: DDMMYYHHMMSS
timestamp=$(date +"%d%m%y%H%M%S")

# Migration directory root
migration_dir="crm/src/main/resources/db/migration"

# Create the migration script file with the formatted name in the designated directory
file_name="${migration_dir}/V${timestamp}__${script_name}.sql"

# Create the file and add a basic SQL template
echo "-- Created on: $(date)" > $file_name
echo "-- Author: $author_name" >> $file_name
echo "-- Add your SQL scripts below..." >> $file_name

# Notify the user
echo "Migration script file created: $file_name"