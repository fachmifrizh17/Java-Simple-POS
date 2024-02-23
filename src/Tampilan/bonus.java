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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author Lenovo
 */
public class bonus extends javax.swing.JFrame {
    
    private Connection conn = new koneksi().getkoneksi();
    private DefaultTableModel tabmode;
    private String jasperPrint;
    Statement stm;
    ResultSet rs;
    public String tgl;
    SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
    
    
    protected void kosong(){
        bonuus();
        nama.setSelectedIndex(0);
        id.setText(""); 
        kode.setText("");
        tipe.setText("");
        bonus.setText("");
        kali.setText("");
        htot.setText("");
        txtCari.setText("");
        save.setEnabled(true);
        edit.setEnabled(false);
    }
    
    protected void datatable(){
        Object [] baris = {"KODE","NAMA","ID","TIPE","JUMLAH","TOTAL","BONUS"};
            tabmode = new DefaultTableModel (null,baris);
            tabelGaji.setModel(tabmode);
            String sql = "select * from bonus";
            try{
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(sql);
                    while (hasil.next()){
                        String a= hasil.getString("kode");
                        String b= hasil.getString("nama");
                        String c= hasil.getString("id");
                        String d= hasil.getString("tipe");
                        String e= hasil.getString("jumlah");
                        String f= hasil.getString("total");
                        String g= hasil.getString("bonus");
                        
                        String[] data = {a,b,c,d,e,f,g};
                        tabmode.addRow(data);
                 
                    
                    }
            } catch (Exception e){
            }
    }
    
