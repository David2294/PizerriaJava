/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pizzeriaproject.vistas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import pizzeriaproject.jdbc.CarritoComprasJDBC;
import pizzeriaproject.jdbc.CategoriasJDBC;
import pizzeriaproject.jdbc.ProductosJDBC;
import pizzeriaproject.modelos.Categoria;
import pizzeriaproject.modelos.CarritoCompras;
import pizzeriaproject.modelos.Producto;

/**
 *
 * @author krist
 */
public class VistaCompras extends javax.swing.JPanel {

    ProductosJDBC conexionJDBC = new ProductosJDBC();
    CarritoComprasJDBC conexionCarritoJDBC = new CarritoComprasJDBC();
    DefaultTableModel tablaProductosModel = new DefaultTableModel();
    DefaultTableModel tablaCarritoComprasModel = new DefaultTableModel();
    CategoriasJDBC conexionCategoriaJDBC = new CategoriasJDBC();
    List<Categoria> listaCategoria = new ArrayList<>();
    List<CarritoCompras> listaProductosPendiente = new ArrayList<>();
    Integer cantidad = 0;
    Double valor = 0.0;
    CarritoCompras productoSeleccionado = new CarritoCompras();

    public VistaCompras() {
        initComponents();
        this.configurarModelo();
        this.obtenerDatosCategoria();
        this.mostrarDatos();
    }

    private void setTablaProductosCarritoCompras() {
        System.out.println("-------");
        System.out.println(this.listaProductosPendiente.get(0).getCodigo().toString());
        System.out.println(this.listaProductosPendiente.get(0).getNombre());
        System.out.println(this.listaProductosPendiente.get(0).getCostoUnd().toString());
        System.out.println(this.listaProductosPendiente.get(0).getCostoTotal().toString());
        System.out.println(this.listaProductosPendiente.get(0).getMetodoPago());
        
        if (!this.listaProductosPendiente.isEmpty()) {
            Object[] datos = new Object[tablaCarritoComprasModel.getColumnCount()];
            // {"idProducto", "nombre", "costo Uni","Total"};
            
            datos[0] = this.listaProductosPendiente.get(0).getCodigo().toString();
            datos[1] = this.listaProductosPendiente.get(0).getNombre();
            datos[2] = this.listaProductosPendiente.get(0).getCostoUnd().toString();
            datos[3] = this.listaProductosPendiente.get(0).getCostoTotal().toString();
            datos[4] = "Efectivo";
            
            tablaCarritoComprasModel.addRow(datos);

            jTable2.setModel(tablaCarritoComprasModel);
            System.out.println("Se agraga a tabla");
        } else {
            System.out.println("NO HAY DATOS");
        }
        System.out.println("Fin");
    }

