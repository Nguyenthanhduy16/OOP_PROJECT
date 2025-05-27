package handle.login;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;

public class LoginHandle {
    private List<User> users;

    public LoginHandle(String filePath) {
        try {
        	
        	ObjectMapper mapper = new ObjectMapper(); 

        	// Đọc file từ resources (classpath)
        	InputStream is = getClass().getClassLoader().getResourceAsStream("data/data.json");
        	if (is == null) {
        	    System.err.println("Không tìm thấy file data.json");
        	    return;
        	}

        	this.users = mapper.readValue(is, new TypeReference<List<User>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
