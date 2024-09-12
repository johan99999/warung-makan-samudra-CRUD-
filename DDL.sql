create database laporan_penjualan;
show databases;
show tables ;
use laporan_penjualan;

create table januari_2023
(
    tanggal_transaksi varchar(20) not null,
    no_struk varchar(15),
    kode_cab int not null,
    nama_cab varchar(100) not null,
    tipe_transaksi varchar(10) not null,
    kode_produk varchar(6) not null,
    nama_produk varchar(100) not null,
    jumlah int not null,
    harga_sat int not null,
    tot_jual int not null
) engine = innodb;

desc januari_2023;

drop table januari_2023;