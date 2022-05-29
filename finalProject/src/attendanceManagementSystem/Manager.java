package attendanceManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager implements ResponsibleUser, ControlledUser{
    private String id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private int status;
    private String attendanceTime;
    private String departureTime;

    public Manager(){
    }
    public Manager(String idManager, String nameManager, String passwordManager, String emailManager, String phoneManager, int statusManager) {
        id = idManager;
        name = nameManager;
        password = passwordManager;
        email = emailManager;
        phoneNumber = phoneManager;
        status = statusManager;
    }
    
    /*
        Getters & Setters Methods
    */
    
    public String getId() {
        return id;
    }
    public void setId(String idManager) {
        if(isValidString(idManager)){
            id = idManager;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String nameManager) {
        if(isValidString(nameManager)){
            id = nameManager;
        }
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String passwordManager) {
        if(isValidString(passwordManager)){
            id = passwordManager;
        }
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String emailManager) {
        if(isValidString(emailManager)){
            id = emailManager;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneManager) {
        if(isValidString(phoneManager)){
            id = phoneManager;
        }
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        Scanner input = new Scanner(System.in);
        if(status == 0 || status == 1)
            this.status = status;
        else{
            while(!(status == 0 || status == 1)){
                invalidInput();
                status = input.nextInt();
            }
        }
        this.status = status;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }
    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    /**
     * وظيفتها فقط إظهار رسالة الخطأ كلما تم استدعائها في الدوال الأخرى في هذا الكلاس
     */
    public void invalidInput(){
        System.out.println("*Invalid Input..");
        System.out.print("-> Please, Enter valid input again: ");
    }
    /**
     * // 1 //  Stringالتي تستقبل متغير من نوع setterتقوم هذه الدالة بالتحقق من دوال ال
     * // 2 // وإلا سيظهر رسالة خطأ ويعيد الإدخال من جديد trueسيرجع  30و اصغر من او يساوي  2في حال كان النص اكبر من او يساوي 
     * @param str
     * @return 
     */
    public boolean isValidString(String str){
        Scanner input = new Scanner(System.in);
        boolean validString;
        do{
            validString = str.length() >= 2 && str.length() <= 30;
            if(validString)
                return true;
            else{
                invalidInput();
                System.out.println("=> (input Chars >= 2 && Chars <= 30): ");
                str = input.nextLine();
            }
        }while(!validString);
        return false;
    }
    
    
    /*
        Utility methods
    */
    
    // 1 // To add new "Employee"
    /**
     * Managerجديد من خلال حساب ال Employeeهذه الدالة تقوم بإنشاء 
     * @return 
     */
    @Override
    public User addUser(){
        Scanner input = new Scanner(System.in);
        Employee tempEmployee = new Employee();
        String addId;
        try{
            System.out.println(">>>>> Add Employee <<<<<\t\t[-1 => cancel]");
            System.out.print("- Enter id: ");
            addId = input.nextLine();
            if(!addId.equals("-1")){
                tempEmployee.setId(addId);
                System.out.print("- Enter name: ");
                tempEmployee.setName(input.nextLine());
                System.out.print("- Enter password: ");
                tempEmployee.setPassword(input.nextLine());
                System.out.print("- Enter email: ");
                tempEmployee.setEmail(input.nextLine());
                System.out.print("- Enter phone number: ");
                tempEmployee.setPhoneNumber(input.nextLine());
                System.out.print("- Type of employee, 1->[Full_Time] || 2->[Part_Time]: ");
                tempEmployee.setType(input.nextInt());
                System.out.print("- Enter the status: ");
                tempEmployee.setStatus(input.nextInt());
                System.out.println("The employee has been successfully added :)");
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        if(addId.equals("-1"))
            return null;
        return tempEmployee;
    }
    // 2 // To update the password of "Employee"
    /**
     *  Employee تقوم بعمل تعديل  على كلمة السر الخاصة بالموظف من حساب ال
     * 
     * @param users 
     */
    @Override
    public void updateUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>>>>>>> Update Employee <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String updateId = input.nextLine();
            if(!updateId.equals("-1")){
                int size = users.size();
                for(int i = 0; i < size; i++){
                    if(users.get(i) instanceof Employee){
                        Employee tempEmployee = (Employee)users.get(i);
                        if(updateId.equals(tempEmployee.getId())){
                            System.out.print("- Enter new password: ");
                            tempEmployee.setPassword(input.nextLine());
                            break;
                        }
                    }
                }
                System.out.println("The employee's data has been successfully updated :)");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 3 // To delete a "Employee"
    /**
     *  Managerالخاص فيه المدخل من قبل الidالذي سيتم تحديده من خلال ال  Employeeتقوم بحذف حساب ال
     * 
     * @param users 
     */
    @Override
    public void deleteUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> Delete Employee <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String removeId = input.nextLine();
            if(!removeId.equals("-1")){
                System.out.println();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Employee){
                        Employee tempEmployee = (Employee)users.get(i);
                        if(removeId.equals(tempEmployee.getId())){
                            users.remove(tempEmployee);
                            break;
                        }
                    }
                }
                System.out.println("The employee has been successfully deleted :)");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 4 // To search about a "Employee"
    /**
     *  Manangerالذي سيتم ادخاله من قبل ال idالمحدد باستخدام ال Employeeتقوم بالبحث وعرض نبذه عن ال
     * @param users 
     */
    @Override
    public void searchAboutUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> Search About Employee <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String searchId = input.nextLine();
            if(!searchId.equals("-1")){
                System.out.println();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Employee){
                        Employee tempEmployee = (Employee)users.get(i);
                        if(searchId.equals(tempEmployee.getId())){
                            System.out.println("Name: " + tempEmployee.getName() + " || Status: " + tempEmployee.getStatus());
                            break;
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 5 // To report about a "Employee"
    /**
     * Managerالذي سيتم ادخاله من قبل حساب ال idالمحدد باستخدام ال  Employeeتعرض كل البيانات الخاصة بال
     * @param users 
     */
    @Override
    public void reportAboutUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> Report About Employee <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String reportId = input.nextLine();
            if(!reportId.equals("-1")){
                System.out.println();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Employee){
                        Employee tempEmployee = (Employee)users.get(i);
                        if(reportId.equals(tempEmployee.getId())){
                            System.out.println(tempEmployee.toString());
                            break;
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 6 // To report about all "Employee"s
    /**
     * الموجودين داخل النظام مع بياناتهم Employeesستقوم بعرض جميع ال
     * 
     * @param users 
     */
    @Override
    public void reportAboutAllUsers(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> Report About All Employees <<<<<\t\t[-1 => cancel]");
            System.out.println("* Choose the way to display data on screen *");
            System.out.print("1. Ascending order by name."
                            + "\n2. Descending order by name."
                            + "\n* Select: ");
            int ans;
            ArrayList<Employee> arrangedEmployees = new ArrayList();
            boolean inputError;
            do{
                inputError = false;
                ans = input.nextInt();
                System.out.println(); // new line
                int sizeUsers = users.size();
                switch(ans){
                    case 1:
                    case 2:
                        for(int i = 0; i < sizeUsers; i++){
                            if(users.get(i) instanceof Employee){
                                Employee tempEmployee = (Employee)users.get(i);
                                int sizeManagers = arrangedEmployees.size();
                                if(sizeManagers == 0){
                                    arrangedEmployees.add(tempEmployee);
                                    continue;
                                }
                                boolean isAdded = false;
                                for(int j = 0; j < sizeManagers; j++){
                                    // الإختلاف هو اشارة الأصغر من  لأن كل واحد بنافس على انو مين الأصغر طول اسم.. Ascending order
                                    if(tempEmployee.getName().length() < arrangedEmployees.get(j).getName().length() && ans == 1){
                                        arrangedEmployees.add(j, tempEmployee);
                                        isAdded = true;
                                        break;
                                    }
                                    // الإختلاف هو اشارة الأكبر من لأن كل واحد بنافس على انو مين الأكبر طول اسم.. Descending order
                                    else if(tempEmployee.getName().length() > arrangedEmployees.get(j).getName().length() && ans == 2){
                                        arrangedEmployees.add(j, tempEmployee);
                                        isAdded = true;
                                        break;
                                    }
                                }
                                // في حال لم يضاف وهو اذا كان في حال كان أكبر واحد وكان الترتيب تصاعدي أو اصغر واحد وكان الترتيب تنازلي
                                // وبالتالي اذا كان احد هذه الحاليتن لن يضاف وبالتالي سيكون موقعه أخر عنصر في المصفوفة
                                if(!isAdded){
                                    arrangedEmployees.add(tempEmployee);
                                    break;
                                }
                            }
                        }
                        break;
                    case -1:
                        System.out.println("< cancel.");
                        break;
                    default:
                        invalidInput();
                        inputError = true;
                        break;
                }
            }while(inputError);
            
            if(ans != -1){
                System.out.println("\n# Final report: \n");
                int sizeManagers = arrangedEmployees.size();
                for(int i = 0; i < sizeManagers; i++)
                    System.out.println(arrangedEmployees.get(i).toString());
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 7 // To make Activate or Dectivate to a "Employee"
    /**
     * Managerالخاص به المدخل من قبل حساب ال Idمحدد من خلا ال Employeeتقوم بتعطيل او تنشيط  حساب 
     * 
     * @param users 
     */
    @Override
    public void activateOrDeactivateUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> Activate & Dectivate Employee <<<<<\t\t[-1 => cancel]");
            System.out.print("1. Activate."
                            + "\n2. Deactivate."
                            + "\n3. Exit."
                            + "\n* Select: ");
            int ans;
            boolean inputError;
            do{
                inputError = false;
                ans = input.nextInt();
                System.out.println();
                switch(ans){
                    case 1:
                    case 2:
                        if(ans == 1)
                            System.out.println(">>>>> Activate Employee <<<<<");
                        else
                            System.out.println(">>>>> Deactivate Employee <<<<<");
                        System.out.print("* Enter id: ");
                        input.nextLine();
                        String checkId = input.nextLine();
                        int sizeUsers = users.size();
                        for(int i = 0; i < sizeUsers; i++){
                            if(users.get(i) instanceof Employee){
                                Employee tempEmployee = (Employee)users.get(i);
                                if(checkId.equals(tempEmployee.getId())){
                                    if(ans == 1){
                                        tempEmployee.setStatus(1);
                                        System.out.println("The account has been activated.");
                                    }
                                    else{
                                        tempEmployee.setStatus(0);
                                        System.out.println("The account has been disabled.");
                                    }
                                    break;
                                }
                            }
                        }
                    case 3:
                    case -1:
                        break;
                    default:
                        invalidInput();
                        inputError = true;
                        break;
                }
            }while(inputError);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 8 // To Promote an employee to Manager
    /**
     * Managerليصبح Employeeقيتم ترقية هذا ال Managerالخاص به من قبل حساب ال idمحدد من خلال ال Employeeتقوم بترقية 
     * 
     * @param users 
     */
    public void promoteEmployeeToManager(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>>  Promote an Employee to Manager <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String promoteId = input.nextLine();
            if(!promoteId.equals("-1")){
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Employee){
                        Employee tempEmployee = (Employee)users.get(i);
                        if(promoteId.equals(tempEmployee.getId())){
                            Manager newManager = new Manager();
                            // I copy the information of last Employee and peast at new Manager
                            newManager.setId(tempEmployee.getId());
                            newManager.setName(tempEmployee.getName());
                            newManager.setPassword(tempEmployee.getPassword());
                            newManager.setEmail(tempEmployee.getEmail());
                            newManager.setPhoneNumber(tempEmployee.getPhoneNumber());
                            newManager.setStatus(tempEmployee.getStatus());
                            users.add(newManager);
                            users.remove(tempEmployee);
                            
                            System.out.println("The employee has been promoted to manager.");
                            break;
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 9 // To set attendance & departure time to current "Manager"
    /**
     *  تقوم بتحديد وقت الحضور ووقت المغادرة الخاص بالمدير الحالي
     */
    @Override
    public void attendanceAndLeaveTime(){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> The Attendance <<<<<\t\t[-1 => cancel]");
            System.out.print("1. Time of attendance."
                            + "\n2. Time to departure."
                            + "\n3. Exit."
                            + "\n* Select: ");
            int ans;
            boolean inputError;
            do{
                inputError = false;
                ans = input.nextInt();
                input.nextLine();
                System.out.println();// new line
                switch(ans){
                    case 1:
                        System.out.println(">>>>> Time of attendance <<<<<");
                        System.out.print("Enter the time:  ");
                        this.setAttendanceTime(input.nextLine());
                        break;
                    case 2:
                        System.out.println(">>>>> Time to departure <<<<<");
                        System.out.print("Enter the time:  ");
                        this.setDepartureTime(input.nextLine());
                        break;
                    case 3:
                    case -1:
                        break;
                    default:
                        invalidInput();
                        inputError = true;
                        break;
                }
            }while(inputError);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 10 // To deal with holiday of this Manager
    /**
     * جديدة holidayالخاصة بالموظف سواء عرضها او إنشاء (holidays)تقوم بالتعامل مع العطل
     * 
     * @param holidays 
     */
    @Override
    public void myHoliday(ArrayList<Holiday> holidays){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(">>>>> Holiday Transactions <<<<<\t\t[-1 => cancel]");
            System.out.print("1. My Holidays."
                            + "\n2. Create Holiday."
                            + "\n3. Exit."
                            + "\n* Select: ");
            int ans;
            boolean inputError;
            do{
                inputError = false;
                ans = input.nextInt();
                input.nextLine();
                System.out.println();
                switch(ans){
                    case 1:
                        System.out.println(">>> My Holidays <<<");
                        for(int i = 0; i < holidays.size(); i++){
                            if(this.id.equals(holidays.get(i).getId()))
                                System.out.println(holidays.get(i).toString());
                        }
                        break;
                    case 2:
                        System.out.println(">>>>> Create Holiday <<<<<");
                        Holiday newHoliday = new Holiday();
                        newHoliday.setId(this.id);
                        System.out.print("Enter Name: ");
                        newHoliday.setName(input.nextLine());
                        System.out.print("Enter Reason: ");
                        newHoliday.setReason(input.nextLine());
                        System.out.print("Enter Details: ");
                        newHoliday.setDetails(input.nextLine());
                        System.out.print("Enter Date: ");
                        newHoliday.setDate(input.nextLine());
                        holidays.add(newHoliday);
                        break;
                    case 3:
                    case -1:
                        break;
                    default:
                        invalidInput();
                        inputError = true;
                        break;
                }
            }while(inputError);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @Override
    public String toString() {
        return "Manager{" + "id = " + id + ", name = " + name + ", password = " + password + ", email = " + email + ", phoneNumber = " + phoneNumber + ", status = " + status + '}';
    }
}