package handle.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Activity;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ActivityHandle {
    private static final String FILE_PATH = "src/data/activity.json";

    // Đọc danh sách từ file JSON
    public static ArrayList<Activity> loadActivities() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<ArrayList<Activity>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Ghi danh sách vào file JSON
    public static void saveActivities(ArrayList<Activity> activities) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), activities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}