
package com.training.javaee.servletcdi.managedbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author training
 */
@ApplicationScoped
public class ServicioStreams {
    
    
    public void copiarInputStreamAOutStream(InputStream is,
            OutputStream os) throws IOException{
        
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
    }
    
}
