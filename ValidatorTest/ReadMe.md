## Steps for setting up the scenario:

add the below environment variable in idea:
    
    mapping=fun_test


## Debugging Scenario 1:
custom validation is not working as expected for POST endpoint /test


## Debugging Scenario 2:
POST /test/fun_test gives 404

    source #project_path#/src/main/resources/environment_variables
    mvn clean install
    java -jar the_generated_jar_file




## reference
how to add env variables in IntelliJ Idea:


    https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html
