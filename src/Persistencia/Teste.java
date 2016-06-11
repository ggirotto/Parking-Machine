package Persistencia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Teste {
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        ArrayList<TicketDTO> listaTickets = new ArrayList<>();
        ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(new
                BufferedInputStream(new FileInputStream("listaTickets.bin")));
                listaTickets = (ArrayList<TicketDTO>) in.readObject();
            } finally {
                in.close();
            }
            
            for(TicketDTO ticket : listaTickets)
                    System.out.format ("\nTicker Serial : " + ticket.getSerial());
    }
}
