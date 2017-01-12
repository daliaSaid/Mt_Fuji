# Mt_Fuji

This is the Mt. Fuji Development Challenge. To build the project, perfom a mvn clean package from the root directory, where
the pom.xml is located. To run the web app, perform mvn spring-boot:run.

You can then navigate to localhost:8080/people. The list of people is sortable by clicking the table headers. To add a new person please enter their data in the row that appears after clicking add new person. The added person's first and last name's first letters will be capitalized and the rest of their names will remain lower-case.

I've opted to use a JS library to implement table sorting and CSS is mostly Bootstrap.
