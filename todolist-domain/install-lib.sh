#!/bin/bash

mvn install:install-file \
	-Dfile=../lib/json-20231013.jar \
	-DgroupId=org \
	-DartifactId=json \
	-Dversion=20231013 \
	-Dpackaging=jar

mvn verify
