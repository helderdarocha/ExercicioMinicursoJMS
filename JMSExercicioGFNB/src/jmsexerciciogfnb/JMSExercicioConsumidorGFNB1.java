/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmsexerciciogfnb;

import java.util.Properties;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author helderdarocha
 */
public class JMSExercicioConsumidorGFNB1 {

    public static void main(String[] args) throws NamingException, JMSException {
        // Configurar acesso JNDI
        Properties propriedades = new Properties();
        propriedades.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        propriedades.put(Context.SECURITY_PRINCIPAL, "jmsuser");
        propriedades.put(Context.SECURITY_CREDENTIALS, "jmsuser");
        propriedades.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
        propriedades.put("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

        Context jndi = new InitialContext(propriedades);
        
        // Obter fabrica de conexoes
        String nomeJndiConFactory = "jms/__defaultConnectionFactory";
        ConnectionFactory factory = (ConnectionFactory) jndi.lookup(nomeJndiConFactory);
        
        // Obter destino
        String nomeJndiDestination = "jms/filmes";
        Queue queue = (Queue) jndi.lookup(nomeJndiDestination);
        
        // Criar consumidor
        Connection con  = factory.createConnection();
        Session session = con.createSession();
        MessageConsumer consumidor = session.createConsumer(queue);
        
        con.start();
        
        // Receber mensagem - e imprimir
        System.out.print("Esperando mensagem...");
        Message mensagem = consumidor.receive();
        
        System.out.println("Mensagem recebida:");
        System.out.println(mensagem.getStringProperty("Titulo"));
        System.out.println(mensagem.getStringProperty("Diretor"));
        System.out.println(mensagem.getIntProperty("Duracao"));
        System.out.println(mensagem.getStringProperty("IMDB"));
    }
    
}
