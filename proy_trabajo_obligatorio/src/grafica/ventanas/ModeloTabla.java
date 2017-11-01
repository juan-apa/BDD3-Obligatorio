/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafica.ventanas;

import java.util.ArrayList;
import java.util.List;
import logica.valueObjects.VObject;

/**
 *
 * @author Juan Aparicio
 */
public class ModeloTabla<T> extends javax.swing.table.AbstractTableModel{
    private List<T> datos;
    private String[] cabezales;
    
    public ModeloTabla(){
        this.datos = new ArrayList<T>();
        this.cabezales = new String[0];
    }
    
    public ModeloTabla(List<T> datos){
        this.datos = datos;
    }
    
    public ModeloTabla(List<T> datos, String[] cabezales){
        this.datos = datos;
        this.cabezales = cabezales;
    }
    

    public List<T> getDatos() {
        return datos;
    }

    public void setDatos(List<T> datos) {
        this.datos = datos;
    }

    public String[] getCabezales() {
        return cabezales;
    }

    public void setCabezales(String[] cabezales) {
        this.cabezales = cabezales;
    }
    
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return cabezales.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        VObject vo = (VObject) datos.toArray()[i];
        String val = vo.toArray()[i1];
        System.out.println(val);
        return val;
    }
    @Override
    public String getColumnName(int i){
        return cabezales[i];
    }
    
    public void updateData(){
        this.fireTableDataChanged();
    }
    
    public void updateData(List<T> data){
        this.datos = data;
        this.fireTableDataChanged();
    }
}
