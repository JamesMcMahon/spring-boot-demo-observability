#!/usr/bin/env bash
set -e

echo "create 25 random log entries"
curl -X POST http://localhost:8080/logs/random \
             -d "lines=25" \
             -H "Content-Type: application/x-www-form-urlencoded"

echo "create 5 widgets"
seq 1 5 | xargs -n1 -P10 -I{} curl -X POST http://localhost:8080/metrics/widgets

echo "create 5 macguffins"
seq 1 5 | xargs -n1 -P10 -I{} curl -X POST http://localhost:8080/metrics/macguffins

echo "create a single INFO log entry"
curl -X POST http://localhost:8080/logs/info

echo "create 10 (default) random log entries"
curl -X POST http://localhost:8080/logs/random

echo "create 10 more widgets"
seq 1 10 | xargs -n1 -P10 -I{} curl -X POST http://localhost:8080/metrics/widgets

echo "create 7 more macguffins"
seq 1 7 | xargs -n1 -P10 -I{} curl -X POST http://localhost:8080/metrics/macguffins
