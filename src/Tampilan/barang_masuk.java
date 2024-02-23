/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tampilan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
/**
 *
 * @author indri
 */
public class barang_masuk extends javax.swing.JFrame {
private DefaultTableModel data;
private String tanggalmasuk;
public String date;
    Connection conn;
    Statement stm;
    ResultSet rs;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     
     
    public barang_masuk() {
        initComponents();
        tabel1();
        namak();
        block();
        barang();
        kobar();
        Locale locale = new Locale ("Id","ID");
        Locale.setDefault(locale);
        ImageIcon img = new ImageIcon("src/icon/car.png");
        this.setIconImage(img.getImage());
        initUI();
    }

    private void initUI(){ 
        getContentPane().setBackground(new Color(245, 245, 245));
        
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        setLocation(dx, dy);
    }
    
     public void kobar() {
        try {
        
            String sql = "SELECT * FROM barang_masuk ORDER by id_bm desc";
            java.sql.Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("id_bm").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

               idibar.setText("M" + Nol + AN);
            } else {
                idibar.setText("M0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
    
    public void  kosong(){
        idibar.setText("");
        nakar.setSelectedItem(0);
        kode.setText("");
        nabar.setSelectedItem(0);
        tgl.setDate(null);
        jml.setText("");
        save.setEnabled(true);
        edit.setEnabled(false);
    }
    
    public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/yellowcarwash","root","");
            stm=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }
    public void tabel1(){
        Object header[]={"ID BARANG MASUK","NAMA KARYAWAN","ID BARANG","NAMA BARANG","TANGGAL MASUK","JUMLAH"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabel.setModel(data);
        setKoneksi();
        String sql="SELECT * FROM barang_masuk";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString("id_bm"); 
                String kolom2=rs.getString("nama_karyawan");  
                String kolom3=rs.getString("id_barang");
                String kolom4=rs.getString("nama_barang");
                String kolom5=rs.getString("tanggal");
                String kolom6=rs.getString("jumlah");
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6};
                data.addRow(kolom);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
    }

    public void namak() {
        java.sql.Statement st;
        java.sql.Connection con;
        java.sql.ResultSet rs;
        try {
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash", "root", "");
            st = con.createStatement();
            String s = "select * from datakaryawan";
            rs = st.executeQuery(s);
            nakar.addItem("NAMA KARYAWAN ");
            while (rs.next()) {
                nakar.addItem(rs.getString(2));
                //jc adalah nama variabel Jcombobox
                //rs.getString(2) adalah kolom nama_dosen yang diambil secara urut dimulai dari satu
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
            //digunakan untuk menampilkan pesan jika terjadi error 
        }
    }
    
    public void barang() {
        java.sql.Statement st;
        java.sql.Connection con;
        java.sql.ResultSet rs;
        try {
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash", "root", "");
            st = con.createStatement();
            String s = "select * from barang";
            rs = st.executeQuery(s);
            nabar.addItem("NAMA BARANG ");
            while (rs.next()) {
                nabar.addItem(rs.getString(2));
                //jc adalah nama variabel Jcombobox
                //rs.getString(2) adalah kolom nama_dosen yang diambil secara urut dimulai dari satu
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
            //digunakan untuk menampilkan pesan jika terjadi error 
        }
    }
    
       protected void block(){
    kode.setEnabled(false);
        idibar.setEnabled(false);
        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tgl = new com.toedter.calendar.JDateChooser();
        kode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jml = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nakar = new javax.swing.JComboBox<>();
        idibar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nabar = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BARANG MASUK");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel1.setFocusable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 390, 100));

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });
        jPanel4.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ID BARANG MASUK");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("NAMA BARANG");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("TANGGAL MASUK");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 30));

        tgl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglPropertyChange(evt);
            }
        });
        jPanel2.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 170, 30));
        jPanel2.add(kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("JUMLAH BARANG");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 30));

        jml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlActionPerformed(evt);
            }
        });
        jPanel2.add(jml, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 170, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("KODE BARANG");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 30));

        nakar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nakarItemStateChanged(evt);
            }
        });
        jPanel2.add(nakar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, 30));
        jPanel2.add(idibar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 60, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("KARYAWAN");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 30));

        nabar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nabarItemStateChanged(evt);
            }
        });
        jPanel2.add(nabar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 170, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton5.setText("Bersih");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 80, 20));

        btndelete.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btndelete.setText("Hapus");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 80, 20));

        edit.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        edit.setText("Ubah");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel3.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 80, 20));

        save.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel3.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 80, 20));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 80, 20));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton2.setText("Cetak");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 80, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = "";
        tanggal = Format.format(tgl.getDate());
        String sql = "insert into barang_masuk values (?,?,?,?,?,?)";
            try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, idibar.getText());
            stat.setString(2, nakar.getSelectedItem().toString());
            stat.setString(3, kode.getText());
            stat.setString(4, nabar.getSelectedItem().toString());
            stat.setString(5, tanggal);
            stat.setString(6, jml.getText());
           

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data akan disimpan");     
            kosong();
            tabel1();
            kobar();
            idibar.requestFocus();
            }
            catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
            }
    }//GEN-LAST:event_saveActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null,"hapus","KOnfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0){
            String sql = "delete from barang_masuk where id_barang='"+kode.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data berhasil dihapus");
                kosong();
                kode.requestFocus();
                tabel1();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"data gagal dihapus" +e);
            }
            
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");   
        String tanggal;
        tanggal = String.valueOf(fm.format(tgl.getDate()));
        try{
            String sql = "update barang_masuk set nama_karyawan=?,id_barang=?, nama_barang=?, tanggal=?,jumlah=? where id_bm=?";
            PreparedStatement stat = conn.prepareStatement(sql);
              
            
            stat.setString(1, nakar.getSelectedItem().toString());
            stat.setString(2, kode.getText());
            stat.setString(3, nabar.getSelectedItem().toString());
            stat.setString(4, tanggal);
            stat.setString(5, jml.getText());
            stat.setString(6, idibar.getText());
            
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            idibar.requestFocus();
            tabel1();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_editActionPerformed

    private void tglPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglPropertyChange
   if (tgl.getDate()!=null){
        date=format.format(tgl.getDate());
    } 
    }//GEN-LAST:event_tglPropertyChange

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
       save.setEnabled(false);
        edit.setEnabled(true);
        SimpleDateFormat tglview = new SimpleDateFormat("yyyy-MM-dd");   
        
     //if (evt.getClickCount()==1) { 
      
        Date dateView = null;
        int baris = tabel.getSelectedRow();
        try {
                dateView = (Date) tglview.parse((String) tabel.getValueAt(baris, 4));
            } catch (ParseException ex) {
                Logger.getLogger(barang__masuk.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        idibar.setText(tabel.getModel().getValueAt(baris, 0).toString());
        nakar.setSelectedItem(tabel.getModel().getValueAt(baris, 1).toString());
        kode.setText(tabel.getModel().getValueAt(baris, 2).toString());
        nabar.setSelectedItem(tabel.getModel().getValueAt(baris, 3).toString());
        tgl.setDate(dateView);
        jml.setText(tabel.getModel().getValueAt(baris, 5).toString());
      
        //}
    }//GEN-LAST:event_tabelMouseClicked

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    kosong();
    tabel1();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
        Object header[]={"ID BARANG MASUK","NAMA KARYAWAN","KODE BARANG","NAMA BARANG","TANGGAL MASUK","JUMLAH"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabel.setModel(data);

        setKoneksi();
        String sql="Select * from barang_masuk where id_barang like '%" + txtcari.getText() + "%'" +"or nama_barang like '%" + txtcari.getText()+"%'";

        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString("id_bm"); 
                String kolom2=rs.getString("nama_karyawan");  
                String kolom3=rs.getString("id_barang");
                String kolom4=rs.getString("nama_barang");
                String kolom5=rs.getString("tanggal");
                String kolom6=rs.getString("jumlah");
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          this.setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nabarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nabarItemStateChanged
        // TODO add your handling code here:
        String[] gaji = nabar.getSelectedItem().toString().split("//s+");
        String kodes = gaji[0];
        if(!kodes.equals("NAMA BARANG")){
            try {
                String barang = "";
                String an = nabar.getSelectedItem().toString();
                switch(nabar.getSelectedIndex()){
                    case 1: barang = "1"; break;
                    case 2: barang = "2"; break;
                    case 3: barang = "3"; break;
                    case 4: barang = "4"; break;
                    case 5: barang = "5"; break;
                    case 6: barang = "6"; break;
                    case 7: barang = "7"; break;
                    case 8: barang = "8"; break;
                    case 9: barang = "9"; break;
                    case 10: barang = "10"; break;
                    case 11: barang = "11"; break;
                    case 12: barang = "12"; break;
                }
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM barang WHERE nama_barang='"+an+"'");
                if(rs.next()){
                    kode.setText(rs.getString("id_barang"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }else{

        }
    }//GEN-LAST:event_nabarItemStateChanged

    private void nakarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nakarItemStateChanged

    }//GEN-LAST:event_nakarItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String reportSource = null;
        String reportDest = null;
        HashMap <String,Object> parameter = new HashMap <String,Object>();
        try{
             Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash","root","");
            reportSource = System.getProperty("user.dir") + "/src/laporanfull/masuk.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/laporanfull/masuk.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
            
        }catch(Exception e){
            System.out.println(e);
        }  
    }//GEN-LAST:event_jButton2ActionPerformed

   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(barang_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barang_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barang_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barang_masuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang_masuk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton edit;
    private javax.swing.JTextField idibar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jml;
    private javax.swing.JTextField kode;
    private javax.swing.JComboBox<String> nabar;
    private javax.swing.JComboBox<String> nakar;
    private javax.swing.JButton save;
    private javax.swing.JTable tabel;
    private com.toedter.calendar.JDateChooser tgl;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables

    private static class barang__masuk {

        public barang__masuk() {
        }
    }
}
