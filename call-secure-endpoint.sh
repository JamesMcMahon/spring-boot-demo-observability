#!/usr/bin/env bash
set -e

echo "Testing /secure endpoint without authentication..."
curl -i http://localhost:8080/secure

echo -e "\nTesting /secure endpoint with basic authentication..."
curl -i -u observability-user:hardcoded-insecure-password http://localhost:8080/secure
