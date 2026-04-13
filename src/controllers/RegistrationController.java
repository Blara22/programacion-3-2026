package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.User;
import views.LoginWindow;
import views.RegistrationWindow;

public class RegistrationController {

    private RegistrationWindow view;

    public RegistrationController(RegistrationWindow view){
        this.view = view;

        registerListeners();
    }

    private void registerListeners(){

        view.getBtnValidate().addActionListener(e -> {

            if(validateForm()){

                User user = new User(
                        view.getUserName(),
                        view.getEmail(),
                        view.getCountry(),
                        view.getGender(),
                        view.getDescription(),
                        view.getLanguages()
                );
                
                System.out.println(user);
                System.out.println("Guardado.");

            }

        });

        view.getBtnReturn().addActionListener(e -> {

            int option = view.confirmReturn();

            if(option == JOptionPane.YES_OPTION){
                new LoginController(new LoginWindow().getLoginView());
                view.dispose();
            }

        });

        view.getTxtName().addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent e){

            	char c = e.getKeyChar();
            	
                if(!Character.isAlphabetic(c) && e.getKeyChar() != ' '){
                    e.consume();
                }

                if(Character.isLowerCase(c)){
                	e.setKeyChar(Character.toUpperCase(c));
                }
                
                //Que no tenga más de 10 caracteres
                /*if(view.getTxtName().getText().length() >= 10){
                    e.consume();
                }*/
            }

            @Override
            public void keyPressed(KeyEvent e){

                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    view.getTxtEmail().requestFocusInWindow();
                }

            }

        });

        view.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent e){
                view.getTxtName().requestFocusInWindow();
            }

        });

        view.getTxtName().getDocument().addDocumentListener(new DocumentListener(){

            public void insertUpdate(DocumentEvent e){
                validateName();
            }

            public void removeUpdate(DocumentEvent e){
                validateName();
            }

            public void changedUpdate(DocumentEvent e){
                validateName();
            }

        });

        view.getCboCountry().addActionListener(e -> validateCountry());

        view.getChkTerms().addActionListener(e -> validateTerms());

        view.getLstLanguages().addListSelectionListener(e -> validateLanguages());

    }

    private boolean validateForm(){

        view.resetErrors();

        boolean valid=true;

        if(!validateName()) valid=false;
        if(!validateEmail()) valid=false;
        if(!validateCountry()) valid=false;
        if(!validateGender()) valid=false;
        if(!validateTerms()) valid=false;
        if(!validateDescription()) valid=false;
        if(!validateLanguages()) valid=false;

        return valid;
    }

    private boolean validateName(){

        if(view.getUserName().trim().isEmpty()){
            view.setErrorName("El nombre es obligatorio");
            return false;
        }

        if(view.getUserName().trim().length()<=3){
            view.setErrorName("Mínimo 4 caracteres");
            return false;
        }

        view.setErrorName("");
        return true;
    }

    private boolean validateEmail(){

        if(view.getEmail().trim().isEmpty()){
            view.setErrorEmail("El email es obligatorio");
            return false;
        }

        if(!view.getEmail().contains("@")){
            view.setErrorEmail("Email inválido");
            return false;
        }

        view.setErrorEmail("");
        return true;
    }

    private boolean validateCountry(){

        if(view.getCountryIndex()==0){
            view.setErrorCombo("Seleccione un país");
            return false;
        }

        view.setErrorCombo("");
        return true;
    }

    private boolean validateGender(){

        if(view.getGenderGroup().getSelection()==null){
            view.setErrorGender("Seleccione un género");
            return false;
        }

        view.setErrorGender("");
        return true;
    }

    private boolean validateTerms(){

        if(!view.isTermsAccepted()){
            view.setErrorTerms("Debe aceptar los términos");
            return false;
        }

        view.setErrorTerms("");
        return true;
    }

    private boolean validateDescription(){

        if(view.getDescription().trim().length()<10){
            view.setErrorDescription("Descripción mínima 10 caracteres");
            return false;
        }

        view.setErrorDescription("");
        return true;
    }

    private boolean validateLanguages(){

        if(view.getLanguages().isEmpty()){
            view.setErrorList("Seleccione al menos un lenguaje");
            return false;
        }

        view.setErrorList("");
        return true;
    }

}