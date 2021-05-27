package MyTesters;

import java.awt.*;
import javax.swing.*;

/**
 * Class Visualizer - GUI para visualizar una imagen asociada a un tester.
 * @author Ignacio Joaquín Dotta
 *
 */
public class Visualizer extends JFrame {
	
	protected JPanel main;
	protected JLabel image;
	protected Dimension default_dim;
	
	/**
	 * Construye la GUI.
	 * @param image_path Ruta del archivo a visualizar.
	 */
	public Visualizer(String image_path) {
		super("Visualizador de elementos no txt");
		initialize(image_path);
	}

	private void initialize(String image_path) {
		default_dim = new Dimension(450, 300);
		setVisible(true);
		setSize(default_dim);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Main panel
		main = new JPanel();
		image = new JLabel();
		
		getContentPane().add(main);
		main.setSize(default_dim);
		main.add(image);
		
		image.setIcon(new ImageIcon(image_path));
	}

}
