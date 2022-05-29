package attendanceManagementSystem;

import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

// هذا الكلاس الرئيسي الخاص بالنظام كامل وهو غرفة العمليات ومخزن البيانات
public class CompanyManagement implements Serializable{
    
    // القادمة من الموظفين والمدراء في مصفوفة لحالholidaysيحتوي على كل الموظفين والمدراء في مصفوفة وكل ال
    // واحد فقط فقمنا بإنشائه في الكلاس الرئيسي للنظامAdminولا ننسى أن النظام يحتوي على 
    private ArrayList<User> users = new ArrayList();
    private ArrayList<Holiday> holidays = new ArrayList();
    private Admin administrator;
    
    
    // من هذا الكلاس سيتم إعطاء المسؤول رقم آي دي وكلمة سر واسم مستخدم كما موضح داخل الكونستراكتورObjectعند عمل 
    public CompanyManagement(){
        String adminId = "0000";
        String adminUsername = "admin";
        String adminPassword = "admin";
        administrator = new Admin(adminId, adminUsername, adminPassword);
    }
    
    /*
        Getters Methods
    */
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<Holiday> getHolidays(){
        return holidays;
    }
    
    /**
     * وظيفتها فقط إظهار رسالة الخطأ كلما تم استدعائها في الدوال الأخرى في هذا الكلاس
     */
    public void invalidInput(){
        System.out.println("*Invalid Input..");
        System.out.print("-> Please, Enter valid input again: ");
    }
    
    /*
        Utility Methods
    */
    
