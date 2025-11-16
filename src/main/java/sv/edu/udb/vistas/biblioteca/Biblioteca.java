package sv.edu.udb.vistas.biblioteca;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import sv.edu.udb.Datos.*;
import sv.edu.udb.clases.*;
import sv.edu.udb.clases.hijas.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    private int idEditorialSeleccionado = 0;
    private int idMaterialSeleccionado = 0;
    private int idUsuarioSeleccionado = 0;
    private int idAutorSeleccionado = 0;
    private int idPrestamoSeleccionado = 0;

    public Biblioteca() {
        editorialDB = new EditorialDB();
        UsuariosDB = new UsuariosDB();
        materialesDB = new MaterialesDB();
        autorDB = new AutorDB();
        libroDB = new LibroDB();
        revistaDB = new RevistaDB();
        audiovisualDB = new AudioVisualDB();
        otroDocumentoDB = new OtroDocumentoDB();
        prestamosDB = new PrestamosDB();
        initComponents();
        cbxtipomaterial.setSelectedIndex(-1);
        ocultarCamposEspecificosMaterial();
        actualizarCamposPorTipoMaterial();
        cargarComboBoxEditoriales();
        cargarListaAutores();
        actualizarTablaUsuario();
        jpEditorial.setVisible(false);
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

    private void actualizarTablaUsuario() {
        tbluser.setModel(UsuariosDB.selectUsuarios());
    }

    private void limpiarFormularioUsuario() {
        jTfiduser.setText("");
        jTfnombreuser.setText("");
        jTfcorreouser.setText("");
        jTfpassworduser.setText("");
        btnguardarUser.setText("Guardar");
        btneliminarUser.setEnabled(false);
        idUsuarioSeleccionado = 0;
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
        jTfBusquedauser = new javax.swing.JTextField();
        JlbBusquedaUser = new javax.swing.JLabel();
        btnEdituser = new javax.swing.JButton();
        javax.swing.JComboBox<String> Tpuser = new javax.swing.JComboBox<>();
        btnnuevouser = new javax.swing.JButton();
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
        jTfBibliotecaamigosDonBosco = new javax.swing.JTextField();
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
                .addGroup(jpEditorialinternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpEditorialinternoLayout.createSequentialGroup()
                        .addComponent(btnEliminarEditorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarEditorial))
                    .addComponent(txtNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaisEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(543, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jTfBusquedauser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfBusquedauserActionPerformed(evt);
            }
        });

        JlbBusquedaUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JlbBusquedaUser.setText("Busqueda ");

        btnEdituser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdituser.setText("Editar");

        Tpuser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Tpuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TpuserActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                        .addComponent(jLbiduser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jTfiduser))
                    .addGroup(jPusuariosLayout.createSequentialGroup()
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbTipouser)
                            .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlbpassworduser, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLbcorreouser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfcorreouser)
                            .addGroup(jPusuariosLayout.createSequentialGroup()
                                .addComponent(Tpuser, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 172, Short.MAX_VALUE))
                            .addComponent(jTfpassworduser, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnguardarUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnnuevouser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEdituser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btneliminarUser, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(jPusuariosLayout.createSequentialGroup()
                        .addComponent(JlbBusquedaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jTfBusquedauser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPusuariosLayout.createSequentialGroup()
                        .addComponent(jLbnombreuser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jTfnombreuser)))
                .addGap(18, 18, 18))
        );
        jPusuariosLayout.setVerticalGroup(
            jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPusuariosLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JlbBusquedaUser)
                    .addComponent(jTfBusquedauser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbiduser)
                    .addComponent(jTfiduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbnombreuser)
                    .addComponent(jTfnombreuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbTipouser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tpuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbcorreouser)
                    .addComponent(jTfcorreouser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbpassworduser)
                    .addComponent(jTfpassworduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdituser)
                    .addComponent(btnnuevouser))
                .addGap(18, 18, 18)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardarUser)
                    .addComponent(btneliminarUser))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPaneluserLayout = new javax.swing.GroupLayout(jPaneluser);
        jPaneluser.setLayout(jPaneluserLayout);
        jPaneluserLayout.setHorizontalGroup(
            jPaneluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneluserLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPaneluserLayout.setVerticalGroup(
            jPaneluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneluserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPusuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Usuarios", jPaneluser);

        Tblmora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Prestamo", "Dias Mora", "Monto Mora"
            }
        ));
        jScrollPane1.setViewportView(Tblmora);

        jPmora.setBackground(new java.awt.Color(0, 102, 204));

        btnConsultamora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConsultamora.setText("Consultar");

        jTfidusermora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfidusermoraActionPerformed(evt);
            }
        });

        jLbidUsermora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbidUsermora.setText("ID Usuario");

        jLbnombremora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbnombremora.setText("Nombre ");

        jTfbusqumora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfbusqumoraActionPerformed(evt);
            }
        });

        jlbBusqumora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbBusqumora.setText("Busqueda ");

        javax.swing.GroupLayout jPmoraLayout = new javax.swing.GroupLayout(jPmora);
        jPmora.setLayout(jPmoraLayout);
        jPmoraLayout.setHorizontalGroup(
            jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmoraLayout.createSequentialGroup()
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPmoraLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmoraLayout.createSequentialGroup()
                                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbBusqumora, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbidUsermora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71))
                            .addGroup(jPmoraLayout.createSequentialGroup()
                                .addComponent(jLbnombremora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)))
                        .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfnombremora, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfidusermora, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfbusqumora, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPmoraLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(btnConsultamora)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPmoraLayout.setVerticalGroup(
            jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmoraLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbBusqumora)
                    .addComponent(jTfbusqumora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbidUsermora)
                    .addComponent(jTfidusermora, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPmoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbnombremora)
                    .addComponent(jTfnombremora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(btnConsultamora)
                .addGap(23, 23, 23))
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
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Mora ", jPanelmora);

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
                .addGap(26, 26, 26)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblNombreUsuarioEnPrestamo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreUsuarioEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblCorreoEnPrestamo)
                                .addGap(18, 18, 18)
                                .addComponent(txtCorreoEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDetallesPrestamo))
                        .addGap(18, 18, 18)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblUbicacionMaterialEnPrestamo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUbicacionEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPprestamosLayout.createSequentialGroup()
                                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPprestamosLayout.createSequentialGroup()
                                        .addComponent(lblTituloMaterialEnPrestamo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTituloMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPprestamosLayout.createSequentialGroup()
                                        .addComponent(lblTipoMaterialEnPrestamo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTipoMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32))))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addComponent(lblFiltrosPrestamo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblTipoMaterialPrestamo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxTipoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addComponent(lblcbxEstadoPrestamo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkConMora)
                                    .addComponent(cbxEstadoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(35, 35, 35)
                        .addComponent(btnFiltrarPrestamo)
                        .addContainerGap())))
            .addGroup(jPprestamosLayout.createSequentialGroup()
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrestarDevolverPrestamo)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAccionesPrestamo)
                                    .addComponent(lblFechaPrestamo))
                                .addGap(29, 29, 29))))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFechaDevolucion))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMoraAplicable)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addComponent(btnDenegarPrestamo)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarFormularioPrestamo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(txtFechaDevolucionPrestamo)
                            .addComponent(txtMoraAplicablePrestamo))
                        .addGap(18, 18, 18)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiasDeRetradoPrestamo)
                            .addComponent(lblMoraActualPrestamo)
                            .addComponent(lblEstadoPrestamo))
                        .addGap(18, 18, 18)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstadoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMoraActualPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiasRetrasoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))))
        );
        jPprestamosLayout.setVerticalGroup(
            jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPprestamosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblFiltrosPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMaterialPrestamo)
                    .addComponent(cbxTipoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnFiltrarPrestamo))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcbxEstadoPrestamo)
                            .addComponent(cbxEstadoMaterialPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkConMora)))
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTituloMaterialEnPrestamo)
                            .addComponent(txtTituloMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPprestamosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDetallesPrestamo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMaterialEnPrestamo)
                    .addComponent(txtTipoMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreoEnPrestamo)
                    .addComponent(txtCorreoEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUbicacionMaterialEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreUsuarioEnPrestamo)
                        .addComponent(txtNombreUsuarioEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtUbicacionEnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(lblAccionesPrestamo)
                .addGap(18, 18, 18)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaPrestamo)
                    .addComponent(lblDiasDeRetradoPrestamo)
                    .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasRetrasoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMoraActualPrestamo)
                    .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFechaDevolucion)
                        .addComponent(txtFechaDevolucionPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMoraActualPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoPrestamo)
                    .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMoraAplicable)
                        .addComponent(txtMoraAplicablePrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEstadoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrestarDevolverPrestamo)
                    .addComponent(btnDenegarPrestamo)
                    .addComponent(btnLimpiarFormularioPrestamo))
                .addGap(36, 36, 36))
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
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanelprestamoLayout.setVerticalGroup(
            jPanelprestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelprestamoLayout.createSequentialGroup()
                .addGroup(jPanelprestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelprestamoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 459, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbprestamos.addTab("Prestamos", jPanelprestamo);

        jTfBibliotecaamigosDonBosco.setEditable(false);
        jTfBibliotecaamigosDonBosco.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jTfBibliotecaamigosDonBosco.setText("Bliblioteca Amigos Don Bosco");
        jTfBibliotecaamigosDonBosco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfBibliotecaamigosDonBoscoActionPerformed(evt);
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
                        .addComponent(jTfBibliotecaamigosDonBosco, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBibliotecaamigosDonBosco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtbprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnEliminarEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEditorialActionPerformed
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

    private void btnFiltrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPrestamoActionPerformed
    
    }//GEN-LAST:event_btnFiltrarPrestamoActionPerformed

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
        
        // Confirmar la denegación del préstamo
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea denegar este préstamo?", 
            "Confirmar Denegación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (confirmacion != JOptionPane.YES_OPTION) {
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
            
            // Verificar que el préstamo esté en estado Pendiente
            if (prestamo.getEstado() != Prestamo.Estado.Pendiente) {
                JOptionPane.showMessageDialog(this, 
                    "Solo se pueden denegar préstamos en estado Pendiente", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Cambiar el estado a Denegado
            prestamo.setEstado(Prestamo.Estado.Denegado);
            
            // Actualizar en la base de datos
            if (prestamosDB.update(prestamo)) {
                JOptionPane.showMessageDialog(this, "Préstamo denegado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioPrestamo();
            } else {
                JOptionPane.showMessageDialog(this, "Error al denegar el préstamo", 
                    "Error", JOptionPane.ERROR_MESSAGE);
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
        } else if (prestamo.getEstado() == Prestamo.Estado.Devuelto && prestamo.getMoraTotal() != null) {
            // Si ya está devuelto, mostrar la mora total que se cobró
            moraActual = prestamo.getMoraTotal();
            
            // Calcular días de retraso basado en la mora total y tarifa diaria
            if (mora != null && mora.getTarifaDiaria().compareTo(java.math.BigDecimal.ZERO) > 0) {
                diasRetraso = moraActual.divide(mora.getTarifaDiaria(), 0, java.math.RoundingMode.DOWN).intValue();
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
                btnDenegarPrestamo.setEnabled(true);
                txtFechaDevolucionPrestamo.setEditable(true);
                break;
                
            case En_Curso:
                btnPrestarDevolverPrestamo.setText("Devolver");
                btnPrestarDevolverPrestamo.setEnabled(true);
                btnDenegarPrestamo.setEnabled(false);
                txtFechaDevolucionPrestamo.setEditable(false);
                break;
                
            case Devuelto:
            case Denegado:
                btnPrestarDevolverPrestamo.setEnabled(false);
                btnDenegarPrestamo.setEnabled(false);
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
        if (tblPrestamo.getColumnModel().getColumnCount() > 0) {
            tblPrestamo.getColumnModel().getColumn(5).setHeaderValue("Fecha de Devolución");
            tblPrestamo.getColumnModel().getColumn(6).setHeaderValue("Estado");
        }
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
            }
        }
    }// GEN-LAST:event_tblPrestamoMouseClicked

    private void jTfbusqumoraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfbusqumoraActionPerformed

    }// GEN-LAST:event_jTfbusqumoraActionPerformed

    private void jTfidusermoraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfidusermoraActionPerformed

    }// GEN-LAST:event_jTfidusermoraActionPerformed

    private void TpuserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_TpuserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_TpuserActionPerformed

    private void jTfBusquedauserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfBusquedauserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTfBusquedauserActionPerformed

    private void jTfiduserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfiduserActionPerformed

    }// GEN-LAST:event_jTfiduserActionPerformed

    private void btnguardarUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnguardarUserActionPerformed

    }// GEN-LAST:event_btnguardarUserActionPerformed

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

    // BOTON EDITAR
    private void btnEdituserActionPerformed(java.awt.event.ActionEvent evt) {

        String id = jTfiduser.getText().trim();
        if (id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Debe ingresar o buscar un usuario antes de editar.",
                    "Aviso",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = jTfnombreuser.getText().trim();
        String correo = jTfcorreouser.getText().trim();
        String password = jTfpassworduser.getText().trim();

        String tipo = "";
        for (java.awt.Component c : jPusuarios.getComponents()) {
            if (c instanceof javax.swing.JComboBox) {
                Object sel = ((javax.swing.JComboBox<?>) c).getSelectedItem();
                tipo = sel == null ? "" : sel.toString();
                break;
            }
        }

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tbluser.getModel();

        boolean encontrado = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            Object existingID = model.getValueAt(i, 0);
            if (existingID != null && id.equalsIgnoreCase(existingID.toString())) {

                model.setValueAt(nombre, i, 1);
                model.setValueAt(tipo, i, 2);
                model.setValueAt(correo, i, 3);
                if (model.getColumnCount() > 4) {
                    model.setValueAt(password, i, 4);
                }

                tbluser.setRowSelectionInterval(i, i);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    " Usuario actualizado correctamente.",
                    "Éxito",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

            jTfiduser.setText("");
            jTfnombreuser.setText("");
            jTfcorreouser.setText("");
            jTfpassworduser.setText("");

            for (java.awt.Component c : jPusuarios.getComponents()) {
                if (c instanceof javax.swing.JComboBox) {
                    ((javax.swing.JComboBox<?>) c).setSelectedIndex(0);
                    break;
                }
            }

            jTfiduser.setEnabled(true);

        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se encontró ningún usuario con ese ID para editar.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } // BOTON DE REGISTRAR PRESTAMO

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Administracion;
    private javax.swing.JLabel JlbBusquedaUser;
    private javax.swing.JTable Tblmora;
    private javax.swing.JButton btmLimpiarAutor;
    private javax.swing.JButton btnConsultamora;
    private javax.swing.JButton btnDenegarPrestamo;
    private javax.swing.JButton btnEdituser;
    private javax.swing.JButton btnEliminarAutor;
    private javax.swing.JButton btnEliminarEditorial;
    private javax.swing.JButton btnEliminarmat;
    private javax.swing.JButton btnFiltrarPrestamo;
    private javax.swing.JButton btnGuardarAutor;
    private javax.swing.JButton btnGuardarEditorial;
    private javax.swing.JButton btnGuardarmat;
    private javax.swing.JButton btnLimpiarEditorial;
    private javax.swing.JButton btnLimpiarFormularioPrestamo;
    private javax.swing.JButton btnPrestarDevolverPrestamo;
    private javax.swing.JButton btneliminarUser;
    private javax.swing.JButton btnguardarUser;
    private javax.swing.JButton btnlimpiarmaterial;
    private javax.swing.JButton btnnuevouser;
    private javax.swing.JComboBox<String> cbxEditorial;
    private javax.swing.JComboBox<String> cbxEstadoMaterialPrestamo;
    private javax.swing.JComboBox<String> cbxTipoMaterialPrestamo;
    private javax.swing.JComboBox<String> cbxtipomaterial;
    private javax.swing.JCheckBox checkConMora;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLbCantDispmat;
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
    private javax.swing.JTextField jTfBusquedauser;
    private javax.swing.JTextField jTfCantdispmat;
    private javax.swing.JTextField jTfCanttotal;
    private javax.swing.JTextField jTfUbimaterial;
    private javax.swing.JTextField jTfbusqumora;
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
    private javax.swing.JLabel jlbBusqumora;
    private javax.swing.JLabel jlbpassworduser;
    private javax.swing.JPanel jpAutores;
    private javax.swing.JPanel jpAutoresInterno;
    private javax.swing.JPanel jpEditorial;
    private javax.swing.JPanel jpEditorialinterno;
    private javax.swing.JPanel jpMaterial;
    private javax.swing.JTabbedPane jtbprestamos;
    private javax.swing.JLabel lblAccionesPrestamo;
    private javax.swing.JLabel lblApellidosAutor;
    private javax.swing.JLabel lblAutores;
    private javax.swing.JLabel lblCantDaniados;
    private javax.swing.JLabel lblCantPrestados;
    private javax.swing.JLabel lblCorreoEnPrestamo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDetallesPrestamo;
    private javax.swing.JLabel lblDiasDeRetradoPrestamo;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEditorial;
    private javax.swing.JLabel lblEstadoPrestamo;
    private javax.swing.JLabel lblFechaDevolucion;
    private javax.swing.JLabel lblFechaPrestamo;
    private javax.swing.JLabel lblFechaPublicacion;
    private javax.swing.JLabel lblFiltrosPrestamo;
    private javax.swing.JLabel lblFormato;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblMoraActualPrestamo;
    private javax.swing.JLabel lblMoraAplicable;
    private javax.swing.JLabel lblNombreAutor;
    private javax.swing.JLabel lblNombreEditorial;
    private javax.swing.JLabel lblNombreUsuarioEnPrestamo;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPaisAutor;
    private javax.swing.JLabel lblPaisEditorial;
    private javax.swing.JLabel lblTipoMaterialEnPrestamo;
    private javax.swing.JLabel lblTipoMaterialPrestamo;
    private javax.swing.JLabel lblTituloMaterialEnPrestamo;
    private javax.swing.JLabel lblUbicacionMaterialEnPrestamo;
    private javax.swing.JLabel lblVolumen;
    private javax.swing.JLabel lblcbxEstadoPrestamo;
    private javax.swing.JList<String> lstAutores;
    private javax.swing.JTable tblAutor;
    private javax.swing.JTable tblEditorial;
    private javax.swing.JTable tblPrestamo;
    private javax.swing.JTable tblmaterial;
    private javax.swing.JTable tbluser;
    private javax.swing.JTextField txtApellidosAutor;
    private javax.swing.JTextField txtCantDaniados;
    private javax.swing.JTextField txtCantPrestados;
    private javax.swing.JTextField txtCorreoEnPrestamo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDiasRetrasoPrestamo;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtEstadoPrestamo;
    private javax.swing.JTextField txtFechaDevolucionPrestamo;
    private javax.swing.JTextField txtFechaPrestamo;
    private javax.swing.JTextField txtFechaPublicacion;
    private javax.swing.JTextField txtFormato;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtMoraActualPrestamo;
    private javax.swing.JTextField txtMoraAplicablePrestamo;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtNombreEditorial;
    private javax.swing.JTextField txtNombreUsuarioEnPrestamo;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPaisAutor;
    private javax.swing.JTextField txtPaisEditorial;
    private javax.swing.JTextField txtTipoMaterialEnPrestamo;
    private javax.swing.JTextField txtTituloMaterialEnPrestamo;
    private javax.swing.JTextField txtUbicacionEnPrestamo;
    private javax.swing.JTextField txtVolumen;
    // End of variables declaration//GEN-END:variables
}
