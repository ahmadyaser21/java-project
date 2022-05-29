package attendanceManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee implements ControlledUser {
    private String id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private int type;
    private int status;
    private String attendanceTime;
    private String departureTime;
    
    public Employee(){
    }
    public Employee(String idEmployee, String nameEmployee, String passwordEmployee, String emailEmployee, String phoneEmployee,int typeEmployee, int statusEmployee){
        id = idEmployee;
        name = nameEmployee;
        password = passwordEmployee;
        email = emailEmployee;
        phoneNumber = phoneEmployee;
        status = statusEmployee;
        type = typeEmployee;
    }
    
    /*
        Getters & Setters Methods
    */
    // idيقوم بإرجاع قيمة ال
    public String getId() {
        return id;
    }
    public void setId(String idEmployee) {
        if(isValidString(idEmployee)){
            id = idEmployee;
        }
    }
    // nameيقوم بإرجاع قيمة ال
    public String getName() {
        return name;
    }
    public void setName(String nameEmployee) {
        if(isValidString(nameEmployee)){
            id = nameEmployee;
        }
    }

    // passwordيقوم بإرجاع قيمة ال
    public String getPassword() {
        return password;
    }
    public void setPassword(String passwordEmployee) {
        if(isValidString(passwordEmployee)){
            id = passwordEmployee;
        }
    }

    // emailيقوم بإرجاع قيمة ال
    public String getEmail() {
        return email;
    }
    public void setEmail(String emailEmployee) {
        if(isValidString(emailEmployee)){
            id = emailEmployee;
        }
    }

    // phoneNumberيقوم بإرجاع قيمة ال
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneEmployee) {
        if(isValidString(phoneEmployee)){
            id = phoneEmployee;
        }
    }
    
    // typeيقوم بإرجاع قيمة ال
    public int getType() {
        return type;
    }
    public void setType(int typeEmployee) {
        Scanner input = new Scanner(System.in);
        if(typeEmployee == 1 || typeEmployee == 2)
            type = typeEmployee;
        else{
            while(!(typeEmployee == 1 || typeEmployee == 2)){
                invalidInput();
                type = input.nextInt();
            }
        }
        type = typeEmployee;
    }

    // statusيقوم بإرجاع قيمة ال
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
    
    // attendanceTimeيقوم بإرجاع قيمة ال
    public String getAttendanceTime() {
        return attendanceTime;
    }
    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    // departuerTimeيقوم بإرجاع قيمة ال
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
    
    // 1 // To Change Password's current employee
    /**
     *  تقوم بتغيير كلمة سر الموظف الحالي  من داخل حسابه الشخصي
     */
    public void changePassword(){
        Scanner input = new Scanner(System.in);
        System.out.println(">>>>> Change Password <<<<<\t\t[-1 => cancel]");
        System.out.print("* Enter new password: ");
        String newPassword = input.nextLine();
        if(!newPassword.equals("-1")){
            setPassword(newPassword);
            System.out.println("The password has been successfully changed :)");
        }else
            System.out.println(" < Operation canceled");
    }
    // 2 // To set attendance & departure time to current "Employee"
    /**
     * تقوم بتحديد وقت الحضور ووقت المغادرة الخاص بالموظف
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
            boolean inputError = false;
            do{
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
    // To deal with holiday of this Manager
    /**
     * الخاصة بالموظف سواء عرضها او إنشاء عطلة جديدة (holidays)تقوم بالتعامل مع العطل
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
            boolean inputError = false;
            do{
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
        return "Employee{" + "id = " + id + ", name = " + name + ", password = " + password + ", email = " + email + ", phoneNumber = " + phoneNumber + ", typeEmployee = " + type + ", status = " + status + ", attendanceTime = " + attendanceTime + ", departureTime = " + departureTime + '}';
    }  
}