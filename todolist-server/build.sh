#!/bin/bash

echo ""
echo "--> menghapus build sebelumnya..."
rm -rf dist

mkdir classes
mkdir mods

echo "--> membuat modul todolist-server..."

javac -p "../lib;../todolist-domain/mods" \
	-d classes/ms.todolist.server \
	$(find src/main/java -name "*.java")

server_ver=1.0
jar --create \
	--file mods/ms.todolist.server@$server_ver.jar \
	--module-version 1.0 \
	--main-class ms.todolist.server.TodoServer \
	-C classes/ms.todolist.server .

#copy library json dan todolist-domain
cp ../lib/json* mods

domain_ver=1.0
cp ../todolist-domain/mods/ms.todolist.domain@$domain_ver.jar mods

#buat distribusi
mkdir dist
cp -r mods dist
cp -r resources dist

#buat script untuk menjalankan server
( echo "#!/bin/bash" ; echo "" ) > todolist-server.sh

echo "java -p mods -m ms.todolist.server \$1" >> todolist-server.sh

echo "java -p mods -m ms.todolist.server %1" > todolist-server.bat

mv todolist-server.sh dist
mv todolist-server.bat dist

#clean
rm -rf classes
rm -rf mods

