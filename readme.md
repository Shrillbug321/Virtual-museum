# PL

# Wirtualne muzeum

**Technologie**<br>
Java + Spring Boot, Lombok, iTextPdf<br/>
Thymeleaf, Bootstrap,
PostgreSQL, H2, Hibernate<br/>

Aplikacja internetowa będąca wirtualnym muzeum obrazów. Dodatkową funkcją jest możliwość umieszczania wycieczek
odbywających się w
rzeczywistych muzeach. <br/>
Poza standardowym CRUD-em obrazów i wycieczek, aplikacja umożliwia wygenerowanie PDF-a z przeglądanego obrazu (a także
wszystkich na raz). <br/>
Witryna zawiera dodatkowo wyszukiwarkę, która umożliwia określenie wielu warunków filtrowania. <br/>
Bazą danych jest PostgreSQL, ale do celów debugowania używana jest H2 „w pamięci”.<br/>

**Dane logowania** login: `admin`, hasło: `admin`

**Uruchomienie**<br>
Uruchomienie z IntelliJ Idea na podstawie konfiguracji z folderu .run wydaje się łatwiejsze.<br>
Pozostanie po tym tylko instalacja bazy i aktualizowanie ścieżek (kroki 5-6 i 12-15).

1. Pobieramy `virtual_museum.war` z https://drive.google.com/file/d/1pVfuNy1RSFGCDQab806B4K4c4NtyTef5/view?usp=sharing
   (plik jest za duży dla GitHuba)
2. Pobieramy SDK Javy
3. Ustawiamy zmienną środowiskową `JAVA_HOME` (jeśli sama się nie ustawiła) <br>
   3.1 Otwieramy okno `Ten komputer` <br>
   3.2 Prawy klik na pustą przestrzeń (tzn. nie ikonę). Z menu wybieramy `Właściwości` <br>
   3.3 Po lewej `Zaawansowane ustawienia systemu` <br>
   3.4 Zakładka `Zaawansowane` a w niej na dole przycisk `Zmienne środowiskowe` <br>
   3.5 Grupa `Zmienne systemowe` -> `Nowa` <br>
   3.6 Nazwa zmiennej: `JAVA_HOME` <br>
   3.7 Wartość zmiennej: lokalizacja instalacji SDK np. `C:\Users\<użytkownik>\.jdks\openjdk-22.0.1` <br>
   3.8 Naciskamy `OK` we wszystkich okienkach. <br>
   3.9 Może być konieczny restart komputera. <br>
4. Pobieramy i instalujemy Tomcata (może być z XAMPP-a).
5. Pobieramy i instalujemy PostgreSQL. <br>
   5.1 Podczas instalacji zaznaczamy pgAdmin, będzie łatwiej stworzyć bazę. <br>
   5.2 Podczas tworzenia użytkownika wpisujemy nazwę użytkownika `postgres` a hasło `superuser`.<br>
   Jeśli będzie inne, trzeba będzie zmienić w aplikacji (punkt 12)
6. Konfiguracja bazy <br>
   6.1 Uruchamiamy `<lokalizacja PostgreSQL>\pgAdmin 4\runtime\pgAdmin4.exe` <br>
   6.2 Po lewej, w `Object Explorer`, rozwijamy: `Servers->PostgreSQL <wersja>->Databases` <br>
   6.3 Prawy klik na `Databases->Create->Database…` <br>
   6.4 W polu Database wpisujemy `virtual_museum` i naciskamy `OK`. <br>
   6.5 Zamykamy pgAdmina
7. Umieszczamy `virtual_museum.war` w `<lokalizacja Tomcata>/webapps`
8. Teraz w `<lokalizacja Tomcata>/bin` uruchamiamy wiersz polecenia
9. Wpisujemy `catalina.bat run`. Uruchomi to serwer Tomcata a razem z nim aplikację.
10. Aplikacja powinna być pod adresem http://localhost:8080/virtual_museum
11. Niestety wymaga jeszcze konfiguracji :(
12. Jeśli zostały ustawione inne dane logowania do bazy niż podane w kroku 5.2: <br>
    12.1 Nawigujemy do `<lokalizacja Tomcata>/webapps/virtual_museum/WEB-INF/classes`<br/>
    (jeśli nie ma folderu `virtual_museum`, uruchom Tomcata jeszcze raz) <br>
    12.2 Edytujemy `application.properties` <br>
    12.3 `spring.datasource.username=` odpowiada za użytkownika (linia 9) <br>
    12.4 `spring.datasource.password=` odpowiada za hasło (linia 10)
13. Jeśli nazwa bazy, url lub port jest inny niż domyślny <br>
    13.1 Lokalizacja i plik jak punkt 12, z tym że linijka 8: <br>
    13.2 `spring.datasource.url=jdbc:postgresql://<url>:<port>/<nazwa_bazy>`
14. Należy zmienić ścieżkę dostępu do plików <br>
    14.1 Nawigujemy do `<lokalizacja Tomcata>/webapps/virtual_museum/WEB-INF/classes` <br/>
    14.2 Edytujemy `config.properties`
15. Zmieniamy 3 pierwsze linijki <br>
    15.1 `files.location=<lokalizacja Tomcata>/webapps/virtual_museum/WEB-INF/classes/static/images` <br>
    15.2 `fonts.location=<lokalizacja Tomcata>/webapps/virtual_museum/WEB-INF/classes/static/fonts` <br>
    15.3 `pdfs.location=`ścieżka może być dowolna, aby istniała na komputerze. <br>
    15.4 Kolejne linie w pliku zmieniamy, jeśli numer portu Tomcata jest inny niż 8080
16. Aplikacja jest skonfigurowana i gotowa do klikania. <br>
    (szkoda tylko, że więcej czasu zajęło uruchamianie, niż klikanie w niej ;) )

