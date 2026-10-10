package genericUtility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtility {
    public String getDataFromProperties(String key) throws IOException {
        Properties p = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("CommonData.properties")) {
            if (in == null) throw new IOException("CommonData.properties not found");
            p.load(in);
        }
        String value = p.getProperty(key);
        if (value == null) throw new IOException("Missing property: " + key);
        return value.trim();
    }
}
