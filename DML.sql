desc januari_2023;

drop table januari_2023;

alter table januari_2023
    modify  no_struk varchar(15) not null ;

insert into januari_2023(tanggal_transaksi, no_struk, kode_cab, nama_cab, tipe_transaksi, kode_produk, nama_produk, jumlah, harga_sat, tot_jual)
    value
    ('2023-01-03', '0203-2023-0001','0203', 'Pancoran Barat', 'Eat in', '01-001', 'Nasi Putih', 1, 5000,  harga_sat * jumlah),
    ('2023-01-03', '0203-2023-0001','0203', 'Pancoran Barat', 'Eat in', '01-002', 'Aneka tumisan', 1, 2000,  harga_sat * jumlah),
    ('2023-01-03', '0203-2023-0001','0203', 'Pancoran Barat', 'Eat in', '02-001', 'Es Teh Tawar', 1, 1000,  harga_sat * jumlah),
    ('2023-01-03', '0203-2023-0002','0203', 'Pancoran Barat', 'Take Away', '01-001', 'Nasi Putih', 1, 5000,  harga_sat * jumlah),
    ('2023-01-03', '0203-2023-0002','0203', 'Pancoran Barat', 'Take Away', '01-007', 'Aneka Sop', 1, 7000,  harga_sat * jumlah),
    ('2023-01-03', '0203-2023-0002','0203', 'Pancoran Barat', 'Take Away', '01-003', 'Aneka Gorengan', 3, 1500,  harga_sat * jumlah),
    ('2023-01-04', '0204-2023-0012','0204', 'Kuningan Tengah', 'Online', '01-004', 'Nasi Goreng Biasa', 1, 16000,  harga_sat * jumlah),
    ('2023-01-05', '0204-2023-0013','0204', 'Kuningan Tengah', 'Take Away', '01-006', 'Mie Goreng Biasa', 1, 17000,  harga_sat * jumlah),
    ('2023-01-05', '0204-2023-0014','0204', 'Kuningan Tengah', 'Eat in', '01-005', 'Nasi Goreng Spesial', 1, 19000,  harga_sat * jumlah),
    ('2023-01-05', '0204-2023-0014','0204', 'Kuningan Tengah', 'Eat in', '04-001', 'Telor Dadar', 1, 5000,  harga_sat * jumlah),
    ('2023-01-03', '0205-2023-1000','0205', 'Pasar Minggu', 'Online', '01-008', 'Ikan Gurame Bakar', 1, 55000,  harga_sat * jumlah),
    ('2023-01-03', '0205-2023-1000','0205', 'Pasar Minggu', 'Online', '02-002', 'Juice Strawberry', 1, 23000,  harga_sat * jumlah),
    ('2023-01-03', '0205-2023-1000','0205', 'Pasar Minggu', 'Online', '01-001', 'Nasi Putih', 3, 5000,  harga_sat * jumlah),
    ('2023-01-03', '0205-2023-1000','0205', 'Pasar Minggu', 'Online', '04-002', 'Sambal Mangga', 2, 7000,  harga_sat * jumlah),
    ('2023-01-06', '0206-2023-1000','0206', 'Cilandak', 'Take Away', '02-003', 'Juice Mangga', 4, 20000,  harga_sat * jumlah);

select * from januari_2023;

delete from januari_2023;

select tanggal_transaksi as Tanggal, no_struk as Bill, kode_cab as 'Branch No', nama_cab as 'Branch Name', tipe_transaksi as Type,
       kode_produk as 'Product Code', nama_produk as 'Product Name', jumlah as Qty, harga_sat as Price, tot_jual as Total from januari_2023
                                                                                                                                   limit 2;

select tipe_transaksi, sum(tot_jual) from januari_2023
group by tipe_transaksi;