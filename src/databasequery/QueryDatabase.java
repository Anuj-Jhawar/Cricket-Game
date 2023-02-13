package databasequery;

import java.sql.Connection;

public interface QueryDatabase {
    int find(String queryTypeName, Connection connection);
}
