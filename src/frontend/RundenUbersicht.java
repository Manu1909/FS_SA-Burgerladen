package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RundenUbersicht extends JFrame implements ActionListener {
	private JButton btnWeiter = new JButton();
	private business.Unternehmen un;
	JFrame frame = new JFrame();
	JPanel contentPane = new JPanel();

	public RundenUbersicht() {
		buildWindow();
	}

	private void buildWindow() {
		btnWeiter.setBounds(100, 200, 40, 30);
		btnWeiter.setText("Weiter");
		btnWeiter.addActionListener(this);
		contentPane.add(btnWeiter);

		frame.setContentPane(contentPane);
		frame.setBounds(500, 400, 500, 400);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == btnWeiter) {
			if (Controller.Controller.getRunde() < 12) {
				Controller.Controller.setRunde(Controller.Controller.getRunde() + 1);
				Overview overview = new Overview(0);
			}
		}
	}
}
