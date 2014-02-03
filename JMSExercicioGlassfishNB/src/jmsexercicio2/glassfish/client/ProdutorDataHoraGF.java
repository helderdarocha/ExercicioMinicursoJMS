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
 * Hello world!
 *
 */
public class ProdutorDataHoraGF {

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
        Message message = jms.createMessage();
        
        System.out.println("Enviando...");
        jms.createProducer().setProperty("DateTime", new Date().getTime()).send(queue, message);
        System.out.println("Enviada!");
    }
}
