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
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import koneksi.koneksi;
import java.util.Locale;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class Transaksi extends javax.swing.JFrame {
    private Connection conn = new koneksi().getkoneksi();
    private DefaultTableModel tabmode;
    private JasperReport JasperReport;
    Statement stm;
    ResultSet rs;
    public String tgl;
    public String Harga;
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     
     
      protected void kosong(){
          nofaktur();
          jDate.setDate(null);
          kar.setSelectedIndex(0);
          nakar.setText("");
          idp.setSelectedIndex(0);
          nape.setText("");
          nopol.setText("");
          kendaraan.setText("");
          cmbPelayanan.setSelectedItem("");
          harga.setText("");
          txtCari.setText("");
          save.setEnabled(true);
        edit.setEnabled(false);
}
     protected void datatable(){
    Object [] baris = {"NoTransaksi","Tanggal","IdKaryawan","NamaKaryawan","IdPelanggan","NamaPelanggan","NoPol","Jenis","Pelayanan","Harga"};
            tabmode = new DefaultTableModel (null,baris);
            TabelTransaksi.setModel(tabmode);
            String sql = "select * from transaksi";
            try{
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(sql);
                    while (hasil.next()){
                        String a= hasil.getString("NoTransaksi");
                        String b= hasil.getString("Tanggal");
                        String c= hasil.getString("IdKaryawan");
                        String d= hasil.getString("NamaKaryawan");
                        String e= hasil.getString("IdPelanggan");
                        String f= hasil.getString("NamaPelanggan");
                        String g= hasil.getString("Nopol");
                        String h= hasil.getString("JenisKendaraan");
                        String i= hasil.getString("JenisPelayanan");
                        String j= hasil.getString("Harga");
                        String[] data = {a,b,c,d,e,f,g,h,i,j};
                        tabmode.addRow(data);       
                    }
            } catch (Exception e){
            }  
}

    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        nofaktur();
        block();
        karyawan();
        konsumen();
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

     private void aktif(boolean x){
    faktur.setEditable(x);
    }
     
     protected void block(){
    faktur.setEnabled(false);
    nakar.setEnabled(false);
    nape.setEnabled(false);
    }
     
      public void nofaktur() {
        try {
        
            String sql = "SELECT * FROM transaksi ORDER by NoTransaksi desc";
            java.sql.Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("NoTransaksi").substring(1);
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

               faktur.setText("T" + Nol + AN);
            } else {
                faktur.setText("T0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
      
      public void karyawan() {
        java.sql.Statement st;
        java.sql.Connection con;
        java.sql.ResultSet rs;
        try {
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash", "root", "");
            st = con.createStatement();
            String s = "select * from datakaryawan";
            rs = st.executeQuery(s);
            kar.addItem("KODE KARYAWAN");
            while (rs.next()) {
                kar.addItem(rs.getString(1));
                //jc adalah nama variabel Jcombobox
                //rs.getString(2) adalah kolom nama_dosen yang diambil secara urut dimulai dari satu
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
            //digunakan untuk menampilkan pesan jika terjadi error 
        }
    }
      
      public void konsumen() {
        java.sql.Statement st;
        java.sql.Connection con;
        java.sql.ResultSet rs;
        try {
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash", "root", "");
            st = con.createStatement();
            String s = "select * from datacostumer";
            rs = st.executeQuery(s);
            idp.addItem("KODE COSTUMER");
            while (rs.next()) {
                idp.addItem(rs.getString(1));
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nakar = new javax.swing.JTextField();
        faktur = new javax.swing.JTextField();
        nopol = new javax.swing.JTextField();
        nape = new javax.swing.JTextField();
        cmbPelayanan = new javax.swing.JComboBox<>();
        kendaraan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        edit = new javax.swing.JButton();
        DELETE = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        kar = new javax.swing.JComboBox<>();
        idp = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelTransaksi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRANSAKSI");
        setMinimumSize(new java.awt.Dimension(1050, 560));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setMaximumSize(new java.awt.Dimension(1050, 560));
        jPanel1.setMinimumSize(new java.awt.Dimension(1050, 560));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 560));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Id Karyawan");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Nama Karyawan");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Id Pelanggan");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Nama Pelanggan");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Jenis Pelayanan");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tanggal Transaksi");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("NoPol Kendaraan");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Jenis Kendaraan");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, -1));

        nakar.setBackground(new java.awt.Color(153, 153, 153));
        nakar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nakar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(nakar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 190, 30));

        faktur.setBackground(new java.awt.Color(153, 153, 153));
        faktur.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        faktur.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(faktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 190, 30));

        nopol.setBackground(new java.awt.Color(153, 153, 153));
        nopol.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nopol.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(nopol, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 190, 30));

        nape.setBackground(new java.awt.Color(153, 153, 153));
        nape.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nape.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(nape, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 190, 30));

        cmbPelayanan.setBackground(new java.awt.Color(153, 153, 153));
        cmbPelayanan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbPelayanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Carwash", "Salon" }));
        cmbPelayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelayananActionPerformed(evt);
            }
        });
        jPanel2.add(cmbPelayanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, 190, 30));

        kendaraan.setBackground(new java.awt.Color(153, 153, 153));
        kendaraan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        kendaraan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(kendaraan, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 190, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Harga");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, -1, -1));

        harga.setBackground(new java.awt.Color(153, 153, 153));
        harga.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        harga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 190, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("No Transaksi");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDatePropertyChange(evt);
            }
        });
        jPanel2.add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 190, 30));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        edit.setText("Ubah");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel4.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, -1));

        DELETE.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        DELETE.setText("Hapus");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });
        jPanel4.add(DELETE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 70, -1));

        save.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel4.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setText("Bersih");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 70, -1));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setText("Struk");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 70, -1));

        Exit.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Exit.setText("Kembali");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jPanel4.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setText("Cetak");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 70, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 590, 40));

        kar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                karItemStateChanged(evt);
            }
        });
        jPanel2.add(kar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 190, 30));

        idp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idpItemStateChanged(evt);
            }
        });
        jPanel2.add(idp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 190, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 980, 270));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel2.setText("TRANSAKSI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 40));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Pencarian");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        txtCari.setBackground(new java.awt.Color(153, 153, 153));
        txtCari.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtCari.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel3.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 790, 30));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setText("Cari");
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 70, 30));

        TabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelTransaksi);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 960, 90));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 980, 150));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        String sql = "insert into transaksi values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat =  conn.prepareStatement(sql);
            stat.setString(1, faktur.getText());
            stat.setString(2, tgl);
            stat.setString(3, kar.getSelectedItem().toString());
            stat.setString(4, nakar.getText());
            stat.setString(5, idp.getSelectedItem().toString());
            stat.setString(6, nape.getText());
            stat.setString(7, nopol.getText());
            stat.setString(8, kendaraan.getText());
            stat.setString(9, cmbPelayanan.getSelectedItem().toString());
            stat.setString(10, harga.getText());
            
            
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            kosong();
            nofaktur();
            faktur.requestFocus();
            datatable();
      
                
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan"+e);
            
        }
    }//GEN-LAST:event_saveActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "update transaksi set Nopol=?, JenisKendaraan=? where Harga=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            
            stat.setString(1, nopol.getText());  
            stat.setString(2, kendaraan.getText());
            stat.setString(3, harga.getText());
          
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            faktur.requestFocus();
            datatable();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
    }//GEN-LAST:event_editActionPerformed

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null,"Hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0){
            String sql = "delete from transaksi where NoTransaksi ='"+faktur.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"data berhasil dihapus");
                kosong();
                faktur.requestFocus();
                datatable();
            
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"data gagal dihapus" +e);
            }
            
        }
    }//GEN-LAST:event_DELETEActionPerformed

    private void jDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDatePropertyChange
        // TODO add your handling code here:
         if (jDate.getDate()!=null){
        tgl=format.format(jDate.getDate());
    } 
    }//GEN-LAST:event_jDatePropertyChange

    private void TabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelTransaksiMouseClicked
        save.setEnabled(false);
        edit.setEnabled(true);
        int bar = TabelTransaksi.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        String j = tabmode.getValueAt(bar, 9).toString();
        
          faktur.setText(a);
          jDate.setDate(null);
          kar.setSelectedItem(c);
          nakar.setText(d);
          idp.setSelectedItem(e);
          nape.setText(f);
          nopol.setText(g);
          kendaraan.setText(h);
          cmbPelayanan.setSelectedItem(i);
          harga.setText(j);
    }//GEN-LAST:event_TabelTransaksiMouseClicked

    private void cmbPelayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelayananActionPerformed
        // TODO add your handling code here:
        String kode = (String) cmbPelayanan.getSelectedItem();
        String Harga ="";
        if (cmbPelayanan.getSelectedItem() == "Carwash") {
            String Mingguan = null;
            Harga = "50000";
            
        }
        else if(cmbPelayanan.getSelectedItem() == "Salon") {
            String Bulanan = null;
            Harga = "300000";
        }
        else{
            Harga="";
        }
        harga.setText(Harga);
       
        
    }//GEN-LAST:event_cmbPelayananActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
         new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ExitActionPerformed

    private void karItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_karItemStateChanged
        // TODO add your handling code here:
        String[] karyawan = kar.getSelectedItem().toString().split("//s+");
        String kode = karyawan[0];
        if(!kode.equals("ID KARYAWAN")){
            try {
                String karya = "";
                String wan = kar.getSelectedItem().toString();
                switch(kar.getSelectedIndex()){
                    case 1: karya = "1"; break;
                    case 2: karya = "2"; break;
                    case 3: karya = "3"; break;
                    case 4: karya = "4"; break;
                    case 5: karya = "5"; break;
                    case 6: karya = "6"; break;
                    case 7: karya = "7"; break;
                    case 8: karya = "8"; break;
                    case 9: karya = "9"; break;
                    case 10: karya = "11"; break;
                    case 12: karya = "12"; break;
                }
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM datakaryawan WHERE Id='"+wan+"'");
                if(rs.next()){
                    nakar.setText(rs.getString("Nama"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }else{

        }
    }//GEN-LAST:event_karItemStateChanged

    private void idpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idpItemStateChanged
        // TODO add your handling code here:
        String[] cus = idp.getSelectedItem().toString().split("//s+");
        String kode = cus[0];
        if(!kode.equals("ID PELANGGAN")){
            try {
                String cs = "";
                String sc = idp.getSelectedItem().toString();
                switch(idp.getSelectedIndex()){
                    case 1: cs = "1"; break;
                    case 2: cs = "2"; break;
                    case 3: cs = "3"; break;
                    case 4: cs = "4"; break;
                    case 5: cs = "5"; break;
                    case 6: cs = "6"; break;
                    case 7: cs = "7"; break;
                    case 8: cs = "8"; break;
                    case 9: cs = "9"; break;
                    case 10: cs = "10"; break;
                    case 11: cs = "11"; break;
                    case 12: cs = "12"; break;
                }
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM datacostumer WHERE Id='"+sc+"'");
                if(rs.next()){
                    nape.setText(rs.getString("Nama"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }else{

        }
    }//GEN-LAST:event_idpItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        kosong();
        datatable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String reportSource = null;
        String reportDest = null;
        HashMap <String,Object> parameter = new HashMap <String,Object>();
        String response;
        response = JOptionPane.showInputDialog("NOMOR TRANSAKSI");  
        try{
           parameter.put("tr", response);
         
             Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash","root","");
            reportSource = System.getProperty("user.dir") + "/src/report/struk.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/report/struk.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         String reportSource = null;
        String reportDest = null;
        HashMap <String,Object> parameter = new HashMap <String,Object>();
        try{
             Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/yellowcarwash","root","");
            reportSource = System.getProperty("user.dir") + "/src/laporanfull/transaksi.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/laporanfull/transaksi.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
            
        }catch(Exception e){
            System.out.println(e);
        }  
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DELETE;
    private javax.swing.JButton Exit;
    private javax.swing.JTable TabelTransaksi;
    private javax.swing.JComboBox<String> cmbPelayanan;
    private javax.swing.JButton edit;
    private javax.swing.JTextField faktur;
    private javax.swing.JTextField harga;
    private javax.swing.JComboBox<String> idp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kar;
    private javax.swing.JTextField kendaraan;
    private javax.swing.JTextField nakar;
    private javax.swing.JTextField nape;
    private javax.swing.JTextField nopol;
    private javax.swing.JButton save;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
