/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmsexercicio2.glassfish.client;

import java.util.Date;
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author helderdarocha
 */
public class ConsumidorDataHoraGF {
    public static void main(String[] args) throws NamingException, JMSException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
        props.put("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put(Context.SECURITY_PRINCIPAL, "admin");
        props.put(Context.SECURITY_CREDENTIALS, "helder");
        Context jndi = new InitialContext(props);
        
        ConnectionFactory factory = (ConnectionFactory) jndi.lookup("jms/__defaultConnectionFactory");
        Destination queue = (Destination) jndi.lookup("jms/queue");
        
        JMSContext jms = factory.createContext();
        
        System.out.println("Vou consumir...");
        Message m = jms.createConsumer(queue).receive();
        System.out.println("Recebido: " + new Date(m.getLongProperty("DateTime")));
    }   
}
