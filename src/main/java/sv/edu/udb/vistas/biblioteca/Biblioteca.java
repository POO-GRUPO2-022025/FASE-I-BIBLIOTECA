package sv.edu.udb.vistas.biblioteca;

import java.awt.event.ActionEvent;
import sv.edu.udb.Datos.EditorialDB;
import sv.edu.udb.Datos.UsuariosDB;
import sv.edu.udb.clases.*;
import sv.edu.udb.Datos.MaterialesDB;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.Datos.AutorDB;

public class Biblioteca extends javax.swing.JFrame {
    EditorialDB editorialDB = null;
    MaterialesDB materialesDB = null;
    UsuariosDB UsuariosDB = null;
    AutorDB autorDB = null;

    private int idEditorialSeleccionado = 0;
    private int idMaterialSeleccionado = 0;
    private int idUsuariosseleccionado = 0;
    private int idAutorSeleccionado = 0;

    public Biblioteca() {
        editorialDB = new EditorialDB();
        UsuariosDB = new UsuariosDB();
        materialesDB = new MaterialesDB();
        autorDB = new AutorDB();
        initComponents();
        actualizarTablaUsuario();
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
    }

    private void limpiarFormularioAutor() {
        txtNombreAutor.setText("");
        txtApellidosAutor.setText("");
        txtPaisAutor.setText("");
        btnGuardarAutor.setText("Guardar");
        btnEliminarAutor.setEnabled(false);
        idAutorSeleccionado = 0;
        actualizarTablaAutor();
    }

    private void limpiarFormularioMaterial() {
        cbxtipomaterial.setSelectedIndex(0);
        jTftitulomaterial.setText("");
        jTfUbimaterial.setText("");
        jTfCanttotal.setText("");
        jTfCantdispmat.setText("");
        txtCantDaniados.setText("");
        txtCantPrestados.setText("");
        btnGuardarmat.setText("Guardar");
        btnEliminarmat.setEnabled(false);
        idMaterialSeleccionado = 0;

        actualizarTablaMateriales();
    }

    private void actualizarTablaMateriales() {
        tblmaterial.setModel(materialesDB.selectMateriales());
    }

    private void actualizarTablaUsuario() {
        tbluser.setModel(UsuariosDB.selectUsuarios());
    }

     private void limpiarFormularioUsuarios() {
        tpUser.setSelectedIndex(0);
        jTfiduser.setText("");
        jTfnombreuser.setText("");
        jTfcorreouser.setText("");
        jTfpassworduser.setText("");
        btnguardarUser.setText("Guardar");
        btneliminarUser.setEnabled(false);
        idUsuariosseleccionado = 0;

        actualizarTablaUsuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
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
        tpUser = new javax.swing.JComboBox<>();
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
        jLbmaterprestam = new javax.swing.JLabel();
        jLbtituloprestamo = new javax.swing.JLabel();
        jLbautorprestamo = new javax.swing.JLabel();
        jLbAnioPubprestamo = new javax.swing.JLabel();
        jTfcatprestamo = new javax.swing.JTextField();
        jTftituloprestamo = new javax.swing.JTextField();
        jTfAutorprestamo = new javax.swing.JTextField();
        jTfAniopubprestamo = new javax.swing.JTextField();
        btnregdev = new javax.swing.JButton();
        btnregprest = new javax.swing.JButton();
        JlbBusquedaUser1 = new javax.swing.JLabel();
        Txtbusqpres = new javax.swing.JTextField();
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

        lblCantPrestados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCantPrestados.setText("Cantidad Prestada");

        lblCantDaniados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCantDaniados.setText("Cantidad Dañados");

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbCantDispmat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbUbicmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbtitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbcanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLbtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPmaterialLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jTfCantdispmat, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterialLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTfUbimaterial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTftitulomaterial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jTfCanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(txtCantPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(jcpTablaMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
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
        btneliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarUserActionPerformed(evt);
            }
        });

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

        tpUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alumno", "Profesor", "Administrador" }));

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
                                    .addGroup(jPusuariosLayout.createSequentialGroup()
                                        .addComponent(tpUser, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTfcorreouser)
                                    .addComponent(jTfpassworduser, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                .addGap(18, 18, 18)
                .addGroup(jPusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbTipouser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(33, Short.MAX_VALUE))
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

        tblPrestamo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Categoria", "Titulo", "Autor", "Año de publicacion", "Fecha de Devolución", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLbmaterprestam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbmaterprestam.setText("Material");

        jLbtituloprestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbtituloprestamo.setText("Titulo");

        jLbautorprestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbautorprestamo.setText("Autor");

        jLbAnioPubprestamo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbAnioPubprestamo.setText("Año de publicacion");

        btnregdev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnregdev.setText("Registrar Devolucion");
        btnregdev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregdevActionPerformed(evt);
            }
        });

        btnregprest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnregprest.setText("Registrar Prestamo");

        JlbBusquedaUser1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JlbBusquedaUser1.setText("Busqueda ");

        Txtbusqpres.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Txtbusqpres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtbusqpresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPprestamosLayout = new javax.swing.GroupLayout(jPprestamos);
        jPprestamos.setLayout(jPprestamosLayout);
        jPprestamosLayout.setHorizontalGroup(
            jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPprestamosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbtituloprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPprestamosLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLbautorprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLbAnioPubprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTftituloprestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jTfAutorprestamo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTfAniopubprestamo, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPprestamosLayout.createSequentialGroup()
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbmaterprestam, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JlbBusquedaUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Txtbusqpres)
                            .addComponent(jTfcatprestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
            .addGroup(jPprestamosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnregprest)
                .addGap(32, 32, 32)
                .addComponent(btnregdev)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPprestamosLayout.setVerticalGroup(
            jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPprestamosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JlbBusquedaUser1)
                    .addComponent(Txtbusqpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLbmaterprestam)
                    .addComponent(jTfcatprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbtituloprestamo)
                    .addComponent(jTftituloprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLbautorprestamo))
                    .addGroup(jPprestamosLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jTfAutorprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfAniopubprestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbAnioPubprestamo))
                .addGap(76, 76, 76)
                .addGroup(jPprestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnregprest)
                    .addComponent(btnregdev))
                .addContainerGap(31, Short.MAX_VALUE))
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
                .addContainerGap(48, Short.MAX_VALUE))
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

        jtbprestamos.addTab("prestamos", jPanelprestamo);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jtbprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    }// GEN-LAST:event_btnEliminarEditorialActionPerformed

    private void tblEditorialMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblEditorialMouseClicked
        int fila = tblEditorial.rowAtPoint(evt.getPoint());
        int columna = tblEditorial.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)) {
            DefaultTableModel modelo = (DefaultTableModel) tblEditorial.getModel();
            idEditorialSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            txtNombreEditorial.setText(modelo.getValueAt(fila, 1).toString());
            txtPaisEditorial.setText(modelo.getValueAt(fila, 2).toString());
            btnGuardarEditorial.setText("Editar");
            btnEliminarEditorial.setEnabled(true);
        }
    }// GEN-LAST:event_tblEditorialMouseClicked

    private void txtPaisAutorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPaisAutorActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtPaisAutorActionPerformed

    private void btnGuardarAutorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarAutorActionPerformed
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
            Autor autor = new Autor(0, nombre, apellidos, pais);
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
    }// GEN-LAST:event_btnGuardarAutorActionPerformed

    private void btnEliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarAutorActionPerformed
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
    }// GEN-LAST:event_btnEliminarAutorActionPerformed

    private void btmLimpiarAutorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btmLimpiarAutorActionPerformed
        limpiarFormularioAutor();
    }// GEN-LAST:event_btmLimpiarAutorActionPerformed

    private void tblAutorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblAutorMouseClicked
        int fila = tblAutor.rowAtPoint(evt.getPoint());
        int columna = tblAutor.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)) {
            DefaultTableModel modelo = (DefaultTableModel) tblAutor.getModel();
            idAutorSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            txtNombreAutor.setText(modelo.getValueAt(fila, 1).toString());
            txtApellidosAutor.setText(modelo.getValueAt(fila, 2).toString());
            txtPaisAutor.setText(modelo.getValueAt(fila, 3).toString());
            btnGuardarAutor.setText("Editar");
            btnEliminarAutor.setEnabled(true);
        }
    }// GEN-LAST:event_tblAutorMouseClicked

    private void jTfCantdispmatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfCantdispmatActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTfCantdispmatActionPerformed

    private void jTfiduserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfiduserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTfiduserActionPerformed

    private void btnguardarUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnguardarUserActionPerformed

        // TRABAJAR : SI EL BOTON DICE GUARDAR ES GUARADARLO, CAPTURAR EL TIPO
        // DEMATERIAL DE CONMBOX Y CONVERTIRLO A ENUM
        Usuarios.TipoUsuario tipo = Usuarios.TipoUsuario.valueOf(tpUser.getSelectedItem().toString());
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

        if (passwordHash.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "La Contraseña es Obligatoria ",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (btnguardarUser.getText().equals("Guardar")) {
            Usuarios usuarios = new Usuarios(0, tipo, nombre, correo, passwordHash);
            Usuarios resultado = UsuariosDB.insert(usuarios);

            if (resultado != null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario guardado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioMaterial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al guardar el Usuario",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Usuarios usuario = new Usuarios(idUsuariosseleccionado, tipo, nombre, correo, passwordHash);
            boolean resultado = UsuariosDB.update(usuario);

            if (resultado) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario actualizado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioMaterial();
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
            jTfiduser.setText(modelo.getValueAt(fila,1).toString());
            jTfnombreuser.setText(modelo.getValueAt(fila,2).toString());
            jTfcorreouser.setText(modelo.getValueAt(fila,4).toString());
            jTfpassworduser.setText(modelo.getValueAt(fila,5).toString());
            jTfpassworduser.setText(""); 
            tpUser.setSelectedItem(modelo.getValueAt(fila,3).toString());
                   
             btnguardarUser.setText("Editar");
            btneliminarUser.setEnabled(true);
        }   
            // TODO add your handling code here:
    }// GEN-LAST:event_tbluserMouseClicked

    private void btneliminarUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btneliminarUserActionPerformed
        if (idUsuariosseleccionado > 0) {
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
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "Error al eliminar el usuario",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un usuario para eliminar",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            // TODO add your handling code here:
        } // GEN-LAST:event_btneliminarUserActionPerformed
    }

    private void jTfBibliotecaamigosDonBoscoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTfBibliotecaamigosDonBoscoActionPerformed

    }// GEN-LAST:event_jTfBibliotecaamigosDonBoscoActionPerformed

    private void TxtbusqpresActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_TxtbusqpresActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_TxtbusqpresActionPerformed

    private void btnregdevActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnregdevActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnregdevActionPerformed

    private void tblPrestamoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblPrestamoMouseClicked

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

    private void btnGuardarmatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarmatActionPerformed
        // TODO add your handling code here:
        // TRABAJAR : SI EL BOTON DICE GUARDAR ES GUARADARLO, CAPTURAR EL TIPO
        // DEMATERIAL DE CONMBOX Y CONVERTIRLO A ENUM
        Material.TipoMaterial tipo = Material.TipoMaterial.valueOf(cbxtipomaterial.getSelectedItem().toString());
        String titulo = jTftitulomaterial.getText();
        String ubicacion = jTfUbimaterial.getText();
        int cantidad_total = Integer.valueOf(jTfCanttotal.getText());
        int cantidad_disponible = Integer.valueOf(jTfCantdispmat.getText());
        int cantidad_prestado = Integer.valueOf(txtCantPrestados.getText());
        int cantidad_daniado = Integer.valueOf(txtCantDaniados.getText());

        if (titulo.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "El titulo del material es obligatorio",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ubicacion.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "ubicacion del material es obligatorio",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cantidad_total < 1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Cantidad total debe ser mayor a o igual a 1 ",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cantidad_disponible < 1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Cantidad disponible debe ser mayor a o igual a 1 ",
                    "Error de validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (btnGuardarmat.getText().equals("Guardar")) {
            Material material = new Material(0, tipo, titulo, ubicacion, cantidad_total, cantidad_disponible,
                    cantidad_prestado, cantidad_daniado);
            Material resultado = materialesDB.insert(material);

            if (resultado != null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Material guardado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioMaterial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al guardar el material",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Material material = new Material(idMaterialSeleccionado, tipo, titulo, ubicacion, cantidad_total,
                    cantidad_disponible, cantidad_prestado, cantidad_daniado);
            boolean resultado = materialesDB.update(material);

            if (resultado) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Material actualizado correctamente",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioMaterial();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al actualizar el material",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_btnGuardarmatActionPerformed

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
            jTftitulomaterial.setText(modelo.getValueAt(fila, 2).toString());
            jTfUbimaterial.setText(modelo.getValueAt(fila, 3).toString());
            jTfCanttotal.setText(modelo.getValueAt(fila, 4).toString());
            jTfCantdispmat.setText(modelo.getValueAt(fila, 5).toString());
            cbxtipomaterial.setSelectedItem(modelo.getValueAt(fila, 1).toString());
            btnGuardarmat.setText("Editar");
            btnEliminarmat.setEnabled(true);
        }
    }// GEN-LAST:event_tblmaterialMouseClicked

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

    private void btnregprestActionPerformed(java.awt.event.ActionEvent evt) {

        java.awt.Component parent = javax.swing.SwingUtilities.getWindowAncestor(btnregprest);

        String id = "";
        String categoria = "";
        String titulo = jTftituloprestamo.getText().trim();
        String autor = jTfAutorprestamo.getText().trim();
        String anioPub = jTfAniopubprestamo.getText().trim();
        String fechaDevolucion = "";
        String estado = "Pendiente";

        if (titulo.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(parent,
                    "Debe llenar al menos el título del préstamo.",
                    "Campos obligatorios",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblPrestamo.getModel();

        Object[] fila = new Object[] {
                id.isEmpty() ? "P" + (model.getRowCount() + 1) : id,
                categoria,
                titulo,
                autor,
                anioPub,
                fechaDevolucion,
                estado
        };

        model.insertRow(0, fila);

        javax.swing.JOptionPane.showMessageDialog(parent,
                "Préstamo registrado correctamente.");
        limpiarCamposPrestamo();
    }

    private void limpiarCamposPrestamo() {
        jTfcatprestamo.setText("");
        jTftituloprestamo.setText("");
        jTfAutorprestamo.setText("");
        jTfAniopubprestamo.setText("");
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Administracion;
    private javax.swing.JLabel JlbBusquedaUser1;
    private javax.swing.JTable Tblmora;
    private javax.swing.JTextField Txtbusqpres;
    private javax.swing.JButton btmLimpiarAutor;
    private javax.swing.JButton btnConsultamora;
    private javax.swing.JButton btnEliminarAutor;
    private javax.swing.JButton btnEliminarEditorial;
    private javax.swing.JButton btnEliminarmat;
    private javax.swing.JButton btnGuardarAutor;
    private javax.swing.JButton btnGuardarEditorial;
    private javax.swing.JButton btnGuardarmat;
    private javax.swing.JButton btnLimpiarEditorial;
    private javax.swing.JButton btneliminarUser;
    private javax.swing.JButton btnguardarUser;
    private javax.swing.JButton btnlimpiarUser;
    private javax.swing.JButton btnlimpiarmaterial;
    private javax.swing.JButton btnregdev;
    private javax.swing.JButton btnregprest;
    private javax.swing.JComboBox<String> cbxtipomaterial;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLbAnioPubprestamo;
    private javax.swing.JLabel jLbCantDispmat;
    private javax.swing.JLabel jLbTipouser;
    private javax.swing.JLabel jLbUbicmaterial;
    private javax.swing.JLabel jLbautorprestamo;
    private javax.swing.JLabel jLbcanttotal;
    private javax.swing.JLabel jLbcorreouser;
    private javax.swing.JLabel jLbidUsermora;
    private javax.swing.JLabel jLbiduser;
    private javax.swing.JLabel jLbmaterprestam;
    private javax.swing.JLabel jLbnombremora;
    private javax.swing.JLabel jLbnombreuser;
    private javax.swing.JLabel jLbtipomaterial;
    private javax.swing.JLabel jLbtitulomaterial;
    private javax.swing.JLabel jLbtituloprestamo;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTfAniopubprestamo;
    private javax.swing.JTextField jTfAutorprestamo;
    private javax.swing.JTextField jTfBibliotecaamigosDonBosco;
    private javax.swing.JTextField jTfCantdispmat;
    private javax.swing.JTextField jTfCanttotal;
    private javax.swing.JTextField jTfUbimaterial;
    private javax.swing.JTextField jTfbusqumora;
    private javax.swing.JTextField jTfcatprestamo;
    private javax.swing.JTextField jTfcorreouser;
    private javax.swing.JTextField jTfiduser;
    private javax.swing.JTextField jTfidusermora;
    private javax.swing.JTextField jTfnombremora;
    private javax.swing.JTextField jTfnombreuser;
    private javax.swing.JTextField jTfpassworduser;
    private javax.swing.JTextField jTftitulomaterial;
    private javax.swing.JTextField jTftituloprestamo;
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
    private javax.swing.JLabel lblApellidosAutor;
    private javax.swing.JLabel lblCantDaniados;
    private javax.swing.JLabel lblCantPrestados;
    private javax.swing.JLabel lblNombreAutor;
    private javax.swing.JLabel lblNombreEditorial;
    private javax.swing.JLabel lblPaisAutor;
    private javax.swing.JLabel lblPaisEditorial;
    private javax.swing.JTable tblAutor;
    private javax.swing.JTable tblEditorial;
    private javax.swing.JTable tblPrestamo;
    private javax.swing.JTable tblmaterial;
    private javax.swing.JTable tbluser;
    private javax.swing.JTextField txtApellidosAutor;
    private javax.swing.JTextField txtCantDaniados;
    private javax.swing.JTextField txtCantPrestados;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtNombreEditorial;
    private javax.swing.JTextField txtPaisAutor;
    private javax.swing.JTextField txtPaisEditorial;
    private javax.swing.JComboBox<String> tpUser;
    // End of variables declaration//GEN-END:variables
}
