package pl.edu.us.pp;

import pl.edu.us.pp.GUI.MainMenu;
import pl.edu.us.pp.Logic.Languages.CppLanguageAnalizer;
import pl.edu.us.pp.Logic.Utility.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
        String content = new Scanner(new File("test1.cpp")).useDelimiter("\\Z").next();
        CppLanguageAnalizer cppAnalizer = new CppLanguageAnalizer(content);
        Report raport = cppAnalizer.analizeCode();
        System.out.println("Status is valid: " + raport.isStatus());
        System.out.println(raport.getMessages().size());
        if(!raport.isStatus()){
            raport.getMessages().stream().forEach(e -> {System.out.println(e.getValue());
                System.out.println();});
        }
        */
        
        MainMenu okno = new MainMenu();
        okno.setSize(800,600);
        okno.setVisible(true);
    }
}
