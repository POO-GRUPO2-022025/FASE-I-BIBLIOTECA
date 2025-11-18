package sv.edu.udb.vistas.biblioteca;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import sv.edu.udb.Datos.*;
import sv.edu.udb.clases.*;
import sv.edu.udb.clases.Usuarios;
import sv.edu.udb.Datos.MaterialesDB;
import javax.swing.JOptionPane;
import sv.edu.udb.clases.hijas.*;
import sv.edu.udb.vistas.INICIO.INICIO;


import javax.swing.table.DefaultTableModel;
import sv.edu.udb.Datos.AutorDB;

public class Biblioteca extends javax.swing.JFrame {
    EditorialDB editorialDB = null;
    MaterialesDB materialesDB = null;
    UsuariosDB UsuariosDB = null;
    AutorDB autorDB = null;
    LibroDB libroDB = null;
    RevistaDB revistaDB = null;
    AudioVisualDB audiovisualDB = null;
    OtroDocumentoDB otroDocumentoDB = null;
    PrestamosDB prestamosDB = null;
    MorasDB morasDB = null;
    int prestamosMaximos = 3;
    boolean usuarioPuedePrestar = false;

    private int idEditorialSeleccionado = 0;
    private int idMaterialSeleccionado = 0;
    private int idUsuariosseleccionado = 0;
    private int idAutorSeleccionado = 0;
    private int idPrestamoConMora = 0;
    private int idPrestamoSeleccionado = 0;
    private int idTarifaSeleccionada = 0;
    private int idUsuarioActual = -1;

    public Biblioteca(Usuarios usuarioValidado) {
        idUsuarioActual = usuarioValidado.getIdUsuario();
        
        editorialDB = new EditorialDB();
        UsuariosDB = new UsuariosDB();
        materialesDB = new MaterialesDB();
        autorDB = new AutorDB();
        libroDB = new LibroDB();
        revistaDB = new RevistaDB();
        audiovisualDB = new AudioVisualDB();
        otroDocumentoDB = new OtroDocumentoDB();
        prestamosDB = new PrestamosDB();
        morasDB = new MorasDB();
        
        initComponents();
        ValidarTipoUsuario(usuarioValidado);
        configurarLimites(usuarioValidado);

    
        setLocationRelativeTo(null);
        cbxtipomaterial.setSelectedIndex(-1);
        ocultarCamposEspecificosMaterial();
        actualizarCamposPorTipoMaterial();
        cargarComboBoxEditoriales();
        cargarListaAutores();
        actualizarTablaUsuario();
        jpEditorial.setVisible(false);
    }
    //Metodo para activar o desactivar paneles acorde al tipo de usuario

    private void configurarLimites(Usuarios usuarioValidado){
        if(usuarioValidado.getTipoUsuario().toString().equals("Alumno")){
            prestamosMaximos = 3;
        } else if (usuarioValidado.getTipoUsuario().toString().equals("Profesor")){
            prestamosMaximos = 6;
        } else {
            prestamosMaximos = 3;
        }
        actualizarMisPrestamos();
    }

    public void ValidarTipoUsuario(Usuarios usuarioValidado){
        if(!usuarioValidado.getTipoUsuario().toString().equals("Encargado")) {
            jtbprestamos.removeTabAt(6);
            jtbprestamos.removeTabAt(5);
            jtbprestamos.removeTabAt(4);
            jtbprestamos.removeTabAt(3);
            jtbprestamos.removeTabAt(2);
            jtbprestamos.removeTabAt(1);
            jtbprestamos.removeTabAt(0);
        } else {
            jtbprestamos.removeTabAt(7);
        }
        jLbNombreUsuario.setText("Hola, "+usuarioValidado.getNombre());
    }

    private void actualizarMisPrestamos(){
        // Obtener préstamos activos del usuario actual
        DefaultTableModel modeloPrestamos = prestamosDB.selectPrestamosActivosPorUsuario(idUsuarioActual);

        // Actualizar la tabla de "Mis Préstamos"
        tblPrestamosUsuario.setModel(modeloPrestamos);

        // Verificar si el usuario puede solicitar más préstamos
        int prestamosActivos = modeloPrestamos.getRowCount();

        if (prestamosActivos >= prestamosMaximos) {
            // Usuario alcanzó el límite de préstamos
            usuarioPuedePrestar = false;
            btnFiltrarPrestamoUsuario.setEnabled(false);
            btnSolicitarPrestamoUsuario.setEnabled(false);
            lblErrorPrestamosUsuario.setVisible(true);
            lblErrorPrestamosUsuario.setText("Ha alcanzado el límite de " + prestamosMaximos + " préstamos activos. No puede solicitar más préstamos.");
        } else {
            // Usuario puede solicitar préstamos
            usuarioPuedePrestar = true;
            btnFiltrarPrestamoUsuario.setEnabled(true);
            btnSolicitarPrestamoUsuario.setEnabled(false); // Se habilitará al seleccionar un material
            lblErrorPrestamosUsuario.setVisible(false);
        }
    }

    private void actualizarTablaEditorial() {
        tblEditorial.setModel(editorialDB.selectEditoriales());
    }

    private void actualizarTablaAutor() {
        tblAutor.setModel(autorDB.selectAutores());
    }

    private void limpiarFormularioEditorial() {
        txtNombreEditorial.setText("");
        txtPaisEditorial.setText("");
        btnGuardarEditorial.setText("Guardar");
        btnEliminarEditorial.setEnabled(false);
        idEditorialSeleccionado = 0;
        actualizarTablaEditorial();
        cargarComboBoxEditoriales();
    }

    private void limpiarFormularioAutor() {
        txtNombreAutor.setText("");
        txtApellidosAutor.setText("");
        txtPaisAutor.setText("");
        btnGuardarAutor.setText("Guardar");
        btnEliminarAutor.setEnabled(false);
        idAutorSeleccionado = 0;
        actualizarTablaAutor();
        cargarListaAutores();
    }

    private void limpiarFormularioMaterial() {
        cbxtipomaterial.setSelectedIndex(0);
        jTftitulomaterial.setText("");
        jTfUbimaterial.setText("");
        jTfCanttotal.setText("");
        jTfCantdispmat.setText("");
        txtCantDaniados.setText("");
        txtCantPrestados.setText("");
        clearMaterialSpecificFields();
        btnGuardarmat.setText("Guardar");
        btnEliminarmat.setEnabled(false);
        idMaterialSeleccionado = 0;
        cbxtipomaterial.setSelectedIndex(-1);
        actualizarTablaMateriales();
    }

    private void limpiarFormularioTarifas() {
        txtAnioAplicable.setText("");
        txtTarifa.setText("");
        cbxTipoUsuarioTarifas.setSelectedIndex(-1);
        btnGuardarTarifa.setText("Guardar");
        btnEliminarTarifa.setEnabled(false);
        idTarifaSeleccionada = 0;
        actualizarTablaTarifas();
    }

    private void limpiarFormularioPrestamoUsuario(){
        txtTituloMaterialEnPrestamoUsuario.setText("");
        txtTipoMaterialEnPrestamoUsuario.setText("");
        cbxTipoMaterialPrestamoUsuario.setSelectedIndex(0);
        btnSolicitarPrestamoUsuario.setEnabled(false);
        actualizarMisPrestamos();
        tblMaterialesPrestamoUsuario.setModel(new DefaultTableModel());
    }

    // Carga todas las editoriales en el combo box (solo para Libros)
    private void cargarComboBoxEditoriales() {
        try {
            cbxEditorial.removeAllItems();
            List<Editorial> editoriales = editorialDB.getAllEditoriales();
            for (Editorial editorial : editoriales) {
                cbxEditorial.addItem(editorial.getNombre());
            }
        } catch (Exception e) {
            System.err.println("Error cargando editoriales: " + e.getMessage());
        }
    }

    // Carga todos los autores en la lista de selección múltiple (solo para Libros)
    private void cargarListaAutores() {
        try {
            DefaultListModel<String> model = new DefaultListModel<>();
            List<Autor> autores = autorDB.getAllAutores();
            for (Autor autor : autores) {
                model.addElement(autor.getNombre() + " " + autor.getApellidos());
            }
            lstAutores.setModel(model);
        } catch (Exception e) {
            System.err.println("Error cargando autores: " + e.getMessage());
        }
    }

    // Oculta todos los campos específicos de cada tipo de material
    private void ocultarCamposEspecificosMaterial() {
        // Campos de Libro
        lblISBN.setVisible(false);
        txtISBN.setVisible(false);
        lblEditorial.setVisible(false);
        cbxEditorial.setVisible(false);
        lblAutores.setVisible(false);
        jScrollPaneAutores.setVisible(false);
        
        // Campos de Revista
        lblVolumen.setVisible(false);
        txtVolumen.setVisible(false);
        lblNumero.setVisible(false);
        txtNumero.setVisible(false);
        lblFechaPublicacion.setVisible(false);
        txtFechaPublicacion.setVisible(false);
        
        // Campos de Audiovisual
        lblFormato.setVisible(false);
        txtFormato.setVisible(false);
        lblDuracion.setVisible(false);
        txtDuracion.setVisible(false);
        
        // Campos de Otro
        lblDescripcion.setVisible(false);
        jScrollPaneDescripcion.setVisible(false);
    }

    // Muestra u oculta campos según el tipo de material seleccionado
    private void actualizarCamposPorTipoMaterial() {
        ocultarCamposEspecificosMaterial();
        String tipoSeleccionado = (String) cbxtipomaterial.getSelectedItem();
        
        if ("Libro".equals(tipoSeleccionado)) {
            lblISBN.setVisible(true);
            txtISBN.setVisible(true);
            lblEditorial.setVisible(true);
            cbxEditorial.setVisible(true);
            lblAutores.setVisible(true);
            jScrollPaneAutores.setVisible(true);
        } else if ("Revista".equals(tipoSeleccionado)) {
            lblVolumen.setVisible(true);
            txtVolumen.setVisible(true);
            lblNumero.setVisible(true);
            txtNumero.setVisible(true);
            lblFechaPublicacion.setVisible(true);
            txtFechaPublicacion.setVisible(true);
        } else if ("Audiovisual".equals(tipoSeleccionado)) {
            lblFormato.setVisible(true);
            txtFormato.setVisible(true);
            lblDuracion.setVisible(true);
            txtDuracion.setVisible(true);
        } else if ("Otro".equals(tipoSeleccionado)) {
            lblDescripcion.setVisible(true);
            jScrollPaneDescripcion.setVisible(true);
        }

        // Forzar actualización de la interfaz
        jPmaterial.revalidate();
        jPmaterial.repaint();
    }

    // Limpia todos los campos específicos de cada tipo de material
    private void clearMaterialSpecificFields() {
        // Limpiar campos de Libro
        txtISBN.setText("");
        cbxEditorial.setSelectedIndex(-1);
        lstAutores.clearSelection();

        // Limpiar campos de Revista
        txtVolumen.setText("");
        txtNumero.setText("");
        txtFechaPublicacion.setText("");

        // Limpiar campos de Audiovisual
        txtFormato.setText("");
        txtDuracion.setText("");

        // Limpiar campos de Otro
        txtDescripcion.setText("");
    }

    private void actualizarTablaMateriales() {
        tblmaterial.setModel(materialesDB.selectMateriales());
        cargarComboBoxEditoriales();
        cargarListaAutores();
    }

    private void actualizarTablaTarifas() {
        tblTarifas.setModel(morasDB.selectMoras());
    }

    private void actualizarTablaUsuario() {
        tbluser.setModel(UsuariosDB.selectUsuarios());
    }

