package org.codehaus.mojo.properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.util.Properties;

/**
 * Generates a string of all properties as command line arguments.
 *
 * @author <a href="mailto:par.eklund@ooyala.com">Par Eklund</a>
 * @version $Id$
 * @goal read-properties-as-commandline
 */
public class ReadPropertiesAsCommandLineMojo extends AbstractReadPropertiesMojo {

   /**
    * The name of the property to hold the command line property arguments
    *
    * @parameter
    */
   private String commandLinePropertyName;

   public void execute() throws MojoExecutionException, MojoFailureException {
      Properties properties = loadAndResolveProperties(false);
      setCommandLineProperty(properties);
   }

   private void setCommandLineProperty(Properties properties) {
      StringBuilder sb = new StringBuilder();
      for (String key : properties.stringPropertyNames()) {
         String prop = properties.getProperty(key);
         if (!prop.contains(" ")) {
            sb.append(" -D").append(key).append("=").append(properties.getProperty(key));
         }
      }
      project.getProperties().put(commandLinePropertyName, sb.toString());
   }
}
