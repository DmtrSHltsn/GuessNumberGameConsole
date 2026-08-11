#!/usr/bin/env bash
set -e

echo "=== Компиляция Java 8 ==="
javac -source 1.8 -target 1.8 -d . src/GuessNumberGameConsole.java

echo "=== Сборка Docker-образа ==="
docker build -t guess-number-console:v1 .

echo "=== Применение K8s Job ==="
kubectl apply -f guess-number-job.yaml

echo "=== Логи ==="
kubectl logs -f job/guess-number-job
