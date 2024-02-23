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
import java.text.DateFormat;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
/**
 *
 * @author Indri
 */
public class barang_keluar extends javax.swing.JFrame {
private DefaultTableModel data;
private String tanggalmasuk;
public String date;
    Connection conn;
    Statement stm;
    ResultSet rs;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form barangkeluar
     */
    public barang_keluar() {
        initComponents();
        block();
        tabel();
        kobar();
        namak();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        tgl.setText(dateFormat.format(cal.getTime()));
        tgl.setEnabled(false);
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
        
            String sql = "SELECT * FROM barang_keluar ORDER by id_dk desc";
            java.sql.Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("id_dk").substring(1);
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

               idikel.setText("K" + Nol + AN);
            } else {
                idikel.setText("K0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
    
    public void namak() {
        java.sql.Statement st;
        java.sql.Connection con;
        java.sql.ResultSet rs;
        try {
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash", "root", "");
            st = con.createStatement();
            String s = "select * from barang_masuk";
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

    private void aktif(boolean x){
    idikel.setEditable(x);
    }
    
    public void  kosong(){
        kobar();
        nakar.setSelectedIndex(0);
        kode.setText("");
        nama.setText("");
        jml.setText("");
        save.setEnabled(true);
        edit.setEnabled(false);
    }
    
    protected void block(){
    idikel.setEnabled(false);
    kode.setEnabled(false);
    nama.setEnabled(false);
    
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
    public void tabel(){
        Object header[]={"ID BARANG KELUAR","NAMA KARYAWAN","KODE BARANG","NAMA BARANG","TANGGAL KELUAR","JUMLAH"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabel.setModel(data);
        setKoneksi();
        String sql="SELECT * FROM barang_keluar";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString("id_dk");
                String kolom2=rs.getString("nama_karyawan");
                String kolom3=rs.getString("id_barang");
                String kolom4=rs.getString("nama_barang");
                String kolom5=rs.getString("tgl");
                String kolom6=rs.getString("jumlah");
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6};
                data.addRow(kolom);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jml = new javax.swing.JTextField();
        nakar = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tgl = new javax.swing.JTextField();
        idikel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        kode = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        save = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        cari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BARANG KELUAR");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ID BARANG KELUAR");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("NAMA BARANG");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("TANGGAL KELUAR");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 30));
        jPanel2.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 170, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("JUMLAH BARANG");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 30));

        jml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlActionPerformed(evt);
            }
        });
        jPanel2.add(jml, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 170, 30));

        nakar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nakarItemStateChanged(evt);
            }
        });
        jPanel2.add(nakar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("KODE BARANG");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 30));
        jPanel2.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 170, 30));
        jPanel2.add(idikel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 80, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("KARYAWAN");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 30));
        jPanel2.add(kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clear.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        clear.setText("Bersih");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel3.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 70, 20));

        delete.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        delete.setText("Hapus");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel3.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 70, 20));

        edit.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        edit.setText("Ubah");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel3.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 70, 20));

        save.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel3.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 40, -1, 20));

        back.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        back.setText("Kembali");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel3.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 80, 20));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 80, 20));

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

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 100));

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariKeyPressed(evt);
            }
        });
        jPanel4.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
kosong();
tabel();
    }//GEN-LAST:event_clearActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
int ok = JOptionPane.showConfirmDialog(null,"hapus","KOnfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0){
            String sql = "delete from barang_keluar where nama_barang='"+nama.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data berhasil dihapus");
                kosong();
                idikel.requestFocus();
                tabel();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"data gagal dihapus" +e);
            }
            
        } 
    }//GEN-LAST:event_deleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
try{
            String sql = "update barang_keluar set nama_karyawan=?,id_barang=?, nama_barang=?, tgl=?,jumlah=? where id_dk=?";
            PreparedStatement stat = conn.prepareStatement(sql);
              
            
            stat.setString(1, nakar.getSelectedItem().toString());
            stat.setString(2, kode.getText());
            stat.setString(3, nama.getText());
            stat.setString(4, tgl.getText());
            stat.setString(5, jml.getText());
            stat.setString(6, idikel.getText());
            
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            idikel.requestFocus();
            tabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_editActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String sql = "insert into barang_keluar values (?,?,?,?,?,?)";
            try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, idikel.getText());
            stat.setString(2, nakar.getSelectedItem().toString());
            stat.setString(3, kode.getText());
            stat.setString(4, nama.getText());
            stat.setString(5, tgl.getText());
            stat.setString(6, jml.getText());
           

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data akan disimpan");     
            kosong();
            tabel();
            kobar();
            idikel.requestFocus();
            }
            catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+e);
            } 
    }//GEN-LAST:event_saveActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
 this.setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
save.setEnabled(false);
        edit.setEnabled(true);
        int bar = tabel.getSelectedRow();
        String a = tabel.getValueAt(bar, 0).toString ();
        String b = tabel.getValueAt(bar, 1).toString ();
        String c = tabel.getValueAt(bar, 2).toString ();
        String d = tabel.getValueAt(bar, 3).toString ();
        String e = tabel.getValueAt(bar, 4).toString ();
        String f = tabel.getValueAt(bar, 5).toString ();
        
        idikel.setText(a);
        nakar.setSelectedItem(b);
        kode.setText(c);
        nama.setText(d);
        tgl.setText(e);
        jml.setText(f);  
    }//GEN-LAST:event_tabelMouseClicked

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
    
    }//GEN-LAST:event_cariActionPerformed

    private void cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyPressed
Object header[]={"ID BARANG KELUAR","NAMA KARYAWAN","KODE BARANG","NAMA BARANG","TANGGAL KELUAR","JUMLAH"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabel.setModel(data);

        setKoneksi();
        String sql="Select * from barang_keluar where nama_barang like '%" + cari.getText() + "%'" +"or id_barang like '%" + cari.getText()+"%'";

        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString("id_dk");
                String kolom2=rs.getString("nama_karyawan");
                String kolom3=rs.getString("id_barang");
                String kolom4=rs.getString("nama_barang");
                String kolom5=rs.getString("tgl");
                String kolom6=rs.getString("jumlah");
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }    
    }//GEN-LAST:event_cariKeyPressed

    private void nakarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nakarItemStateChanged
        // TODO add your handling code here:
        String[] gaji = nakar.getSelectedItem().toString().split("//s+");
        String kodes = gaji[0];
        if(!kodes.equals("NAMA KARYAWAN")){
            try {
                String barang = "";
                String an = nakar.getSelectedItem().toString();
                switch(nakar.getSelectedIndex()){
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
                java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM barang_masuk WHERE nama_karyawan='"+an+"'");
                if(rs.next()){
                    kode.setText(rs.getString("id_barang"));
                    nama.setText(rs.getString("nama_barang"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }else{

        }
    }//GEN-LAST:event_nakarItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String reportSource = null;
        String reportDest = null;
        HashMap <String,Object> parameter = new HashMap <String,Object>();
        try{
             Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash","root","");
            reportSource = System.getProperty("user.dir") + "/src/laporanfull/keluar.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/laporanfull/keluar.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
            
        }catch(Exception e){
            System.out.println(e);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(barang_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barang_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barang_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barang_keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang_keluar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField cari;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JTextField idikel;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JComboBox<String> nakar;
    private javax.swing.JTextField nama;
    private javax.swing.JButton save;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tgl;
    // End of variables declaration//GEN-END:variables
}
