package handle.login;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.User;

public class LoginHandle {
    private List<User> users;
    
    // Xử lý cache
    public List<User> loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File("src/data/data.json");
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
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
