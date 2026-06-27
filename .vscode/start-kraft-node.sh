#!/usr/bin/env bash
set -euo pipefail
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 <config-file> <log-dir>" >&2
  exit 1
fi
CONFIG_FILE="$1"
LOG_DIR="$2"
KAFKA_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CLUSTER_ID="zr7ynnIMRs6yuxTh2w5zQA"
cd "$KAFKA_ROOT"
if [ ! -f "$LOG_DIR/meta.properties" ]; then
  echo "Formatting KRaft storage for $CONFIG_FILE"
  "$KAFKA_ROOT/bin/kafka-storage.sh" format --cluster-id "$CLUSTER_ID" --config "$CONFIG_FILE"
fi
exec "$KAFKA_ROOT/bin/kafka-server-start.sh" "$CONFIG_FILE"
