/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql3;


import com.mysql.cj.util.StringUtils;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





/**
 *
 * @author Ziruxeno
 */
public class CSeries {
     int codigo;
    String nombreSeries;
    String generoSeries;
    String temporadaSeries;
    String episodiosSeries;
    String LanzamientoSeries;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreSeries() {
        return nombreSeries;
    }

    public void setNombreSeries(String nombreSeries) {
        this.nombreSeries = nombreSeries;
    }

    public String getGeneroSeries() {
        return generoSeries;
    }

    public void setGeneroSeries(String generoSeries) {
        this.generoSeries = generoSeries;
    }

    public String getTemporadaSeries() {
        return temporadaSeries;
    }

    public void setTemporadaSeries(String temporadaSeries) {
        this.temporadaSeries = temporadaSeries;
    }

    public String getEpisodiosSeries() {
        return episodiosSeries;
    }

    public void setEpisodiosSeries(String episodiosSeries) {
        this.episodiosSeries = episodiosSeries;
    }

    public String getLanzamientoSeries() {
        return LanzamientoSeries;
    }

    public void setLanzamientoSeries(String LanzaminetoSeries) {
        this.LanzamientoSeries = LanzaminetoSeries;
    }
    
    /**
     *
     * @param paramNombre
     * @param paramGenero
     * @param paramTemporada
     * @param paramEpisodios
     * @param paramLamzamiento
     */
    public void InsertarSeries(JTextField paramNombre, JTextField paramGenero,JTextField paramTemporada, JTextField paramEpisodios, JTextField paramLamzamiento){
        
        setNombreSeries(paramNombre.getText());
        setGeneroSeries(paramGenero.getText());
        setTemporadaSeries(paramTemporada.getText());
        setEpisodiosSeries(paramEpisodios.getText());
        setLanzamientoSeries(paramLamzamiento.getText());
        
        CConexion objetoConexion = new CConexion();
        
        
        String consulta ="INSERT INTO Series (nombre, genero, temporada, episodios, lanzamiento) VALUES (?, ?, ?, ?,?);";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreSeries());
            cs.setString(2, getGeneroSeries());
            cs.setString(3, getTemporadaSeries());
            cs.setString(4, getEpisodiosSeries());
            cs.setString(5, getLanzamientoSeries());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente la serie");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString()+"No Se inserto correctamente la serie, error: ");
        }
            
        
    }
    
    public void MostrarSeries(JTable paramTotalSeries) {
    CConexion objetoConexion = new CConexion();

    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<DefaultTableModel> OrdenarTable = new TableRowSorter<>(modelo);

    if (paramTotalSeries != null) {
        paramTotalSeries.setRowSorter(OrdenarTable);
        paramTotalSeries.setModel(modelo);

        String sql = "SELECT * FROM Series;";

        try (Statement st = objetoConexion.estableceConexion().createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            int numColumnas = rs.getMetaData().getColumnCount();

            for (int i = 1; i <= numColumnas; i++) {
                modelo.addColumn(rs.getMetaData().getColumnName(i));
            }

            while (rs.next()) {
                String[] datos = new String[numColumnas];
                for (int i = 1; i <= numColumnas; i++) {
                    datos[i - 1] = rs.getString(i);
                }
                modelo.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros, error: " + e.toString());
        }
    } else {
        JOptionPane.showMessageDialog(null, "El JTable no ha sido inicializado correctamente.");
    }
}

    public void SeleccionarSeries(JTable paramTablaSeries, JTextField paramCodigo, JTextField paramNombre, JTextField paramGenero, JTextField paramTemporada, JTextField paramEpisodios, JTextField paramLanzamiento) {
    try {
        int fila = paramTablaSeries.getSelectedRow();

        if (fila >= 0) {
            paramCodigo.setText(paramTablaSeries.getValueAt(fila, 0).toString());
            paramNombre.setText(paramTablaSeries.getValueAt(fila, 1).toString());
            paramGenero.setText(paramTablaSeries.getValueAt(fila, 2).toString());
            paramTemporada.setText(paramTablaSeries.getValueAt(fila, 3).toString());
            paramEpisodios.setText(paramTablaSeries.getValueAt(fila, 4).toString());
            paramLanzamiento.setText(paramTablaSeries.getValueAt(fila, 5).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.toString());
    }
}
      public void ModificarSeries(JTextField paramCodigo, JTextField paramNombre, JTextField paramGenero, JTextField paramTemporada, JTextField paramEpisodios, JTextField paramLanzamiento) {
    try {
        System.out.println("Iniciando método ModificarSeries...");

        if (paramCodigo == null || paramNombre == null || paramGenero == null || paramTemporada == null || paramEpisodios == null || paramLanzamiento == null) {
            System.out.println("Uno o más campos son nulos");
            System.out.println("paramCodigo"+ paramCodigo);
            JOptionPane.showMessageDialog(null, "Uno o más campos son nulos");
            return;
        }

        int codigo = 0;
        try {
            codigo = Integer.parseInt(paramCodigo.getText());
        } catch (NumberFormatException e) {
            System.out.println("El código no es un número válido.");
            JOptionPane.showMessageDialog(null, "El código no es un número válido.");
            return;
        }

        String nombre = paramNombre.getText();
        String genero = paramGenero.getText();
        String temporada = paramTemporada.getText();
        String episodios = paramEpisodios.getText();
        String lanzamiento = paramLanzamiento.getText();

        if (nombre.isEmpty() || genero.isEmpty() || temporada.isEmpty() || episodios.isEmpty() || lanzamiento.isEmpty()) {
            System.out.println("Uno o más campos están vacíos. Por favor, completa todos los campos.");
            JOptionPane.showMessageDialog(null, "Uno o más campos están vacíos. Por favor, completa todos los campos.");
            return;
        }

       CSeries serie = new CSeries();
       
serie.setCodigo(codigo);

System.out.println("Codigo obtenido: " + serie.getCodigo());
System.out.flush();

serie.setNombreSeries(nombre);

System.out.println("Nombre optenido: " + serie.getNombreSeries());
System.out.flush();

serie.setGeneroSeries(genero);

System.out.println("Genero optenido: " + serie.getGeneroSeries());
System.out.flush();

serie.setTemporadaSeries(temporada);

System.out.println("Temporada optenido: " + serie.getTemporadaSeries());
System.out.flush();

serie.setEpisodiosSeries(episodios);

System.out.println("Episodios optenido: " + serie.getEpisodiosSeries());
System.out.flush();

serie.setLanzamientoSeries(lanzamiento);

System.out.println(serie.getLanzamientoSeries() + "Lanzamiento optenido: ");
System.out.flush();


        CConexion objetoConexion = new CConexion();

        String consulta = """
                UPDATE Series
                SET nombre = ?,
                    genero = ?,
                    temporada = ?,
                    episodios = ?,
                    lanzamiento = ?
                WHERE id = ?;
                """;

        try (CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta)) {
            cs.setString(1, serie.getNombreSeries());
            System.out.println("NombreSeries"+serie.getNombreSeries());
            cs.setString(2, serie.getGeneroSeries());
            System.out.println("GeneroSeries"+serie.getGeneroSeries());
            cs.setString(3, serie.getTemporadaSeries());
            cs.setString(4, serie.getEpisodiosSeries());
            cs.setString(5, serie.getLanzamientoSeries());
            cs.setInt(6, serie.getCodigo());

            cs.execute();

            System.out.println("Se modificó correctamente la serie");
            JOptionPane.showMessageDialog(null, "Se modificó correctamente la serie");
        } catch (SQLException e) {
            System.out.println("No se modificó correctamente la serie. Error SQL: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se modificó correctamente la serie. Error SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("No se modificó correctamente la serie. Otro error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se modificó correctamente la serie. Otro error: " + e.getMessage());
        } finally {
            //objetoConexion.cerrarConexion();
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}     
    public void EliminarSeries(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta ="DELETE FROM Series where series.id=?;";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se elimino bien la series");
            
            
        } catch (SQLException e){
            
            JOptionPane.showInternalMessageDialog(null,"No se pudo eliminar, error: "+ e.toString());
            
        }
    }
}


