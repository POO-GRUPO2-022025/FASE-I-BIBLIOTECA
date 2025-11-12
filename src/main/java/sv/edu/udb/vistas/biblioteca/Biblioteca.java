package sv.edu.udb.vistas.biblioteca;

import sv.edu.udb.Datos.EditorialDB;
import sv.edu.udb.clases.Editorial;
import javax.swing.table.DefaultTableModel;


public class Biblioteca extends javax.swing.JFrame {
    EditorialDB editorialDB = null;
    private int idEditorialSeleccionado = 0;
    
    public Biblioteca() {
        editorialDB = new EditorialDB();
        initComponents();
    }

    private void actualizarTablaEditorial() {
        tblEditorial.setModel(editorialDB.selectEditoriales());
    }

    private void limpiarFormularioEditorial() {
        txtNombreEditorial.setText("");
        txtPaisEditorial.setText("");
        btnGuardarEditorial.setText("Guardar");
        btnEliminarEditorial.setEnabled(false);
        idEditorialSeleccionado = 0;
        actualizarTablaEditorial();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jtbprestamos = new javax.swing.JTabbedPane();
        jpinicio = new javax.swing.JPanel();
        jpEditorial = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEditorial = new javax.swing.JTable();
        jPmaterial1 = new javax.swing.JPanel();
        btnConsultarmat1 = new javax.swing.JButton();
        txtNombreEditorial = new javax.swing.JTextField();
        txtPaisEditorial = new javax.swing.JTextField();
        jLbtipomaterial1 = new javax.swing.JLabel();
        jLbtitulomaterial1 = new javax.swing.JLabel();
        btnGuardarEditorial = new javax.swing.JButton();
        btnEliminarEditorial = new javax.swing.JButton();
        btnLimpiarEditorial = new javax.swing.JButton();
        jPmat = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblmaterial = new javax.swing.JTable();
        jPmaterial = new javax.swing.JPanel();
        btnEliminarmat = new javax.swing.JButton();
        jTfTipomaterial = new javax.swing.JTextField();
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
        btnnuevomat = new javax.swing.JButton();
        btneditarpmat = new javax.swing.JButton();
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

        jpinicio.setBackground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout jpinicioLayout = new javax.swing.GroupLayout(jpinicio);
        jpinicio.setLayout(jpinicioLayout);
        jpinicioLayout.setHorizontalGroup(
            jpinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpinicioLayout.setVerticalGroup(
            jpinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jtbprestamos.addTab("Inicio", jpinicio);

        tblEditorial.setModel(editorialDB.selectEditoriales());
        tblEditorial.setToolTipText("");
        tblEditorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEditorialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblEditorialMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(tblEditorial);

        jPmaterial1.setBackground(new java.awt.Color(0, 102, 204));

        btnConsultarmat1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConsultarmat1.setText("Consultar");

        txtPaisEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisEditorialActionPerformed(evt);
            }
        });

        jLbtipomaterial1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbtipomaterial1.setText("Nombre:");

        jLbtitulomaterial1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbtitulomaterial1.setText("Pais");

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

