

package com.mycompany.kalkulatorsederhana;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KalkulatorSederhana extends JFrame {

    private JTextField display;
    private String angka = "";
    private String operator = "";
    private double hasil = 0;

    public KalkulatorSederhana() {
        // Set up frame
        setTitle("Kalkulator Sederhana");
        setSize(250, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Panel untuk tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 10, 10));

        // Label untuk tombol
        String[] tombol = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", "C", "=", "/"};

        // Menambahkan tombol ke panel dan menambahkan MouseListener untuk menangani event klik
        for (String label : tombol) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonClicked(button.getText());
                }
            });
            panelTombol.add(button);
        }

        add(panelTombol);

        setVisible(true);
    }

    // Fungsi untuk menangani tombol yang diklik
    private void buttonClicked(String command) {
        // Jika angka ditekan
        if (command.matches("[0-9]")) {
            angka += command;
            display.setText(angka);
        } 
        // Jika tombol clear (C) ditekan
        else if (command.equals("C")) {
            angka = "";
            operator = "";
            hasil = 0;
            display.setText("");
        } 
        // Jika tombol sama dengan (=) ditekan
        else if (command.equals("=")) {
            hitung();
            display.setText(String.valueOf(hasil));
            angka = "";
        } 
        // Jika operator ditekan
        else {
            if (!angka.isEmpty()) {
                hitung();
            }
            operator = command;
            angka = "";
        }
    }

    // Fungsi hitung operasi matematika
    private void hitung() {
        double angkaSekarang = Double.parseDouble(angka);

        switch (operator) {
            case "+":
                hasil += angkaSekarang;
                break;
            case "-":
                hasil -= angkaSekarang;
                break;
            case "*":
                hasil *= angkaSekarang;
                break;
            case "/":
                if (angkaSekarang != 0) {
                    hasil /= angkaSekarang;
                } else {
                    display.setText("Error"); // Tangani pembagian dengan nol
                    angka = "";
                }
                break;
            default:
                hasil = angkaSekarang;
                break;
        }
    }

    public static void main(String[] args) {
        new KalkulatorSederhana();
    }
}