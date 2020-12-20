package fr.miage.rois.distributeur.services;

import com.google.gson.Gson;
import fr.miage.rois.distributeur.entities.Abonnement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * <code>MessageProducer</code> example.
 *
 * @author <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @version $Revision: 1.3 $ $Date: 2005/11/18 03:28:01 $
 */
public class Sender extends Thread {
    
    private Abonnement abonnement;

    /**
     * Run line.
     *
     */
    @Override
    public void run() {
        Context context = null;
        ConnectionFactory factory;
        Connection connection = null;
        String factoryName = "jms/__defaultConnectionFactory";
        String destName = "QueueD";
        Destination dest;
        Session session;
        MessageProducer sender;
        Gson gson = new Gson();
        
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        /*
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        props.put(Context.PROVIDER_URL, "http://localhost:3700/");
        */
        try {
            
            // create the JNDI initial context.
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(destName);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                    false, Session.AUTO_ACKNOWLEDGE);

            // create the sender
            sender = session.createProducer(dest);

            // start the connection, to enable message sends
            connection.start();
            
            TextMessage message = session.createTextMessage();
            message.setText(gson.toJson(this.abonnement));
            
            sender.send(message);
            System.out.println("Sent: " + message.getText());
            
        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
}
