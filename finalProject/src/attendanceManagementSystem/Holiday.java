package attendanceManagementSystem;

import java.io.Serializable;
import java.util.Scanner;

// قادر على تحويله الى قيم باينري وتخزينه في ملف النظام الذي يخزن قيم باينري فقطcompilerحتى يكون الSerializable للإنترفيس إلي اسموimplementsبعمل Holidyهنا  الكلاس 
public class Holiday implements Serializable{
    private User user; // المرر في الكونستراكتور userمن خلال البراميتر attributeويتم تعيين قيمة هذا ال Aggregationهنا يوجد علاقة 
    private String name;
    private String id;
    private String reason;
    private String details;
    private String date;
    private int check;
    
    public Holiday(){
    }
    public Holiday(User user, String reason, String details, String date){
        this.user = user;
        // Manager OD Employeeعبارة عن userمن خلال التحقق هل الuser الحقيقي مش الobjectيتم تعيين الإسم ورقم الآي دي من خلال ال
        if(user instanceof Manager){
            Manager m = (Manager)this.user;
            this.name = m.getName();
            this.id = m.getId();
        }else if(user instanceof Employee){
            Employee e = (Employee)this.user;
            this.name = e.getName();
            this.id = e.getId();
        }
        this.reason = reason;
        this.details = details;
        this.date = date;
    }
    
    /*
        Getters & Setters Methods
    */
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getCheck() {
        return check;
    }
    /**
     *  ولكن تتحقق أولا من القيمة في حال كانت غير صحيحية تظهر رسالة خطأ وتطلب إعادة ادخال قيمة صحيحةcheckتقوم هذه الدالة بإسناد قيمة المتغير
     * تعتبرها صحيحة وتقوم بإسنادها للمتغير 0 - 2وإن كانت القيمة بين 
     * 
     * @param check 
     */
    public void setCheck(int check) {
        Scanner input = new Scanner(System.in);
        if(check == 0 || check == 1 || check == 2)
            this.check = check;
        else{
            while(!(check == 0 || check == 1 || check == 2)){
                invalidInput();
                check = input.nextInt();
            }
        }
        this.check = check;
    }
    
    /*
        Utility methods
    */
    
    /**
     * وظيفتها فقط إظهار رسالة الخطأ كلما تم استدعائها في الدوال الأخرى في هذا الكلاس
     */
    public void invalidInput(){
        System.out.println("Invalid Input..");
        System.out.print("- Please, Enter valid input: ");
    }
    /**
     * toStringالحالي بالإستعانة بدالة  Objectلعمل تقرير عن كل بيانات ال
     */
    public void report(){
        System.out.println(this.toString());
    }
    @Override
    public String toString() {
        return "Id = " + id + " || Name = " + name + " || check: " + check + "\nReason = " + reason + "\nDetails = " + details + "\ndate = " + date;
    }
}