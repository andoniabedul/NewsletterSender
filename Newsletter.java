/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsletter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author andoni
 */
public class Newsletter {

    
    public void Calendar(){
        Calendar calendario = new GregorianCalendar();
    }
    
    public void Fecha(){
        int date;

        String momentDate;
        
        if (Calendar.HOUR_OF_DAY < 12){
            momentDate = "Mañana";
        } else if (Calendar.HOUR_OF_DAY < 18){
            momentDate = "Tarde";
        } else {
            momentDate = "Noche";
        }
        
        Date dNow = new Date();
        SimpleDateFormat ft = 
        new SimpleDateFormat ("dd/MM/yyyy");
        String currentDate = ft.format(dNow);
        
        String Subject = ("Subject" + currentDate + momentDate ); 
        String SubjectMail = ("Subject #2" + Subject );
    }
    
    public void Envio(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("Newsletter.html");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                System.out.println(linea);
            }
            catch(Exception e){
                e.printStackTrace();
            }finally{
                // En el finally cerramos el fichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta
                // una excepcion.
                try{                   
                    if( null != fr ){  
                       fr.close();    
                    }                 
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        }
    
    public static void main(String[] args) {
        Lectura lectura = new Lectura();
        Fecha fecha = new Fecha();  
        
        // Dentro de un «try» por si algo sucede mal
        try {
            // Propiedades de la conexión
            Properties props = new Properties();

            //Host de donde se envia el correo
            props.setProperty("mail.smtp.host", "correo.telesurtv.net");

            props.setProperty("mail.smtp.starttls.enable", "true");

            //Puerto de donde se conecta 
            props.setProperty("mail.smtp.port", "25");

            //Usuario de donde se envía el correo
            props.setProperty("mail.smtp.user", "correo@dominio.com");

            //Autenticación TRUE
            props.setProperty("mail.smtp.auth", "true");
            
            //Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            //Construimos el mensaje
            MimeMessage message = new MimeMessage(session);

            //Metadatos para el campo FROM del correo
            message.setFrom(new InternetAddress("Nombre <correo@dominio.com>"));

            //A quien se envía el mensaje
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("receptor@dominio.com"));

            //Asunto del correo
            message.setSubject(fecha.FechaMethod() );

            //Asignas el texto, en este caso la lectura de la página señalada
            message.setText(lectura.Lectura1(),  "ISO-8859-1", "html" );

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("webtelesur@telesurtv.net", "/W3b-Tele$ur..");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }    
    }
    
}
