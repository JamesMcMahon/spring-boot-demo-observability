#!/usr/bin/env bash
set -e

while true; do
    ./seed-data.sh
    sleep 1 # Optional: Add a delay between iterations if needed
done
