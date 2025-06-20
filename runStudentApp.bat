@echo off
echo Cleaning old class files...
del /s /q bin\*.class >nul 2>&1

echo Compiling Java source using JDK 8...
javac -cp ".;lib\ojdbc8.jar" -d bin src\StudentApp.java

if errorlevel 1 (
    echo ❌ Compilation failed. Please fix the errors above.
    pause
    exit /b
)

echo ✅ Compilation successful.

echo Running Student Management App...
java -cp ".;lib\ojdbc8.jar;bin" StudentApp

pause
