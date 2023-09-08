/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzeriaproject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import pizzeriaproject.modelos.Categoria;

public class CategoriasJDBC {

    //constructor
    public CategoriasJDBC() {
        this.categoriaConexion = new Conexion();
    }

    //atributo
    private Conexion categoriaConexion;

    //metodos
    public List<Categoria> obtenerDatosCategoria() throws SQLException {
        Connection conexión = DriverManager.getConnection(categoriaConexion.url,
                categoriaConexion.usuario, categoriaConexion.contrasena);
        Statement statement = conexión.createStatement();

        String consulta = "SELECT * FROM categorias";
        ResultSet resultSet = statement.executeQuery(consulta);

        List<Categoria> listaCategoria = new ArrayList<>();
        while (resultSet.next()) {
            Categoria categoria = new Categoria();
            int idCategoria = resultSet.getInt("id_categorias");
            String nombreCategoria = resultSet.getString("nombre_categorias");
            // System.out.println("ID: " + idCategoria + ", Nombre: " + nombreCategoria);
            categoria.setCodigo(idCategoria);
            categoria.setNombre(nombreCategoria);

            listaCategoria.add(categoria);

        }

        resultSet.close();
        statement.close();
        conexión.close();
        return listaCategoria;

    }

    public void crearDatosCategoria(String nombreCategoria) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(categoriaConexion.url,
                    categoriaConexion.usuario, categoriaConexion.contrasena);

            // Crear una sentencia SQL para insertar un dato en la tabla
            String insertQuery = "INSERT INTO categorias ( nombre_categorias) VALUES ('" + nombreCategoria + "');";
            System.out.println(insertQuery);
            // Ejecutar la sentencia SQL para insertar el dato
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertQuery);

            System.out.println("Dato insertado correctamente.");

            // Cerrar la conexión
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarDtosCategoria(Integer idCategorias, String nombreCategorias) {
        try (Connection conn = DriverManager.getConnection(categoriaConexion.url,
                categoriaConexion.usuario, categoriaConexion.contrasena)) {
            // Crear la consulta SQL con parámetros
            String updateQuery = "UPDATE categorias SET nombre_categorias ='" + nombreCategorias + "' WHERE id_categorias = " + idCategorias + " ;";

            // Preparar la sentencia SQL
            PreparedStatement statement = conn.prepareStatement(updateQuery);

            // Ejecutar la consulta
            int filasActualizadas = statement.executeUpdate();

            // Verificar si se realizaron actualizaciones
            if (filasActualizadas > 0) {
                System.out.println("El registro se ha actualizado correctamente.");
            } else {
                System.out.println("No se encontró el registro a actualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDatosCategoria(Integer idCategorias) {
        try (Connection conn = DriverManager.getConnection(categoriaConexion.url,
                categoriaConexion.usuario, categoriaConexion.contrasena)) {
            // Crear la consulta SQL con parámetros
            String deleteQuery = "DELETE FROM categorias WHERE id_categorias = " + idCategorias + " ;";

            // Preparar la sentencia SQL
            PreparedStatement statement = conn.prepareStatement(deleteQuery);

            // Ejecutar la consulta
            int filasEliminadas = statement.executeUpdate();

            // Verificar si se realizaron eliminaciones
            if (filasEliminadas > 0) {
                System.out.println("El registro se ha eliminado correctamente.");
            } else {
                System.out.println("No se encontró el registro a eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
