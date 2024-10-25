#!/bin/bash

echo""
echo "--> menghapus build sebelumnya..."
rm -rf dist

mkdir classes
mkdir mods

echo "--> membuat modul todolist-client-gui..."

javac -p "../lib;../todolist-domain/mods" \
	-d classes/ms.todolist.client.gui \
	$(find src/main/java -name "*.java")

client_gui_ver=1.0
jar --create \
	--file mods/ms.todolist.client.gui@$client_gui_ver.jar \
	--module-version 1.0 \
	--main-class ms.todolist.client.gui.Main \
	-C classes/ms.todolist.client.gui .

#copy library json dan todolist-domain
cp ../lib/json* mods

domain_ver=1.0
cp ../todolist-domain/mods/ms.todolist.domain@$domain_ver.jar mods

#buat distribusi
mkdir dist
cp -r mods dist
cp -r resources dist

#buat script untuk menjalankan todolist client gui
( echo "#!/bin/bash" ; echo "" ) > todolist-client-gui.sh

CMD="java -p mods -m ms.todolist.client.gui" 
echo $CMD >> todolist-client-gui.sh
echo $CMD > todolist-client-gui.bat

mv todolist-client-gui.sh dist
mv todolist-client-gui.bat dist

#clean
rm -rf classes
rm -rf mods
