package main;

import javax.swing.*;
import java.util.Objects;

public class LandingWindow extends JFrame {
    String TEXT_FIELD = "Ingresá tu nombre";
    String AGGRESIVE_TEXT_FIELD = "PONÉ EL NOMBRE";

    public LandingWindow() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        JButton button = new JButton("Entrar");
        button.setBounds(200, 240, 100, 20);
        this.add(button);
        JTextField field = new JTextField(TEXT_FIELD);
        field.setBounds(175, 200, 150, 20);
        this.add(field);
        button.addActionListener(e -> {
            if(!Objects.equals(field.getText(), TEXT_FIELD) && !Objects.equals(field.getText(), AGGRESIVE_TEXT_FIELD)) {
                dispose();
                new GameWindow(field.getText());
            } else {
                field.setText(AGGRESIVE_TEXT_FIELD);
            }
        });
        this.setVisible(true);
    }
}
