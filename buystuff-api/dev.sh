#!/bin/bash

run_mode=$1

if [ "$run_mode" = "start" ]; then
	echo "running spring-boot:start"
	mvn spring-boot:start
else
	echo "running spring-boot:run"
	mvn spring-boot:run
fi