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
#### Connector

###### Initializing connectors
```Java
    private static final Connector connector = new Connector("url", "username", "password");
````

###### Using connectors
Two implementations, [Action](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Action.java) and [Function](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Function.java).

Action is used when not returning anything from the connection
```Java
    connector.withConnection(new Action<Connection>(){
        public void apply(Connection connection) {
            //code
        }
    });
````

Function is used when returning `X` from the connection
```Java
    return connector.withConnection(new Function<Connection, X>(){
        public X apply(Connection connection) {
            //code
        }
    });
````
where X is a Data Type

#### Query's

##### Generic Objects from database

To query your own objects from the database, implement ([`Interface FromDb<T>`](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/FromDb.java))

Implement the `from()` method which will be used to call the appropriate types from the columns provided, the syntax follows
the simple process ` resultSet.getType( column )  `

```Java
    public TestObject from(EdgeResultSet resultSet) {
        return new TestObject(resultSet.getInt(1),resultSet.getString(2));
    }
````

##### Primitive Data Types from database

Import the primitive [getters](https://github.com/nhibberd/toro/blob/master/src/main/java/toro/Getters.java) from `import static toro.Getters.*;`

This will allow the use of a number of methods called `getType` where type representents a primitive data type.

For example, the following code will return `java.lang.String`

```Java
    database.query(connection, "sql statement", column, getString);
````

##### Other

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

##### Simple
###### Creating a table

```Java
    database.executeUpdate(connection,"create table test ( version varchar(10), second varchar(255) )");
```

###### Inserting values

```Java
    database.updateObjects(connection, "insert into test values (?, ?)", 1, "foo");
```

###### Updating values

```Java
    database.updateObjects(connection,"update test set version=?", 5);
```

###### Updating values with VarArgs

```Java
    database.updateObjects(connection,"update test set version=? where second=?", 5, "foo");
```

note that the value I am setting comes first in the varargs.


