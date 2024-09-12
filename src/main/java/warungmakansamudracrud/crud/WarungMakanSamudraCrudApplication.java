package warungmakansamudracrud.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class WarungMakanSamudraCrudApplication {

	static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/laporan_penjualan";
	static final String USER = "root";
	static final String PASS = "gacorkangx999";

	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			boolean running = true;
			while (running) {
				System.out.println("Menu:");
				System.out.println("1. Insert Data Transaksi");
				System.out.println("2. Update Data Transaksi");
				System.out.println("3. Tampilkan Data Transaksi");
				System.out.println("4. Keluar");
				System.out.print("Pilih menu: ");
				int menu = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (menu) {
					case 1:
						insertDataTransaksi();
						break;
					case 2:
						updateDataTransaksi();
						break;
					case 3:
						tampilkanDataTransaksi();
						tampilkanSummary();
						break;
					case 4:
						running = false;
						break;
					default:
						System.out.println("Menu tidak valid");
				}
			}

			stmt.close();
			conn.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	private static void insertDataTransaksi() throws SQLException {
		LocalDate tanggalTransaksi = LocalDate.now();
		System.out.print("Masukkan nomor struk: ");
		String noStruk = scanner.nextLine();
		System.out.println("0203 (Pancoran Barat)/0204 (Kuningan Tengah)/0205 (Pasar Minggu)/0206 (Cilandak)");
		System.out.print("Masukkan kode cabang: ");
		String kodeCab = scanner.nextLine();
		System.out.println("Pancoran Barat/Kuningan Tengah/Pasar Minggu/Cilandak");
		System.out.print("Masukkan nama cabang: ");
		String namaCab = scanner.nextLine();
		System.out.println("Eat in/Take Away/Online");
		System.out.print("Masukkan tipe transaksi: ");
		String tipeTransaksi = scanner.nextLine();
		System.out.print("Masukkan kode produk: ");
		String kodeProduk = scanner.nextLine();
		System.out.print("Masukkan nama produk: ");
		String namaProduk = scanner.nextLine();
		System.out.print("Masukkan jumlah: ");
		int jumlah = scanner.nextInt();
		System.out.print("Masukkan harga satuan: ");
		int hargaSat = scanner.nextInt();
		int totJual = jumlah * hargaSat;
		 // Consume newline

		String sql = "INSERT INTO januari_2023 (tanggal_transaksi, no_struk, kode_cab, nama_cab, tipe_transaksi, kode_produk, nama_produk, jumlah, harga_sat, tot_jual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, String.valueOf(tanggalTransaksi));
		pstmt.setString(2, noStruk);
		pstmt.setString(3, kodeCab);
		pstmt.setString(4, namaCab);
		pstmt.setString(5, tipeTransaksi);
		pstmt.setString(6, kodeProduk);
		pstmt.setString(7, namaProduk);
		pstmt.setInt(8, jumlah);
		pstmt.setInt(9, hargaSat);
		pstmt.setInt(10, totJual);

		pstmt.executeUpdate();
		System.out.println("Data transaksi berhasil ditambahkan.");
	}

	private static void updateDataTransaksi() throws SQLException {
		System.out.print("Masukkan nomor struk yang ingin diupdate: ");
		String noStruk = scanner.nextLine();

		System.out.print("Masukkan tanggal transaksi baru (YYYY-MM-DD): ");
		String tanggalTransaksi = scanner.nextLine();
		System.out.print("Masukkan kode cabang baru: ");
		String kodeCab = scanner.nextLine();
		System.out.print("Masukkan nama cabang baru: ");
		String namaCab = scanner.nextLine();
		System.out.print("Masukkan tipe transaksi baru: ");
		String tipeTransaksi = scanner.nextLine();
		System.out.print("Masukkan kode produk baru: ");
		String kodeProduk = scanner.nextLine();
		System.out.print("Masukkan nama produk baru: ");
		String namaProduk = scanner.nextLine();
		System.out.print("Masukkan jumlah baru: ");
		int jumlah = scanner.nextInt();
		System.out.print("Masukkan harga satuan baru: ");
		int hargaSat = scanner.nextInt();
		System.out.print("Masukkan total jual baru: ");
		int totJual = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String sql = "UPDATE januari_2023 SET tanggal_transaksi = ?, kode_cab = ?, nama_cab = ?, tipe_transaksi = ?, kode_produk = ?, nama_produk = ?, jumlah = ?, harga_sat = ?, tot_jual = ? WHERE no_struk = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tanggalTransaksi);
		pstmt.setString(2, kodeCab);
		pstmt.setString(3, namaCab);
		pstmt.setString(4, tipeTransaksi);
		pstmt.setString(5, kodeProduk);
		pstmt.setString(6, namaProduk);
		pstmt.setInt(7, jumlah);
		pstmt.setInt(8, hargaSat);
		pstmt.setInt(9, totJual);
		pstmt.setString(10, noStruk);

		pstmt.executeUpdate();
		System.out.println("Data transaksi berhasil diupdate.");
	}

	private static void tampilkanDataTransaksi() throws SQLException {
		String sql = "SELECT * FROM januari_2023";
		rs = stmt.executeQuery(sql);

		System.out.printf("%-12s %-15s %-15s %-20s %-10s %-15s %-20s %-10s %-10s %-10s%n",
				"Tanggal", "Bill", "Branch No", "Branch Name", "Type", "Product Code", "Product Name", "Qty", "Price", "Total");
		System.out.println("================================================================================================================================================================");

		while (rs.next()) {
			System.out.printf("%-12s %-15s %-15s %-20s %-10s %-15s %-20s %-10d %-10d %-10d%n",
					rs.getString("tanggal_transaksi"),
					rs.getString("no_struk"),
					"0" + rs.getString("kode_cab"),
					rs.getString("nama_cab"),
					rs.getString("tipe_transaksi"),
					rs.getString("kode_produk"),
					rs.getString("nama_produk"),
					rs.getInt("jumlah"),
					rs.getInt("harga_sat"),
					rs.getInt("tot_jual"));
		}
	}

	private static void tampilkanSummary() throws SQLException {
		String sqlSummary = "SELECT tipe_transaksi, SUM(tot_jual) AS total_penjualan FROM januari_2023 GROUP BY tipe_transaksi";
		rs = stmt.executeQuery(sqlSummary);

		System.out.println("\nSummary of Sales by Transaction Type:");
		System.out.printf("%-20s : %-15s%n", "Transaction Type", "Total Sales");
		System.out.println("===========================================");

		while (rs.next()) {
			System.out.printf("%-20s : %-15d%n",
					rs.getString("tipe_transaksi"),
					rs.getInt("total_penjualan"));
		}
	}

}
