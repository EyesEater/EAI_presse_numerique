/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Article;
import java.util.List;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author sagab
 */
public class RedacChefJMSSender {
    
    public static void envoyerArticlesAMiseSousPresse(List<Article> articles) throws JMSException, NamingException {
        ConnectionFactory factory;
        Connection connection;
        String factoryName = "jms/__defaultConnectionFactory";
        String destName = "QueueRC";
        Destination dest;
        Session session;
        MessageProducer sender;

        System.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext context = new InitialContext();
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


        StreamMessage sm = session.createStreamMessage();

        for (Article a : articles) {
            sm.writeObject(a);
        }

        sender.send(sm);
        
        connection.close();
    }
    
}
