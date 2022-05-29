
package attendanceManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin implements ResponsibleUser{
    private String id;
    private String username;
    private String password;
    
    public Admin(String idAdmin, String usernameAdmin, String passwordAdmin){
        id = idAdmin;
        username = usernameAdmin;
        password = passwordAdmin;
    }
    
    /*
        Getters Methods
    */
    public String getId() {
        return id;
    }
    public void setId(String idAdmin){
        if(isValidString(idAdmin)){
            id = idAdmin;
        }
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String usernameAdmin){
        if(isValidString(usernameAdmin)){
            id = usernameAdmin;
        }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String passwordAdmin){
        if(isValidString(passwordAdmin)){
            id = passwordAdmin;
        }
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
    
    // 1 // To add new "Manager"
    /**
     * Adminجديد من خلال حساب ال Managerهذه الدالة تقوم بإنشاء 
     * @return 
     */
    @Override
    public User addUser(){
        Scanner input = new Scanner(System.in);
        Manager tempManager = new Manager();
        String addId;
        try{
            System.out.println("\t>>>>> Add Manager <<<<<\t\t[-1 => cancel]");
            System.out.print("- Enter id: ");
            addId = input.nextLine();
            if(!addId.equals("-1")){
                tempManager.setId(addId);
                System.out.print("- Enter name: ");
                tempManager.setName(input.nextLine());
                System.out.print("- Enter password: ");
                tempManager.setPassword(input.nextLine());
                System.out.print("- Enter email: ");
                tempManager.setEmail(input.nextLine());
                System.out.print("- Enter phone number: ");
                tempManager.setPhoneNumber(input.nextLine());
                System.out.print("- Enter the status: ");
                tempManager.setStatus(input.nextInt());
                System.out.println("The manager has been successfully added :)");
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        if(addId.equals("-1"))
            return null;
        return tempManager;
    }
    // 2 // To update the password of "Manager"
    /**
     * Admin تقوم بعمل تعديل  على كلمة السر الخاصة بالمدير من حساب ال
     * 
     * @param users 
     */
    @Override
    public void updateUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("  >>>>> Update Manager <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String updateId = input.nextLine();
            boolean done = false;
            if(!updateId.equals("-1")){
                int size = users.size();
                for(int i = 0; i < size; i++){
                    if(users.get(i) instanceof Manager){
                        Manager tempManager = (Manager)users.get(i);
                        if(updateId.equals(tempManager.getId())){
                            System.out.print("- Enter new password: ");
                            tempManager.setPassword(input.nextLine());
                            System.out.println("The manager's data has been successfully updated :)");
                            done = true;
                            break;
                        }
                    }
                }
                if(done)
                    System.out.println("User not found!");
            }else{
                System.out.println(" < cancel.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 3 // To delete a "Manager"
    /**
     *  Adminالخاص فيه المدخل من قبل الidالذي سيتم تحديده من خلال ال  Managerتقوم بحذف حساب ال
     * 
     * @param users 
     */
    @Override
    public void deleteUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("  >>>>> Delete Manager <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String removeId = input.nextLine();
            boolean done = false;
            if(!removeId.equals("-1")){
                System.out.println();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Manager){
                        Manager tempManager = (Manager)users.get(i);
                        if(removeId.equals(tempManager.getId())){
                            users.remove(tempManager);
                            done = true;
                            System.out.println("The manager has been successfully deleted :)");
                            break;
                        }
                    }
                }
                if(done)
                    System.out.println("User not found!");
            }else{
                System.out.println(" < cancel.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 4 // To search about a "Manager"
    /**
     *  Amdinالذي سيتم ادخاله من قبل ال idالمحدد باستخدام ال Managerتقوم بالبحث وعرض نبذه عن ال
     * @param users 
     */
    @Override
    public void searchAboutUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("  >>>>> Search About Manager <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String searchId = input.nextLine();
            boolean done = false;
            if(!searchId.equals("-1")){
                System.out.println();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Manager){
                        Manager tempManager = (Manager)users.get(i);
                        if(searchId.equals(tempManager.getId())){
                            System.out.println("Name: " + tempManager.getName() + " || Status: " + tempManager.getStatus());
                            break;
                        }
                    }
                }
                if(done)
                    System.out.println("User not found!");
            }else{
                System.out.println(" < cancel.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 5 // To report about a "Manager"
    /**
     * Amdinالذي سيتم ادخاله من قبل ال idالمحدد باستخدام ال  Mangerتعرض كل البيانات الخاصة بال
     * @param users 
     */
    @Override
    public void reportAboutUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println(" >>>>> Report About Manager <<<<<\t\t[-1 => cancel]");
            System.out.print("* Enter id: ");
            String reportId = input.nextLine();
            boolean done = false;
            if(!reportId.equals("-1")){
                System.out.println();
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i) instanceof Manager){
                        Manager tempManager = (Manager)users.get(i);
                        if(reportId.equals(tempManager.getId())){
                            System.out.println(tempManager.toString());
                            break;
                        }
                    }
                }
                if(done){
                    System.out.println("User not found!");
                }   
            }else{
                System.out.println(" < cancel.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 6 // To report about all "Manager"s
    /**
     * الموجودين داخل النظام مع بياناتهم Managersستقوم بعرض جميع ال
     * @param users 
     */
    @Override
    public void reportAboutAllUsers(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("  >>>>> Report About All Manager <<<<<\t\t[-1 => cancel]");
            System.out.println("* Choose the way to display data on screen *");
            System.out.print("1. Ascending order by name."
                            + "\n2. Descending order by name."
                            + "\n* Select: ");
            int ans;
            ArrayList<Manager> arrangedManagers = new ArrayList();
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
                            if(users.get(i) instanceof Manager){
                                Manager tempManager = (Manager)users.get(i);
                                int sizeManagers = arrangedManagers.size();
                                if(sizeManagers == 0){
                                    arrangedManagers.add(tempManager);
                                    continue;
                                }
                                boolean isAdded = false;
                                for(int j = 0; j < sizeManagers; j++){
                                    // الإختلاف هو اشارة الأصغر من  لأن كل واحد بنافس على انو مين الأصغر طول اسم .. Ascending order
                                    if(tempManager.getName().length() < arrangedManagers.get(j).getName().length() && ans == 1){
                                        arrangedManagers.add(j, tempManager);
                                        isAdded = true;
                                        break;
                                    }
                                    // الإختلاف هو اشارة الأكبر من لأن كل واحد بنافس على انو مين الأكبر طول اسم.. Descending order
                                    else if(tempManager.getName().length() > arrangedManagers.get(j).getName().length() && ans == 2){
                                        arrangedManagers.add(j, tempManager);
                                        isAdded = true;
                                        break;
                                    }
                                }
                                // في حال لم يضاف وهو اذا كان في حال كان أكبر واحد وكان الترتيب تصاعدي أو اصغر واحد وكان الترتيب تنازلي
                                // وبالتالي اذا كان احد هذه الحاليتن لن يضاف وبالتالي سيكون موقعه أخر عنصر في المصفوفة
                                if(!isAdded){
                                    arrangedManagers.add(tempManager);
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
                int sizeManagers = arrangedManagers.size();
                for(int i = 0; i < sizeManagers; i++)
                    System.out.println(arrangedManagers.get(i).toString());
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 7 // To make Activate or Dectivate to a "Manager"
    /**
     * Adminالخاص به المدخل من قبل ال Idمحدد من خلا ال Managerتقوم بتعطيل او تنشيط  حساب 
     * 
     * @param users 
     */
    @Override
    public void activateOrDeactivateUser(ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("  >>>>> Activate & Dectivate Manager <<<<<\t\t[-1 => cancel]");
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
                            System.out.println("\n\t  >>> Activate Manager <<<");
                        else
                            System.out.println("\n\t  >>> Deactivate Manager <<<");
                        System.out.print("* Enter id: ");
                        input.nextLine();
                        String checkId = input.nextLine();
                        int sizeUsers = users.size();
                        for(int i = 0; i < sizeUsers; i++){
                            if(users.get(i) instanceof Manager){
                                Manager tempManager = (Manager)users.get(i);
                                if(checkId.equals(tempManager.getId())){
                                    if(ans == 1){
                                        tempManager.setStatus(1);
                                        System.out.println("The account has been activated.");
                                    }
                                    else{
                                        tempManager.setStatus(0);
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
                        break;
                }
            }while(inputError);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // 8 // To deal with "Manager's" & "Employee's" holidays
    /**
     * Adminمن عرضهم او قبول او رفض الطلب وهذا كله من قبل ال holidayتقوم بالتعامل مع طلبات ال
     * 
     * @param users
     * @param holidays 
     */
    public void holidayRequests(ArrayList<User> users, ArrayList<Holiday> holidays){
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("  >>>>> Holiday Recuests <<<<<\t\t[-1 => cancel]");
            System.out.print("1. View Holiday Recuests."
                         + "\n2. Accept a holiday."
                         + "\n3. Reject a holiday."
                         + "\n Select: ");
            int ans;
            boolean inputError;
            do{
                inputError = false;
                ans = input.nextInt();
                System.out.println(); // new line
                switch(ans){
                    case 1:
                        System.out.println(">>>>> All Holiday Recuests <<<<<");
                        for(int i = 0; i < holidays.size(); i++)
                            holidays.get(i).report();
                        break;
                    case 2:
                    case 3:
                        if(ans == 2){
                            System.out.println(">>> Accept Holiday Recuests <<<");
                        }   
                        else{
                            System.out.println(">>> Reject Holiday Recuests <<<");
                        }   
                        System.out.print("Enter id: ");
                        input.nextLine();
                        String checkId = input.nextLine();
                        int sizeHolidays = holidays.size();
                        for(int i = 0; i < sizeHolidays; i++){
                            Holiday tempHoliday = holidays.get(i);
                            if(checkId.equals(tempHoliday.getId())){
                                if(ans == 2){
                                    tempHoliday.setCheck(1);
                                    System.out.println("Approved successfully :)");
                                }
                                else{
                                    tempHoliday.setCheck(2);
                                    System.out.println("The request is rejected.");
                                }
                                break;
                            }
                        }
                        break;
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
    // 9 // To Get Manager Count and Employee count
    /**
     * الذين بداخل النظام Managers & Employeesتقوم بإحصاء كل ال
     * @param users 
     */
    public void getManagersAndEmployeesCount(ArrayList<User> users){
        int managersCount = 0, employeesCount = 0;
        try{
            for(int i = 0; i < users.size(); i++){
                if(users.get(i) instanceof Manager)
                    managersCount++;
                else
                    employeesCount++;
            }
            System.out.println("\n  >>>>> Get Manager Count and Employee count <<<<<");
            System.out.println("- Manager Count: " + managersCount + "\n- Employee Count: " + employeesCount);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}