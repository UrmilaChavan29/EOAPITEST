# EOAPITEST
https://github.com/UrmilaChavan29/EOAPITEST This is a RESTful API testing Framework using Java, Eclipse IDE, Maven, TestNG and REST Assured to test REQ|RES (https://reqres.in/) REST API.

# Project Natures
# Maven  
Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information. https://maven.apache.org/

# TestNG      
Next Generation Java Testing introduces breakthrough Java testing techniques and TestNG, a powerful open source Java testing platform. TestNG is designed to cover all categories of tests:  unit, functional, end-to-end, integration, etc... https://testng.org/

# Eclipse    
 The Eclipse Foundation provides our global community of individuals and organisations with a mature, scalable, and business-friendly environment for open source software collaboration and innovation. https://www.eclipse.org/
 
# Clone the repository:
###From command prompt/terminal to your machine:
To clone this repository type this command in cmd :
 git clone https://github.com/UrmilaChavan29/EOAPITEST.git

# Clone In your Eclipse:
To clone the repository to your eclipse IDE :
Eclipse=>Windows=>Show view=>Other=>Git=>Git Repositories=>Clone Repository=>Give the path https://github.com/UrmilaChavan29/EOAPITEST.git

# Routes Tested
The following HTTP methods are tested:
•	GET
•	POST

# Integrated Development Environment
Eclipse IDE is used to develop this Framework.

# Run Tests from Eclipse
•	Open file in Eclipse: ..\ RestAssuredAPITestEO\src\test\java
•	Right click => Run As=> TestNG Test
OR
•	Open file in Eclipse: ..\ RestAssuredAPITestEO\pom.xml
•	Right click => Run As=> Maven test

# Run Tests with Command Prompt/Windows PowerShell
•	Open Folder in File Explorer:  ..\RestAssuredAPITestEO (where you have the "pom.xml" file)
•	Open Command Prompt/Windows PowerShell
•	Run "mvn clean test"

# Notes ('mvn' is not recognized as an internal or external command)
•	Download "apache-maven-3.6.3-bin.tar.gz" from "https://maven.apache.org/download.cgi#"
•	Set the Environment Variables

# Test cases are available in excel file here

https://github.com/UrmilaChavan29/EOAPITEST/blob/master/src/main/java/com/eo/util/DataUtil.xlsx

# Test cases execution/run reports
After test case execution extension report will be generated which shows test execution reports as below 

Extent HTML Report Path:
..\ RestAssuredAPITestEO\test-output\ExtentReports.html

Emailable Report Path:
..\RestAssuredAPITestEO\test-output\emailable-report.html

# Report Dashboard Examples:

![DashboardImage](/test-output/Extend%20report-dashboard.PNG "Report dashboard")

Pass & Fail Report :

![Image](/test-output/Extent%20report-%20pass%20and%20fail.png "Psss and Fail Report")

Index HTML report :

![IndexHTML Report](/test-output/Index-HTML%20Report.png "Index HTML Report")


