## Word Count API 1.0
 
This REST API provides a list of n number of most occurring words in a sample text in decreasing order, where n is provided as a parameter.


Example: http://localhost:8080/getTopResultsApi?count=4
```
{
   "wordOne": 91,
   "wordTwo": 80,
   "wordThree": 70,
   "wordFour": 60
}
```
The sample text used for this application was generated using http://www.dummytextgenerator.com/#jump and can be found here `/resources/sample.txt`.

### Installation
 1. Compile with mvn install
 2. Run Main.java
 3. Open browser on http://localhost:8080/swagger-ui.html to get the information about the endpoint.
 
Details for basic in-memory login for the 2 default users:

| User          | Password      |
| ------------- |:-------------:|
| admin         | *password*    |
| user          | *password*    |

### Assignment
#### Task-1
- Create a BDD Automation Suite for this endpoint (Preferably Rest Assured Serenity or any BDD framework of your choice).
- Include all the possible test scenarios in the automation suite. 
- If you think any of test scenario which should not be added in automation then please mention the rational behind it. 
- Automation suite must have response status & body validations.
- Setup a CI pipeline to run the tests. (E.g. Setup a Jenkins file with stage to run automation test on local using docker)
#### Task-2
- Create a performance test script (Preferably JMeter or Gatling).
- Load - 1 TPS
- Duration - 5 Min. 
- Setup a CI pipeline to run the tests. (E.g. Setup a Jenkins file with stage to run performance test on local using docker)

### Guidelines
- Add your tasks work to new feature branches and send the zipped Git project back to us.
- Update or Create a README.md file explaining how to execute your tests.
- For any additional comments, please add a section in README file.