Listopad 2021 - styczeń 2022

# EN

# Virtual museum

**Technologies**<br>
Java + Spring Boot, Lombok, iTextPdf<br/>
Thymeleaf, Bootstrap,
PostgreSQL, H2, Hibernate<br/>

Internet application who is paintings virtual museum. Extra function is a possibility write information about trips in
real museums. <br/>
Besides standard paintings and trips CRUDs, application allow generating PDF-a from previewed painting (and from all
paintings in one time). <br/>
Website extra contains searcher, which makes it possible to define many filters conditions. <br/>
Database is PostgreSQL, but to debugging is using H2 "in memory".<br/>

**Login data** login: `admin`, password: `admin`

**Launching**<br>
Launching from IntelliJ Idea on base configuration from .run folder seems easier.<br>
This will leave only base installation and path updates (steps 5-6 and 12-15).

1. Download from `virtual_museum.war`
   from https://drive.google.com/file/d/1pVfuNy1RSFGCDQab806B4K4c4NtyTef5/view?usp=sharing (file is too big for GitHub)
2. Download Java SDK
3. Set environment variable `JAVA_HOME` (if not set automatically) <br>
   3.1 Open window `This PC` <br>
   3.2 Right click on empty space (that's mean. not icon). From menu choose `Properties` <br>
   3.3 Click located on the left side `Advanced system's settings` <br>
   3.4 Tab `Advanced`, next button on bottom `Environment Variables` <br>
   3.5 Group `System variables` -> `New` <br>
   3.6 Variable name: `JAVA_HOME` <br>
   3.7 Variable value: SDK install localization ex. `C:\Users\<user>\.jdks\openjdk-22.0.1` <br>
   3.8 Press `OK` in all windows. <br>
   3.9 You may need to restart a computer. <br>
4. Download and install Tomcat (can be from XAMPP).
5. Download and install PostgreSQL. <br>
   5.1 During installation check pgAdmin, to easier database creating. <br>
   5.2 During user creating enter username `postgres` password `superuser`.<br>
   If other, you must change later in application (step 12)
6. Base configuration <br>
   6.1 Run `<PostgreSQL localization>\pgAdmin 4\runtime\pgAdmin4.exe` <br>
   6.2 In left side, in `Object Explorer`, expand: `Servers->PostgreSQL <version>->Databases` <br>
   6.3 Right click on `Databases->Create->Database…` <br>
   6.4 In Database field enter `virtual_museum` and press `OK`. <br>
   6.5 Close pgAdmin
7. Place `virtual_museum.war` into `<Tomcat localization>/webapps`
8. Now in `<Tomcat localization>/bin` run command line
9. Enter `catalina.bat run`. This run Tomcat server with application.
10. Application should be on http://localhost:8080/virtual_museum
11. Unfortunaly, application need configuration :(
12. If you have set other login data for the database than that given in step 5.2: <br>
    12.1 Navigate to `<Tomcat localization>/webapps/virtual_museum/WEB-INF/classes`<br/>
    (if `virtual_museum` folder doesn't exists, run Tomcat again) <br>
    12.2 Edit `application.properties` <br>
    12.3 `spring.datasource.username=` configure username (line 9) <br>
    12.4 `spring.datasource.password=` configure password (line 10)
13. If database mame, url or port is other than default <br>
    13.1 Localization and file just like step 12, but line 8: <br>
    13.2 `spring.datasource.url=jdbc:postgresql://<url>:<port>/<database_name>`
14. You must change files paths <br>
    14.1 Navigate to `<Tomcat localization/webapps/virtual_museum/WEB-INF/classes` <br/>
    14.2 Edit `config.properties`
15. Change first 3 lines <br>
    15.1 `files.location=<Tomcat localization>/webapps/virtual_museum/WEB-INF/classes/static/images` <br>
    15.2 `fonts.location=<Tomcat localization>/webapps/virtual_museum/WEB-INF/classes/static/fonts` <br>
    15.3 `pdfs.location=`path can be any, but exist on computer. <br>
    15.4 Next lines changed if, port number is not equal 8080
16. Application is configured and ready to click. <br>
    (it's just a pity that it took more time to start up than to click on it ;) ) <br>
    <br>
    November 2021 - January 2022

Lista obrazów<br/>
Paintings list<br/>
![img.png](readme/img.png)

Ta sama lista po angielsku<br/>
This same list in English<br/>
![img_3.png](readme/img_3.png)

Szczegóły obrazu<br/>
Painting details<br/>
![img_1.png](readme/img_1.png)

Edycja obrazu<br/>
Painting editing<br/>
![img.png](readme/img_5.png)

Strona wyszukiwania<br/>
Search page<br/>
![img_2.png](readme/img_2.png)

Wygenerowany PDF<br/>
Generated PDF<br/>
![img_4.png](readme/img_4.png)

Edycja wycieczki <br>
Trip editing <br>
![img_1.png](readme/img_6.png)

Lista wycieczek <br>
Trips list <br>
![img_2.png](readme/img_7.png)

Szczegóły wycieczki <br>
Trip details <br>
![img.png](readme/img_8.png)