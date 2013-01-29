# Toro


[![Build Status](https://travis-ci.org/nhibberd/toro.png)](https://travis-ci.org/nhibberd/toro)

### What is toro?

Toro is a Java library to help manage SQL connections and transactions. It provides functionality to easily intereact with databases in a safe and simple manner.


### Features

* Neatly handles SQL connections
* Convenient ([`Option<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Option.java)) return types
* Interfaces for Generic Data Types ([`Interface Get<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Get.java)) and Generic Objects ([`Interface FromDb<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/FromDb.java))
* Update Generic Data Types
* Query Option of Generic Data Types and Generic Objects
* Query Lists of Primitive Data Types and Generic Objects


### Documentation

* [User Docs]()
* [Examples](https://github.com/nhibberd/toro/blob/master/src/main/java/example/Example.java)


### Usage
#### Query Generic Objects from database

To query your own objects from the database, implement ([`Interface FromDb<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/FromDb.java))

Implement the `from()` method which will be used to call the appropriate types from the columns provided, the syntax follows
the simple process ` resultSet.getType( column )  `

```Java
    public TestObject from(EdgeResultSet resultSet) {
        return new TestObject(resultSet.getInt(1),resultSet.getString(2));
    }
````

#### Query Primitive Data Types from database

Import the primitive [getters](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Getters.java) from `import static toro.Getters.*;`

This will allow the use of a number of methods called `getType` where type representents a primitive data type.

For example, the following code will return `java.lang.String`

```Java
    database.query(connection, "sql statement", column, getString);
````

#### Query's

Simple query setting an object in the prepared statement

```Java
    database.queryFromObjects(connection,"select * from test where version=?", getString, 1);
```

Simple query setting multiply objects in the prepared statement

```Java
    database.queryFromObjects(connection,"select * from test where version=? and second=?", getString, 1, "one");
```

**NB**: a number of methods in [Db](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Db.java) use VarArgs as can be seen above


#### Updates

Simple update - creating a table

```Java
    database.executeUpdate(connection,"create table test ( version varchar(10), second varchar(255) )");
```

Simple update - inserting values

```Java
    database.updateObjects(connection, "insert into test values (?, ?)", 1, "foo");
```

Simple update - updating values

```Java
    database.updateObjects(connection,"update test set version=?", 5);
```


Update - updating values with VarArgs

```Java
    database.updateObjects(connection,"update test set version=? where second=?", 5, "foo");
```

note that the value I am setting comes first in the varargs.


