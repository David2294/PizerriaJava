/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzeriaproject.modelos;

  public class Categoria {
    private Integer codigo;
    private String nombre;

    public Categoria() {
    }

       
    //Get    

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
 
    //Set

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