     private void limpiarFormularioUsuarios() {
        cbxuser.setSelectedIndex(0);
        jTfiduser.setText("");
        jTfnombreuser.setText("");
        jTfcorreouser.setText("");
        jTfpassworduser.setText("");
        btnguardarUser.setText("Guardar");
        btneliminarUser.setEnabled(false);
        jTfpassworduser.setText("");
        btnCambiarContra.setEnabled(false);
        idUsuariosseleccionado = 0;

        actualizarTablaUsuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jtbprestamos = new javax.swing.JTabbedPane();
        jpEditorial = new javax.swing.JPanel();
        jpEditorialinterno = new javax.swing.JPanel();
        txtNombreEditorial = new javax.swing.JTextField();
        txtPaisEditorial = new javax.swing.JTextField();
        lblNombreEditorial = new javax.swing.JLabel();
        lblPaisEditorial = new javax.swing.JLabel();
        btnGuardarEditorial = new javax.swing.JButton();
        btnEliminarEditorial = new javax.swing.JButton();
        btnLimpiarEditorial = new javax.swing.JButton();
        jcpTablaEditorial = new javax.swing.JScrollPane();
        tblEditorial = new javax.swing.JTable();
        jpAutores = new javax.swing.JPanel();
        jpAutoresInterno = new javax.swing.JPanel();
        txtNombreAutor = new javax.swing.JTextField();
        txtApellidosAutor = new javax.swing.JTextField();
        txtPaisAutor = new javax.swing.JTextField();
        lblNombreAutor = new javax.swing.JLabel();
        lblApellidosAutor = new javax.swing.JLabel();
        lblPaisAutor = new javax.swing.JLabel();
        btnGuardarAutor = new javax.swing.JButton();
        btnEliminarAutor = new javax.swing.JButton();
        btmLimpiarAutor = new javax.swing.JButton();
        jcpTablaAutoresInterno = new javax.swing.JScrollPane();
        tblAutor = new javax.swing.JTable();
        jpMaterial = new javax.swing.JPanel();
        jcpTablaMaterial = new javax.swing.JScrollPane();
        tblmaterial = new javax.swing.JTable();
        jPmaterial = new javax.swing.JPanel();
        btnEliminarmat = new javax.swing.JButton();
        jTftitulomaterial = new javax.swing.JTextField();
        jTfUbimaterial = new javax.swing.JTextField();
        jTfCanttotal = new javax.swing.JTextField();
        jLbtipomaterial = new javax.swing.JLabel();
        jLbtitulomaterial = new javax.swing.JLabel();
        jLbUbicmaterial = new javax.swing.JLabel();
        jLbcanttotal = new javax.swing.JLabel();
        jTfCantdispmat = new javax.swing.JTextField();
        jLbCantDispmat = new javax.swing.JLabel();
        btnGuardarmat = new javax.swing.JButton();
        btnlimpiarmaterial = new javax.swing.JButton();
        cbxtipomaterial = new javax.swing.JComboBox<>();
        lblCantPrestados = new javax.swing.JLabel();
        txtCantPrestados = new javax.swing.JTextField();
        lblCantDaniados = new javax.swing.JLabel();
        txtCantDaniados = new javax.swing.JTextField();
        lblISBN = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        lblEditorial = new javax.swing.JLabel();
        cbxEditorial = new javax.swing.JComboBox<>();
        lblAutores = new javax.swing.JLabel();
        jScrollPaneAutores = new javax.swing.JScrollPane();
        lstAutores = new javax.swing.JList<>();
        lblVolumen = new javax.swing.JLabel();
        txtVolumen = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblFechaPublicacion = new javax.swing.JLabel();
        btnnuevouser = new javax.swing.JButton();
        txtFechaPublicacion = new javax.swing.JTextField();
        lblFormato = new javax.swing.JLabel();
        txtFormato = new javax.swing.JTextField();
        lblDuracion = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        jScrollPaneDescripcion = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPaneluser = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbluser = new javax.swing.JTable();
        jPusuarios = new javax.swing.JPanel();
        btnguardarUser = new javax.swing.JButton();
        btneliminarUser = new javax.swing.JButton();
        jTfiduser = new javax.swing.JTextField();
        jLbiduser = new javax.swing.JLabel();
        jLbnombreuser = new javax.swing.JLabel();
        jLbTipouser = new javax.swing.JLabel();
        jTfnombreuser = new javax.swing.JTextField();
        jTfcorreouser = new javax.swing.JTextField();
        jTfpassworduser = new javax.swing.JTextField();
        jLbcorreouser = new javax.swing.JLabel();
        jlbpassworduser = new javax.swing.JLabel();
        btnlimpiarUser = new javax.swing.JButton();
        cbxuser = new javax.swing.JComboBox<>();
        btnCambiarContra = new javax.swing.JToggleButton();
        jPanelmora = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tblmora = new javax.swing.JTable();
        jPmora = new javax.swing.JPanel();
        btnConsultamora = new javax.swing.JButton();
        jTfidusermora = new javax.swing.JTextField();
        jLbidUsermora = new javax.swing.JLabel();
        jLbnombremora = new javax.swing.JLabel();
        jTfnombremora = new javax.swing.JTextField();
        jTfbusqumora = new javax.swing.JTextField();
        jlbBusqumora = new javax.swing.JLabel();
        jpTarifas = new javax.swing.JPanel();
        jpTarifasInterno = new javax.swing.JPanel();
        txtAnioAplicable = new javax.swing.JTextField();
        txtTarifa = new javax.swing.JTextField();
        lblAnioAplicable = new javax.swing.JLabel();
        lblTarifa = new javax.swing.JLabel();
        btnGuardarTarifa = new javax.swing.JButton();
        btnEliminarTarifa = new javax.swing.JButton();
        btnLimpiarFormularioTarifas = new javax.swing.JButton();
        lnlTipoUsuarioTarifa = new javax.swing.JLabel();
        cbxTipoUsuarioTarifas = new javax.swing.JComboBox<>();
        jcpTablaTarifas = new javax.swing.JScrollPane();
        tblTarifas = new javax.swing.JTable();
        lblPrestamoAdminMora = new javax.swing.JLabel();
        lblFechaDePrestamoMora = new javax.swing.JLabel();
        lblFechaDevolucionMora = new javax.swing.JLabel();
        lblMoraActualEnMora = new javax.swing.JLabel();
        btnAbonarMoraEnMora = new javax.swing.JToggleButton();
        txtFechaPrestamoEnMora = new javax.swing.JTextField();
        txtFechaDevolucionEnMora = new javax.swing.JTextField();
        txtMoraActualEnMora = new javax.swing.JTextField();
        lblDiasDeRetrasoMora = new javax.swing.JLabel();
        txtDiasDeRetrasoMora = new javax.swing.JTextField();
        jPanelprestamo = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPrestamo = new javax.swing.JTable();
        jPprestamos = new javax.swing.JPanel();
        lblFiltrosPrestamo = new javax.swing.JLabel();
        lblDetallesPrestamo = new javax.swing.JLabel();
        lblTipoMaterialPrestamo = new javax.swing.JLabel();
        lblcbxEstadoPrestamo = new javax.swing.JLabel();
        cbxTipoMaterialPrestamo = new javax.swing.JComboBox<>();
        cbxEstadoMaterialPrestamo = new javax.swing.JComboBox<>();
        lblCorreoEnPrestamo = new javax.swing.JLabel();
        lblTituloMaterialEnPrestamo = new javax.swing.JLabel();
        lblNombreUsuarioEnPrestamo = new javax.swing.JLabel();
        lblTipoMaterialEnPrestamo = new javax.swing.JLabel();
        lblUbicacionMaterialEnPrestamo = new javax.swing.JLabel();
        lblAccionesPrestamo = new javax.swing.JLabel();
        lblFechaPrestamo = new javax.swing.JLabel();
        lblFechaDevolucion = new javax.swing.JLabel();
        lblMoraAplicable = new javax.swing.JLabel();
        lblEstadoPrestamo = new javax.swing.JLabel();
        lblDiasDeRetradoPrestamo = new javax.swing.JLabel();
        lblMoraActualPrestamo = new javax.swing.JLabel();
        btnPrestarDevolverPrestamo = new javax.swing.JButton();
        btnDenegarPrestamo = new javax.swing.JButton();
        btnLimpiarFormularioPrestamo = new javax.swing.JButton();
        txtCorreoEnPrestamo = new javax.swing.JTextField();
        txtNombreUsuarioEnPrestamo = new javax.swing.JTextField();
        txtTituloMaterialEnPrestamo = new javax.swing.JTextField();
        txtTipoMaterialEnPrestamo = new javax.swing.JTextField();
        txtUbicacionEnPrestamo = new javax.swing.JTextField();
        txtFechaPrestamo = new javax.swing.JTextField();
        txtFechaDevolucionPrestamo = new javax.swing.JTextField();
        txtMoraAplicablePrestamo = new javax.swing.JTextField();
        txtDiasRetrasoPrestamo = new javax.swing.JTextField();
        txtMoraActualPrestamo = new javax.swing.JTextField();
        txtEstadoPrestamo = new javax.swing.JTextField();
        btnFiltrarPrestamo = new javax.swing.JButton();
        checkConMora = new javax.swing.JCheckBox();
        jPanelPrestamoUsuario = new javax.swing.JPanel();
        jspPrestamoUsuarioTabla = new javax.swing.JScrollPane();
        tblMaterialesPrestamoUsuario = new javax.swing.JTable();
        jpPrestamosUsuario = new javax.swing.JPanel();
        lblFiltrosPrestamoUsuario = new javax.swing.JLabel();
        lblDetallesPrestamoUsuario = new javax.swing.JLabel();
        lblTipoMaterialPrestamoUsuario = new javax.swing.JLabel();
        lblTituloMaterialPrestamoUsuarioFiltro = new javax.swing.JLabel();
        cbxTipoMaterialPrestamoUsuario = new javax.swing.JComboBox<>();
        lblTituloMaterialEnPrestamoUsuario = new javax.swing.JLabel();
        lblTipoMaterialEnPrestamoUsuario = new javax.swing.JLabel();
        btnSolicitarPrestamoUsuario = new javax.swing.JButton();
        btnLimpiarFormularioPrestamoUsuario = new javax.swing.JButton();
        txtTituloMaterialEnPrestamoUsuario = new javax.swing.JTextField();
        txtTipoMaterialEnPrestamoUsuario = new javax.swing.JTextField();
        btnFiltrarPrestamoUsuario = new javax.swing.JButton();
        txtTituloFiltroPrestamoUsuario = new javax.swing.JTextField();
        lblMisPrestamosUsuario = new javax.swing.JLabel();
        jspTablaPrestamosUsuario = new javax.swing.JScrollPane();
        tblPrestamosUsuario = new javax.swing.JTable();
        lblErrorPrestamosUsuario = new javax.swing.JLabel();
        jTfBibliotecaamigosDonBosco = new javax.swing.JTextField();
        jLbNombreUsuario = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Administracion = new javax.swing.JMenuBar();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jTextField1.setText("jTextField1");

        jtbprestamos.setBackground(new java.awt.Color(204, 204, 204));
        jtbprestamos.setForeground(new java.awt.Color(51, 0, 51));
        jtbprestamos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jtbprestamos.setName(""); // NOI18N

        jpEditorialinterno.setBackground(new java.awt.Color(0, 102, 204));

        txtPaisEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisEditorialActionPerformed(evt);
            }
        });

        lblNombreEditorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombreEditorial.setText("Nombre:");

        lblPaisEditorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPaisEditorial.setText("Pais");

        btnGuardarEditorial.setText("Guardar");
        btnGuardarEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEditorialActionPerformed(evt);
            }
        });

        btnEliminarEditorial.setText("Eliminar");
        btnEliminarEditorial.setEnabled(false);
        btnEliminarEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEditorialActionPerformed(evt);
            }
        });

        btnLimpiarEditorial.setText("Limpiar");
        btnLimpiarEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarEditorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpEditorialinternoLayout = new javax.swing.GroupLayout(jpEditorialinterno);
        jpEditorialinterno.setLayout(jpEditorialinternoLayout);
        jpEditorialinternoLayout.setHorizontalGroup(
            jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEditorialinternoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPaisEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarEditorial, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpEditorialinternoLayout.createSequentialGroup()
                        .addComponent(btnEliminarEditorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarEditorial))
                    .addComponent(txtNombreEditorial, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaisEditorial, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jpEditorialinternoLayout.setVerticalGroup(
            jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEditorialinternoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaisEditorial)
                    .addComponent(txtPaisEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEditorial)
                    .addComponent(btnEliminarEditorial)
                    .addComponent(btnLimpiarEditorial))
                .addContainerGap(323, Short.MAX_VALUE))
        );

        tblEditorial.setModel(editorialDB.selectEditoriales()
        );
        tblEditorial.setEnabled(false);
        tblEditorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEditorialMouseClicked(evt);
            }
        });
        jcpTablaEditorial.setViewportView(tblEditorial);

        javax.swing.GroupLayout jpEditorialLayout = new javax.swing.GroupLayout(jpEditorial);
        jpEditorial.setLayout(jpEditorialLayout);
        jpEditorialLayout.setHorizontalGroup(
            jpEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEditorialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpEditorialinterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcpTablaEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(544, Short.MAX_VALUE))
        );
        jpEditorialLayout.setVerticalGroup(
            jpEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEditorialLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpEditorialinterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcpTablaEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );

        jtbprestamos.addTab("Editoriales", jpEditorial);

        jpAutoresInterno.setBackground(new java.awt.Color(0, 102, 204));

        txtPaisAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisAutorActionPerformed(evt);
            }
        });

        lblNombreAutor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombreAutor.setText("Nombre:");

        lblApellidosAutor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblApellidosAutor.setText("Apellidos:");

        lblPaisAutor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPaisAutor.setText("Pais");

        btnGuardarAutor.setText("Guardar");
        btnGuardarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAutorActionPerformed(evt);
            }
        });

        btnEliminarAutor.setText("Eliminar");
        btnEliminarAutor.setEnabled(false);
        btnEliminarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAutorActionPerformed(evt);
            }
        });

        btmLimpiarAutor.setText("Limpiar");
        btmLimpiarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmLimpiarAutorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAutoresInternoLayout = new javax.swing.GroupLayout(jpAutoresInterno);
        jpAutoresInterno.setLayout(jpAutoresInternoLayout);
        jpAutoresInternoLayout.setHorizontalGroup(
            jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAutoresInternoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpAutoresInternoLayout.createSequentialGroup()
                        .addComponent(lblPaisAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPaisAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpAutoresInternoLayout.createSequentialGroup()
                            .addComponent(lblNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpAutoresInternoLayout.createSequentialGroup()
                            .addComponent(btnGuardarAutor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminarAutor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btmLimpiarAutor)))
                    .addGroup(jpAutoresInternoLayout.createSequentialGroup()
                        .addComponent(lblApellidosAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtApellidosAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jpAutoresInternoLayout.setVerticalGroup(
            jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAutoresInternoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidosAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidosAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaisAutor)
                    .addComponent(txtPaisAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jpAutoresInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btmLimpiarAutor)
                    .addComponent(btnEliminarAutor)
                    .addComponent(btnGuardarAutor))
                .addContainerGap(282, Short.MAX_VALUE))
        );

        tblAutor.setModel(autorDB.selectAutores()
        );
        tblAutor.setEnabled(false);
        tblAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAutorMouseClicked(evt);
            }
        });
        jcpTablaAutoresInterno.setViewportView(tblAutor);

        javax.swing.GroupLayout jpAutoresLayout = new javax.swing.GroupLayout(jpAutores);
        jpAutores.setLayout(jpAutoresLayout);
        jpAutoresLayout.setHorizontalGroup(
            jpAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAutoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpAutoresInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcpTablaAutoresInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(543, Short.MAX_VALUE))
        );
        jpAutoresLayout.setVerticalGroup(
            jpAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAutoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpAutoresInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcpTablaAutoresInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );

        jtbprestamos.addTab("Autores", jpAutores);

        tblmaterial.setModel(materialesDB.selectMateriales()
        );
        tblmaterial.setEnabled(false);
        tblmaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmaterialMouseClicked(evt);
            }
        });
        jcpTablaMaterial.setViewportView(tblmaterial);

        jPmaterial.setBackground(new java.awt.Color(0, 102, 204));

        btnEliminarmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarmat.setText("Eliminar");
        btnEliminarmat.setEnabled(false);
        btnEliminarmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarmatActionPerformed(evt);
            }
        });

        jTftitulomaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTftitulomaterialActionPerformed(evt);
            }
        });

        jLbtipomaterial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbtipomaterial.setText("Tipo de Material");

        jLbtitulomaterial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbtitulomaterial.setText("Titulo");

        jLbUbicmaterial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbUbicmaterial.setText("Ubicacion");

        jLbcanttotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbcanttotal.setText("Cantidad Total");

        jTfCantdispmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfCantdispmatActionPerformed(evt);
            }
        });

        jLbCantDispmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbCantDispmat.setText("Cantidad Disponible");

        btnGuardarmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarmat.setText("Guardar");
        btnGuardarmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarmatActionPerformed(evt);
            }
        });

        btnlimpiarmaterial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlimpiarmaterial.setText("Limpiar");
        btnlimpiarmaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarmaterialActionPerformed(evt);
            }
        });

        cbxtipomaterial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxtipomaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libro", "Revista", "Audiovisual", "Otro" }));
        cbxtipomaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxtipomaterialActionPerformed(evt);
            }
        });

        lblCantPrestados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCantPrestados.setText("Cantidad Prestada");

        lblCantDaniados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCantDaniados.setText("Cantidad Dañados");

        lblISBN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblISBN.setText("ISBN");

        lblEditorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEditorial.setText("Editorial");

        cbxEditorial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblAutores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAutores.setText("Autores");

        jScrollPaneAutores.setViewportView(lstAutores);

        lblVolumen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVolumen.setText("Volumen");

        lblNumero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNumero.setText("Número");

        lblFechaPublicacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaPublicacion.setText("Fecha Publicación");

        lblFormato.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFormato.setText("Formato");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDuracion.setText("Duración (min)");

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDescripcion.setText("Descripción");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        jScrollPaneDescripcion.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPmaterialLayout = new javax.swing.GroupLayout(jPmaterial);
        jPmaterial.setLayout(jPmaterialLayout);
        jPmaterialLayout.setHorizontalGroup(
            jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterialLayout.createSequentialGroup()
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPmaterialLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarmat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarmat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnlimpiarmaterial))
                    .addGroup(jPmaterialLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblCantDaniados, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCantDaniados, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblCantPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCantPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(jLbcanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTfCanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(jLbCantDispmat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTfCantdispmat, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(jLbUbicmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTfUbimaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(jLbtitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTftitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(jLbtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPaneAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblFechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPaneDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(45, 45, 45))
        );
        jPmaterialLayout.setVerticalGroup(
            jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmaterialLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbtitulomaterial)
                    .addComponent(jTftitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbUbicmaterial)
                    .addComponent(jTfUbimaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbcanttotal)
                    .addComponent(jTfCanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbCantDispmat)
                    .addComponent(jTfCantdispmat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantPrestados)
                    .addComponent(txtCantPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantDaniados)
                    .addComponent(txtCantDaniados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblISBN)
                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEditorial)
                    .addComponent(cbxEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAutores)
                    .addComponent(jScrollPaneAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVolumen)
                    .addComponent(txtVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaPublicacion)
                    .addComponent(txtFechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFormato)
                    .addComponent(txtFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracion)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion)
                    .addComponent(jScrollPaneDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlimpiarmaterial)
                    .addComponent(btnEliminarmat)
                    .addComponent(btnGuardarmat))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jpMaterialLayout = new javax.swing.GroupLayout(jpMaterial);
        jpMaterial.setLayout(jpMaterialLayout);
        jpMaterialLayout.setHorizontalGroup(
            jpMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMaterialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcpTablaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jpMaterialLayout.setVerticalGroup(
            jpMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMaterialLayout.createSequentialGroup()
                .addGroup(jpMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPmaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcpTablaMaterial))
                .addGap(21, 21, 21))
        );

        jtbprestamos.addTab("Material", jpMaterial);

        tbluser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Tipo de Uusuario", "Correo"
            }
        ));
        tbluser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbluserMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbluser);

        jPusuarios.setBackground(new java.awt.Color(0, 102, 204));

        btnguardarUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnguardarUser.setText("Guardar");
        btnguardarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarUserActionPerformed(evt);
            }
        });

        btneliminarUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btneliminarUser.setText("Eliminar");
        btneliminarUser.setEnabled(false);
        btneliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarUserActionPerformed(evt);
            }
        });

        jTfiduser.setEditable(false);
        jTfiduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfiduserActionPerformed(evt);
            }
        });

        jLbiduser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbiduser.setText("ID Usuario");

        jLbnombreuser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbnombreuser.setText("Nombre ");

        jLbTipouser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbTipouser.setText("Tipo de usuario");

        jLbcorreouser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbcorreouser.setText("Correo");

        jlbpassworduser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbpassworduser.setText("Password");

        btnlimpiarUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlimpiarUser.setText("Limpiar ");
        btnlimpiarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarUserActionPerformed(evt);
            }
        });

        cbxuser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alumno", "Profesor", "Encargado" }));

        btnCambiarContra.setText("Cambiar contraseña");
        btnCambiarContra.setEnabled(false);
        btnCambiarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContraActionPerformed(evt);
            }
        });

        btnnuevouser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnuevouser.setText("Nuevo");

        javax.swing.GroupLayout jPusuariosLayout = new javax.swing.GroupLayout(jPusuarios);
        jPusuarios.setLayout(jPusuariosLayout);
        jPusuariosLayout.setHorizontalGroup(
            jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPusuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPusuariosLayout.createSequentialGroup()
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbcorreouser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbpassworduser, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPusuariosLayout.createSequentialGroup()
                                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbiduser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbnombreuser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74)
                                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTfnombreuser)
                                    .addComponent(jTfiduser)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPusuariosLayout.createSequentialGroup()
                                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbTipouser)
                                    .addGroup(jPusuariosLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(btnlimpiarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPusuariosLayout.createSequentialGroup()
                                        .addComponent(btnguardarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                        .addComponent(btneliminarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33))
                                    .addComponent(jTfcorreouser)
                                    .addComponent(jTfpassworduser, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPusuariosLayout.createSequentialGroup()
                                        .addComponent(cbxuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPusuariosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCambiarContra)))
                        .addGap(18, 18, 18))))
        );
        jPusuariosLayout.setVerticalGroup(
            jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbiduser)
                    .addComponent(jTfiduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbnombreuser)
                    .addComponent(jTfnombreuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbTipouser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPusuariosLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLbcorreouser))
                    .addGroup(jPusuariosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTfcorreouser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbpassworduser)
                    .addComponent(jTfpassworduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCambiarContra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnlimpiarUser)
                            .addComponent(btneliminarUser))
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                        .addComponent(btnguardarUser)
                        .addGap(50, 50, 50))))
        );

        javax.swing.GroupLayout jPaneluserLayout = new javax.swing.GroupLayout(jPaneluser);
        jPaneluser.setLayout(jPaneluserLayout);
        jPaneluserLayout.setHorizontalGroup(
            jPaneluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneluserLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPaneluserLayout.setVerticalGroup(
            jPaneluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneluserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Usuarios", jPaneluser);

        Tblmora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblmoraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tblmora);

        jPmora.setBackground(new java.awt.Color(0, 102, 204));

        btnConsultamora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConsultamora.setText("Consultar");
        btnConsultamora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultamoraActionPerformed(evt);
            }
        });

        jTfidusermora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfidusermoraActionPerformed(evt);
            }
        });

        jLbidUsermora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbidUsermora.setText("ID Usuario");

        jLbnombremora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbnombremora.setText("Nombre ");

        jTfnombremora.setEditable(false);

        lblPrestamoAdminMora.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrestamoAdminMora.setText("Prestamo");

        lblFechaDePrestamoMora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaDePrestamoMora.setText("Fecha de prestamo:");

        lblFechaDevolucionMora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaDevolucionMora.setText("Fecha de devolucion:");

        lblMoraActualEnMora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMoraActualEnMora.setText("Mora actual:");

        btnAbonarMoraEnMora.setText("Abonar");
        btnAbonarMoraEnMora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarMoraEnMoraActionPerformed(evt);
            }
        });

        txtFechaPrestamoEnMora.setEditable(false);

        txtFechaDevolucionEnMora.setEditable(false);

        txtMoraActualEnMora.setEditable(false);

        lblDiasDeRetrasoMora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDiasDeRetrasoMora.setText("Dias de retraso:");

        txtDiasDeRetrasoMora.setEditable(false);

        javax.swing.GroupLayout jPmoraLayout = new javax.swing.GroupLayout(jPmora);
        jPmora.setLayout(jPmoraLayout);
        jPmoraLayout.setHorizontalGroup(
            jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmoraLayout.createSequentialGroup()
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPmoraLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPmoraLayout.createSequentialGroup()
                                .addComponent(lblDiasDeRetrasoMora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDiasDeRetrasoMora, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmoraLayout.createSequentialGroup()
                                .addComponent(lblFechaDevolucionMora)
                                .addGap(68, 68, 68)
                                .addComponent(txtFechaDevolucionEnMora))
                            .addComponent(lblPrestamoAdminMora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPmoraLayout.createSequentialGroup()
                                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbidUsermora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbnombremora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTfnombremora, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPmoraLayout.createSequentialGroup()
                                        .addComponent(jTfidusermora, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(btnConsultamora))))
                            .addGroup(jPmoraLayout.createSequentialGroup()
                                .addComponent(lblFechaDePrestamoMora)
                                .addGap(77, 77, 77)
                                .addComponent(txtFechaPrestamoEnMora))
                            .addGroup(jPmoraLayout.createSequentialGroup()
                                .addComponent(lblMoraActualEnMora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMoraActualEnMora, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPmoraLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btnAbonarMoraEnMora)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPmoraLayout.setVerticalGroup(
            jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmoraLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbidUsermora)
                    .addComponent(jTfidusermora, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultamora))
                .addGap(18, 18, 18)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbnombremora)
                    .addComponent(jTfnombremora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addComponent(lblPrestamoAdminMora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaDePrestamoMora)
                    .addComponent(txtFechaPrestamoEnMora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaDevolucionMora)
                    .addComponent(txtFechaDevolucionEnMora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiasDeRetrasoMora)
                    .addComponent(txtDiasDeRetrasoMora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoraActualEnMora)
                    .addComponent(txtMoraActualEnMora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAbonarMoraEnMora)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelmoraLayout = new javax.swing.GroupLayout(jPanelmora);
        jPanelmora.setLayout(jPanelmoraLayout);
        jPanelmoraLayout.setHorizontalGroup(
            jPanelmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelmoraLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPmora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanelmoraLayout.setVerticalGroup(
            jPanelmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmoraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPmora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Mora ", jPanelmora);

        jpTarifasInterno.setBackground(new java.awt.Color(0, 102, 204));

        txtTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarifaActionPerformed(evt);
            }
        });

        lblAnioAplicable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAnioAplicable.setText("Año Aplicable:");

        lblTarifa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifa.setText("Tarifa Diaria:");

        btnGuardarTarifa.setText("Guardar");
        btnGuardarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTarifaActionPerformed(evt);
            }
        });

        btnEliminarTarifa.setText("Eliminar");
        btnEliminarTarifa.setEnabled(false);
        btnEliminarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTarifaActionPerformed(evt);
            }
        });

        btnLimpiarFormularioTarifas.setText("Limpiar");
        btnLimpiarFormularioTarifas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFormularioTarifasActionPerformed(evt);
            }
        });

        lnlTipoUsuarioTarifa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lnlTipoUsuarioTarifa.setText("Tipo Usuario:");

        cbxTipoUsuarioTarifas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alumno", "Profesor" }));
        cbxTipoUsuarioTarifas.setSelectedIndex(-1);

        javax.swing.GroupLayout jpTarifasInternoLayout = new javax.swing.GroupLayout(jpTarifasInterno);
        jpTarifasInterno.setLayout(jpTarifasInternoLayout);
        jpTarifasInternoLayout.setHorizontalGroup(
            jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTarifasInternoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lnlTipoUsuarioTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpTarifasInternoLayout.createSequentialGroup()
                        .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnioAplicable, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarTarifa, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpTarifasInternoLayout.createSequentialGroup()
                                .addComponent(btnEliminarTarifa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiarFormularioTarifas))
                            .addComponent(txtAnioAplicable, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTarifa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipoUsuarioTarifas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jpTarifasInternoLayout.setVerticalGroup(
            jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTarifasInternoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnioAplicable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnioAplicable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnlTipoUsuarioTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipoUsuarioTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTarifa)
                    .addComponent(txtTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jpTarifasInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarTarifa)
                    .addComponent(btnEliminarTarifa)
                    .addComponent(btnLimpiarFormularioTarifas))
                .addContainerGap(323, Short.MAX_VALUE))
        );

        tblTarifas.setModel(morasDB.selectMoras()
        );
        tblTarifas.setEnabled(false);
        tblTarifas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTarifasMouseClicked(evt);
            }
        });
        jcpTablaTarifas.setViewportView(tblTarifas);

        javax.swing.GroupLayout jpTarifasLayout = new javax.swing.GroupLayout(jpTarifas);
        jpTarifas.setLayout(jpTarifasLayout);
        jpTarifasLayout.setHorizontalGroup(
            jpTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTarifasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTarifasInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcpTablaTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(543, Short.MAX_VALUE))
        );
        jpTarifasLayout.setVerticalGroup(
            jpTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTarifasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTarifasInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpTarifasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jcpTablaTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92))
        );

        jtbprestamos.addTab("Admin Tarifas", jpTarifas);

        jPanelprestamo.setBackground(new java.awt.Color(255, 255, 255));

        tblPrestamo.setModel(prestamosDB.selectPrestamosDetallado());
        tblPrestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPrestamoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblPrestamo);
        if (tblPrestamo.getColumnModel().getColumnCount() > 0) {
            tblPrestamo.getColumnModel().getColumn(5).setHeaderValue("Fecha de Devolución");
            tblPrestamo.getColumnModel().getColumn(6).setHeaderValue("Estado");
        }

        jPprestamos.setBackground(new java.awt.Color(0, 102, 204));

        lblFiltrosPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFiltrosPrestamo.setText("Filtros");

        lblDetallesPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDetallesPrestamo.setText("Detalles");

        lblTipoMaterialPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoMaterialPrestamo.setText("Tipo de material");

        lblcbxEstadoPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblcbxEstadoPrestamo.setText("Estado de prestamo");

        cbxTipoMaterialPrestamo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Libro", "Revista", "Audiovisual", "Otro" }));

        cbxEstadoMaterialPrestamo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendiente", "En curso", "Devuelto", "Denegado" }));

        lblCorreoEnPrestamo.setText("Correo:");

        lblTituloMaterialEnPrestamo.setText("Titulo:");

        lblNombreUsuarioEnPrestamo.setText("Nombre:");

        lblTipoMaterialEnPrestamo.setText("Tipo:");

        lblUbicacionMaterialEnPrestamo.setText("Ubicacion:");

        lblAccionesPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAccionesPrestamo.setText("Prestamo");

        lblFechaPrestamo.setText("Fecha de prestamo");

        lblFechaDevolucion.setText("Fecha de devolucion");

        lblMoraAplicable.setText("Mora aplicable");

        lblEstadoPrestamo.setText("Estado");

        lblDiasDeRetradoPrestamo.setText("Dias de retraso:");

        lblMoraActualPrestamo.setText("Mora actual:");

        btnPrestarDevolverPrestamo.setText("Aprobar");
        btnPrestarDevolverPrestamo.setEnabled(false);
        btnPrestarDevolverPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestarDevolverPrestamoActionPerformed(evt);
            }
        });

        btnDenegarPrestamo.setText("Denegar");
        btnDenegarPrestamo.setEnabled(false);
        btnDenegarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenegarPrestamoActionPerformed(evt);
            }
        });

        btnLimpiarFormularioPrestamo.setText("Limpiar");
        btnLimpiarFormularioPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFormularioPrestamoActionPerformed(evt);
            }
        });

        txtCorreoEnPrestamo.setEditable(false);

        txtNombreUsuarioEnPrestamo.setEditable(false);
        txtNombreUsuarioEnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioEnPrestamoActionPerformed(evt);
            }
        });

        txtTituloMaterialEnPrestamo.setEditable(false);

        txtTipoMaterialEnPrestamo.setEditable(false);

        txtUbicacionEnPrestamo.setEditable(false);
        txtUbicacionEnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUbicacionEnPrestamoActionPerformed(evt);
            }
        });

        txtMoraAplicablePrestamo.setEditable(false);

        txtDiasRetrasoPrestamo.setEditable(false);

        txtMoraActualPrestamo.setEditable(false);

        txtEstadoPrestamo.setEditable(false);

        btnFiltrarPrestamo.setText("Filtrar");
        btnFiltrarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPrestamoActionPerformed(evt);
            }
        });

        checkConMora.setText("Con Mora");

        javax.swing.GroupLayout jPprestamosLayout = new javax.swing.GroupLayout(jPprestamos);
        jPprestamos.setLayout(jPprestamosLayout);
        jPprestamosLayout.setHorizontalGroup(
            jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPprestamosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFiltrosPrestamo)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(checkConMora)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDetallesPrestamo)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblNombreUsuarioEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreUsuarioEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblCorreoEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCorreoEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblTituloMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTituloMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblTipoMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipoMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblUbicacionMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUbicacionEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblAccionesPrestamo)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaDevolucionPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblMoraAplicable, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMoraAplicablePrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblDiasDeRetradoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiasRetrasoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblMoraActualPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMoraActualPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblEstadoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstadoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addComponent(btnPrestarDevolverPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDenegarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarFormularioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPprestamosLayout.createSequentialGroup()
                            .addComponent(lblTipoMaterialPrestamo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPprestamosLayout.createSequentialGroup()
                            .addComponent(lblcbxEstadoPrestamo)
                            .addGap(12, 12, 12)
                            .addComponent(cbxEstadoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPprestamosLayout.setVerticalGroup(
            jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPprestamosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblFiltrosPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMaterialPrestamo)
                    .addComponent(cbxTipoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcbxEstadoPrestamo)
                    .addComponent(cbxEstadoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkConMora)
                    .addComponent(btnFiltrarPrestamo))
                .addGap(20, 20, 20)
                .addComponent(lblDetallesPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuarioEnPrestamo)
                    .addComponent(txtNombreUsuarioEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloMaterialEnPrestamo)
                    .addComponent(txtTituloMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreoEnPrestamo)
                    .addComponent(txtCorreoEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoMaterialEnPrestamo)
                    .addComponent(txtTipoMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUbicacionMaterialEnPrestamo)
                    .addComponent(txtUbicacionEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblAccionesPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaPrestamo)
                    .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiasDeRetradoPrestamo)
                    .addComponent(txtDiasRetrasoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaDevolucion)
                    .addComponent(txtFechaDevolucionPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoraActualPrestamo)
                    .addComponent(txtMoraActualPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoraAplicable)
                    .addComponent(txtMoraAplicablePrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoPrestamo)
                    .addComponent(txtEstadoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrestarDevolverPrestamo)
                    .addComponent(btnDenegarPrestamo)
                    .addComponent(btnLimpiarFormularioPrestamo))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelprestamoLayout = new javax.swing.GroupLayout(jPanelprestamo);
        jPanelprestamo.setLayout(jPanelprestamoLayout);
        jPanelprestamoLayout.setHorizontalGroup(
            jPanelprestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelprestamoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelprestamoLayout.setVerticalGroup(
            jPanelprestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelprestamoLayout.createSequentialGroup()
                .addGroup(jPanelprestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelprestamoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPprestamos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Admin Prestamos", jPanelprestamo);

        jPanelPrestamoUsuario.setBackground(new java.awt.Color(255, 255, 255));

        tblMaterialesPrestamoUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblMaterialesPrestamoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaterialesPrestamoUsuarioMouseClicked(evt);
            }
        });
        jspPrestamoUsuarioTabla.setViewportView(tblMaterialesPrestamoUsuario);
        if (tblMaterialesPrestamoUsuario.getColumnModel().getColumnCount() > 0) {
            tblMaterialesPrestamoUsuario.getColumnModel().getColumn(5).setHeaderValue("Fecha de Devolución");
            tblMaterialesPrestamoUsuario.getColumnModel().getColumn(6).setHeaderValue("Estado");
        }

        jpPrestamosUsuario.setBackground(new java.awt.Color(0, 102, 204));

        lblFiltrosPrestamoUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFiltrosPrestamoUsuario.setText("Filtros");

        lblDetallesPrestamoUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDetallesPrestamoUsuario.setText("Detalles");

        lblTipoMaterialPrestamoUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoMaterialPrestamoUsuario.setText("Tipo de material");

        lblTituloMaterialPrestamoUsuarioFiltro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTituloMaterialPrestamoUsuarioFiltro.setText("Titulo");

        cbxTipoMaterialPrestamoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Libro", "Revista", "Audiovisual", "Otro" }));

        lblTituloMaterialEnPrestamoUsuario.setText("Titulo:");

        lblTipoMaterialEnPrestamoUsuario.setText("Tipo:");

        btnSolicitarPrestamoUsuario.setText("Solicitar");
        btnSolicitarPrestamoUsuario.setEnabled(false);
        btnSolicitarPrestamoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarPrestamoUsuarioActionPerformed(evt);
            }
        });

        btnLimpiarFormularioPrestamoUsuario.setText("Limpiar");
        btnLimpiarFormularioPrestamoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFormularioPrestamoUsuarioActionPerformed(evt);
            }
        });

        txtTituloMaterialEnPrestamoUsuario.setEditable(false);
        txtTituloMaterialEnPrestamoUsuario.setEnabled(false);

        txtTipoMaterialEnPrestamoUsuario.setEditable(false);
        txtTipoMaterialEnPrestamoUsuario.setEnabled(false);

        btnFiltrarPrestamoUsuario.setText("Filtrar");
        btnFiltrarPrestamoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPrestamoUsuarioActionPerformed(evt);
            }
        });

        lblMisPrestamosUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMisPrestamosUsuario.setText("Mis prestamos pendientes");

        jspTablaPrestamosUsuario.setViewportView(tblPrestamosUsuario);

        lblErrorPrestamosUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblErrorPrestamosUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblErrorPrestamosUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblErrorPrestamosUsuario.setText("Posee prestamos pendientes. No es posible solicitar un nuevo prestamo");
        lblErrorPrestamosUsuario.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jpPrestamosUsuarioLayout = new javax.swing.GroupLayout(jpPrestamosUsuario);
        jpPrestamosUsuario.setLayout(jpPrestamosUsuarioLayout);
        jpPrestamosUsuarioLayout.setHorizontalGroup(
            jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jspTablaPrestamosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                            .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFiltrosPrestamoUsuario)
                                .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                                    .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTituloMaterialEnPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTipoMaterialEnPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTipoMaterialEnPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTituloMaterialEnPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(btnSolicitarPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnLimpiarFormularioPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                                    .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMisPrestamosUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTituloMaterialPrestamoUsuarioFiltro, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                                            .addComponent(lblDetallesPrestamoUsuario)
                                            .addGap(42, 42, 42))
                                        .addComponent(lblTipoMaterialPrestamoUsuario, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                                            .addComponent(txtTituloFiltroPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnFiltrarPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cbxTipoMaterialPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(49, 49, 49)))
                    .addComponent(lblErrorPrestamosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPrestamosUsuarioLayout.setVerticalGroup(
            jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrestamosUsuarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblFiltrosPrestamoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoMaterialPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoMaterialPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloMaterialPrestamoUsuarioFiltro)
                    .addComponent(txtTituloFiltroPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrarPrestamoUsuario))
                .addGap(9, 9, 9)
                .addComponent(lblDetallesPrestamoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloMaterialEnPrestamoUsuario)
                    .addComponent(txtTituloMaterialEnPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMaterialEnPrestamoUsuario)
                    .addComponent(txtTipoMaterialEnPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrestamosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSolicitarPrestamoUsuario)
                    .addComponent(btnLimpiarFormularioPrestamoUsuario))
                .addGap(18, 18, 18)
                .addComponent(lblErrorPrestamosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMisPrestamosUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspTablaPrestamosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelPrestamoUsuarioLayout = new javax.swing.GroupLayout(jPanelPrestamoUsuario);
        jPanelPrestamoUsuario.setLayout(jPanelPrestamoUsuarioLayout);
        jPanelPrestamoUsuarioLayout.setHorizontalGroup(
            jPanelPrestamoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrestamoUsuarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jpPrestamosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jspPrestamoUsuarioTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanelPrestamoUsuarioLayout.setVerticalGroup(
            jPanelPrestamoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrestamoUsuarioLayout.createSequentialGroup()
                .addGroup(jPanelPrestamoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jspPrestamoUsuarioTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPrestamoUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpPrestamosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Prestamos", jPanelPrestamoUsuario);

        jTfBibliotecaamigosDonBosco.setEditable(false);
        jTfBibliotecaamigosDonBosco.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jTfBibliotecaamigosDonBosco.setText("Bliblioteca Amigos Don Bosco");
        jTfBibliotecaamigosDonBosco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfBibliotecaamigosDonBoscoActionPerformed(evt);
            }
        });

        jLbNombreUsuario.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLbNombreUsuario.setForeground(new java.awt.Color(51, 153, 255));
        jLbNombreUsuario.setText("jLabel2");
        jLbNombreUsuario.setName("LbNombreUser"); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Logout");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        setJMenuBar(Administracion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jtbprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 1321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(jTfBibliotecaamigosDonBosco, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLbNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTfBibliotecaamigosDonBosco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLbNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtbprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContraActionPerformed
        String correo = jTfcorreouser.getText();
        String contra =  jTfpassworduser.getText();

        if(correo.isEmpty() || contra.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Debe proporcionar un correo y una contraseña",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean resultado = UsuariosDB.ResetPass(correo, contra);
        if (resultado) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Contraseña asignada correctamente\nContraseña nueva: " + contra,
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            limpiarFormularioAutor();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al cambiar contraseña",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        jTfpassworduser.setText("");
    }//GEN-LAST:event_btnCambiarContraActionPerformed

    private void btnEliminarEditorialActionPerformed(java.awt.event.ActionEvent evt) {
        if (idEditorialSeleccionado == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Debe seleccionar una editorial para eliminar",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea eliminar esta editorial?",
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            Editorial editorial = new Editorial();
            editorial.setIdEditorial(idEditorialSeleccionado);

            int resultado = editorialDB.delete(editorial);

            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Editorial eliminada correctamente",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioEditorial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al eliminar la editorial",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarEditorialActionPerformed
    
    private void tblEditorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEditorialMouseClicked
        int fila = tblEditorial.rowAtPoint(evt.getPoint());
        int columna = tblEditorial.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            DefaultTableModel modelo = (DefaultTableModel) tblEditorial.getModel();
            idEditorialSeleccionado = Integer.parseInt(modelo.getValueAt(fila,0).toString());
            txtNombreEditorial.setText(modelo.getValueAt(fila,1).toString());
            txtPaisEditorial.setText(modelo.getValueAt(fila,2).toString());
            btnGuardarEditorial.setText("Editar");
            btnEliminarEditorial.setEnabled(true);
        }
    }//GEN-LAST:event_tblEditorialMouseClicked

    private void txtPaisAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisAutorActionPerformed

    private void btnGuardarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAutorActionPerformed
        String nombre = txtNombreAutor.getText().trim();
        String apellidos = txtNombreAutor.getText().trim();
        String pais = txtPaisAutor.getText().trim();

        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "El nombre del autor es obligatorio", 
                "Error de validación", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (apellidos.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Los apellidos del autor son obligatorios", 
                "Error de validación", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (pais.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "El país del autor es obligatorio", 
                "Error de validación", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (btnGuardarAutor.getText().equals("Guardar")) {
            Autor autor = new Autor(0,nombre, apellidos, pais);
            int resultado = autorDB.insert(autor);

            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Autor guardado correctamente", 
                    "Éxito", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioAutor();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error al guardar el autor", 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Autor autor = new Autor(idAutorSeleccionado, nombre, apellidos, pais);
            int resultado = autorDB.update(autor);

            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Autor actualizado correctamente", 
                    "Éxito", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioAutor();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error al actualizar el autor", 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarAutorActionPerformed

    private void btnEliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAutorActionPerformed
        if (idAutorSeleccionado > 0) {
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar este autor?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                int resultado = autorDB.delete(idAutorSeleccionado);

                if (resultado > 0) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Autor eliminado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormularioAutor();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al eliminar el autor",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Debe seleccionar un autor para eliminar",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarAutorActionPerformed

    private void btmLimpiarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmLimpiarAutorActionPerformed
        limpiarFormularioAutor();
    }//GEN-LAST:event_btmLimpiarAutorActionPerformed

    private void tblAutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAutorMouseClicked
        int fila = tblAutor.rowAtPoint(evt.getPoint());
        int columna = tblAutor.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            DefaultTableModel modelo = (DefaultTableModel) tblAutor.getModel();
            idAutorSeleccionado = Integer.parseInt(modelo.getValueAt(fila,0).toString());
            txtNombreAutor.setText(modelo.getValueAt(fila,1).toString());
            txtApellidosAutor.setText(modelo.getValueAt(fila,2).toString());
            txtPaisAutor.setText(modelo.getValueAt(fila,3).toString());
            btnGuardarAutor.setText("Editar");
            btnEliminarAutor.setEnabled(true);
        }
    }//GEN-LAST:event_tblAutorMouseClicked

    private void jTfCantdispmatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfCantdispmatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTfCantdispmatActionPerformed

    private void btnAbonarMoraEnMoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarMoraEnMoraActionPerformed
     if (idPrestamoConMora == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un préstamo primero",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener el préstamo actual

            // Solicitar el monto a abonar
            String montoStr = JOptionPane.showInputDialog(this,
                "Mora actual: $" + txtMoraActualEnMora.getText() + "\n\nIngrese el monto a abonar:",
                "Abonar a la Mora",
                JOptionPane.QUESTION_MESSAGE);
            if(montoStr == null) {
                return; // El usuario canceló la operación
            }

            try {
                java.math.BigDecimal montoAbonar = new java.math.BigDecimal(montoStr.trim());

                // Validar que el monto sea positivo
                if (montoAbonar.compareTo(java.math.BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this,
                        "El monto debe ser mayor a cero",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el monto no sea mayor a la mora actual
                if (montoAbonar.compareTo(new java.math.BigDecimal(txtMoraActualEnMora.getText())) > 0) {
                    JOptionPane.showMessageDialog(this,
                        "El monto a abonar no puede ser mayor a la mora actual ($" + txtMoraActualEnMora.getText() + ")",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calcular nueva mora
                java.math.BigDecimal nuevaMora = new java.math.BigDecimal(txtMoraActualEnMora.getText()).subtract(montoAbonar);

                // Confirmar el abono
                int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Confirmar abono?\n\n" +
                    "Mora actual: $" + txtMoraActualEnMora.getText() + "\n" +
                    "Monto a abonar: $" + montoAbonar + "\n" +
                    "Nueva mora: $" + nuevaMora,
                    "Confirmar Abono",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }

                Prestamo prestamo = prestamosDB.select(idPrestamoConMora);

                // Actualizar la mora
                prestamo.setMoraTotal(nuevaMora);

                // Guardar en la base de datos
                if (prestamosDB.update(prestamo)) {
                    JOptionPane.showMessageDialog(this,
                        "Abono registrado exitosamente\n" +
                        "Nueva mora: $" + nuevaMora,
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Actualizar solo el campo de mora en la interfaz
                    txtMoraActualEnMora.setText(nuevaMora.toString());

                    // Si la mora llegó a cero, deshabilitar el botón
                    if (nuevaMora.compareTo(java.math.BigDecimal.ZERO) == 0) {
                        btnAbonarMoraEnMora.setEnabled(false);
                    }

                    Tblmora.setModel(new DefaultTableModel()); // Limpiar la tabla
                    txtMoraActualEnMora.setText(""); // Limpiar el campo de mora
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el abono",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un número válido",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAbonarMoraEnMoraActionPerformed

    private void TblmoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblmoraMouseClicked
        int fila = Tblmora.rowAtPoint(evt.getPoint());
        int columna = Tblmora.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            DefaultTableModel modelo = (DefaultTableModel) Tblmora.getModel();
            idPrestamoConMora = Integer.parseInt(modelo.getValueAt(fila,0).toString());
            txtFechaPrestamoEnMora.setText(modelo.getValueAt(fila,2).toString());
            txtFechaDevolucionEnMora.setText(modelo.getValueAt(fila,4).toString());
            txtDiasDeRetrasoMora.setText(modelo.getValueAt(fila,5).toString());
            txtMoraActualEnMora.setText(modelo.getValueAt(fila,6).toString());
        }

    }//GEN-LAST:event_TblmoraMouseClicked

    private void btnConsultamoraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnConsultamoraActionPerformed
        // TODO add your handling code here:
        String idTexto = jTfidusermora.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el ID de usuario.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;

        }
        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idTexto);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El ID de usuario debe ser numérico.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuarios usuario = UsuariosDB.select(idUsuario);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró el usuario en la tabla de usuarios.",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        jTfnombremora.setText(usuario.getNombre());

        Tblmora.setModel(prestamosDB.selectPrestamosConMoraTotal(idUsuario));


    }

    private void jTfiduserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfiduserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTfiduserActionPerformed

    private void btnguardarUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnguardarUserActionPerformed

        // TRABAJAR : SI EL BOTON DICE GUARDAR ES GUARADARLO, CAPTURAR EL TIPO
        // DEMATERIAL DE CONMBOX Y CONVERTIRLO A ENUM
        Usuarios.TipoUsuario tipo = Usuarios.TipoUsuario.valueOf(cbxuser.getSelectedItem().toString());
        String nombre = jTfnombreuser.getText();
        String correo = jTfcorreouser.getText();
        String passwordHash = jTfpassworduser.getText();

        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "El nombre del usuario es obligatorio",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (correo.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "El correo es obligatorio",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (btnguardarUser.getText().equals("Guardar")) {

            Usuarios usuarioNuevo = new Usuarios(
                    0,
                    nombre,
                    correo,
                    passwordHash,
                    tipo
            );
            UsuariosDB db = new UsuariosDB();
            Usuarios resultado = db.insert(usuarioNuevo);

            if (resultado != null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario guardado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioUsuarios();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al guardar el Usuario",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Usuarios usuario = new Usuarios(
                    idUsuariosseleccionado,
                    nombre,
                    correo,
                    passwordHash,
                    tipo
            );
            UsuariosDB db = new UsuariosDB();
            boolean resultado = UsuariosDB.update(usuario);
            if (resultado) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario actualizado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioUsuarios();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al actualizar el Usuario",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

        // TODO add your handling code here:
    }// GEN-LAST:event_btnguardarUserActionPerformed

    private void btnlimpiarUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnlimpiarUserActionPerformed
        limpiarFormularioUsuarios();

        // TODO add your handling code here:
    }// GEN-LAST:event_btnlimpiarUserActionPerformed

    private void tbluserMouseClicked(java.awt.event.MouseEvent evt) {

        int fila = tbluser.rowAtPoint(evt.getPoint());
        int columna = tbluser.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            DefaultTableModel modelo = (DefaultTableModel) tbluser.getModel();
            idUsuariosseleccionado = Integer.parseInt(modelo.getValueAt(fila,0).toString());
            jTfiduser.setText(modelo.getValueAt(fila,0).toString());
            jTfnombreuser.setText(modelo.getValueAt(fila,1).toString());
            cbxuser.setSelectedItem(modelo.getValueAt(fila,2).toString());
            jTfcorreouser.setText(modelo.getValueAt(fila,3).toString());
            jTfpassworduser.setText("");
            jTfpassworduser.setEditable(true);
            btnCambiarContra.setEnabled(true);

        String tipoTabla = modelo.getValueAt(fila, 3).toString().trim();

        boolean encontrado = false;
        for (int i = 0; i < cbxuser.getItemCount(); i++) {
            String item = cbxuser.getItemAt(i).trim();
            if (item.equalsIgnoreCase(tipoTabla)) {
                cbxuser.setSelectedIndex(i);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Advertencia: el ComboBox no contiene el tipo '" + tipoTabla + "'");
        }

             btnguardarUser.setText("Editar");
            btneliminarUser.setEnabled(true);
        }
            // TODO add your handling code here:
    }// GEN-LAST:event_tbluserMouseClicked

    private void btneliminarUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btneliminarUserActionPerformed
        if (idUsuariosseleccionado == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un Usuario para eliminar",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }


            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar este Usuario?",
                    "Confirmar eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            boolean resultado = UsuariosDB.delete(idUsuariosseleccionado);

                if (resultado) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Usuario eliminado correctamente",
                            "Éxito",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    limpiarFormularioUsuarios();
                    actualizarTablaUsuario ();


                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Error al eliminar el usuario",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }


            // TODO add your handling code here:
        } // GEN-LAST:event_btneliminarUserActionPerformed
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new INICIO().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnFiltrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPrestamoActionPerformed
        try {
            // Obtener valores seleccionados de los filtros
            String tipoMaterialSeleccionado = (String) cbxTipoMaterialPrestamo.getSelectedItem();
            String estadoSeleccionado = (String) cbxEstadoMaterialPrestamo.getSelectedItem();
            boolean soloConMora = checkConMora.isSelected();

            // Convertir "En curso" a "En_Curso" para que coincida con el enum
            if (estadoSeleccionado != null && estadoSeleccionado.equals("En curso")) {
                estadoSeleccionado = "En_Curso";
            }

            // Limpiar selección actual
            idPrestamoSeleccionado = 0;
            limpiarCamposDetallesPrestamo();

            // Aplicar filtros y actualizar tabla
            if (tipoMaterialSeleccionado.equals("Todos") && estadoSeleccionado.equals("Todos") && !soloConMora) {
                // Si todos los filtros están en "Todos" y no hay filtro de mora, mostrar todos los préstamos
                actualizarTablaPrestamos();
            } else {
                // Aplicar filtros
                DefaultTableModel modeloFiltrado = prestamosDB.selectPrestamosDetalladoFiltrado(
                    tipoMaterialSeleccionado,
                    estadoSeleccionado,
                    soloConMora
                );
                tblPrestamo.setModel(modeloFiltrado);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al aplicar filtros: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnFiltrarPrestamoActionPerformed

    // Limpia solo los campos de detalles del préstamo sin afectar la tabla
    private void limpiarCamposDetallesPrestamo() {
        txtCorreoEnPrestamo.setText("");
        txtNombreUsuarioEnPrestamo.setText("");
        txtTituloMaterialEnPrestamo.setText("");
        txtTipoMaterialEnPrestamo.setText("");
        txtUbicacionEnPrestamo.setText("");
        txtFechaPrestamo.setText("");
        txtFechaDevolucionPrestamo.setText("");
        txtMoraAplicablePrestamo.setText("");
        txtDiasRetrasoPrestamo.setText("0");
        txtMoraActualPrestamo.setText("0");
        txtEstadoPrestamo.setText("");

        btnPrestarDevolverPrestamo.setText("Prestar");
        btnPrestarDevolverPrestamo.setEnabled(false);
        btnDenegarPrestamo.setEnabled(false);
        txtFechaDevolucionPrestamo.setEditable(false);
    }

    private void txtUbicacionEnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUbicacionEnPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionEnPrestamoActionPerformed

    private void txtNombreUsuarioEnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioEnPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioEnPrestamoActionPerformed

    private void btnLimpiarFormularioPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFormularioPrestamoActionPerformed
        limpiarFormularioPrestamo();
    }//GEN-LAST:event_btnLimpiarFormularioPrestamoActionPerformed

    private void btnDenegarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenegarPrestamoActionPerformed
        if (idPrestamoSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un préstamo primero",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener el préstamo actual
            Prestamo prestamo = prestamosDB.select(idPrestamoSeleccionado);

            if (prestamo == null) {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el préstamo seleccionado",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(btnDenegarPrestamo.getText().equals("Denegar")){
                // DENEGAR PRÉSTAMO: Cambiar de Pendiente a Denegado
                int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea denegar este préstamo?",
                    "Confirmar Denegación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }

                prestamo.setEstado(Prestamo.Estado.Denegado);

                if (prestamosDB.update(prestamo)) {
                    JOptionPane.showMessageDialog(this,
                        "Préstamo denegado exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaPrestamos();
                    limpiarFormularioPrestamo();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al denegar el préstamo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }

            // Verificar que el préstamo tenga mora
            if (prestamo.getMoraTotal() == null || prestamo.getMoraTotal().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Este préstamo no tiene mora pendiente",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Solicitar el monto a abonar
            String montoStr = JOptionPane.showInputDialog(this,
                "Mora actual: $" + prestamo.getMoraTotal() + "\n\nIngrese el monto a abonar:",
                "Abonar a la Mora",
                JOptionPane.QUESTION_MESSAGE);

            if (montoStr == null || montoStr.trim().isEmpty()) {
                return; // Usuario canceló
            }

            try {
                java.math.BigDecimal montoAbonar = new java.math.BigDecimal(montoStr.trim());

                // Validar que el monto sea positivo
                if (montoAbonar.compareTo(java.math.BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this,
                        "El monto debe ser mayor a cero",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el monto no sea mayor a la mora actual
                if (montoAbonar.compareTo(prestamo.getMoraTotal()) > 0) {
                    JOptionPane.showMessageDialog(this,
                        "El monto a abonar no puede ser mayor a la mora actual ($" + prestamo.getMoraTotal() + ")",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calcular nueva mora
                java.math.BigDecimal nuevaMora = prestamo.getMoraTotal().subtract(montoAbonar);

                // Confirmar el abono
                int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Confirmar abono?\n\n" +
                    "Mora actual: $" + prestamo.getMoraTotal() + "\n" +
                    "Monto a abonar: $" + montoAbonar + "\n" +
                    "Nueva mora: $" + nuevaMora,
                    "Confirmar Abono",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }

                // Actualizar la mora
                prestamo.setMoraTotal(nuevaMora);

                // Guardar en la base de datos
                if (prestamosDB.update(prestamo)) {
                    JOptionPane.showMessageDialog(this,
                        "Abono registrado exitosamente\n" +
                        "Nueva mora: $" + nuevaMora,
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Actualizar solo el campo de mora en la interfaz
                    txtMoraActualPrestamo.setText(nuevaMora.toString());

                    // Si la mora llegó a cero, deshabilitar el botón
                    if (nuevaMora.compareTo(java.math.BigDecimal.ZERO) == 0) {
                        btnDenegarPrestamo.setEnabled(false);
                    }

                    actualizarTablaPrestamos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el abono",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un número válido",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDenegarPrestamoActionPerformed

    private void btnPrestarDevolverPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestarDevolverPrestamoActionPerformed
        if (idPrestamoSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un préstamo primero",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener el préstamo actual
            Prestamo prestamo = prestamosDB.select(idPrestamoSeleccionado);

            if (prestamo == null) {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el préstamo seleccionado",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Determinar la acción según el estado actual
            if (prestamo.getEstado() == Prestamo.Estado.Pendiente) {
                // APROBAR PRÉSTAMO: Cambiar de Pendiente a En_Curso
                aprobarPrestamo(prestamo);

            } else if (prestamo.getEstado() == Prestamo.Estado.En_Curso) {
                // REGISTRAR DEVOLUCIÓN: Cambiar de En_Curso a Devuelto
                registrarDevolucion(prestamo);

            } else {
                JOptionPane.showMessageDialog(this,
                    "Este préstamo no se puede modificar en su estado actual",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPrestarDevolverPrestamoActionPerformed

    private void tblMaterialesPrestamoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaterialesPrestamoUsuarioMouseClicked
        // Habilitar el botón Solicitar solo si el usuario puede prestar
        if (usuarioPuedePrestar) {
            btnSolicitarPrestamoUsuario.setEnabled(true);
            int fila = tblmaterial.rowAtPoint(evt.getPoint());
            int columna = tblmaterial.columnAtPoint(evt.getPoint());
            if ((fila > -1) && (columna > -1)){
                DefaultTableModel modelo = (DefaultTableModel) tblmaterial.getModel();
                idMaterialSeleccionado = Integer.parseInt(modelo.getValueAt(fila,0).toString());
                txtTituloMaterialEnPrestamoUsuario.setText(modelo.getValueAt(fila,1).toString());
                txtTipoMaterialEnPrestamoUsuario.setText(modelo.getValueAt(fila,2).toString());
            }
        }
    }//GEN-LAST:event_tblMaterialesPrestamoUsuarioMouseClicked

    private void btnSolicitarPrestamoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarPrestamoUsuarioActionPerformed
        if(usuarioPuedePrestar){
            if (idMaterialSeleccionado == 0) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un material primero",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Crear nuevo préstamo
                Prestamo nuevoPrestamo = new Prestamo();
                nuevoPrestamo.setIdUsuario(idUsuarioActual);
                nuevoPrestamo.setIdMaterial(idMaterialSeleccionado);
                nuevoPrestamo.setFechaPrestamo(new java.sql.Date(System.currentTimeMillis()));
                nuevoPrestamo.setIdMora(morasDB.getIdMoraPorTipoUsuario(Usuarios.TipoUsuario.Alumno, Year.now().getValue())); // TODO: Ajustar usando objeto usuario
                nuevoPrestamo.setEstado(Prestamo.Estado.Pendiente);
                nuevoPrestamo.setMoraTotal(java.math.BigDecimal.ZERO);

                // Insertar en la base de datos
                Prestamo resultado = prestamosDB.insert(nuevoPrestamo);

                if (resultado != null) {
                    JOptionPane.showMessageDialog(this,
                        "Préstamo solicitado correctamente. Espere la aprobación del administrador.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormularioPrestamo();
                    actualizarMisPrestamos(); // Actualizar tabla de préstamos del usuario
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Error al solicitar el préstamo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSolicitarPrestamoUsuarioActionPerformed

    private void btnLimpiarFormularioPrestamoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFormularioPrestamoUsuarioActionPerformed
        limpiarFormularioPrestamoUsuario();
    }//GEN-LAST:event_btnLimpiarFormularioPrestamoUsuarioActionPerformed

    private void btnFiltrarPrestamoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPrestamoUsuarioActionPerformed
        String tipoMaterial = (String) cbxTipoMaterialPrestamoUsuario.getSelectedItem();
        String tituloMaterial = txtTituloFiltroPrestamoUsuario.getText().trim();
        DefaultTableModel modeloFiltrado = materialesDB.selectMaterialesFiltrado(tipoMaterial, tituloMaterial);
        tblMaterialesPrestamoUsuario.setModel(modeloFiltrado);
    }//GEN-LAST:event_btnFiltrarPrestamoUsuarioActionPerformed

    private void txtTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarifaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarifaActionPerformed

    private void btnGuardarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTarifaActionPerformed
        String anioStr = txtAnioAplicable.getText().trim();
        String tipoUsuarioStr = (String) cbxTipoUsuarioTarifas.getSelectedItem();
        String tarifaStr = txtTarifa.getText().trim();

        if (anioStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "El año aplicable es obligatorio",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anioAplicable;
        try {
            anioAplicable = Integer.parseInt(anioStr);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "El año aplicable debe ser un número",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tarifaStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "La tarifa es obligatoria",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.math.BigDecimal tarifa;
        try {
            tarifa = new java.math.BigDecimal(tarifaStr);
            if (tarifa.compareTo(java.math.BigDecimal.ZERO) < 0) {
                throw new NumberFormatException("Tarifa negativa");
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "La tarifa debe ser un número válido mayor o igual a cero",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuarios.TipoUsuario tipoUsuario = Usuarios.TipoUsuario.valueOf(tipoUsuarioStr);

        if (btnGuardarTarifa.getText().equals("Guardar")) {
            Mora resultado = morasDB.insert( anioAplicable, tipoUsuario, tarifa);

            if (resultado != null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Tarifa guardada correctamente",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaTarifas();
                    limpiarFormularioTarifas();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al guardar la tarifa",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Mora tarifaExistente = morasDB.select(idTarifaSeleccionada);
            if (tarifaExistente == null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "No se encontró la tarifa seleccionada",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            tarifaExistente.setanio_aplicable(anioAplicable);
            tarifaExistente.setTipoUsuario(tipoUsuario);
            tarifaExistente.setTarifaDiaria(tarifa);

            boolean resultado = morasDB.update(tarifaExistente);

            if (resultado) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Tarifa actualizada correctamente",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaTarifas();
                    limpiarFormularioTarifas();

            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al actualizar la tarifa",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarTarifaActionPerformed

    private void btnEliminarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTarifaActionPerformed
        if (idTarifaSeleccionada == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Debe seleccionar una tarifa para eliminar",
                "Error de validación",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea eliminar esta tarifa?",
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

            boolean resultado = morasDB.delete(idTarifaSeleccionada);

            if (resultado) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Tarifa eliminada correctamente",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioTarifas();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al eliminar la tarifa\nPosiblemente está en uso por préstamos existentes",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarTarifaActionPerformed

    private void btnLimpiarFormularioTarifasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFormularioTarifasActionPerformed
        limpiarFormularioTarifas();
    }//GEN-LAST:event_btnLimpiarFormularioTarifasActionPerformed

    private void tblTarifasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTarifasMouseClicked
        int fila = tblTarifas.rowAtPoint(evt.getPoint());
        int columna = tblTarifas.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            DefaultTableModel modelo = (DefaultTableModel) tblTarifas.getModel();
            idTarifaSeleccionada = Integer.parseInt(modelo.getValueAt(fila,0).toString());
            txtAnioAplicable.setText(modelo.getValueAt(fila,1).toString());
            cbxTipoUsuarioTarifas.setSelectedItem(modelo.getValueAt(fila,2).toString());
            txtTarifa.setText(modelo.getValueAt(fila,3).toString());
            btnGuardarTarifa.setText("Editar");
            btnEliminarTarifa.setEnabled(true);
        }

    }//GEN-LAST:event_tblTarifasMouseClicked

    // Aprueba un préstamo pendiente y lo pone en curso
    private void aprobarPrestamo(Prestamo prestamo) {
        // Obtener la fecha de devolución del campo (puede haber sido editada por el usuario)
        String fechaDevolucionStr = txtFechaDevolucionPrestamo.getText().trim();

        if (fechaDevolucionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una fecha de devolución estimada",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Parsear la fecha
            LocalDate fechaEstimada = LocalDate.parse(fechaDevolucionStr);

            // Validar que la fecha estimada sea posterior a la fecha de préstamo
            if (fechaEstimada.isBefore(prestamo.getFechaPrestamo().toLocalDate()) ||
                fechaEstimada.isEqual(prestamo.getFechaPrestamo().toLocalDate())) {
                JOptionPane.showMessageDialog(this,
                    "La fecha de devolución debe ser posterior a la fecha de préstamo",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmar la aprobación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea aprobar este préstamo?",
                "Confirmar Aprobación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            // Actualizar el préstamo
            prestamo.setEstado(Prestamo.Estado.En_Curso);
            prestamo.setFechaEstimada(java.sql.Date.valueOf(fechaEstimada));
            prestamo.setFechaDevolucion(null);

            // Guardar en la base de datos
            if (prestamosDB.update(prestamo)) {
                JOptionPane.showMessageDialog(this, "Préstamo aprobado exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioPrestamo();
            } else {
                JOptionPane.showMessageDialog(this, "Error al aprobar el préstamo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la fecha: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Registra la devolución de un préstamo en curso
    private void registrarDevolucion(Prestamo prestamo) {
        LocalDate fechaDevolucion = LocalDate.now();
        LocalDate fechaEstimada = prestamo.getFechaEstimada().toLocalDate();

        // Calcular mora si hay retraso
        java.math.BigDecimal moraTotal = java.math.BigDecimal.ZERO;
        int diasRetraso = 0;

        if (fechaDevolucion.isAfter(fechaEstimada)) {
            diasRetraso = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaEstimada, fechaDevolucion);

            // Obtener la tarifa de mora
            MorasDB morasDB = new MorasDB();
            Mora mora = morasDB.select(prestamo.getIdMora());

            if (mora != null && diasRetraso > 0) {
                moraTotal = mora.getTarifaDiaria().multiply(java.math.BigDecimal.valueOf(diasRetraso));
            }
        }

        // Mostrar información de la devolución
        String mensaje = "¿Confirmar devolución del préstamo?\n\n" +
                         "Fecha de devolución: " + fechaDevolucion + "\n" +
                         "Días de retraso: " + diasRetraso + "\n" +
                         "Mora total: $" + moraTotal;

        int confirmacion = JOptionPane.showConfirmDialog(this,
            mensaje,
            "Confirmar Devolución",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        // Actualizar el préstamo
        prestamo.setEstado(Prestamo.Estado.Devuelto);
        prestamo.setFechaDevolucion(java.sql.Date.valueOf(fechaDevolucion));
        prestamo.setMoraTotal(moraTotal);

        // Guardar en la base de datos
        if (prestamosDB.update(prestamo)) {
            JOptionPane.showMessageDialog(this,
                "Devolución registrada exitosamente\n" +
                "Mora total: $" + moraTotal,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormularioPrestamo();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la devolución",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTfBibliotecaamigosDonBoscoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfBibliotecaamigosDonBoscoActionPerformed

    }// GEN-LAST:event_jTfBibliotecaamigosDonBoscoActionPerformed

    private void TxtbusqpresActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_TxtbusqpresActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_TxtbusqpresActionPerformed

    private void btnregdevActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnregdevActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnregdevActionPerformed

    // Calcula los días de retraso y la mora actual para un préstamo
    private void calcularDiasRetrasoYMora(Prestamo prestamo, Mora mora) {
        LocalDate hoy = LocalDate.now();
        int diasRetraso = 0;
        java.math.BigDecimal moraActual = java.math.BigDecimal.ZERO;

        // Solo calcular retraso si el préstamo está "En_Curso"
        if (prestamo.getEstado() == Prestamo.Estado.En_Curso) {
            LocalDate fechaEstimada = prestamo.getFechaEstimada().toLocalDate();

            // Si la fecha actual es posterior a la fecha estimada, hay retraso
            if (hoy.isAfter(fechaEstimada)) {
                diasRetraso = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaEstimada, hoy);

                // Calcular mora actual = días de retraso * tarifa diaria
                if (mora != null && diasRetraso > 0) {
                    moraActual = mora.getTarifaDiaria().multiply(java.math.BigDecimal.valueOf(diasRetraso));
                }
            }
        } else if (prestamo.getEstado() == Prestamo.Estado.Devuelto) {
            // Si ya está devuelto, mostrar la mora total que se cobró
            if (prestamo.getMoraTotal() != null) {
                moraActual = prestamo.getMoraTotal();
            }

            // Calcular días de retraso basado en la fecha de devolución vs fecha estimada
            if (prestamo.getFechaDevolucion() != null && prestamo.getFechaEstimada() != null) {
                LocalDate fechaDevolucion = prestamo.getFechaDevolucion().toLocalDate();
                LocalDate fechaEstimada = prestamo.getFechaEstimada().toLocalDate();

                if (fechaDevolucion.isAfter(fechaEstimada)) {
                    diasRetraso = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaEstimada, fechaDevolucion);
                }
            }
        }

        txtDiasRetrasoPrestamo.setText(String.valueOf(diasRetraso));
        txtMoraActualPrestamo.setText(moraActual.toString());
    }

    // Actualiza la visibilidad y texto de los botones según el estado del préstamo
    private void actualizarBotonesSegunEstado(Prestamo.Estado estado) {
        switch (estado) {
            case Pendiente:
                btnPrestarDevolverPrestamo.setText("Aprobar");
                btnPrestarDevolverPrestamo.setEnabled(true);
                btnDenegarPrestamo.setText("Denegar");
                btnDenegarPrestamo.setEnabled(true); // No hay mora en pendiente
                txtFechaDevolucionPrestamo.setEditable(true);
                break;

            case En_Curso:
                btnPrestarDevolverPrestamo.setText("Devolver");
                btnPrestarDevolverPrestamo.setEnabled(true);
                btnDenegarPrestamo.setText("Abonar Mora");
                btnDenegarPrestamo.setEnabled(false); // Todavía no hay mora registrada
                txtFechaDevolucionPrestamo.setEditable(false);
                break;

            case Devuelto:
            case Denegado:
                btnPrestarDevolverPrestamo.setEnabled(false);
                btnDenegarPrestamo.setText("Abonar Mora");
                // Habilitar solo si hay mora pendiente
                btnDenegarPrestamo.setEnabled(false); // Se habilitará en el método de selección si hay mora
                txtFechaDevolucionPrestamo.setEditable(false);
                break;
        }
    }

    // Limpia todos los campos del formulario de préstamos
    private void limpiarFormularioPrestamo() {
        idPrestamoSeleccionado = 0;
        txtCorreoEnPrestamo.setText("");
        txtNombreUsuarioEnPrestamo.setText("");
        txtTituloMaterialEnPrestamo.setText("");
        txtTipoMaterialEnPrestamo.setText("");
        txtUbicacionEnPrestamo.setText("");
        txtFechaPrestamo.setText("");
        txtFechaDevolucionPrestamo.setText("");
        txtMoraAplicablePrestamo.setText("");
        txtDiasRetrasoPrestamo.setText("0");
        txtMoraActualPrestamo.setText("0");
        txtEstadoPrestamo.setText("");

        btnPrestarDevolverPrestamo.setText("Prestar");
        btnPrestarDevolverPrestamo.setEnabled(false);
        btnDenegarPrestamo.setEnabled(false);
        txtFechaDevolucionPrestamo.setEditable(false);
        actualizarTablaPrestamos();
    }

    // Actualiza la tabla de préstamos
    private void actualizarTablaPrestamos() {
        tblPrestamo.setModel(prestamosDB.selectPrestamosDetallado());
    }

    private void tblPrestamoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblPrestamoMouseClicked
        int fila = tblPrestamo.rowAtPoint(evt.getPoint());
        int columna = tblPrestamo.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)) {
            DefaultTableModel modelo = (DefaultTableModel) tblPrestamo.getModel();

            // Obtener ID del préstamo seleccionado
            idPrestamoSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());

            // Cargar datos del préstamo completo desde la base de datos
            Prestamo prestamo = prestamosDB.select(idPrestamoSeleccionado);

            if (prestamo != null) {
                // Cargar información del usuario
                Usuarios usuario = UsuariosDB.select(prestamo.getIdUsuario());
                if (usuario != null) {
                    txtNombreUsuarioEnPrestamo.setText(usuario.getNombre());
                    txtCorreoEnPrestamo.setText(usuario.getCorreo());
                }

                // Cargar información del material
                Material material = materialesDB.select(prestamo.getIdMaterial());
                if (material != null) {
                    txtTituloMaterialEnPrestamo.setText(material.getTitulo());
                    txtTipoMaterialEnPrestamo.setText(material.getTipoMaterial().toString());
                    txtUbicacionEnPrestamo.setText(material.getUbicacion());
                }

                // Cargar información de la mora
                MorasDB morasDB = new MorasDB();
                Mora mora = morasDB.select(prestamo.getIdMora());
                if (mora != null) {
                    txtMoraAplicablePrestamo.setText(mora.getTarifaDiaria().toString());
                } else {
                    txtMoraAplicablePrestamo.setText("0.00");
                }

                // Cargar fechas
                txtFechaPrestamo.setText(prestamo.getFechaPrestamo().toString());

                // Para fecha de devolución, si es estado "Pendiente" calcular fecha estimada
                if (prestamo.getEstado() == Prestamo.Estado.Pendiente) {
                    // Si la fecha estimada es null, calcular 3 días después de la fecha de préstamo
                    if (prestamo.getFechaEstimada() != null) {
                        txtFechaDevolucionPrestamo.setText(prestamo.getFechaEstimada().toString());
                    } else {
                        LocalDate fechaEstimadaCalculada = prestamo.getFechaPrestamo().toLocalDate().plusDays(3);
                        txtFechaDevolucionPrestamo.setText(fechaEstimadaCalculada.toString());
                    }
                } else {
                    // Para otros estados, mostrar fecha de devolución real o calcular 3 días después
                    if (prestamo.getFechaDevolucion() != null) {
                        txtFechaDevolucionPrestamo.setText(prestamo.getFechaDevolucion().toString());
                    } else {
                        LocalDate fechaEstimadaCalculada = prestamo.getFechaPrestamo().toLocalDate().plusDays(3);
                        txtFechaDevolucionPrestamo.setText(fechaEstimadaCalculada.toString());
                    }
                }

                // Cargar estado
                txtEstadoPrestamo.setText(prestamo.getEstado().toString().replace("_", " "));

                // Calcular días de retraso y mora actual
                calcularDiasRetrasoYMora(prestamo, mora);

                // Actualizar botones según el estado
                actualizarBotonesSegunEstado(prestamo.getEstado());

                // Habilitar botón "Abonar Mora" solo si hay mora pendiente
                if (prestamo.getMoraTotal() != null && prestamo.getMoraTotal().compareTo(java.math.BigDecimal.ZERO) > 0) {
                    btnDenegarPrestamo.setEnabled(true);
                }
            }
        }
    }// GEN-LAST:event_tblPrestamoMouseClicked

    private void jTfbusqumoraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfbusqumoraActionPerformed

    }// GEN-LAST:event_jTfbusqumoraActionPerformed

    private void jTfidusermoraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfidusermoraActionPerformed

    }// GEN-LAST:event_jTfidusermoraActionPerformed

    private void jTfBusquedauserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfBusquedauserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTfBusquedauserActionPerformed

    private void btnlimpiarmaterialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnlimpiarmaterialActionPerformed
        // TODO add your handling code here:
        // llamar a la funcion que limpia el formulario de materiales
        limpiarFormularioMaterial();
    }// GEN-LAST:event_btnlimpiarmaterialActionPerformed

    private void cbxtipomaterialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbxtipomaterialActionPerformed
        actualizarCamposPorTipoMaterial();
    }// GEN-LAST:event_cbxtipomaterialActionPerformed

    private void btnGuardarmatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarmatActionPerformed
        try {
            if(cbxtipomaterial.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de material", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String tipoStr = cbxtipomaterial.getSelectedItem().toString();
            Material.TipoMaterial tipo = Material.TipoMaterial.valueOf(tipoStr);
            String titulo = jTftitulomaterial.getText();
            String ubicacion = jTfUbimaterial.getText();
            int cantidad_total = Integer.parseInt(jTfCanttotal.getText());
            int cantidad_disponible = Integer.parseInt(jTfCantdispmat.getText());
            int cantidad_prestado = Integer.parseInt(txtCantPrestados.getText());
            int cantidad_daniado = Integer.parseInt(txtCantDaniados.getText());

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título del material es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ubicacion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La ubicación del material es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cantidad_total < 1) {
                JOptionPane.showMessageDialog(this, "Cantidad total debe ser mayor o igual a 1", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cantidad_disponible < 0) {
                JOptionPane.showMessageDialog(this, "Cantidad disponible debe ser mayor o igual a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isUpdate = !btnGuardarmat.getText().equals("Guardar");

            if ("Libro".equals(tipoStr)) {
                guardarLibro(tipo, titulo, ubicacion, cantidad_total, cantidad_disponible, cantidad_prestado, cantidad_daniado, isUpdate);
            } else if ("Revista".equals(tipoStr)) {
                guardarRevista(tipo, titulo, ubicacion, cantidad_total, cantidad_disponible, cantidad_prestado, cantidad_daniado, isUpdate);
            } else if ("Audiovisual".equals(tipoStr)) {
                guardarAudiovisual(tipo, titulo, ubicacion, cantidad_total, cantidad_disponible, cantidad_prestado, cantidad_daniado, isUpdate);
            } else if ("Otro".equals(tipoStr)) {
                guardarOtroDocumento(tipo, titulo, ubicacion, cantidad_total, cantidad_disponible, cantidad_prestado, cantidad_daniado, isUpdate);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnGuardarmatActionPerformed

    // Guarda o actualiza un Libro con sus campos específicos (ISBN, Editorial, Autores)
    private void guardarLibro(Material.TipoMaterial tipo, String titulo, String ubicacion,
                              int cantTotal, int cantDisp, int cantPrest, int cantDan, boolean isUpdate) {
        try {
            // Obtener datos específicos del libro
            String isbn = txtISBN != null ? txtISBN.getText() : "";
            int idEditorial = cbxEditorial != null && cbxEditorial.getSelectedIndex() >= 0 ?
                              editorialDB.getAllEditoriales().get(cbxEditorial.getSelectedIndex()).getIdEditorial() : 0;

            List<Integer> idsAutores = new ArrayList<>();
            if (lstAutores != null && lstAutores.getSelectedIndices().length > 0) {
                List<Autor> autores = autorDB.getAllAutores();
                for (int idx : lstAutores.getSelectedIndices()) {
                    if (idx < autores.size()) {
                        idsAutores.add(autores.get(idx).getIdAutor());
                    }
                }
            }

            Libro libro = new Libro();
            libro.setIdMaterial(isUpdate ? idMaterialSeleccionado : 0);
            libro.setTipoMaterial(tipo);
            libro.setTitulo(titulo);
            libro.setUbicacion(ubicacion);
            libro.setCantidadTotal(cantTotal);
            libro.setCantidadDisponible(cantDisp);
            libro.setCantidadPrestada(cantPrest);
            libro.setCantidadDaniada(cantDan);
            libro.setIsbn(isbn);
            libro.setIdEditorial(idEditorial);
            libro.setIdsAutores(idsAutores);

            if (isUpdate) {
                materialesDB.update(libro);
                libroDB.update(libro);
                JOptionPane.showMessageDialog(this, "Libro actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Libro resultado = libroDB.insert(libro);
                if (resultado != null) {
                    JOptionPane.showMessageDialog(this, "Libro guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar el libro", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            limpiarFormularioMaterial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Guarda o actualiza una Revista con sus campos específicos (Volumen, Número, Fecha)
    private void guardarRevista(Material.TipoMaterial tipo, String titulo, String ubicacion,
                                int cantTotal, int cantDisp, int cantPrest, int cantDan, boolean isUpdate) {
        try {
            // Obtener datos específicos de la revista
            String volumen = txtVolumen != null ? txtVolumen.getText() : "";
            String numero = txtNumero != null ? txtNumero.getText() : "";
            LocalDate fechaPub = null;
            if (txtFechaPublicacion != null && !txtFechaPublicacion.getText().isEmpty()) {
                fechaPub = LocalDate.parse(txtFechaPublicacion.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            }

            Revista revista = new Revista();
            revista.setIdMaterial(isUpdate ? idMaterialSeleccionado : 0);
            revista.setTipoMaterial(tipo);
            revista.setTitulo(titulo);
            revista.setUbicacion(ubicacion);
            revista.setCantidadTotal(cantTotal);
            revista.setCantidadDisponible(cantDisp);
            revista.setCantidadPrestada(cantPrest);
            revista.setCantidadDaniada(cantDan);
            revista.setVolumen(volumen);
            revista.setNumero(numero);
            revista.setFechaPublicacion(fechaPub);

            if (isUpdate) {
                materialesDB.update(revista);
                revistaDB.update(revista);
                JOptionPane.showMessageDialog(this, "Revista actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Revista resultado = revistaDB.insert(revista);
                if (resultado != null) {
                    JOptionPane.showMessageDialog(this, "Revista guardada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar la revista", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            limpiarFormularioMaterial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar revista: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Guarda o actualiza un Audiovisual con sus campos específicos (Formato, Duración)
    private void guardarAudiovisual(Material.TipoMaterial tipo, String titulo, String ubicacion,
                                    int cantTotal, int cantDisp, int cantPrest, int cantDan, boolean isUpdate) {
        try {
            // Obtener datos específicos del audiovisual
            String formato = txtFormato != null ? txtFormato.getText() : "";
            int duracion = 0;
            if (txtDuracion != null && !txtDuracion.getText().isEmpty()) {
                duracion = Integer.parseInt(txtDuracion.getText());
            }

            Audiovisual audiovisual = new Audiovisual();
            audiovisual.setIdMaterial(isUpdate ? idMaterialSeleccionado : 0);
            audiovisual.setTipoMaterial(tipo);
            audiovisual.setTitulo(titulo);
            audiovisual.setUbicacion(ubicacion);
            audiovisual.setCantidadTotal(cantTotal);
            audiovisual.setCantidadDisponible(cantDisp);
            audiovisual.setCantidadPrestada(cantPrest);
            audiovisual.setCantidadDaniada(cantDan);
            audiovisual.setFormato(formato);
            audiovisual.setDuracion(duracion);

            if (isUpdate) {
                materialesDB.update(audiovisual);
                audiovisualDB.update(audiovisual);
                JOptionPane.showMessageDialog(this, "Audiovisual actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Audiovisual resultado = audiovisualDB.insert(audiovisual);
                if (resultado != null) {
                    JOptionPane.showMessageDialog(this, "Audiovisual guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar el audiovisual", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            limpiarFormularioMaterial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar audiovisual: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Guarda o actualiza Otro tipo de documento con su campo específico (Descripción)
    private void guardarOtroDocumento(Material.TipoMaterial tipo, String titulo, String ubicacion,
                                      int cantTotal, int cantDisp, int cantPrest, int cantDan, boolean isUpdate) {
        try {
            // Obtener datos específicos del documento
            String descripcion = txtDescripcion != null ? txtDescripcion.getText() : "";

            OtroDocumento otroDoc = new OtroDocumento();
            otroDoc.setIdMaterial(isUpdate ? idMaterialSeleccionado : 0);
            otroDoc.setTipoMaterial(tipo);
            otroDoc.setTitulo(titulo);
            otroDoc.setUbicacion(ubicacion);
            otroDoc.setCantidadTotal(cantTotal);
            otroDoc.setCantidadDisponible(cantDisp);
            otroDoc.setCantidadPrestada(cantPrest);
            otroDoc.setCantidadDaniada(cantDan);
            otroDoc.setDescripcion(descripcion);

            if (isUpdate) {
                materialesDB.update(otroDoc);
                otroDocumentoDB.update(otroDoc);
                JOptionPane.showMessageDialog(this, "Documento actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                OtroDocumento resultado = otroDocumentoDB.insert(otroDoc);
                if (resultado != null) {
                    JOptionPane.showMessageDialog(this, "Documento guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar el documento", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            limpiarFormularioMaterial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar documento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTftitulomaterialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTftitulomaterialActionPerformed

    }// GEN-LAST:event_jTftitulomaterialActionPerformed

    private void btnEliminarmatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarmatActionPerformed
        // TODO add your handling code here:
        // COPIAR CODIGO DE EDITORIAL
        if (idMaterialSeleccionado == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un material para eliminar",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar este material?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

            boolean resultado = materialesDB.delete(idMaterialSeleccionado);

            if (resultado) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Material eliminado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioMaterial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al eliminar el material",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_btnEliminarmatActionPerformed

    private void tblmaterialMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblmaterialMouseClicked
        int fila = tblmaterial.rowAtPoint(evt.getPoint());
        int columna = tblmaterial.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)) {
            DefaultTableModel modelo = (DefaultTableModel) tblmaterial.getModel();
            idMaterialSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            String tipoMaterial = modelo.getValueAt(fila, 1).toString();
            jTftitulomaterial.setText(modelo.getValueAt(fila, 2).toString());
            jTfUbimaterial.setText(modelo.getValueAt(fila, 3).toString());
            jTfCanttotal.setText(modelo.getValueAt(fila, 4).toString());
            jTfCantdispmat.setText(modelo.getValueAt(fila, 5).toString());
            txtCantPrestados.setText(modelo.getValueAt(fila, 6) != null ? modelo.getValueAt(fila, 6).toString() : "0");
            txtCantDaniados.setText(modelo.getValueAt(fila, 7) != null ? modelo.getValueAt(fila, 7).toString() : "0");
            cbxtipomaterial.setSelectedItem(tipoMaterial);
            
            cargarDatosEspecificosMaterial(idMaterialSeleccionado, tipoMaterial);
            
            btnGuardarmat.setText("Editar");
            btnEliminarmat.setEnabled(true);
        }
    }// GEN-LAST:event_tblmaterialMouseClicked

    // Carga los datos específicos de un material según su tipo al editar
    private void cargarDatosEspecificosMaterial(int idMaterial, String tipoMaterial) {
        try {
            if ("Libro".equals(tipoMaterial)) {
                Libro libro = libroDB.select(idMaterial);
                if (libro != null) {
                    // Cargar datos específicos del libro
                    txtISBN.setText(libro.getIsbn() != null ? libro.getIsbn() : "");
                    // Seleccionar la editorial correspondiente
                    if (libro.getIdEditorial() > 0) {
                        List<Editorial> editoriales = editorialDB.getAllEditoriales();
                        for (int i = 0; i < editoriales.size(); i++) {
                            if (editoriales.get(i).getIdEditorial() == libro.getIdEditorial()) {
                                cbxEditorial.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    // Seleccionar los autores correspondientes
                    if (libro.getIdsAutores() != null) {
                        List<Autor> todosAutores = autorDB.getAllAutores();
                        List<Integer> indices = new ArrayList<>();
                        for (int i = 0; i < todosAutores.size(); i++) {
                            if (libro.getIdsAutores().contains(todosAutores.get(i).getIdAutor())) {
                                indices.add(i);
                            }
                        }
                        int[] selectedIndices = indices.stream().mapToInt(Integer::intValue).toArray();
                        lstAutores.setSelectedIndices(selectedIndices);
                    }
                }
            } else if ("Revista".equals(tipoMaterial)) {
                // Cargar datos específicos de la revista
                Revista revista = revistaDB.select(idMaterial);
                if (revista != null) {
                    txtVolumen.setText(revista.getVolumen() != null ? revista.getVolumen() : "");
                    txtNumero.setText(revista.getNumero() != null ? revista.getNumero() : "");
                    if (revista.getFechaPublicacion() != null) {
                        txtFechaPublicacion.setText(revista.getFechaPublicacion().toString());
                    }
                }
            } else if ("Audiovisual".equals(tipoMaterial)) {
                // Cargar datos específicos del audiovisual
                Audiovisual audiovisual = audiovisualDB.select(idMaterial);
                if (audiovisual != null) {
                    txtFormato.setText(audiovisual.getFormato() != null ? audiovisual.getFormato() : "");
                    txtDuracion.setText(String.valueOf(audiovisual.getDuracion()));
                }
            } else if ("Otro".equals(tipoMaterial)) {
                // Cargar datos específicos del otro documento
                OtroDocumento otro = otroDocumentoDB.select(idMaterial);
                if (otro != null) {
                    txtDescripcion.setText(otro.getDescripcion() != null ? otro.getDescripcion() : "");
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando datos específicos del material: " + e.getMessage());
        }
    }

    private void btnLimpiarEditorialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarEditorialActionPerformed
        limpiarFormularioEditorial();
    }// GEN-LAST:event_btnLimpiarEditorialActionPerformed

    private void txtPaisEditorialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPaisEditorialActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtPaisEditorialActionPerformed

    private void btnGuardarEditorialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarEditorialActionPerformed
        String nombre = txtNombreEditorial.getText().trim();
        String pais = txtPaisEditorial.getText().trim();
        
        if (nombre.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "El nombre de la editorial es obligatorio", 
                "Error de validación", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (pais.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "El país de la editorial es obligatorio", 
                "Error de validación", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (btnGuardarEditorial.getText().equals("Guardar")) {
            Editorial editorial = new Editorial(nombre, pais);
            int resultado = editorialDB.insert(editorial);
            
            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Editorial guardada correctamente", 
                    "Éxito", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioEditorial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error al guardar la editorial", 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Editorial editorial = new Editorial(idEditorialSeleccionado, nombre, pais);
            int resultado = editorialDB.update(editorial);
            
            if (resultado > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Editorial actualizada correctamente", 
                    "Éxito", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioEditorial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error al actualizar la editorial", 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }// GEN-LAST:event_btnGuardarEditorialActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Administracion;
    private javax.swing.JTable Tblmora;
    private javax.swing.JButton btmLimpiarAutor;
    private javax.swing.JToggleButton btnCambiarContra;
    private javax.swing.JToggleButton btnAbonarMoraEnMora;
    private javax.swing.JButton btnConsultamora;
    private javax.swing.JButton btnDenegarPrestamo;
    private javax.swing.JButton btnEliminarAutor;
    private javax.swing.JButton btnEliminarEditorial;
    private javax.swing.JButton btnEliminarTarifa;
    private javax.swing.JButton btnEliminarmat;
    private javax.swing.JButton btnFiltrarPrestamo;
    private javax.swing.JButton btnFiltrarPrestamoUsuario;
    private javax.swing.JButton btnGuardarAutor;
    private javax.swing.JButton btnGuardarEditorial;
    private javax.swing.JButton btnGuardarTarifa;
    private javax.swing.JButton btnGuardarmat;
    private javax.swing.JButton btnLimpiarEditorial;
    private javax.swing.JButton btnLimpiarFormularioPrestamo;
    private javax.swing.JButton btnLimpiarFormularioPrestamoUsuario;
    private javax.swing.JButton btnLimpiarFormularioTarifas;
    private javax.swing.JButton btnPrestarDevolverPrestamo;
    private javax.swing.JButton btnSolicitarPrestamoUsuario;
    private javax.swing.JButton btneliminarUser;
    private javax.swing.JButton btnguardarUser;
    private javax.swing.JButton btnlimpiarUser;
    private javax.swing.JButton btnlimpiarmaterial;
    private javax.swing.JButton btnnuevouser;
    private javax.swing.JComboBox<String> cbxEditorial;
    private javax.swing.JComboBox<String> cbxEstadoMaterialPrestamo;
    private javax.swing.JComboBox<String> cbxTipoMaterialPrestamo;
    private javax.swing.JComboBox<String> cbxTipoMaterialPrestamoUsuario;
    private javax.swing.JComboBox<String> cbxTipoUsuarioTarifas;
    private javax.swing.JComboBox<String> cbxtipomaterial;
    private javax.swing.JComboBox<String> cbxuser;
    private javax.swing.JCheckBox checkConMora;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLbCantDispmat;
    private javax.swing.JLabel jLbNombreUsuario;
    private javax.swing.JLabel jLbTipouser;
    private javax.swing.JLabel jLbUbicmaterial;
    private javax.swing.JLabel jLbcanttotal;
    private javax.swing.JLabel jLbcorreouser;
    private javax.swing.JLabel jLbidUsermora;
    private javax.swing.JLabel jLbiduser;
    private javax.swing.JLabel jLbnombremora;
    private javax.swing.JLabel jLbnombreuser;
    private javax.swing.JLabel jLbtipomaterial;
    private javax.swing.JLabel jLbtitulomaterial;
    private javax.swing.JPanel jPanelPrestamoUsuario;
    private javax.swing.JPanel jPanelmora;
    private javax.swing.JPanel jPanelprestamo;
    private javax.swing.JPanel jPaneluser;
    private javax.swing.JPanel jPmaterial;
    private javax.swing.JPanel jPmora;
    private javax.swing.JPanel jPprestamos;
    private javax.swing.JPanel jPusuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPaneAutores;
    private javax.swing.JScrollPane jScrollPaneDescripcion;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTfBibliotecaamigosDonBosco;
    private javax.swing.JTextField jTfCantdispmat;
    private javax.swing.JTextField jTfCanttotal;
    private javax.swing.JTextField jTfUbimaterial;
    private javax.swing.JTextField jTfcorreouser;
    private javax.swing.JTextField jTfiduser;
    private javax.swing.JTextField jTfidusermora;
    private javax.swing.JTextField jTfnombremora;
    private javax.swing.JTextField jTfnombreuser;
    private javax.swing.JTextField jTfpassworduser;
    private javax.swing.JTextField jTftitulomaterial;
    private javax.swing.JScrollPane jcpTablaAutoresInterno;
    private javax.swing.JScrollPane jcpTablaEditorial;
    private javax.swing.JScrollPane jcpTablaMaterial;
    private javax.swing.JScrollPane jcpTablaTarifas;
    private javax.swing.JLabel jlbpassworduser;
    private javax.swing.JPanel jpAutores;
    private javax.swing.JPanel jpAutoresInterno;
    private javax.swing.JPanel jpEditorial;
    private javax.swing.JPanel jpEditorialinterno;
    private javax.swing.JPanel jpMaterial;
    private javax.swing.JPanel jpPrestamosUsuario;
    private javax.swing.JPanel jpTarifas;
    private javax.swing.JPanel jpTarifasInterno;
    private javax.swing.JScrollPane jspPrestamoUsuarioTabla;
    private javax.swing.JScrollPane jspTablaPrestamosUsuario;
    private javax.swing.JTabbedPane jtbprestamos;
    private javax.swing.JLabel lblAccionesPrestamo;
    private javax.swing.JLabel lblAnioAplicable;
    private javax.swing.JLabel lblApellidosAutor;
    private javax.swing.JLabel lblAutores;
    private javax.swing.JLabel lblCantDaniados;
    private javax.swing.JLabel lblCantPrestados;
    private javax.swing.JLabel lblCorreoEnPrestamo;
    private javax.swing.JLabel lblDetallesPrestamo;
    private javax.swing.JLabel lblDetallesPrestamoUsuario;
    private javax.swing.JLabel lblDiasDeRetradoPrestamo;
    private javax.swing.JLabel lblErrorPrestamosUsuario;
    private javax.swing.JLabel lblEstadoPrestamo;
    private javax.swing.JLabel lblFechaDevolucion;
    private javax.swing.JLabel lblFechaPrestamo;
    private javax.swing.JLabel lblFechaPublicacion;
    private javax.swing.JLabel lblFiltrosPrestamo;
    private javax.swing.JLabel lblFiltrosPrestamoUsuario;
    private javax.swing.JLabel lblFormato;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblMisPrestamosUsuario;
    private javax.swing.JLabel lblMoraActualPrestamo;
    private javax.swing.JLabel lblMoraAplicable;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDiasDeRetrasoMora;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEditorial;
    private javax.swing.JLabel lblFechaDePrestamoMora;
    private javax.swing.JLabel lblFechaDevolucionMora;
    private javax.swing.JLabel lblMoraActualEnMora;
    private javax.swing.JLabel lblNombreAutor;
    private javax.swing.JLabel lblNombreEditorial;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNombreUsuarioEnPrestamo;
    private javax.swing.JLabel lblPaisAutor;
    private javax.swing.JLabel lblPaisEditorial;
    private javax.swing.JLabel lblPrestamoAdminMora;
    private javax.swing.JLabel lblTarifa;
    private javax.swing.JLabel lblTipoMaterialEnPrestamo;
    private javax.swing.JLabel lblTipoMaterialEnPrestamoUsuario;
    private javax.swing.JLabel lblTipoMaterialPrestamo;
    private javax.swing.JLabel lblTipoMaterialPrestamoUsuario;
    private javax.swing.JLabel lblTituloMaterialEnPrestamo;
    private javax.swing.JLabel lblTituloMaterialEnPrestamoUsuario;
    private javax.swing.JLabel lblTituloMaterialPrestamoUsuarioFiltro;
    private javax.swing.JLabel lblUbicacionMaterialEnPrestamo;
    private javax.swing.JLabel lblVolumen;
    private javax.swing.JLabel lblcbxEstadoPrestamo;
    private javax.swing.JLabel lnlTipoUsuarioTarifa;
    private javax.swing.JList<String> lstAutores;
    private javax.swing.JTable tblAutor;
    private javax.swing.JTable tblEditorial;
    private javax.swing.JTable tblMaterialesPrestamoUsuario;
    private javax.swing.JTable tblPrestamo;
    private javax.swing.JTable tblPrestamosUsuario;
    private javax.swing.JTable tblTarifas;
    private javax.swing.JTable tblmaterial;
    private javax.swing.JTable tbluser;
    private javax.swing.JTextField txtAnioAplicable;
    private javax.swing.JTextField txtApellidosAutor;
    private javax.swing.JTextField txtCantDaniados;
    private javax.swing.JTextField txtCantPrestados;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDiasDeRetrasoMora;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtFechaDevolucionEnMora;
    private javax.swing.JTextField txtFechaPrestamoEnMora;
    private javax.swing.JTextField txtFechaPublicacion;
    private javax.swing.JTextField txtFormato;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtMoraActualEnMora;
    private javax.swing.JTextField txtCorreoEnPrestamo;
    private javax.swing.JTextField txtDiasRetrasoPrestamo;
    private javax.swing.JTextField txtEstadoPrestamo;
    private javax.swing.JTextField txtFechaDevolucionPrestamo;
    private javax.swing.JTextField txtFechaPrestamo;
    private javax.swing.JTextField txtMoraActualPrestamo;
    private javax.swing.JTextField txtMoraAplicablePrestamo;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtNombreEditorial;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNombreUsuarioEnPrestamo;
    private javax.swing.JTextField txtPaisAutor;
    private javax.swing.JTextField txtPaisEditorial;
    private javax.swing.JTextField txtTarifa;
    private javax.swing.JTextField txtTipoMaterialEnPrestamo;
    private javax.swing.JTextField txtTipoMaterialEnPrestamoUsuario;
    private javax.swing.JTextField txtTituloFiltroPrestamoUsuario;
    private javax.swing.JTextField txtTituloMaterialEnPrestamo;
    private javax.swing.JTextField txtTituloMaterialEnPrestamoUsuario;
    private javax.swing.JTextField txtUbicacionEnPrestamo;
    private javax.swing.JTextField txtVolumen;
    private javax.swing.JTextField jTfbusqumora;
    private javax.swing.JLabel jlbBusqumora;
    // End of variables declaration//GEN-END:variables
}
