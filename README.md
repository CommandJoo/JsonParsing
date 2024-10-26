# Json Parser
This Project is a simple JsonParser including a CLI written in Java

### Functionality
```java
/*
    The first parameter determines the amount of spaces to indent with
    The second paramter is for formatting the code
    The last parameter tells the parser whether to color the output or not
 */
JsonParser parser = new JsonParser(4, true, true);
/*
    To print the contents of a file use parser.printFile(file)
 */
parser.printFile("input.json");
/*
    If you only want to work with the json use
 */
parser.parseFile("input.json");
```

### Libraries Used
[JoptSimple](https://github.com/jopt-simple/jopt-simple) for the Command Line Interface