        javax.swing.GroupLayout jPmaterial1Layout = new javax.swing.GroupLayout(jPmaterial1);
        jPmaterial1.setLayout(jPmaterial1Layout);
        jPmaterial1Layout.setHorizontalGroup(
            jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmaterial1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPmaterial1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPaisEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPmaterial1Layout.createSequentialGroup()
                        .addGroup(jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbtitulomaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLbtipomaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarEditorial, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPmaterial1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarEditorial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiarEditorial))
                            .addGroup(jPmaterial1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(txtNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(29, Short.MAX_VALUE))))
            .addGroup(jPmaterial1Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(btnConsultarmat1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPmaterial1Layout.setVerticalGroup(
            jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterial1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbtipomaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbtitulomaterial1)
                    .addComponent(txtPaisEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPmaterial1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEditorial)
                    .addComponent(btnEliminarEditorial)
                    .addComponent(btnLimpiarEditorial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(btnConsultarmat1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jpEditorialLayout = new javax.swing.GroupLayout(jpEditorial);
        jpEditorial.setLayout(jpEditorialLayout);
        jpEditorialLayout.setHorizontalGroup(
            jpEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEditorialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPmaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jpEditorialLayout.setVerticalGroup(
            jpEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEditorialLayout.createSequentialGroup()
                .addGroup(jpEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpEditorialLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPmaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jtbprestamos.addTab("Editoriales", jpEditorial);

        tblmaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Título", "Ubicacion", "Cantidad Total", "Cantidad Disponible"
            }
        ));
        tblmaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmaterialMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblmaterial);

        jPmaterial.setBackground(new java.awt.Color(0, 102, 204));

        btnEliminarmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarmat.setText("Eliminar");

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

        jLbCantDispmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLbCantDispmat.setText("Cantidad Disponible");

        btnGuardarmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarmat.setText("Guardar");

        btnnuevomat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnuevomat.setText("Nuevo");

        btneditarpmat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btneditarpmat.setText("Editar");

        javax.swing.GroupLayout jPmaterialLayout = new javax.swing.GroupLayout(jPmaterial);
        jPmaterial.setLayout(jPmaterialLayout);
        jPmaterialLayout.setHorizontalGroup(
            jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmaterialLayout.createSequentialGroup()
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPmaterialLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLbtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTfTipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterialLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarmat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnnuevomat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btneditarpmat)
                            .addComponent(btnEliminarmat))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterialLayout.createSequentialGroup()
                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterialLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLbcanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(jPmaterialLayout.createSequentialGroup()
                                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPmaterialLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLbUbicmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLbtitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPmaterialLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLbCantDispmat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)))
                        .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTfUbimaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTftitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfCanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTfCantdispmat, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 45, 45))
        );
        jPmaterialLayout.setVerticalGroup(
            jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmaterialLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbtipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTfTipomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbtitulomaterial)
                    .addComponent(jTftitulomaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbUbicmaterial)
                    .addComponent(jTfUbimaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbcanttotal)
                    .addComponent(jTfCanttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbCantDispmat)
                    .addComponent(jTfCantdispmat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneditarpmat)
                    .addComponent(btnnuevomat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPmaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarmat)
                    .addComponent(btnGuardarmat))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPmatLayout = new javax.swing.GroupLayout(jPmat);
        jPmat.setLayout(jPmatLayout);
        jPmatLayout.setHorizontalGroup(
            jPmatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPmatLayout.setVerticalGroup(
            jPmatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmatLayout.createSequentialGroup()
                .addGroup(jPmatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPmaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                .addGap(92, 92, 92))
        );

        jtbprestamos.addTab("Material", jPmat);

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
          btnEdituser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdituserActionPerformed(evt);
            }
        });



        Tpuser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alumno", "Profesor", "", "" }));

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
                .addContainerGap(50, Short.MAX_VALUE))
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
    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
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
             btnregprestActionPerformed(evt);
            }
        });

        btnregprest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnregprest.setText("Registrar Prestamo");
           btnregprest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregprestActionPerformed(evt);
            }
        });


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
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTfBibliotecaamigosDonBosco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtbprestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTfBibliotecaamigosDonBoscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfBibliotecaamigosDonBoscoActionPerformed
        
    }//GEN-LAST:event_jTfBibliotecaamigosDonBoscoActionPerformed

    private void jTfiduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfiduserActionPerformed
        
    }//GEN-LAST:event_jTfiduserActionPerformed

    //BOTON DE BUSQUEDA DE USUARIO

    private void jTfBusquedauserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfBusquedauserActionPerformed

        String buscarID = jTfBusquedauser.getText().trim();

    if (buscarID.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Ingrese un ID para buscar.", 
            "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tbluser.getModel();

    boolean encontrado = false;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object id = model.getValueAt(i, 0); // 
        if (id != null && buscarID.equalsIgnoreCase(id.toString())) {

            //  TABLA
            jTfiduser.setText(model.getValueAt(i, 0).toString());  
            jTfnombreuser.setText(model.getValueAt(i, 1).toString()); 
            String tipo = model.getValueAt(i, 2).toString(); 
            jTfcorreouser.setText(model.getValueAt(i, 3).toString()); 
            jTfpassworduser.setText(model.getValueAt(i, 4).toString());


          
            if (model.getColumnCount() > 4 && model.getValueAt(i, 4) != null) {
                jTfpassworduser.setText(model.getValueAt(i, 4).toString());
            }

            
            for (java.awt.Component c : jPusuarios.getComponents()) {
                if (c instanceof javax.swing.JComboBox) {
                    javax.swing.JComboBox<?> combo = (javax.swing.JComboBox<?>) c;
                    combo.setSelectedItem(tipo);
                    break;
                }
            }

            tbluser.setRowSelectionInterval(i, i);

            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "No se encontró un usuario con ese ID.", 
            "Sin resultados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

        
    }//GEN-LAST:event_jTfBusquedauserActionPerformed

    private void jTfidusermoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfidusermoraActionPerformed
       
    }//GEN-LAST:event_jTfidusermoraActionPerformed

    private void jTfbusqumoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfbusqumoraActionPerformed
        
    }//GEN-LAST:event_jTfbusqumoraActionPerformed

    private void tblPrestamoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrestamoMouseClicked
    
    }//GEN-LAST:event_tblPrestamoMouseClicked

    private void tblmaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmaterialMouseClicked
  
    }//GEN-LAST:event_tblmaterialMouseClicked

    private void jTftitulomaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTftitulomaterialActionPerformed
      
    }//GEN-LAST:event_jTftitulomaterialActionPerformed

    //TABLA  USUARIO
    private void btnguardarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarUserActionPerformed
        java.awt.Component parent = javax.swing.SwingUtilities.getWindowAncestor(btnguardarUser);


