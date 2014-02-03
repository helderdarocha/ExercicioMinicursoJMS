package jmsexercicio2.jboss.client;

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
public class ProdutorDataHoraJB {

    public static void main(String[] args) throws NamingException, JMSException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL,"http-remoting://localhost:8080"); 
        props.put("jboss.naming.client.ejb.context", true);
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context jndi = new InitialContext(props);
        
        ConnectionFactory factory = (ConnectionFactory) jndi.lookup("jms/RemoteConnectionFactory");
        Destination queue = (Destination) jndi.lookup("jms/queue");
        
        JMSContext jms = factory.createContext("helder", "@rgonav1s");
        Message message = jms.createMessage();
        
        System.out.println("Enviando...");
        jms.createProducer().setProperty("DateTime", new Date().getTime()).send(queue, message);
        System.out.println("Enviada!");
    }
}
