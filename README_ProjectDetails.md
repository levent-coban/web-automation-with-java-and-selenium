* ### In tests use Page Object model  

    To reduce code duplication and improve test case maintenance, the PageObjectModel is implemented.

---

* ### Create a html report (Extent report/Surefire report or any other library) with detailed information what happens in test:

    *ExtentReports* Library is used.  
    The **extent-reports** folder located at the project root contains reports.
        
---

* ### One test that uses data provider: 

    Data provider used in the following test: ``TestCase4`` 

    The **data.UserData** class is created and used to provide data.  
    Data provider named as "InputDataProvider":
    ```@DataProvider(name="InputDataProvider")```

---

* ### 2 different types of tests - ‘Smoke tests’ and ‘Regression tests’  

    **Smoke tests:**
    - TestCase1 (PetStoreTests) 
    - TestCase5 (GlobalSqaTests)  

    **Regression tests:**  
    - TestCase2 (GlobalSqaTests)
    - TestCase3 (PetStoreTests) 
    - TestCase4 (GlobalSqaTests)

---

* ### Tests use configuration file:

    - Base URLs of the pages are taken from the **config.properties** file.  

        ```https://petstore.octoperf.com```  
        ```https://www.globalsqa.com```

    - System/Environment values for **ExtentReports** are taken from the **config.properties** file.

---

* ### Create methods that are executed before and after tests and use them to reduce repetitive code,
    ### & Use common classes to reduce repetitive code:

    The "*BaseTest*" class and methods, the "*BasePage*" class and methods, and a few other *utils* classes, such as the Listener class (*ExtentReportsListener.java*) and methods, complete these tasks. Also, to reduce repetitive code, the *PageObjectModel* is implemented.

---


# Maven Commands  

Write in readMe file maven commands + explanation 
These commands don’t need to execute just explain possibilities:


### 1. How can you run one specific test class  

The following commands runs only the "PetStore" test class:  

- mvn test -Dtest=PetStoreTest

- mvn surefire:test -Dtest=PetStoreTests


### 2. How can you run one specific test in class  

The following commands run only *testCase1* in the "PetStoreTest" test class:

- mvn test -Dtest=PetStoreTest#testCase1

- mvn surefire:test -Dtest=PetStoreTest#testCase1


### 3. How can you run all ‘Regression tests'  

The following commands run only regression testCases in the "PetStoreTest" test class:

- mvn test -Dtest=PetStoreTest -Dgroups=regression

OR

- mvn surefire:test -Dtest=PetStoreTest -Dgroups=regression 

OR

The following commands run only regression testCases in the "PetStoreTest" & "GlobalSqaTest" test classes:

- mvn test -DxmlFilePath=testng.xml -Dgroups=regression

    **testng.xml**
    ```  
    <groups>
        <run>
            <include name="regression"></include>
        </run>
    </groups>

    <classes>
        <class name="tests.PetStoreTest" />
        <class name="tests.GlobalSqaTest" />
    </classes>

    ```


### 4. Excluding tests from execution.
#### (describe at least 3 ways (maven commands and different configuration possibilities)):  


#### 4.1

The following command exclude the "PetStoreTest" test class:  

**a.**  mvn test -Dtest=!PetStoreTest


Exclude the both "PetStoreTest" and "GlobalSqaTest" test class:

**b.** mvn test -Dtest=!PetStoreTest,!GlobalSqaTest



#### 4.2

By using the **exclude** tag in testng.xml

testng.xml  

```
<classes>
    <class name = "tests.PetStoreTest" />
    <class name = "tests.GlobalSqaTest">
        <methods>
            <!-- exclude all test methods -->
            <exclude name=".*" />
            <!-- exclude only testCase2 -->
            <!-- <exclude name="testCase2" /> -->
        </methods>
    </class>
</classes>

```


#### 4.3

By excluding the GlobalSqaTest class from test execution in "maven-surefire-plugin"  

**4.3.A.** pom.xml

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>3.0.0-M5</version>
    <configuration>
    <excludes>
        <exclude>**/GlobalSqaTest.java</exclude>
    </excludes>
    </configuration>
</plugin>

```


**4.3.B.** regex  
*maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html#regular-expression-support*


```
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.2.2</version>
        <configuration>
          <includes>
            <include>%regex[.*(Cat|Dog).*Test.*]</include>
          </includes>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
