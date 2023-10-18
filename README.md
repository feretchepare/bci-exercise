# BCI Exercise project 

Creation of users with H2 database, Spring Security and JWT authentication.

## Download

Copy project path and clone it 

```bash
git clone https://github.com/feretchepare/bci-exercise.git
```

## Installation (Windows OS)

1 - Install [7-zip](https://www.7-zip.org/download.html) on Windows.

2 - Duplicate a copy of 7z.exe to be zip.exe

3 - Close and reopen terminal

4 - Add 7-zip folder (C:\Program Files\7-Zip) to PATH

- On gitbash type: export PATH=$PATH:"C:\Program Files\7-Zip" (temporary)

- On Windows, adding PATH in environment variables (permanent)

5 - Install [SDKMAN](https://sdkman.io/) package manager 

```bash
curl -s "https://get.sdkman.io" | bash
```

6 - Close and reopen terminal

7 - Install Gradle using installed SDKMAN

```bash
sdk install gradle 6.9
```

8 - Add Gradle wrapper
```bash
gradle wrapper
```

9 - Run Gradle project

```bash
./gradlew bootRun
```

## Test

Import this [Postman collection](https://github.com/feretchepare/bci-exercise/blob/main/documentation/BCI%20Exercise.postman_collection.json) in documentation folder to test endpoints

## License

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)