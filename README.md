# todolist-client-server
Berbeda dengan versi yang <a href="https://github.com/mohamad-salman/todolist-standalone">standalone</a>, aplikasi todolist ini menyimpan datanya di server.

## Compile project
Untuk meng-compile-nya bisa dengan menjalankan script `build.sh` di masing-masing project.

`todolist-server` dan `todolist-client-gui` membutuhkan modul `todolist-domain` karena itu di-compile duluan. 


## Menjalankan server
Setelah di-compile, akan muncul direktori `dist`. Bila ingin menjalankan `todolist-server` pada mesin lain, copy isi dari direktori `dist` tersebut.

Default port `todolist-server` adalah port 8000. Untuk menggunakan port lain misalnya 8080 bisa dengan menambahkannya pada argumen script: `./todolist-server.sh 8080`

## Menjalankan client-gui
Setelah di-compile, akan muncul direktori `dist`. Bila ingin menjalankan `todolist-client-gui` pada mesin lain, copy isi dari direktori `dist` tersebut.

Dan bila `todolist-server` dijalankan pada mesin lain atau bukan port default 8000, maka edit file `resources/config.properties`.

### Membuka project pada IDE
Maven (pom) dibuat agar supaya project bisa dibuka pada IDE.

Data yang dikirim ke server adalah JSON. Dalam mengubah format data tersebut, aplikasi ini menggunakan library JSON. Bila library tersebut tidak ada pada repository local maven maka IDE (netbeans) menunjukkan gagal compile.

Library JSON telah tersedia dalam direktori `lib`. Untuk meng-install-nya, bisa dengan menjalankan script `install-lib.sh` yang terdapat pada project `todolist-domain`.