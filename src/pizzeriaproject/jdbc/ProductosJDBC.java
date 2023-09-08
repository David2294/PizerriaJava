/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzeriaproject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pizzeriaproject.modelos.Producto;


public class ProductosJDBC {

    //constructor
    public ProductosJDBC() {
        this.productoConexion=new Conexion();
    }
    
    //atributo
    private Conexion productoConexion;
    
    //metodos
     public List<Producto> obtenerDatosProducto() throws SQLException{
        Connection conexión = DriverManager.getConnection(productoConexion.url,
                    productoConexion.usuario, productoConexion.contrasena);
            Statement statement = conexión.createStatement();

            String consulta = "SELECT * FROM productos";
            ResultSet resultSet = statement.executeQuery(consulta);
            List<Producto> listaProductos = new ArrayList<>();
            while (resultSet.next()) {
                Producto producto = new Producto();
                int idProducto = resultSet.getInt("id_producto");
                int  idCategoria = resultSet.getInt("id_categoria");
                String nombreProducto = resultSet.getString("nombre_producto");
                
                producto.setId(idProducto);
                producto.setIdCategoria(idCategoria);
                producto.setNombreProducto(nombreProducto);
                listaProductos.add(producto);
            }

            resultSet.close();
            statement.close();
            conexión.close();
            return listaProductos;
     }
     
     public void crearDatosProducto(Integer idCategoria, String nombreProducto){
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(productoConexion.url,
                    productoConexion.usuario, productoConexion.contrasena);

            // Crear una sentencia SQL para insertar un dato en la tabla
            String insertQuery = "INSERT INTO productos (id_categoria, nombre_producto) VALUES ("+idCategoria+", '"+nombreProducto+"');";
            System.out.println(insertQuery );
 
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
     
     public void actualizarDtosProducto(Integer idProducto, String nombreProducto){
       try (Connection conn = DriverManager.getConnection(productoConexion.url,
                    productoConexion.usuario, productoConexion.contrasena)) {
            // Crear la consulta SQL con parámetros
            String updateQuery = "UPDATE productos SET nombre_producto ='"+nombreProducto+"' WHERE id_producto = "+idProducto+" ;";
            
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
     
     public void eliminarDatosProducto(Integer idProducto){
        try (Connection conn = DriverManager.getConnection(productoConexion.url,
                    productoConexion.usuario, productoConexion.contrasena)) {
            // Crear la consulta SQL con parámetros
            String deleteQuery = "DELETE FROM productos WHERE id_producto = "+idProducto+" ;";
            
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
