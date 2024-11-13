package tpsit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Hello world!");

        Socket s = new Socket(" localhost", 3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        System.out.println("MENU");
        String stringa = in.readLine();
        List contatti = new ArrayList<String>();

        boolean loop = true;
        do {

            switch (stringa) {
                case "1":
                    out.writeBytes("LISTA_CONTATTI");
                    contatti.add(in.readLine()); /* riceve "LISTA_CONTATTI " */
                    /* STAMPARE I CONTATTI */
                    for (int i = 0; i < contatti.size(); i++) {
                        System.out.print(i);
                    }
                    /* TORNARE INDIETRO */
                    out.writeBytes("INDIETRO");

                    /* CONNESSO O NON CONNESSO */
                    in.readLine();
                    if (stringa.equals("CONNESSO")) {
                        System.out.println("Inserire messaggio");
                        /* Inserire il messaggio */

                        Scanner mess = new Scanner(System.in);
                        
                        /* Manda al server "MESSAGGIO" */
                        out.writeBytes("MESSAGGIO");
                    } else if (stringa.equals("NON_CONNESSO")) {
                        /* Non connesso User */

                        /* MANDA UNA STRINGA NON VALIDO PER FARE CAPIRE CHE L'USER NON è PRESENTE */
                        out.writeBytes("NON_VALIDO");
                    }

                    break;
                case "2":
                    out.writeBytes("SCELTA_CONTATTO");
                    in.readLine(); /* riceve SCELTA_CONTATTO */
                    /* Digitare l'user del contatto */
                    System.out.println("Scrivi l'username con cui vuoi chattare");
                    Scanner user = new Scanner(System.in);

                    /* TORNA L'USERNAME */
                    out.writeBytes(user.nextLine() + "\n");
                    break;
                case "3":
                    out.writeBytes("MENUCHAT");
                    in.readLine(); /* riceve MENU */
                    loop = false;
                    break;
            }
        } while (loop);
    }
}