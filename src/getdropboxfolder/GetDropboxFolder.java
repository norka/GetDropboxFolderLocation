/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdropboxfolder;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Royal 
 * 
 */
public class GetDropboxFolder {

    private static String homelocation = System.getProperty("user.home");
    private static final String osname = System.getProperty("os.name").toLowerCase();

    /**
     * 
     * @return returns path of Dropbox folder as a string
     */
    public static String GetDropboxFolder() {

        if (osname.startsWith("windows")) {
            homelocation = homelocation + "\\AppData\\Roaming\\Dropbox\\host.db";
        } else if (osname.startsWith("linux") || osname.startsWith("darwin")) {
            homelocation = homelocation + "/.dropbox/host.db";
        }
        

        byte[] Dropboxlocation;
        String location = "";
        String linein;
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader(homelocation));

             linein = reader.readLine();
             linein = reader.readLine();
            reader.close();
            
            // 64 binary to assci value array
            Dropboxlocation = DatatypeConverter.parseBase64Binary(linein); 
            
            // assci value array to string
            for (int i = 0; i < Dropboxlocation.length; i++) {
                char s = (char) Dropboxlocation[i];
                location = location + s;

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GetDropboxFolder.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(GetDropboxFolder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return location;
    }


}
