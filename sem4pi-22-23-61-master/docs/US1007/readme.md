# US 1007


## 1. Context

This is the beginning of the US 1007

## 2. Requirements

*In this section you should present the functionality that is being developed, how do you understand it, as well as possible correlations to other requirements (i.e., dependencies).*

*Example*

**US1007** As Manager, I want to enroll students in bulk by importing their data using a csv file

This US has no dependencies. It is only required a csv file with data to import


## 3. Analysis
Regras de negócio:
Testes unitários:
## 4. Design

### 4.1. System Sequence Diagram
[US1007-SSD](../US1007/US1007-SSD.puml)
### 4.2. Domain Model
[US1007-DM](../US1007/US1007-DM.puml)
### 4.3. Sequence Diagram
[US1007-SD](../US1007/US1007-SD.puml)
### 4.4. Class Diagram
[US1007-CD](../US1007/US1007-CD.puml)








### 4.4. Tests

**Test 1:** *Verifies that it is not possible to create an instance of the Example class with null values.*

````
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed() {
	Example instance = new Example(null, null);
}
````

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

## 6. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the development this work.*