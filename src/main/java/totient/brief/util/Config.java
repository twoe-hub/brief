package totient.brief.util;

import java.util.ResourceBundle;

public enum Config {
  CONF;
  
  private final ResourceBundle props = ResourceBundle.getBundle("config");
  private final String HTTP_SERVER = "http.server";
  private final String REDIS_SERVER = "redis.server";
  private final String HTTP_PORT = "http.port";
  private final String REDIS_PORT = "redis.port";
  private final String REDIS_DB = "redis.db";
  private final String APP_NAME = "app.name";
  
  public String getServer() {
    return props.getString(REDIS_SERVER);
  }

  public int getPort() {
    return Integer.parseInt(props.getString(REDIS_PORT));
  }

  public int getDB() {
    return Integer.parseInt(props.getString(REDIS_DB));
  }

  public String getBriefDomain() {
    return "http://" + props.getString(HTTP_SERVER)
            + ":" + props.getString(HTTP_PORT)
            + "/" + props.getString(APP_NAME) + "/";
  }

}
