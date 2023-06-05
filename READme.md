# Setup Projek RESTful API Spring Boot

## Buat Database MySQL
Buat database dengan nama "vehicle"
username database `root` dan password dikosongkan

## Clone Projek
``git clone https://github.com/Flemel1/restful-api-vehicle.git``

## Instal Depedencies
Install library pada projek ``mvn install``

## Import Collection Postman
Import file `Vehicle Spring Boot.postman_collection.json` di aplikasi postman

## Jalankan Projek
Jalankan projek pada code editor seperti visual studio code
URL `http://localhost:8080/api/vehicle-brand`
Setelah menjalankan projek pertama kali jangan lupa untuk komen baris kode `spring.sql.init.mode=always` pada file `application.properties` agar data pada database tidak ditambahkan kembali ketika menjalankan program untuk kedua kalinya

<img src="/screenshots/credential.png" />
File credential `application.properties` 

### Catatan
1. database.sql merupakan database dari projek (Optional jika perlu diimport)
2. `Vehicle Spring Boot.postman_collection` file collection postman
3. User Admin. email: admin@example.com password: 12345678
4. User Non Admin. email: user@example.com password: 12345678
5. Untuk pagination gunakan query parameter `page` misal: `http://localhost:8080/api/pricelist?page=2`