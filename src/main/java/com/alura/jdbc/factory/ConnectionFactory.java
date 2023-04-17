package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final DataSource _dataSources;
    public ConnectionFactory(){
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("Lapiz1994");

        // Setear la cantidad máxima de conexiones en el pool
        pooledDataSource.setMaxPoolSize(10);

        this._dataSources = pooledDataSource;
    }
    public Connection recuperaConexion() {
        try {
            return this._dataSources.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
