package com.mycompany.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculator extends JFrame {
    private JTextField angkaPertama;
    private JTextField angkaKedua;
    private JTextField hasil;

    public Calculator() {
        // Pengaturan Frame
        setTitle("Kalkulator Sederhana");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Membuat panel utama untuk menampung komponen
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Menambahkan padding antara komponen

        // Label dan TextField untuk input angka dan hasil
        JLabel labelAngkaPertama = new JLabel("Masukkan Angka Pertama:");
        JLabel labelAngkaKedua = new JLabel("Masukkan Angka Kedua:");
        JLabel labelHasil = new JLabel("Hasil:");

        angkaPertama = new JTextField(10);
        angkaKedua = new JTextField(10);
        hasil = new JTextField(10);
        hasil.setEditable(false); // Hasil tidak bisa diubah

        // Tombol operasi
        JButton tambah = new JButton("+");
        JButton kurang = new JButton("-");
        JButton kali = new JButton("*");
        JButton bagi = new JButton("/");

        // Menambah komponen ke panel dengan layout GridBag
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(labelAngkaPertama, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(angkaPertama, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(labelAngkaKedua, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(angkaKedua, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(labelHasil, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(hasil, gbc);

        // Panel untuk tombol operasi
        JPanel panelTombol = new JPanel(new GridLayout(1, 4, 10, 10)); // Mengatur tombol dalam satu baris
        panelTombol.add(tambah);
        panelTombol.add(kurang);
        panelTombol.add(kali);
        panelTombol.add(bagi);

        // Menambahkan panel tombol ke frame
        add(panel, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        // Listener untuk setiap tombol menggunakan MouseListener
        tambah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hitung('+');
            }
        });

        kurang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hitung('-');
            }
        });

        kali.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hitung('*');
            }
        });

        bagi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hitung('/');
            }
        });
    }

    // Fungsi untuk menghitung operasi aritmatika
    private void hitung(char operator) {
        try {
            // Ambil nilai dari text field
            double num1 = Double.parseDouble(angkaPertama.getText());
            double num2 = Double.parseDouble(angkaKedua.getText());
            double result = 0;

            // Lakukan operasi berdasarkan operator yang dipilih
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        hasil.setText("Tidak bisa dibagi nol");
                        return;
                    }
                    break;
            }

            // Tampilkan hasil di text field
            hasil.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            hasil.setText("Input tidak valid");
        }
    }

    public static void main(String[] args) {
        Calculator kalkulator = new Calculator();
        kalkulator.setVisible(true);
    }
}
