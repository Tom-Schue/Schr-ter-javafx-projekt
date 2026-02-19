package org.example;

import java.util.ArrayList;
import java.util.List;

public class To_Do_List {
    private List<String> aufgaben = new ArrayList<>();

    public void hinzufuegen(String aufgabe) {
        aufgaben.add(aufgabe);
        System.out.println("Hinzugefügt: " + aufgabe);
    }

    public void loeschen(String aufgabe) {
        aufgaben.remove(aufgabe);
        System.out.println("Gelöscht: " + aufgabe);
    }

    public void anzeigen() {
        System.out.println("Aktuelle Aufgaben:");
        for (String a : aufgaben) {
            System.out.println("- " + a);
        }
    }
}

