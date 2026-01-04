package ec.edu.espoch.productojavafx;

import java.time.LocalDate;

public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private LocalDate fechaIngreso;
    private int stock;

    public Producto(String nombre, String categoria, double precio, LocalDate fechaIngreso, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
    }

    // Getters y Setters
    public String getNombre(){ 
        return nombre; 
    }
    
    public void setNombre(String nombre){ 
        this.nombre = nombre; 
    }
    
    public String getCategoria(){ 
        return categoria; 
    }
    
    public void setCategoria(String categoria){ 
        this.categoria = categoria; 
    }
    
    public double getPrecio(){ 
        return precio; 
    }
    public void setPrecio(double precio){ 
        this.precio = precio; 
    }
    
    public LocalDate getFechaIngreso(){ 
        return fechaIngreso; 
    }
    
    public void setFechaIngreso(LocalDate fechaIngreso){ 
        this.fechaIngreso = fechaIngreso; 
    }
    
    public int getStock(){ 
        return stock; 
    }
    
    public void setStock(int stock){ 
        this.stock = stock; 
    }
}
