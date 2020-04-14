package edu.unicundi.mvc.controladores;

import edu.unicundi.mvc.vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Juan José Rangel
 * @version 1.0.0
 * @since 1.0.0
 */
public class Controlador implements ActionListener {

    private final Ventana ventana;
    DefaultTableModel modelo;

    /**
     * constructor para hacer la ventana visible y crear el modelo de tabla
     */
    Controlador(Ventana ventana) {
        this.ventana = ventana;
        ventana.setVisible(true);
        this.ventana.btnInsertar.addActionListener(this);
        this.modelo = (DefaultTableModel) ventana.jTable1.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ventana.cajaNombre.getText().equals("")
                || ventana.cajaApellido.getText().equals("")
                || ventana.cajaCelular.getText().equals("")
                || ventana.cajaCorreo.getText().equals("")
                || ventana.cajaDireccion.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Diligencie Correctamente los datos", "Error!", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        } else {
            Crear.crear(ventana.cajaNombre.getText(),
                    ventana.cajaApellido.getText(),
                    ventana.cajaCelular.getText(),
                    ventana.cajaCorreo.getText(),
                    ventana.cajaDireccion.getText());
            llenarTabla();
            limpiarCampos();
        }
    }
    /**
     *deja en blanco los campos del formulario.
     */
    private void limpiarCampos() {
        ventana.cajaNombre.setText("");
        ventana.cajaApellido.setText("");
        ventana.cajaCelular.setText("");
        ventana.cajaCorreo.setText("");
        ventana.cajaDireccion.setText("");
    }

    /**
     * borra el modelo de tabla fila por fila y luego la rellena con la consulta
     * de la base de datos
     */
    private void llenarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (String[] p : Crear.obtenerTodos()) {
            modelo.addRow(p);
        }
    }

}
