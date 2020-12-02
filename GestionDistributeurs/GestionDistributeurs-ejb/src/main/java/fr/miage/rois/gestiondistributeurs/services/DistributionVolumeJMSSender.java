/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.gestiondistributeurs.entities.Volume;
import java.util.List;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author sagab
 */
public class DistributionVolumeJMSSender {

    public static void envoyerVolumeAuxDistributeurs(Volume volume) {
        ConnectionFactory factory;
        Connection connection;
        String factoryName = "jms/__defaultConnectionFactory";
        String destName = "TopicD";
        Destination dest;
        Session session;
        MessageProducer sender;
        Gson gson = new Gson();
        Logger logger = Logger.getLogger(DistributionVolumeJMSSender.class);

        try {
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

            TextMessage tm = session.createTextMessage();
            
            tm.setText(new JsonParser().parse(gson.toJson(volume)).getAsString());
            
            sender.send(tm);

            connection.close();
        } catch (JMSException | NamingException e) {
            logger.error("Error during sending list of Volume with JMS.");
        }
    }

}
