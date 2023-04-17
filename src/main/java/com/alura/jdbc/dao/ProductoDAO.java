package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {
    final private Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }

    public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    while (resultSet.next()) {
                       final Producto filas = new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD"));
                        resultado.add(filas);
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public void guardar(Producto producto) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO PRODUCTO "
                            + "(nombre, descripcion, cantidad)"
                            + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, producto.get_nombre());
                statement.setString(2, producto.get_descripcion());
                statement.setInt(3, producto.get_cantidad());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        producto.set_id(resultSet.getInt(1));

                        System.out.printf("Fue insertado el producto: %s%n", producto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE PRODUCTO SET "
                            + " NOMBRE = ?, "
                            + " DESCRIPCION = ?,"
                            + " CANTIDAD = ?"
                            + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


