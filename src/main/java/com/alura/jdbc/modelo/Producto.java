package com.alura.jdbc.modelo;

public class Producto {

    private Integer _id;
    private String _nombre;
    private String _descripcion;
    private  Integer _cantidad;


    public Producto(String nombre, String descripcion, Integer cantidad){
        this._nombre = nombre;
        this._descripcion = descripcion;
        this._cantidad = cantidad;
    }

    public Integer get_id() {
        return this._id;
    }

    public void set_id(int id){
        this._id = id;
    }


    public Producto(int id, String nombre, String descripcion, Integer cantidad){
        this._id = id;
        this._nombre = nombre;
        this._descripcion = descripcion;
        this._cantidad = cantidad;
    }

    public String get_nombre() {
        return _nombre;
    }

      public String get_descripcion() {
        return _descripcion;
    }


    public Integer get_cantidad() {
        return _cantidad;
    }




    @Override
    public String toString(){
        return String.format("{id: %s, nombre: %s, descripcion: %s, cantidad: %d}",
                this._id,
                this._nombre,
                this._descripcion,
                this._cantidad);
    }

}
