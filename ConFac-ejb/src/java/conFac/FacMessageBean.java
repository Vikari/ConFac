/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conFac;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author jukka
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/mydest")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class FacMessageBean implements MessageListener {

    public FacMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println(msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(FacMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
