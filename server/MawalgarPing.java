package cz.mawalgar.services.server;

import java.io.IOException;
import java.net.Socket;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@RequestScoped
public class MawalgarPing {

  private static final Logger LOG = LogManager.getLogger(MawalgarPing.class);


  public boolean hostAvailabilityCheck(final String serverAddress, final int port) {
    try (Socket s = new Socket(serverAddress, port)) {
      return true;
    } catch (IOException ex) {
      /* ignore */
    }
    return false;
  }

}