String id = jTfiduser.getText().trim();
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

if (id.isEmpty() || nombre.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(parent,
        "Los campos ID y Nombre son obligatorios.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
javax.swing.table.DefaultTableModel model;
try {
    model = (javax.swing.table.DefaultTableModel) tbluser.getModel();
} catch (ClassCastException ex) {
    javax.swing.JOptionPane.showMessageDialog(parent,
        "El modelo de la tabla no es Correcto.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    return;
}

for (int r = 0; r < model.getRowCount(); r++) {
    Object existing = model.getValueAt(r, 0);
    if (existing != null && id.equals(String.valueOf(existing))) {
        javax.swing.JOptionPane.showMessageDialog(parent,
            "Ya existe un usuario con ese ID.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
}

Object[] fila = new Object[] { id, nombre, tipo, correo };

model.insertRow(0,fila);

javax.swing.JOptionPane.showMessageDialog(parent, "✅ Usuario guardado correctamente.");

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
        
    }//GEN-LAST:event_btnguardarUserActionPerformed
        
    private void TxtbusqpresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtbusqpresActionPerformed
        // TODO add your handling code here:
}    //BOTON EDITAR
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

    javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tbluser.getModel();

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
}            //BOTON DE REGISTRAR PRESTAMO
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
    javax.swing.table.DefaultTableModel model = 
        (javax.swing.table.DefaultTableModel) tblPrestamo.getModel();

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
        

      private void btnregdevActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                                   

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

    private void txtPaisEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisEditorialActionPerformed

    private void tblEditorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEditorialMouseEntered
        
    }//GEN-LAST:event_tblEditorialMouseEntered

    private void btnLimpiarEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarEditorialActionPerformed
            limpiarFormularioEditorial();
    }//GEN-LAST:event_btnLimpiarEditorialActionPerformed

    private void btnGuardarEditorialActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

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
    private javax.swing.JLabel JlbBusquedaUser;
    private javax.swing.JLabel JlbBusquedaUser1;
    private javax.swing.JTable Tblmora;
    private javax.swing.JTextField Txtbusqpres;
    private javax.swing.JButton btnConsultamora;
    private javax.swing.JButton btnEdituser;
    private javax.swing.JButton btnEliminarmat;
    private javax.swing.JButton btnGuardarmat;
    private javax.swing.JButton btneditarpmat;
    private javax.swing.JButton btnConsultarmat1;
    private javax.swing.JButton btnEliminarEditorial;
    private javax.swing.JButton btnGuardarEditorial;
    private javax.swing.JButton btnLimpiarEditorial;
    private javax.swing.JButton btneliminarUser;
    private javax.swing.JButton btnguardarUser;
    private javax.swing.JButton btnnuevomat;
    private javax.swing.JButton btnnuevouser;
    private javax.swing.JButton btnregdev;
    private javax.swing.JButton btnregprest;
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
    private javax.swing.JLabel jLbtipomaterial1;
    private javax.swing.JLabel jLbtitulomaterial;
    private javax.swing.JLabel jLbtitulomaterial1;
    private javax.swing.JLabel jLbtituloprestamo;
    private javax.swing.JPanel jPanelmora;
    private javax.swing.JPanel jPanelprestamo;
    private javax.swing.JPanel jPaneluser;
    private javax.swing.JPanel jPmat;
    private javax.swing.JPanel jPmaterial;
    private javax.swing.JPanel jPmaterial1;
    private javax.swing.JPanel jPmora;
    private javax.swing.JPanel jPprestamos;
    private javax.swing.JPanel jPusuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTfAniopubprestamo;
    private javax.swing.JTextField jTfAutorprestamo;
    private javax.swing.JTextField jTfBibliotecaamigosDonBosco;
    private javax.swing.JTextField jTfBusquedauser;
    private javax.swing.JTextField jTfCantdispmat;
    private javax.swing.JTextField jTfCanttotal;
    private javax.swing.JTextField jTfTipomaterial;
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
    private javax.swing.JLabel jlbBusqumora;
    private javax.swing.JLabel jlbpassworduser;
    private javax.swing.JPanel jpEditorial;
    private javax.swing.JPanel jpinicio;
    private javax.swing.JTabbedPane jtbprestamos;
    private javax.swing.JTable tblEditorial;
    private javax.swing.JTable tblPrestamo;
    private javax.swing.JTable tblmaterial;
    private javax.swing.JTable tbluser;
    private javax.swing.JTextField txtNombreEditorial;
    private javax.swing.JTextField txtPaisEditorial;
    // End of variables declaration//GEN-END:variables
}
