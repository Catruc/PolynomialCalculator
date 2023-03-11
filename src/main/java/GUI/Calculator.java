package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Calculator {

    JTextField selectedTextField = null;

    public void calculatorInterface() {

        /// JFrame

        JFrame calculatorFrame = new JFrame("POLYNOMIAL CALCULATOR");   /// new frame created
        calculatorFrame.setSize(800, 600);          /// frame dimensions
        calculatorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   /// default operation for closing the frame
        calculatorFrame.setLayout(null);                   /// layout set to null in order to be able to place the objects on the frame wherever I want to

        /// JTextField

        JTextField firstPolynomialText = new JTextField();     /// textField for the first Polynomial
        JTextField secondPolynomialText = new JTextField();    /// textField for the second Polynomial
        JTextField resultText = new JTextField();   /// textField for the result
        firstPolynomialText.setBounds(180, 40, 600, 30);  /// size and coordinates for the firstPolynomial
        secondPolynomialText.setBounds(180, 90, 600, 30); /// size and coordinates for the secondPolynomial
        resultText.setBounds(180, 140, 600, 30);   /// size and coordinates for the resultText
        resultText.setEditable(false);     /// resultText cannot be edited

        /// JLabel

        JLabel firstPolynomialTitle = new JLabel("FIRST POLYNOMIAL:");  /// a label to show the user where to write th first Polynomial
        JLabel secondPolynomialTitle = new JLabel("SECOND POLYNOMIAL:");  /// a label to show the user where to write th second Polynomial
        JLabel resultTitle = new JLabel("RESULT:");    /// a label to show where the result is printed
        firstPolynomialTitle.setBounds(50, 30, 190, 50);    /// size and coordinates for the firstPolynomialTitle Label
        secondPolynomialTitle.setBounds(30, 80, 190, 50);   /// size and coordinates for the secondPolynomialTitle Label
        resultTitle.setBounds(75, 130, 190, 50);         /// size and coordinates for the resultTitle Label


        /// JButton

        JButton digit0 = new JButton("0");   /// a button for digit 0
        JButton digit1 = new JButton("1");   /// a button for digit 1
        JButton digit2 = new JButton("2");   /// a button for digit 2
        JButton digit3 = new JButton("3");   /// a button for digit 3
        JButton digit4 = new JButton("4");   /// a button for digit 4
        JButton digit5 = new JButton("5");   /// a button for digit 5
        JButton digit6 = new JButton("6");   /// a button for digit 6
        JButton digit7 = new JButton("7");   /// a button for digit 7
        JButton digit8 = new JButton("8");   /// a button for digit 8
        JButton digit9 = new JButton("9");   /// a button for digit 9
        JButton plusButton = new JButton("+");  /// a button for + sign
        JButton minusButton = new JButton("-");  /// a button for - sign
        JButton powerButton = new JButton("^");  /// a button for ^ sign
        JButton xButton = new JButton("x");  /// a button for x sign
        JButton deleteButton = new JButton("del");  /// a button for deleting a character
        digit0.setBounds(120, 490, 70, 50);  /// size and coordinates for the digit0 Button
        digit1.setBounds(20, 280, 70, 50);  /// size and coordinates for the digit1 Button
        digit2.setBounds(120, 280, 70, 50);  /// size and coordinates for the digit2 Button
        digit3.setBounds(220, 280, 70, 50);  /// size and coordinates for the digit3 Button
        digit4.setBounds(20, 350, 70, 50);  /// size and coordinates for the digit4 Button
        digit5.setBounds(120, 350, 70, 50);  /// size and coordinates for the digit5 Button
        digit6.setBounds(220, 350, 70, 50);  /// size and coordinates for the digit6 Button
        digit7.setBounds(20, 420, 70, 50);  /// size and coordinates for the digit7 Button
        digit8.setBounds(120, 420, 70, 50);  /// size and coordinates for the digit8 Button
        digit9.setBounds(220, 420, 70, 50);  /// size and coordinates for the digit9 Button

        plusButton.setBounds(400,280,70,50);  /// size and coordinates for the plus Button
        minusButton.setBounds(400,350,70,50);  /// size and coordinates for the minus Button
        powerButton.setBounds(400,420,70,50);  /// size and coordinates for the power Button
        xButton.setBounds(20,490,70,50);     /// size and coordinates for the power Button
        deleteButton.setBounds(220,490,70,50);  /// size and coordinates for the delete Button


        /// adding to the frame

        calculatorFrame.add(deleteButton);  /// delete Button added tot the frame
        calculatorFrame.add(xButton);  /// x Button added to the frame
        calculatorFrame.add(plusButton);  /// plus Button added to the frame
        calculatorFrame.add(minusButton);  /// minus Button added to the frame
        calculatorFrame.add(powerButton);  /// power Button added to the frame
        calculatorFrame.add(digit0);     /// digit0 Button added to the frame
        calculatorFrame.add(digit1);     /// digit1 Button added to the frame
        calculatorFrame.add(digit2);     /// digit2 Button added to the frame
        calculatorFrame.add(digit3);     /// digit3 Button added to the frame
        calculatorFrame.add(digit4);     /// digit4 Button added to the frame
        calculatorFrame.add(digit5);     /// digit5 Button added to the frame
        calculatorFrame.add(digit6);     /// digit6 Button added to the frame
        calculatorFrame.add(digit7);     /// digit7 Button added to the frame
        calculatorFrame.add(digit8);     /// digit8 Button added to the frame
        calculatorFrame.add(digit9);     /// digit9 Button added to the frame
        calculatorFrame.add(resultTitle);    /// resultTitle Label added to the frame
        calculatorFrame.add(resultText);     /// resultText TextField added to the frame
        calculatorFrame.add(firstPolynomialTitle);    /// firstPolynomial Label added to the Frame
        calculatorFrame.add(secondPolynomialTitle);   /// secondPolynomial Label added to the Frame
        calculatorFrame.add(firstPolynomialText);     /// firstPolynomial textField added to the Frame
        calculatorFrame.add(secondPolynomialText);    /// secondPolynomial textField added to the Frame


        /// FocusListener to check which text field is selected
        firstPolynomialText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                selectedTextField = firstPolynomialText;
            }

            @Override
            public void focusLost(FocusEvent e) {
                // No need to change selectedTextField
            }
        });


        secondPolynomialText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                selectedTextField = secondPolynomialText;
            }

            @Override
            public void focusLost(FocusEvent e) {
                // No need to change selectedTextField
            }
        });

        /// ActionListeners to add digits
        /// If the firstPolynomialText is selected, you can write into it, else you can write into the other one
        digit0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "0");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "0");
                }
            }
        });


        digit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "1");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "1");
                }
            }
        });


        digit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "2");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "2");
                }
            }
        });


        digit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "3");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "3");
                }
            }
        });


        digit4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "4");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "4");
                }
            }
        });


        digit5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "5");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "5");
                }
            }
        });


        digit6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "6");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "6");
                }
            }
        });


        digit7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "7");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "7");
                }
            }
        });


        digit8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "8");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "8");
                }
            }
        });


        digit9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "9");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "9");
                }
            }
        });

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "+");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "+");
                }
            }
        });

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "-");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "-");
                }
            }
        });


        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "^");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "^");
                }
            }
        });


        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField == firstPolynomialText) {
                    firstPolynomialText.setText(firstPolynomialText.getText() + "x");
                } else if (selectedTextField == secondPolynomialText) {
                    secondPolynomialText.setText(secondPolynomialText.getText() + "x");
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTextField != null) {
                    String text = selectedTextField.getText();
                    int caretPosition = selectedTextField.getCaretPosition();
                    if (caretPosition > 0) {         /// we put the coondition if there is one character before the caret position
                        String newText = text.substring(0, caretPosition - 1) + text.substring(caretPosition);  ///we remove the character
                        selectedTextField.setText(newText);
                        selectedTextField.setCaretPosition(caretPosition - 1);    /// then we modify the caret position
                    }
                }
            }
        });


        /// visible

        calculatorFrame.setVisible(true);       /// frame set to be visible

    }

}