    /**
     * // 1 //  هذه الدالة تعتبر نقطة بداية ونقطة نهاية البرنامج لأنه حرفيا يتم بدء البرنامج من عندها وعند انتهائه يكون قد خرج منها
     * // 2 // بالشكل الموضح في الدالةselectPath(selectLogin)وتخزن القيمة التي تمثل الخيار في متغير وتمرره الي الدالة loginScreen()تقوم باستدعاء دالة تسجيل الدخول 
     * // 3 // في دالة تسجيل الدخول وإلا سيتم خروجه من البرنامج بشكل كامل 5 سيتم تكرار العملية في كل مرة طالما لم يدخل المستخدم قيمة
     */
    public void startAndEndSystem(){
        int selectLogin;
        do{
            selectLogin = loginScreen();
            selectPath(selectLogin);
        }while(selectLogin != 5);
    }
    /**
     * // 1 // تقوم بعرض خيارات الدخول وتحديد اتجاه سير المستخدم وتقوم بإرجاع قيمة تعبر عن الخيار الذي اختاره المستخدم...شاشة تسجيل الدخول
     * // 2 // بشكل إجباري على المستخدم لأنه في حال قام المستخدم لإدخال خاطي تظهر رسالة الخطأ ويعيد الإدخال من جديد 1-5ستقوم الدالة بإرجاع قيمة ما بين:ملاحظة
     * 
     * @return 
     */
    public int loginScreen(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n==================================================");
        System.out.println("\t\t  LOGIN SCREEN");
        System.out.println("__________________________________________________");
        System.out.println("1. Login As Admin.");
        System.out.println("2. Login As Manager.");
        System.out.println("3. Login As Employee.");
        System.out.println("4. About System.");
        System.out.println("5. EXIT.");
        System.out.print("\n*Select: ");
        int ans = 5;
        try{ // لانو في حال ادخل المستخدم حرف بدل من الارقام Exception Handllingعملنا هنا 
            ans = input.nextInt();
            while(ans > 5 || ans < 1){
                invalidInput();
                ans = input.nextInt();
            }
        }catch(Exception e){
            System.out.println(e + "\nInput Error!");
            startAndEndSystem();
        }
        System.out.println("__________________________________________________\n");
        return ans;
    }
    /**
     *    loginScreen() ستقوم بتحديد مسار اتجاه البرنامج حسب خيارات دالة تسجيل الدخول
     * 
     * @param selectLogin 
     */
    public void selectPath(int selectLogin){
        switch(selectLogin){
            case 1:
            case 2:
            case 3:
                // يتم استدعاء هذه الدالة لإرجاع الحساب او المستخدم الذي يريد الدخول على النظام
                User user = specifyUserAccount(selectLogin);
                
                // موجود وكلمة السر الصحيحة الخاصة بالحساب المحدد سيدخل من خلال هذه الدالة الى حسابهidتقوم هذه الدالة السماح للمستخدم في حال ادخل 
                enterToAccount(user);
                break;
            case 4:
                aboutSystem();
                break;
            case 5:
                break;
        }
    }
    /**
     * // 1 //  من دالة تسجيل الدخول 1-3سيتم الوصول لهذه الدالة واستدعاءها فقط في حال تم ارجاع قيمة من 
     * 
     * // 2 //  idعلى حسب الخيار المرجع من دالة تسجيل الدخول يستم تحديد نوع المستخدم وجعله يقوم بإدخال رقم ال 
     * 
     * // 3 // تقوم بتحديد نوع المستخدم وعند ادخال الآي دي تقوم بتحديد المستخدم نفسه في حال كان مخزن في النظام ولكن في حال كان :ملاحظة
     *          المستخدم غير موجود تعرض له رسالة خطأ ان المستخدم الذي تبحث عنه غير موجود
     * 
     * @param selectLogin
     * @return 
     */
    public User specifyUserAccount(int selectLogin){
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n==================================================");
        switch (selectLogin) {
            //نفذ الأوامر في الأسفل  1 = loginScreen()في حال كانت القيمة المرجعة من دالة تسجيل الدخول 
            case 1:
                System.out.println("\t  .....[ Login As Admin ].....\n");
                System.out.print("> Enter ID: ");
                String adminId = input.nextLine();
                if(adminId.equals(administrator.getId()))// Adminصحيح سترجع الدالة المستخدم المحدد ألا وهو ال idفي حال كان ال
                    return administrator; 
                else
                    System.out.println("The id isn't matched with id's administrator!!");
                break;
            //نفذ الأوامر في الأسفل  2 = loginScreen()في حال كانت القيمة المرجعة من دالة تسجيل الدخول 
            case 2:
                System.out.println("\t  .....[ Login As Manager ].....\n");
                System.out.print("> Enter ID: ");
                String managerId = input.nextLine();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Manager){
                        Manager tempManager = (Manager)users.get(i);
                        if(tempManager.getId().equals(managerId))//الذي تم ادخاله idخاصته مع الidالذي توافق الManagerصحيح سترجع الدالة المستخدم المحدد ألا وهو ال idفي حال كان ال
                            return tempManager; 
                    }
                }
                System.out.println("The manager account that you try to login is not exist!!");
                break;
            //نفذ الأوامر في الأسفل  3 = loginScreen()في حال كانت القيمة المرجعة من دالة تسجيل الدخول 
            case 3:
                System.out.println("\t  .....[ Login As Employee ].....\n");
                System.out.print("> Enter ID: ");
                String employeeId = input.nextLine();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Employee){
                        Employee tempEmployee = (Employee)users.get(i);
                        if(tempEmployee.getId().equals(employeeId)) //الذي تم ادخاله idخاصته مع الidالذي توافق الEmployeeصحيح سترجع الدالة المستخدم المحدد ألا وهو ال idفي حال كان ال
                            return tempEmployee; 
                    }
                }
                System.out.println("The employee account that you try to login is not exist!!");
                break;
        }
        return null;
    }
    /**
     * // 1 // ...سيتم تنفيذ اوامر الدالة هذه specifyUserAccount(int selectLogin) في حال تم إيجاد المستخدم من خلال الدالة السابقة
     * // 2 // حسب نوع المستخدم كما قلنا ifثم الدخول على جملة Userمن نوع  objectوهي تحدد نوع المستخدم من خلال 
     * // 3 //  booleanوالتي تقوم بالتحقق من كلمة السر وترجع قيمة checkLogin(user)وقبل الدخول على الحساب سيتم استدعاء الدالة
     * // 4 // سيعرض له رسالة خطأ falseسيدخل على الحساب الخاص بالمستخدم ايا كان نوعه من الأنواع الثلاثة واذا  trueفي حال تم إرجاع 
     *           والتي سيتم شرح عملها تحت في الأسفل فوق الدالة مباشرةcheckLogin(user) الموجودة في دالة التحقق من كلمة السر
     * 
     * @param user 
     */
    public void enterToAccount(User user){
        if(user != null){
            if(user instanceof Admin){
                if(checkLogin(user)){
                    System.out.println("==================================================\n");
                    int selectAdminOperation;
                    do{
                        // لهذا المتغير الذي يمثل رقم العملية Adminسيتم اسناد القيمة المرجعة من شاشة ال
                        selectAdminOperation = adminScreen();
                        // وهذا كله لتنفيذ رقم العملية المحددة Userمن نوع  Objectالخاص بالنظام ك Adminسيتم ارسال رقم العملية وال
                        adminControl(selectAdminOperation, user);
                    }while(selectAdminOperation != 10); // تقوم بالخروج والعودة الي شاشة تسجيل الدخول الرئيسية
                }
            }else if(user instanceof Manager){
                if(checkLogin(user)){
                    System.out.println("==================================================\n");
                    int selectManagerOperation;
                    do{
                        // لهذا المتغير الذي يمثل رقم العملية ُManagerسيتم اسناد القيمة المرجعة من شاشة ال
                        selectManagerOperation = managerScreen();
                        // وهذا كله لتنفيذ رقم العملية المحددة Userمن نوع  Objectالخاص بالنظام ك Managerسيتم ارسال رقم العملية وال
                        managerControl(selectManagerOperation, user);
                    }while(selectManagerOperation != 11); // تقوم بالخروج والعودة الي شاشة تسجيل الدخول الرئيسية
                }
            }else if(user instanceof Employee){
                if(checkLogin(user)){
                    System.out.println("==================================================\n");
                    int selectEmployeeOperation;
                    do{
                        // لهذا المتغير الذي يمثل رقم العملية Employeeسيتم اسناد القيمة المرجعة من شاشة ال
                        selectEmployeeOperation = employeeScreen();
                        // وهذا كله لتنفيذ رقم العملية المحددة Userمن نوع  Objectالخاص بالنظام ك Employeeسيتم ارسال رقم العملية وال
                        employeeControl(selectEmployeeOperation, user);
                    }while(selectEmployeeOperation != 4); // تقوم بالخروج والعودة الي شاشة تسجيل الدخول الرئيسية
                }
            }
        }
    }
    /**
     * ...كما وضحنا واعطينا نبذة عن عمل هذه الدالة فوق
     *  falseفي حال ادخل كلمة السر الصحيحة الخاصة بالمستخدم واظهار رسالة خطأ ان كلمة السر غير مع ارجاع قيمة trueتقوم بإرجاع 
     * 
     * @param user
     * @return 
     */
    public boolean checkLogin(User user){
        Scanner input = new Scanner(System.in);
        if(user instanceof Admin){
            System.out.print("> Enter password: ");
            String adminPassword = input.nextLine();
            if(adminPassword.equals(((Admin)user).getPassword())) // trueفي حال صحيحة سيرجع 
                return true;
            else{  // -1لا ينتهي الى ان يدخل كلمة السر الصحيحة او loopللخروج والغاء العملية سيدخل في  -1ولكن ان كانت خاطئة وايضا لم يقم المستخدم ادخال
                while(!(adminPassword.equals(administrator.getPassword()) || adminPassword.equals("-1"))){
                    System.out.println("*The password to this account is wrong, Try again please!");
                    System.out.print("-> Enter password, (or press -1 to cancel login): ");
                    adminPassword = input.nextLine();
                    if(adminPassword.equals(administrator.getPassword()))
                        return true;
                    else if(adminPassword.equals("-1")) //سيلغي محاول عملية تسجيل الدخول -1هنا في حال ادخل 
                        return false;
                }
            }
        }else if(user instanceof Manager){
            System.out.print("> Enter password: ");
            String managerPassword = input.nextLine();
            Manager tempManager = (Manager)user;
            boolean isCorrectPassword = managerPassword.equals(tempManager.getPassword());
            if(isCorrectPassword) // trueفي حال صحيحة سيرجع 
                return true;
            else{  // -1لا ينتهي الى ان يدخل كلمة السر الصحيحة او loopللخروج والغاء العملية سيدخل في  -1ولكن ان كانت خاطئة وايضا لم يقم المستخدم ادخال
                while(!(isCorrectPassword || managerPassword.equals("-1"))){
                    System.out.println("*The password to this account is wrong, Try again please!");
                    System.out.print("-> Enter password, (or press -1 to cancel login): ");
                    managerPassword = input.nextLine();
                    isCorrectPassword = managerPassword.equals(tempManager.getPassword());
                    if(isCorrectPassword)
                        return true;
                    else if(managerPassword.equals("-1"))  //سيلغي محاول عملية تسجيل الدخول -1هنا في حال ادخل 
                        return false;
                }
            }
        }else if(user instanceof Employee){
            System.out.print("> Enter password: ");
            String employeePassword = input.nextLine();
            Employee tempEmployee = (Employee)user;
            boolean isCorrectPassword = employeePassword.equals(tempEmployee.getPassword());
            if(isCorrectPassword) // trueفي حال صحيحة سيرجع 
                return true;
            else{  // -1لا ينتهي الى ان يدخل كلمة السر الصحيحة او loopللخروج والغاء العملية سيدخل في  -1ولكن ان كانت خاطئة وايضا لم يقم المستخدم ادخال
                while(!(isCorrectPassword || employeePassword.equals("-1"))){
                    System.out.println("*The password to this account is wrong, Try again please!");
                    System.out.print("-> Enter password, (or press -1 to cancel login): ");
                    employeePassword = input.nextLine();
                    isCorrectPassword  = employeePassword.equals(tempEmployee.getPassword());
                    if(isCorrectPassword)
                        return true;
                    else if(employeePassword.equals("-1")) //سيلغي محاول عملية تسجيل الدخول -1هنا في حال ادخل 
                        return false;
                }
            }
        }
        return false;
    }
    /**
     * // 1 //  الخاص به Idلحظة دخوله الى الحساب بعد ادخال كلمة السر الصحيحة وال Adminتعرض شاشة ال
     * // 2 // كإختيار لرقم العملية 1-10مثل فكرة دالة تسجيل الدخول  الدخول الرئيسية تقوم هذه الدالة بعد عرض شاشة المسؤول وإرجاع قيمة ما بين
     * // 3 // الى ان يدخل قيمة صحيحة loopوإلافسيتم عرض رسالة الخطأ ويدخل في 1-10سيتم إجبار المستخدم ادخال قيمة صحيحة بين  :ملاحظة
     * 
     * @return 
     */
    public int adminScreen(){
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("\t     >>>>>>> ADMIN <<<<<<<\n");
        System.out.println(" 1. Add Manager."
                + "\n 2. Update Manager."
                + "\n 3. Delete Magager."
                + "\n 4. Search About Manager."
                + "\n 5. Report About Manager."
                + "\n 6. Report About All Managers."
                + "\n 7. Holiday Requests."
                + "\n 8. Activate & Deactivate A Manager."
                + "\n 9. Get Manager Count & Employee Count."
                + "\n10. Exit.");
        System.out.print("\n* SELECT: ");
        int ans;
        try{ // لانو في حال ادخل المستخدم حرف بدل من الارقام Exception Handllingهنا قمنا بعمل 
            ans = input.nextInt();
            while(ans > 10 || ans < 1){
                invalidInput();
                ans = input.nextInt(); // هذه الدالة تمثل رسالة الخطأ ان ادخل قيمة غير صحيحة
            }
        }catch(Exception e){
            System.out.println(e + "\nInput Error!");
            System.out.println("--------------------------------------------------\n");
            return 10;
        }
        System.out.println("--------------------------------------------------\n");
        return ans;
    }
    /**
     * // 1 // التي تدخل المستخدم الى الحساب enterToAccount(User user)من داخل دالة Adminسيتم استدعاء هذه الدالة مباشرة بعد عرض شاشة ال
     *          بعد ادخال كلمة السر الصحيحة كما وضحنا في شرح الدالة فوق
     * // 2 // Adminوتقوم بإستدعاء الدوال من داخل حساب ال Adminوتقوم هذه الدالة بتنفيذ العملية التي اختارها المستخدم بناء على الدالة التي تعرض شاشة ال
     * // 3 //بشكل مضمون من قبل دالة عرض شاشة المسؤول 1-10المرر في الدالة سيكون من selecteOperationرقم العملية  :ملاحطة
     * 
     * @param selectOperation
     * @param user 
     */
    public void adminControl(int selectOperation, User user){
        Admin admin = (Admin)user;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Adminسيتم شرح عمل كل دالة في كلاس ال :ملاحظة
        switch(selectOperation){
            case 1:
                users.add(admin.addUser());
                break;
            case 2:
                admin.updateUser(users);
                break;
            case 3:
                admin.deleteUser(users);
                break;
            case 4:
                admin.searchAboutUser(users);
                break;
            case 5:
                admin.reportAboutUser(users);
                break;
            case 6:
                admin.reportAboutAllUsers(users);
                break;
            case 7:
                admin.holidayRequests(users, holidays);
                break;
            case 8:
                admin.activateOrDeactivateUser(users);
                break;
            case 9:
                admin.getManagersAndEmployeesCount(users);
                break;
            case 10:
                break;
            default:
               break;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    /**
     * // 1 //  الخاص به Idلحظة دخوله الى الحساب بعد ادخال كلمة السر الصحيحة وال Managerتعرض شاشة ال
     * // 2 // كإختيار لرقم العملية 1-11مثل فكرة دالة تسجيل الدخول الرئيسية تقوم هذه الدالة بعد عرض شاشة المدير وإرجاع قيمة ما بين
     * // 3 // الى ان يدخل قيمة صحيحة loopوإلافسيتم عرض رسالة الخطأ ويدخل في 1-11سيتم إجبار المستخدم ادخال قيمة صحيحة بين  :ملاحظة
     * 
     * @return 
     */
    public int managerScreen(){
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("\t     >>>>>>> MANAGER <<<<<<<\n");
        System.out.println(" 1. Add Employee."
                + "\n 2. Update Employee."
                + "\n 3. Delete Employee."
                + "\n 4. Search About Employee."
                + "\n 5. Report About Employee."
                + "\n 6. Report About All Employee."
                + "\n 7. Activate & Deactivate A Employee."
                + "\n 8. Promote An Employee To Manager."
                + "\n 9. Attendance."
                + "\n10. Holiday."
                + "\n11. Exit.");
        System.out.print("\n* SELECT: ");
        int ans;
        try{ // لانو في حال ادخل المستخدم حرف بدل من الارقام Exception Handllingعملنا هنا 
            ans = input.nextInt();
            while(ans > 11 || ans < 1){
                invalidInput(); // هذه الدالة تمثل رسالة الخطأ ان ادخل قيمة غير صحيحة
                ans = input.nextInt();
            }
        }catch(Exception e){
            System.out.println(e + "\nInput Error!");
            System.out.println("--------------------------------------------------\n");
            return 11;
        }
        System.out.println("--------------------------------------------------\n");
        return ans;
    }
    /**
     * // 1 // التي تدخل المستخدم الى الحساب enterToAccount(User user)من داخل دالة Managerسيتم استدعاء هذه الدالة مباشرة بعد عرض شاشة ال
     *          بعد ادخال كلمة السر الصحيحة كما وضحنا في شرح الدالة فوق
     * // 2 // Managerوتقوم بإستدعاء الدوال من داخل حساب ال Managerوتقوم هذه الدالة بتنفيذ العملية التي اختارها المستخدم بناء على الدالة التي تعرض شاشة ال
     * // 3 //بشكل مضمون من قبل دالة عرض شاشة المدير 1-11المرر في الدالة سيكون من selecteOperationرقم العملية  :ملاحطة
     * 
     * @param selectOperation
     * @param user 
     */
    public void managerControl(int selectOperation, User user){
        Manager manager = (Manager)user;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Managerسيتم شرح عمل كل دالة في كلاس ال :ملاحظة
        switch(selectOperation){
            case 1:
                users.add(manager.addUser());
                break;
            case 2:
                manager.updateUser(users);
                break;
            case 3:
                manager.deleteUser(users);
                break;
            case 4:
                manager.searchAboutUser(users);
                break;
            case 5:
                manager.reportAboutUser(users);
                break;
            case 6:
                manager.reportAboutAllUsers(users);
                break;
            case 7:
                manager.activateOrDeactivateUser(users);
                break;
            case 8:
                manager.promoteEmployeeToManager(users);
                break;
            case 9:
                manager.attendanceAndLeaveTime();
                break;
            case 10:
                manager.myHoliday(holidays);
                break;
            case 11:
                break;
            default:
                break;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    /**
     * // 1 //  الخاص به Idلحظة دخوله الى الحساب بعد ادخال كلمة السر الصحيحة وال Employeeتعرض شاشة ال
     * // 2 // كإختيار لرقم العملية 1-4مثل فكرة دالة تسجيل الدخول الرئيسية تقوم هذه الدالة بعد عرض شاشة الموظف وإرجاع قيمة ما بين
     * // 3 // الى ان يدخل قيمة صحيحة loopوإلافسيتم عرض رسالة الخطأ ويدخل في 1-4سيتم إجبار المستخدم ادخال قيمة صحيحة بين  :ملاحظة
     * 
     * @return 
     */
    public int employeeScreen(){
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("\t     >>>>>>> EMPLOYEE <<<<<<<\n");
        System.out.println("1. Change Password."
                + "\n2. Attendance."
                + "\n3. Holiday."
                + "\n4. Exit.");
        System.out.print("\n* SELECT: ");
        int ans;
        try{ // لانو في حال ادخل المستخدم حرف بدل من الارقام Exception Handllingعملنا هنا 
            ans = input.nextInt();
            while(ans > 4 || ans < 1){
                invalidInput(); // هذه الدالة تمثل رسالة الخطأ ان ادخل قيمة غير صحيحة
                ans = input.nextInt();
            }
        }catch(Exception e){
            System.out.println(e + "\nInput Error!");
            System.out.println("--------------------------------------------------\n");
            return 4;
        }
        System.out.println("--------------------------------------------------\n");
        return ans;
    }
    /**
     * // 1 // التي تدخل المستخدم الى الحساب enterToAccount(User user)من داخل دالة Employeeسيتم استدعاء هذه الدالة مباشرة بعد عرض شاشة ال
     *          بعد ادخال كلمة السر الصحيحة كما وضحنا في شرح الدالة فوق
     * // 2 // Employeeوتقوم بإستدعاء الدوال من داخل حساب ال Employeeوتقوم هذه الدالة بتنفيذ العملية التي اختارها المستخدم بناء على الدالة التي تعرض شاشة ال
     * // 3 //بشكل مضمون من قبل دالة عرض شاشة الموظف 1-4المرر في الدالة سيكون من selecteOperationرقم العملية  :ملاحطة
     * 
     * @param selectOperation
     * @param user 
     */
    public void employeeControl(int selectOperation, User user){
        Employee employee = (Employee)user;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Employeeسيتم شرح عمل كل دالة في كلاس ال :ملاحظة
        switch(selectOperation){
            case 1:
                employee.changePassword();
                break;
            case 2:
                employee.attendanceAndLeaveTime();
                break;
            case 3:
                employee.myHoliday(holidays);
                break;
            case 4:
                break;
            default:
                break;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    /**
     * تقوم بعرض معلومات عامة حول النظام
     */
    public void aboutSystem(){
        System.out.println("*-----------------------------------------*");
        System.out.println("\n  ### System Information ###  ");
        System.out.println(" > Company Management System."
                + "\n > Developed By \"Your Name\"."
                + "\n > @2021-2022.");
        System.out.println("*-----------------------------------------*");
    }
}