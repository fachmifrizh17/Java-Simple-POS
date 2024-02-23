-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Agu 2021 pada 07.52
-- Versi server: 10.4.20-MariaDB
-- Versi PHP: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yellowcarwash`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `absen`
--

CREATE TABLE `absen` (
  `Id` varchar(50) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Masuk` varchar(50) NOT NULL,
  `Keluar` varchar(50) NOT NULL,
  `tgl` date DEFAULT NULL,
  `Lembur` varchar(50) NOT NULL,
  `Keterangan` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `absen`
--

INSERT INTO `absen` (`Id`, `Nama`, `Masuk`, `Keluar`, `tgl`, `Lembur`, `Keterangan`) VALUES
('K0001', 'Jhonny', '09.50', '13.20', '2021-08-11', 'Tidak Lembur', ''),
('K0002', 'Hayabusa', '09.50', '14.20', '2021-08-11', 'Tidak Lembur', ''),
('K0003', 'Mugenhy', '08.50', '15.00', '2021-08-11', 'Tidak Lembur', 'Kerja Ampe Tangan Kapalan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `stok` int(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `stok`) VALUES
('D0001', 'Sikat Kawat', -1),
('D0002', 'Ember Plastik', 11),
('D0003', 'Sabun Mobil', 27),
('D0004', 'Selang Air', 0),
('D0005', 'Spoons', 5),
('D0006', 'Kanebo', 11),
('D0007', 'Drum Besar', 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang_keluar`
--

CREATE TABLE `barang_keluar` (
  `id_dk` varchar(50) NOT NULL,
  `nama_karyawan` varchar(50) NOT NULL,
  `id_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `tgl` date NOT NULL,
  `jumlah` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang_keluar`
--

INSERT INTO `barang_keluar` (`id_dk`, `nama_karyawan`, `id_barang`, `nama_barang`, `tgl`, `jumlah`) VALUES
('K0001', 'Jhonny', 'D0001', 'Sikat Kawat', '2021-08-23', 2),
('K0002', 'Jhonny', 'D0001', 'Sikat Kawat', '2021-08-23', 5),
('K0003', 'Hayabusa', 'D0002', 'Ember Plastik', '2021-08-23', 1),
('K0004', 'Mugenhy', 'D0003', 'Sabun Mobil', '2021-08-23', 3);

--
-- Trigger `barang_keluar`
--
DELIMITER $$
CREATE TRIGGER `b_keluar` AFTER INSERT ON `barang_keluar` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-NEW.jumlah
WHERE id_barang=NEW.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `b_keluar_delete` AFTER DELETE ON `barang_keluar` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-OLD.jumlah
WHERE id_barang=OLD.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang_masuk`
--

CREATE TABLE `barang_masuk` (
  `id_bm` varchar(50) NOT NULL,
  `nama_karyawan` varchar(50) NOT NULL,
  `id_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `jumlah` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang_masuk`
--

INSERT INTO `barang_masuk` (`id_bm`, `nama_karyawan`, `id_barang`, `nama_barang`, `tanggal`, `jumlah`) VALUES
('M0001', 'Jhonny', 'D0001', 'Sikat Kawat', '2021-08-23', 7),
('M0002', 'Hayabusa', 'D0002', 'Ember Plastik', '2021-08-26', 12),
('M0003', 'Mugenhy', 'D0003', 'Sabun Mobil', '2021-08-24', 31),
('M0004', 'Jhonny', 'D0004', 'Selang Air', '2021-08-25', 4),
('M0005', 'Hayabusa', 'D0005', 'Spoons', '2021-08-25', 5),
('M0006', 'Mugenhy', 'D0006', 'Kanebo', '2021-08-26', 11),
('M0007', 'Mugenhy', 'D0007', 'Drum Besar', '2021-08-26', 3);

--
-- Trigger `barang_masuk`
--
DELIMITER $$
CREATE TRIGGER `b_masuk` AFTER INSERT ON `barang_masuk` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok+NEW.jumlah
WHERE id_barang=NEW.id_barang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `b_masuk_delete` AFTER DELETE ON `barang_masuk` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-OLD.jumlah
WHERE id_barang=OLD.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bonus`
--

CREATE TABLE `bonus` (
  `kode` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `id` varchar(50) NOT NULL,
  `tipe` varchar(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `total` int(150) NOT NULL,
  `bonus` int(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `bonus`
--

INSERT INTO `bonus` (`kode`, `nama`, `id`, `tipe`, `jumlah`, `total`, `bonus`) VALUES
('K0001', 'tes1', 'K0001', 'mobil', 5000, 4, 20000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `datacostumer`
--

CREATE TABLE `datacostumer` (
  `Id` varchar(50) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Alamat` text NOT NULL,
  `NoTlp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `datacostumer`
--

INSERT INTO `datacostumer` (`Id`, `Nama`, `Alamat`, `NoTlp`) VALUES
('C0001', 'Sutoyo BKN', 'Cawang', '023131312'),
('C0002', 'Slipi Kemanggisan', 'Jakbar', '0892313211'),
('C0003', 'Purnomo', 'Jaktim	', '0238183193'),
('C0004', 'Encuy', 'Bogor', '0231312312');

-- --------------------------------------------------------

--
-- Struktur dari tabel `datakaryawan`
--

CREATE TABLE `datakaryawan` (
  `Id` varchar(100) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Alamat` text NOT NULL,
  `NoTlp` varchar(50) NOT NULL,
  `Jabatan` varchar(50) NOT NULL,
  `Tanggal_Masuk` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `datakaryawan`
--

INSERT INTO `datakaryawan` (`Id`, `Nama`, `Alamat`, `NoTlp`, `Jabatan`, `Tanggal_Masuk`) VALUES
('K0001', 'Jhonny', 'BSD', '02313123', 'ceo', '2021-08-12'),
('K0002', 'Hayabusa', 'Karawaci', '01231313131', 'ob', '2021-08-26'),
('K0003', 'Mugenhy', 'Gading Serpong', '032313132', 'Karyawan 1', '2021-08-27');

-- --------------------------------------------------------

--
-- Struktur dari tabel `registrasi`
--

CREATE TABLE `registrasi` (
  `Id` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `NoTelp` varchar(50) NOT NULL,
  `Alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `registrasi`
--

INSERT INTO `registrasi` (`Id`, `Username`, `Password`, `NoTelp`, `Alamat`) VALUES
(1, 'test', 'test', '123', 'khuvukiland');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `NoTransaksi` varchar(50) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdKaryawan` varchar(50) NOT NULL,
  `NamaKaryawan` varchar(50) NOT NULL,
  `IdPelanggan` varchar(50) NOT NULL,
  `NamaPelanggan` varchar(50) NOT NULL,
  `Nopol` varchar(50) NOT NULL,
  `JenisKendaraan` varchar(100) NOT NULL,
  `JenisPelayanan` varchar(100) NOT NULL,
  `Harga` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`NoTransaksi`, `Tanggal`, `IdKaryawan`, `NamaKaryawan`, `IdPelanggan`, `NamaPelanggan`, `Nopol`, `JenisKendaraan`, `JenisPelayanan`, `Harga`) VALUES
('T0001', '2021-08-06', 'K0001', 'tes1', 'C0002', 'test02', 'b123sd', 'mobil', 'Salon', '15000'),
('T0002', '2021-08-06', 'K0002', 'tes2', 'C0003', 'test3', 'b321dsa', 'motor', 'Carwash', '14000');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `absen`
--
ALTER TABLE `absen`
  ADD PRIMARY KEY (`Id`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `barang_keluar`
--
ALTER TABLE `barang_keluar`
  ADD PRIMARY KEY (`id_dk`);

--
-- Indeks untuk tabel `barang_masuk`
--
ALTER TABLE `barang_masuk`
  ADD PRIMARY KEY (`id_bm`);

--
-- Indeks untuk tabel `bonus`
--
ALTER TABLE `bonus`
  ADD PRIMARY KEY (`kode`);

--
-- Indeks untuk tabel `datacostumer`
--
ALTER TABLE `datacostumer`
  ADD PRIMARY KEY (`Id`);

--
-- Indeks untuk tabel `datakaryawan`
--
ALTER TABLE `datakaryawan`
  ADD PRIMARY KEY (`Id`);

--
-- Indeks untuk tabel `registrasi`
--
ALTER TABLE `registrasi`
  ADD PRIMARY KEY (`Id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`NoTransaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
