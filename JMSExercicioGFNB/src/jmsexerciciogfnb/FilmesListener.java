/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmsexerciciogfnb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author helderdarocha
 */
public class FilmesListener implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Mensagem recebida assincronamente:");
            System.out.println(msg.getStringProperty("Titulo"));
            System.out.println(msg.getStringProperty("Diretor"));
            System.out.println(msg.getIntProperty("Duracao"));
            System.out.println(msg.getStringProperty("IMDB"));
        } catch (JMSException ex) {
            Logger.getLogger(FilmesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
