# Reference:
https://gist.github.com/doug-ol/0c018e82c095cd3addcb1275999c966f

# Getting Started
* Run maven to build
    - mvn clean install
    - Builds and runs tests
    - Generates jacoco code coverage reports: target/site/jacoco/index.html
        at least 80% coverage provided

# Launch Program
* Assuming source code direectory as current working directory

* Use STDIN and STDOUT (default)
    - cat ~/Downloads/input.json | java -jar ./target/mongodb_interviews-0.0.1-SNAPSHOT.jar

* Read input from file and Write output to file
  java -jar ./target/mongodb_interviews-0.0.1-SNAPSHOT.jar --input=/tmp/input.json --output=/tmp/output.json

