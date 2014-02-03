/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmsexerciciogfnb;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author helderdarocha
 */
public class JMSExercicioProdutorGFNB {

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
        
        // Criar produtor
        Connection con  = factory.createConnection();
        Session session = con.createSession();
        MessageProducer producer = session.createProducer(queue);
        
        // Enviar mensagem
        Message mensagem = session.createMessage();
        mensagem.setStringProperty("Titulo", "The Shining");
        mensagem.setStringProperty("Diretor", "Stanley Kubrick");
        mensagem.setIntProperty("Duracao", 144);
        mensagem.setStringProperty("IMDB", "tt0081505");
        
        producer.send(mensagem);
        System.out.println("Mensagem enviada!");
        
    }
    
}
