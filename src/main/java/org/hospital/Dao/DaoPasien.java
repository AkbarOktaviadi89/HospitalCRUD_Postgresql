package org.hospital.Dao;

import org.hospital.view.MenuView;

import java.sql.*;

public class DaoPasien {

    public void createTable(Connection conn, String tableName){
        Statement statement;
        try{
            String query = "CREATE TABLE IF NOT EXISTS "+tableName+" (id SERIAL PRIMARY KEY, nama VARCHAR(255), alamat VARCHAR(255), jenisKelamin VARCHAR(255), umur INTEGER)";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertData(Connection conn, String tableName, String nama, String alamat, String jenisKelamin, Integer umur){
        Statement statement;
        try{
            String query = String.format("insert into %s (nama, alamat, jenisKelamin, umur) values ('%s', '%s', '%s', %s)", tableName, nama, alamat, jenisKelamin, umur);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void readData(Connection conn, String tableName){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = "SELECT * FROM "+tableName;
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            System.out.println(MenuView.SEPARATOR );
            System.out.println(MenuView.centerText("LIST DATA PASIEN", MenuView.SEPARATOR.length()));
            System.out.println(MenuView.SEPARATOR);
            System.out.println(" ----------------------------------------------------------------------------");
            System.out.printf("| %-4s | %-20s | %-15s | %-12s | %s |%n"," No ", "     Nama Pasien     ", "     Alamat     ", " Jenis Kelamin ", " Umur ");
            System.out.println(" ----------------------------------------------------------------------------");
            while (rs.next()){
                System.out.printf("| %-4s | %-21s | %-16s | %-15s | %s     |%n", rs.getInt("id"), rs.getString("nama"), rs.getString("alamat"), rs.getString("jenisKelamin"), rs.getInt("umur"));
            }
            System.out.println("\n1. Tambah Data Pasien");
            System.out.println("2. Ubah Data Pasien");
            System.out.println("3. Hapus Data Pasien");
            System.out.println("4. Kembali");
            System.out.print("=> ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData(Connection conn, String tableName, int id){
        Statement statement;
        try{
            String query = String.format("DELETE FROM %s WHERE id = %d", tableName,id);
            String queryAlter = "ALTER SEQUENCE " + tableName + "_id_seq RESTART";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(queryAlter);
            System.out.println("Data Pasien berhasil di hapus");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateData(Connection conn, String tableName, String nama, String alamat, String jenisKelamin, Integer umur, int id) {
        PreparedStatement preparedStatement = null;
        try {
            String query = String.format("UPDATE %s SET nama = ?, alamat = ?, jenisKelamin = ?, umur = ? WHERE id = ?", tableName);
            preparedStatement = conn.prepareStatement(query);

            // Setel nilai parameter
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, alamat);
            preparedStatement.setString(3, jenisKelamin);
            preparedStatement.setInt(4, umur);
            preparedStatement.setInt(5, id);

            // Eksekusi pernyataan UPDATE
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Data Updated");
            } else {
                System.out.println("Data not found for ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
