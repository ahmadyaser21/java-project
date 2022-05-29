package attendanceManagementSystem;

import java.util.ArrayList;

// 1 // Admin & Managerهذا الإنترفيس سيكون بمثابة الأب الخاص بالكلاسات التي لها سلطة في النظام وهي ال
// 2 // لكل الميثود الأبستراكتimplementationوايضا سيكون كخطة او تصميم لكل كلاس يرث منه بحيث إجباري على كل كلاس يرث من هذا الإنترفيس أن يقوم بعمل
public interface ResponsibleUser extends User{
    User addUser();
    void updateUser(ArrayList<User> users);
    void deleteUser(ArrayList<User> users);
    void searchAboutUser(ArrayList<User> users);
    void reportAboutUser(ArrayList<User> users);
    void reportAboutAllUsers(ArrayList<User> users);
    void activateOrDeactivateUser(ArrayList<User> users);
}
