# Toro


[![Build Status](https://travis-ci.org/nhibberd/toro.png)](https://travis-ci.org/nhibberd/toro)

### What is toro?

Toro is a Java library to help manage SQL connections and transactions. It provides functionality to easily intereact with databases in a safe and simple manner.


### Features

* Neatly handling SQL connections
* Convenient ([`Option<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Option.java)) return types
* Interfaces for Generic Data Types ([`Interface Get<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Get.java)) and Generic Objects ([`Interface FromDb<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/FromDb.java))
* Update Generic Data Types
* Query Option of Generic Data Types and Generic Objects
* Query Lists of Primitive Data Types and Generic Objects


### Documentation

* [User Docs]()
* [Examples](https://github.com/nhibberd/toro/blob/master/src/main/java/example/Example.java)


### Usage
#### Generic Objects from database

To query your own objects from the database, implement ([`Interface FromDb<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/FromDb.java))

Implement the `from()` method which will be used to call the appropriate types from the columns provided, the syntax follows
the simple process ` resultSet.getType( column )  `

```Java
    public TestObject from(EdgeResultSet resultSet) {
        return new TestObject(resultSet.getInt(1),resultSet.getString(2));
    }
````

#### Primitive Data Types from database

Import the primitive [getters](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Getters.java) from `import static toro.Getters.*;`

This will allow the use of a number of methods called `getType` where type representents a primitive data type.

For example, the following code will return `java.lang.String`

```Java
    database.query(connection, "sql statement", column, getString);
````