    private void mostrarDatos() {
        System.out.println("Buscando Categroias");
        try {
            List<Producto> listaProductos = this.conexionJDBC.obtenerDatosProducto();
            if (!listaProductos.isEmpty()) {
                Object[] datos = new Object[tablaProductosModel.getColumnCount()];

                this.limpiarTabla();
                for (Producto productos : listaProductos) {
                    datos[0] = productos.getId();
                    datos[1] = productos.getNombreProducto();
                    datos[2] = productos.getIdCategoria();

                    tablaProductosModel.addRow(datos);
                }

                jTableProductos.setModel(tablaProductosModel);
            } else {
                System.out.println("NO HAY DATOS");
            }

        } catch (SQLException ex) {
            Logger.getLogger(VistaCompras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void obtenerDatosCategoria() {
        System.out.println("Buscando Categroias");
        try {
            this.listaCategoria = this.conexionCategoriaJDBC.obtenerDatosCategoria();
            if (listaCategoria.isEmpty()) {
                System.out.println("NO HAY DATOS");
            } else {
                this.llenarComboBox();
            }

        } catch (SQLException ex) {
            Logger.getLogger(VistaCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void limpiarTabla() {
        for (int i = tablaProductosModel.getRowCount() - 1; i >= 0; i--) {
            tablaProductosModel.removeRow(i);
        }
    }

    private String obtenerIdCategoriaComboBox() {
        // Obtén el ID de la categoría seleccionada
        String idCategoriaSeleccionada
                = jComboBoxCategoria.getSelectedItem().toString();

        String[] partes = idCategoriaSeleccionada.split("\\(|\\)");
        String valor = partes[1];
        return valor;

    }

    private void llenarComboBox() {

        // Agrega las categorías al JComboBox
        for (Categoria categoria : listaCategoria) {
            jComboBoxCategoria.addItem("(" + categoria.getCodigo() + ") " + categoria.getNombre());
        }
    }

    private void configurarModelo() {
        String[] titulosTabla = {"id", "nombre", "Tipo Categoria"};
        tablaProductosModel.setColumnIdentifiers(titulosTabla);
        jTableProductos.setModel(tablaProductosModel);
        
        String[] titulosTablaCarrito = {"idProducto", "nombre", "costo Uni", "Total","MetodoPago"};
        tablaCarritoComprasModel.setColumnIdentifiers(titulosTablaCarrito);
        jTable2.setModel(tablaCarritoComprasModel);

    }

    private void agregarCompra() {

        Double valorTotal = this.cantidad * this.valor;

        this.productoSeleccionado.setCostoUnd(this.valor.intValue());
        this.productoSeleccionado.setCostoTotal(valorTotal.intValue());
        this.listaProductosPendiente = new ArrayList<>();
        this.listaProductosPendiente.add(this.productoSeleccionado);
        this.setTablaProductosCarritoCompras();
        JTextCantidad.setText("");
        JTextValor.setText("");
        this.cantidad = 0;
        this.valor = 0.0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscarProductos = new javax.swing.JButton();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        JTextCantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelAlerta = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JTextValor = new javax.swing.JTextField();

        btnBuscarProductos.setText("Filtrar");
        btnBuscarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductosActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtrar por categoria");

        jLabel2.setText("Comprar productos seleccionados");

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProductos);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("Comprar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Producto");
        jButton1.setActionCommand("Agregar compra");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JTextCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextCantidadActionPerformed(evt);
            }
        });

        jLabel3.setText("Cantidad");

        jLabel4.setText("Agregar Productos");

        jLabelAlerta.setForeground(new java.awt.Color(255, 0, 0));

        jLabel5.setText("Valor $");

        JTextValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProductos))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JTextValor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                    .addComponent(JTextCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabelAlerta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarProductos))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(JTextCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(JTextValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAlerta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductosActionPerformed
        // TODO add your handling code here:
        this.mostrarDatos();
    }//GEN-LAST:event_btnBuscarProductosActionPerformed

    private void jTableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductosMouseClicked

        int seleccionar = jTableProductos.rowAtPoint(evt.getPoint());

        this.productoSeleccionado.setCodigo((Integer) jTableProductos.getValueAt(seleccionar, 0));
        this.productoSeleccionado.setNombre((String) jTableProductos.getValueAt(seleccionar, 1));
        System.out.println("Seleccionado producto " + this.productoSeleccionado.getCodigo() + ":" + this.productoSeleccionado.getNombre());


    }//GEN-LAST:event_jTableProductosMouseClicked

    private void JTextCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextCantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            System.out.println("btn");

            Integer cantidadProducto = Integer.parseInt(JTextCantidad.getText().toString());
            Double valorProducto = Double.parseDouble(JTextValor.getText().toString());
            System.out.println("btn");
            if (cantidadProducto > 0 && valorProducto > 0.0) {
                System.out.println("btnif");
                this.cantidad = cantidadProducto;
                this.valor = valorProducto;
                this.agregarCompra();
                jLabelAlerta.setText("");
            } else {
                System.out.println("btn fin");
            }
        } catch (Exception e) {
            System.out.println("Error en los valores");
            JTextCantidad.setText("");
            JTextValor.setText("");
            jLabelAlerta.setText("Ingrese Valores admitidos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JTextValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextValorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // TODO:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextCantidad;
    private javax.swing.JTextField JTextValor;
    private javax.swing.JButton btnBuscarProductos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelAlerta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableProductos;
    // End of variables declaration//GEN-END:variables
}
