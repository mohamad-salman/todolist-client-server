#!/bin/bash

echo ""
echo "--> menghapus build sebelumnya..."

rm -rf mods

mkdir mods

echo "--> membuat modul todolist-domain..."

javac -p ../lib \
	-d classes/ms.todolist.domain \
	$(find src/main/java -name "*.java")

jar --create \
	--file mods/ms.todolist.domain@1.0.jar \
	--module-version 1.0 \
	-C classes/ms.todolist.domain .

#clean
rm -rf classes

