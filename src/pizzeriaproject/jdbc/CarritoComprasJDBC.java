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

/**
 *
 * @author admin
 */
public class CarritoComprasJDBC {

    public CarritoComprasJDBC() {
        
        this.carritoConexion=new Conexion();
        
    }
    
    private Conexion carritoConexion;
    
    
    //metodos
    public void obtenerDatosCarrito() throws SQLException{
        Connection conexión = DriverManager.getConnection(carritoConexion.url,
                    carritoConexion.usuario, carritoConexion.contrasena);
            Statement statement = conexión.createStatement();

            String consulta = "SELECT * FROM carrito_compras";
            ResultSet resultSet = statement.executeQuery("SELECT * FROM carrito_compras");

            while (resultSet.next()) {
                int idCarrito = resultSet.getInt("id_carrito");
                int idProducto = resultSet.getInt("id_producto");
                String detalle = resultSet.getString("detalle");
                Double costoUnd = resultSet.getDouble("costo_und");
                Double costoTotal = resultSet.getDouble("costo_total");
                String metodoPago = resultSet.getString("metodo_pago");
                Integer cantidad = resultSet.getInt("cantidad");
                
                System.out.println("ID: " + idCarrito + ", ID:"+idProducto+", Detalle:"+detalle+", Costo unidad:"+costoUnd+", Costo total:"+costoTotal+", pago:"+metodoPago+", Cantidad:"+cantidad+"" );
            }

            resultSet.close();
            statement.close();
            conexión.close();
    }
    
    public void crearDatosCarrito(Integer idProducto, String detalle, Integer cantidadVenta, String metodoPago, Integer costoTotal, Integer costoUnidad){
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(carritoConexion.url,
                    carritoConexion.usuario, carritoConexion.contrasena);

            // Crear una sentencia SQL para insertar un dato en la tabla
            String insertQuery = "INSERT INTO carrito_compras (id_producto, detalle, costo_und, costo_total, metodo_pago, cantidad) VALUES ('"+idProducto+"','"+detalle+"','"+costoUnidad+"','"+costoTotal+"','"+metodoPago+"','"+cantidadVenta+"');";
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
    
    public void actualizarDtosCarrito(Integer idCarrito, String detalleCarrito, Integer cantidadVenta, Integer costoTotal ){
       try (Connection conn = DriverManager.getConnection(carritoConexion.url,
                    carritoConexion.usuario, carritoConexion.contrasena)) {
            // Crear la consulta SQL con parámetros
            String updateQuery = "UPDATE carrito_compras SET detalle='"+detalleCarrito+"', cantidad='"+cantidadVenta+"', costo_total='"+costoTotal+"' WHERE id_carrito="+idCarrito+";";
            
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
    
    public void eliminarDatosCarrito(Integer idCarrito){
        try (Connection conn = DriverManager.getConnection(carritoConexion.url,
                    carritoConexion.usuario, carritoConexion.contrasena)) {
            // Crear la consulta SQL con parámetros
            String deleteQuery = "DELETE FROM carrito_compras WHERE id_carrito="+idCarrito+";";
            
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
    

