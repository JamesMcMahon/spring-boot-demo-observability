#!/usr/bin/env bash
set -e

echo "Testing /secure endpoint without authentication..."
seq 1 3 | xargs -n1 -P3 -I{} curl -i http://localhost:8080/secure

echo -e "\nTesting /secure endpoint with basic authentication..."
seq 1 2 | xargs -n1 -P2 -I{} curl -i -u observability-user:hardcoded-insecure-password http://localhost:8080/secure

echo -e "\nTesting /secure endpoint with incorrect basic authentication..."
curl -i -u observability-user:incorrect-password http://localhost:8080/secure