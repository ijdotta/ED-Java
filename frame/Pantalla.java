package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pantalla extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngreseLaContrasea;
	private JButton btnNewButton, btnNewButton_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla frame = new Pantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIngreseLaContrasea = new JTextField();
		txtIngreseLaContrasea.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtIngreseLaContrasea.setText("Ingrese la contrase\u00F1a...");
		txtIngreseLaContrasea.setBounds(168, 52, 397, 51);
		contentPane.add(txtIngreseLaContrasea);
		txtIngreseLaContrasea.setColumns(10);
		
		btnNewButton = new JButton("Cifrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(txtIngreseLaContrasea.getText().getBytes().toString());
			}
		});
		btnNewButton.setBounds(168, 113, 169, 88);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Descifrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(txtIngreseLaContrasea.getText().getBytes().toString());
			}
		});
		btnNewButton_1.setBounds(347, 113, 218, 88);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("Aqu\u00ED ver\u00E1 su cifrado o descifrado");
		lblNewLabel.setBounds(168, 211, 397, 74);
		contentPane.add(lblNewLabel);
	}
}
