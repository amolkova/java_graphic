package frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private final JTextField textField;
    private char operation;
    
    private boolean isFirstDigit;

    public MyFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 170, 300);
        setVisible(true);
        isFirstDigit = true;
        
        JPanel jPanel = new JPanel();

        textField = new JTextField(12);
        textField.setEditable(false);

        jPanel.add(textField);

        JButton[] jbs = new JButton[9];
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton(String.valueOf(i + 1));
            jbs[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    action(e);
                }
            });
            jPanel.add(jbs[i]);
        }

        JButton buttonZero = new JButton("0");
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonMultiplay = new JButton("*");
        JButton buttonSplit = new JButton(":");
        JButton buttonEquals = new JButton("=");
        JButton buttonCleanTextField = new JButton("CE");
        buttonZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        
        buttonMultiplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        
        buttonSplit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculating(e);
            }
        }); 
        
        buttonCleanTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	deleteAction(e);
            }
        });

        jPanel.add(buttonZero);
        jPanel.add(buttonPlus);
        jPanel.add(buttonMinus);
        jPanel.add(buttonMultiplay);
        jPanel.add(buttonSplit);
        jPanel.add(buttonEquals);
        jPanel.add(buttonCleanTextField);
        add(jPanel);
    }

    /**
     * 1) Р‘РµСЂС‘Рј С‚РµРєСЃС‚ РёР· С‚РµРєСЃС‚РѕРІРѕРіРѕ РїРѕР»СЏ
     * 2) Р�РґС‘Рј РїРѕ РєР°Р¶РґРѕРјСѓ СЃРёРјРІРѕР»Сѓ РїРѕРєР° РЅРµ РІСЃС‚СЂРµС‚РёРј + РёР»Рё -
     * 3) Р—Р°С‚РµРј РїРµСЂРµРІРѕРґРёРј РЅР°С€Рµ С‡РёСЃР»Рѕ РёР· String РІ Double
     * 4) Р—Р°РїРёСЃС‹РІР°РµРј С‚РµРєСѓС‰РёР№ СЃРёРјРІРѕР»(+ РёР»Рё -)
     * 5) Р•СЃР»Рё Сѓ РЅР°СЃ СѓР¶Рµ Р·Р°РїРёСЃР°РЅРѕ РѕРґРЅРѕ С‡РёСЃР»Рѕ, РїСЂРѕРІРѕРґРёРј РѕРїРµСЂР°С†РёСЋ Рё РїРµСЂРµС…РѕРґРёРј РЅР° С€Р°Рі 2, РёРЅР°С‡Рµ РїРµСЂРµС…РѕРґРёРј РЅР° С€Р°Рі 2
     *
     * @param e
     */
    private void calculating(ActionEvent e) {
    	double total = 0;
        String text = textField.getText();
        String digit = "";
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (letter == '-' || letter == '+' || letter == '*' || letter == ':') {
                double currentValue = Double.parseDouble(digit);
                if (isFirstDigit) {
                    total = currentValue;
                    isFirstDigit = false;
                } else {
                    if (operation == '-') {
                        total -= currentValue;
                    } else if (operation == '+') {
                        total += currentValue;
                    } else if (operation == '*') {
                    	total = total * currentValue;
                    } else if (operation == ':') {
                    	total = total / currentValue;
                    }
                }

                digit = "";
                operation = letter;
                continue;
            }

            digit += letter;
        }

        double currentValue = Double.parseDouble(digit);
        if (operation == '-') {
            total -= currentValue;
        } else if (operation == '+') {
            total += currentValue;
        } else if (operation == '*') {
        	total = total * currentValue;
        } else if (operation == ':') {
        	total = total / currentValue;
        }


        isFirstDigit = true;
        textField.setText(String.valueOf(total));
        total = 0;
    }

    public void action(ActionEvent event) {
        textField.setText(textField.getText() + event.getActionCommand());
    }
    
    public void deleteAction(ActionEvent event) {
        textField.setText("");
    }

}
