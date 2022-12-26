package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    private Connection connection;
    private String tableName;

    public Connection getConnection() {
        return connection;
    }


}