    public void bonuus() {
        try {
        
            String sql = "SELECT * FROM bonus ORDER by kode desc";
            java.sql.Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("KODE").substring(1);
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

               kode.setText("B" + Nol + AN);
            } else {
                kode.setText("B0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  

    /**
     * Creates new form GajiKaryawan
     */
    public bonus() {
        initComponents();
        tampilnama();
        block();
        bonuus();
        datatable();
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

    protected void block(){
    kode.setEnabled(false);
    id.setEnabled(false);
    tipe.setEnabled(false);
    }
    
    public void tampilnama() {
        java.sql.Statement st;
        java.sql.Connection con;
        java.sql.ResultSet rs;
        try {
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash", "root", "");
            st = con.createStatement();
            String s = "select * from transaksi";
            rs = st.executeQuery(s);
            nama.addItem("NAMA KARYAWAN");
            while (rs.next()) {
                nama.addItem(rs.getString(4));
                //jc adalah nama variabel Jcombobox
                //rs.getString(2) adalah kolom nama_dosen yang diambil secara urut dimulai dari satu
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
            //digunakan untuk menampilkan pesan jika terjadi error 
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

        bonusan = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        kode = new javax.swing.JTextField();
        bonus = new javax.swing.JTextField();
        kali = new javax.swing.JTextField();
        btnHitung = new javax.swing.JButton();
        nama = new javax.swing.JComboBox<>();
        htot = new javax.swing.JLabel();
        tipe = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelGaji = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BONUS");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nama");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, 77, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Id Karyawan");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 119, -1, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("ID");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tipe");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 50, 33));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Total");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 50, 29));
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 119, 162, 30));

        kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeActionPerformed(evt);
            }
        });
        jPanel2.add(kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 162, 30));
        jPanel2.add(bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 80, 30));
        jPanel2.add(kali, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 40, 30));

        btnHitung.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnHitung.setText("Hitung");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });
        jPanel2.add(btnHitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, -1));

        nama.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                namaItemStateChanged(evt);
            }
        });
        jPanel2.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 30));

        htot.setText("0");
        jPanel2.add(htot, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, 30));
        jPanel2.add(tipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 160, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Bonus Dan Jumlah");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 120, 33));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 102, 368, 360));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        save.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel3.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, -1));

        edit.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        edit.setText("Ubah");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel3.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, -1));

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 70, -1));

        btnClear.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnClear.setText("Bersih");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel3.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 70, -1));

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnExit.setText("Kembali");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel3.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 80, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton1.setText("Cetak");
        jButton1.setPreferredSize(new java.awt.Dimension(71, 22));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 80, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 370, 80));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Pencarian");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 106, 30));
        jPanel4.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 13, 500, 30));

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        jPanel4.add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, 40));

        tabelGaji.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelGajiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelGaji);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 650, 190));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 672, 270));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setText("BONUS KARYAWAN");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("-Bonus 1 Motor 3500");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, 140, 33));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("*NOTE:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, 120, 33));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("-Bonus 1 Mobil 5000");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 140, 33));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
         String sql = "insert into bonus values (?,?,?,?,?,?,?)";
        try{
     
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, kode.getText());
            stat.setString(2, nama.getSelectedItem().toString());
            stat.setString(3, id.getText());   
            stat.setString(4, tipe.getText());
            stat.setString(5, bonus.getText());
            stat.setString(6, kali.getText());
            stat.setString(7, htot.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            kosong();
            id.requestFocus();
            datatable();
            bonuus();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan"+e);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "update bonus set jumlah=?, where Jumlah=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            
             
            
            stat.setString(1, bonus.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            id.requestFocus();
            datatable();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_editActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
          int ok = JOptionPane.showConfirmDialog(null,"hapus","KOnfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0){
            String sql = "delete from bonus where kode='"+id.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data berhasil dihapus");
                kosong();
                id.requestFocus();
                datatable();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"data gagal dihapus" +e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        kosong();
        datatable();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
       String a = bonus.getText();
        int aa = Integer.parseInt(a);
        
        String b = kali.getText();
        int bb = Integer.parseInt(b);

        int hargaa,jumlahh,totall;

        hargaa = Integer.parseInt(bonus.getText().toString());
        jumlahh = Integer.parseInt(kali.getText().toString());
        totall = jumlahh*hargaa;
            htot.setText(Integer.toString(totall));
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        Object[] Baris = {"KODE","NAMA","ID","TIPE","JUMLAH","TOTAL","BONUS"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelGaji.setModel(tabmode);
        String sql = "select * from bonus where kode='"+txtCari.getText()+"'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kode");
                String b = hasil.getString("nama");
                String c = hasil.getString("id");
                String d = hasil.getString("tipe");
                String e = hasil.getString("jumlah");
                String f = hasil.getString("total");
                String g = hasil.getString("bonus");
                
                String[] data={a,b,c,d,e,f,g};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
    }
    }//GEN-LAST:event_btnCariActionPerformed

    private void tabelGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelGajiMouseClicked
        save.setEnabled(false);
        edit.setEnabled(true);
        int bar = tabelGaji.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        
        kode.setText(a);
        nama.setSelectedItem(b);
        id.setText(c);
        tipe.setText(d);
        bonus.setText(e); 
        kali.setText(f);
        htot.setText(g);
    }//GEN-LAST:event_tabelGajiMouseClicked

    private void kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeActionPerformed

    private void namaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_namaItemStateChanged
        // TODO add your handling code here:
        String[] nama_pembeli = nama.getSelectedItem().toString().split("//s+");
        String kode = nama_pembeli[0];
        if(!kode.equals("NAMA KARYAWAN")){
            try {
                String id_pembelii = "";
                String id_beli = nama.getSelectedItem().toString();
                switch(nama.getSelectedIndex()){
                    case 1: id_pembelii = "1"; break;
                    case 2: id_pembelii = "2"; break;
                    case 3: id_pembelii = "3"; break;
                    case 4: id_pembelii = "4"; break;
                    case 5: id_pembelii = "5"; break;
                    case 6: id_pembelii = "6"; break;
                    case 7: id_pembelii = "7"; break;
                    case 8: id_pembelii = "8"; break;
                    case 9: id_pembelii = "9"; break;
                    case 10: id_pembelii = "10"; break;
                    case 11: id_pembelii = "11"; break;
                    case 12: id_pembelii = "12"; break;
                }
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM transaksi WHERE NamaKaryawan='"+id_beli+"'");
                if(rs.next()){
                    id.setText(rs.getString("IdKaryawan"));
                    tipe.setText(rs.getString("JenisKendaraan"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }else{

        }
    }//GEN-LAST:event_namaItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String reportSource = null;
        String reportDest = null;
        HashMap <String,Object> parameter = new HashMap <String,Object>();
        try{
             Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash","root","");
            reportSource = System.getProperty("user.dir") + "/src/laporanfull/bonus.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/laporanfull/bonus.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
            
        }catch(Exception e){
            System.out.println(e);
        }        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(bonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bonus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bonus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bonus;
    private javax.swing.ButtonGroup bonusan;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton edit;
    private javax.swing.JLabel htot;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kali;
    private javax.swing.JTextField kode;
    private javax.swing.JComboBox<String> nama;
    private javax.swing.JButton save;
    private javax.swing.JTable tabelGaji;
    private javax.swing.JTextField tipe;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
