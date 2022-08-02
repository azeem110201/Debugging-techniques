# Scenario 8 - Lightning cart

## Prerequisites
- Level - Advanced
- Language - Java 
- JUnit
- Multi-threading 

## Local Setup
- Ensure that the local system has Java 11 JDK installed.
- Clone this repository. 
- Open lightning-cart module in an IDE.
- Write test cases/unit tests to test any use case. 
- To actually see that the code is malfunctioning, write multi-threaded test 
  cases using tempus-fugit on method LighteningCartIntegrationTest#testSmoothCheckout. 
  Refer to https://www.baeldung.com/java-testing-multithreaded for more information.


### Note
- An actual e-shopping application is not coded this way. It is a lot 
more sophisticated. For the purpose of the debugging bootcamp, persistent 
storage, cache, etc and other layers have been omitted. Only the relevant 
code, where the bug can be isolated, has been included.
- Code for purchase via credit card has been omitted. Customer can only 
purchase items if he has enough credit balance.
- Code for bootstrapping the code in a spring boot service (Application class, 
  Controllers, etc) has been omitted as it is not the cause for concern in this 
  debugging scenario.

