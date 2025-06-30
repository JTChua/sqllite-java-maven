#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

# Load SQL into the database file in bank-app/
sqlite3 ../bank-app/banking-app.db < queryscript.sql

echo "✅ Database loaded into ../bank-app/banking-app.db"
