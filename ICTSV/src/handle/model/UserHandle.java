package handle.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.Activity;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHandle {
    private static final String DATA_JSON_PATH = "src/data/data.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Load raw JsonNode users (admin + student)
    private static List<JsonNode> loadRawUsers() {
        try {
            File file = new File(DATA_JSON_PATH);
            if (!file.exists()) return new ArrayList<>();
            JsonNode root = mapper.readTree(file);
            if (root.isArray()) {
                List<JsonNode> list = new ArrayList<>();
                for (JsonNode node : root) {
                    list.add(node);
                }
                return list;
            }
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Load tất cả User (bỏ qua role)
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        List<JsonNode> rawUsers = loadRawUsers();
        for (JsonNode node : rawUsers) {
            try {
                User user = mapper.treeToValue(node, User.class);
                users.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    // Load danh sách activity từ activity.json
    ArrayList<Activity> activities = ActivityHandle.loadActivities();

    // Tìm JsonNode của student (user có registeredActivities)
    private static JsonNode findStudentNode() {
        List<JsonNode> rawUsers = loadRawUsers();
        for (JsonNode node : rawUsers) {
            if (node.has("registeredActivities")) {
                return node;
            }
        }
        return null;
    }

    // Tìm JsonNode của admin (user có listActivities)
    private static JsonNode findAdminNode() {
        List<JsonNode> rawUsers = loadRawUsers();
        for (JsonNode node : rawUsers) {
            if (node.has("listActivities")) {
                return node;
            }
        }
        return null;
    }

    // Lấy danh sách registeredActivities của student
    public static List<Activity> getRegisteredActivities() {
        JsonNode studentNode = findStudentNode();
        if (studentNode != null && studentNode.has("registeredActivities")) {
            try {
                return mapper.readValue(studentNode.get("registeredActivities").traverse(), new TypeReference<List<Activity>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    // Cập nhật registeredActivities của student và lưu lại file
    public static void updateRegisteredActivities(List<Activity> newActivities) {
        try {
            File file = new File(DATA_JSON_PATH);
            List<JsonNode> rawUsers = loadRawUsers();

            for (int i = 0; i < rawUsers.size(); i++) {
                JsonNode node = rawUsers.get(i);
                if (node.has("registeredActivities")) {
                    if (node.isObject()) {
                        ObjectNode objNode = (ObjectNode) node;
                        objNode.putPOJO("registeredActivities", newActivities);
                        rawUsers.set(i, objNode);
                        break;
                    }
                }
            }

            // Ghi lại toàn bộ danh sách user (admin + student)
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, rawUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ví dụ thêm một activity cho student (thêm vào registeredActivities)
    public static void addActivityToStudent(Activity newActivity) {
        List<Activity> activities = getRegisteredActivities();
        activities.add(newActivity);
        updateRegisteredActivities(activities);
    }

    // Xóa activity khỏi registeredActivities student (theo title hoặc name)
    public static void removeActivityFromStudent(String activityTitleOrName) {
        List<Activity> activities = getRegisteredActivities();
        activities.removeIf(act -> act.getTitle().equals(activityTitleOrName) || act.getName().equals(activityTitleOrName));
        updateRegisteredActivities(activities);
    }

    // Tính tổng score (totalCost) của các hoạt động đã đăng ký có status = true
    public static int totalScore() {
        int total = 0;
        List<Activity> activities = getRegisteredActivities();
        for (Activity act : activities) {
            if (act.isStatus()) {
                total += act.getScore();
            }
        }
        return total;
    }

    // In ra các hoạt động đã đăng ký của student
    public static void viewRegisteredActivities() {
        List<Activity> activities = getRegisteredActivities();
        if (activities.isEmpty()) {
            System.out.println("Student chưa đăng ký hoạt động nào.");
            return;
        }
        System.out.println("Các hoạt động đã đăng ký của student:");
        for (Activity act : activities) {
            System.out.println(act);
        }
    }

    // In ra tất cả các activity có thể đăng ký từ activity.json
    public static void viewAllActivities() {
        List<Activity> activities = ActivityHandle.loadActivities();
        if (activities.isEmpty()) {
            System.out.println("Không có hoạt động nào trong activity.json.");
            return;
        }
        System.out.println("Danh sách các hoạt động có thể đăng ký:");
        for (Activity act : activities) {
            System.out.println(act);
        }
    }

    // Tìm kiếm activity trong activity.json theo tên (title hoặc name)
    public static List<Activity> searchActivityByName(String key) {
        List<Activity> activities = ActivityHandle.loadActivities();
        List<Activity> result = new ArrayList<>();
        for (Activity act : activities) {
            if (act.getTitle().toLowerCase().contains(key.toLowerCase())
                || act.getName().toLowerCase().contains(key.toLowerCase())) {
                result.add(act);
            }
        }
        return result;
    }
}