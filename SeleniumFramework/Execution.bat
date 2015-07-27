@echo        		********* Setting Class Paths *********
cd %~dp0%
set projectPath=%~dp0%
set classpath=%projectPath%\bin;%projectPath%\lib files\*;%projectPath%\log4j Properties;

@echo               ******   TestNG xml is running    ******
java org.testng.TestNG %projectPath%\testng.xml
pause