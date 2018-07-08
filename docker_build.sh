#!/usr/bin/env bash

echo "create volume..."

docker volume create pg_data

echo "remove if exists database container..."

docker rm semantic_trajectory_db

sleep 3

echo "create database container..."

docker run --name=semantic_trajectory_db -d \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASS=postgres \
    -e POSTGRES_DBNAME=semantic_trajectory \
    -e ALLOW_IP_RANGE=0.0.0.0/0 \
    -p 5432:5432 \
    -v pg_data:/var/lib/postgresql \
    --restart=always kartoza/postgis:9.6-2.4

sleep 7

echo "Container started"
