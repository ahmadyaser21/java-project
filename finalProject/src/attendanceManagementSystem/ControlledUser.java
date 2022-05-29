package attendanceManagementSystem;

import java.util.ArrayList;

// 1 // Employee & Managerهذا الإنترفيس سيكون بمثابة الأب الخاص بالكلاسات المتحكم بها مثل ال
// 2 // لكل الميثود الأبستراكتimplementationوايضا سيكون كخطة او تصميم لكل كلاس يرث منه بحيث إجباري على كل كلاس يرث من هذا الإنترفيس أن يقوم بعمل
public interface ControlledUser extends User{
    void attendanceAndLeaveTime();
    void myHoliday(ArrayList<Holiday> holidays);
}
