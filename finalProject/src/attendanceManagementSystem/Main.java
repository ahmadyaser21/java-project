package attendanceManagementSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
            
        // هدول السطرين بأشرو على مكان وجود ملف حفظ بيانات النظام في جهازك
        String filePath = System.getProperty("user.dir");
        File fileName = new File(filePath + "\\fileSystem.dat");

        // في حال كان الملف موجود مسبقا في الجهاز نفذ الأوامر التالية
        if(fileName.exists()){
            // سيتم مباشرة الإستعانة بهذه الكلاسات لقراءة البيانات من الملف واحضارها الى النظام
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // ليتم حفظ البيانات المحضرة من الملف الى النظام بهذه الطريقة البسيطة CompanyManagementمن نوع الكلاس Objectقمت بإنشاء 
            CompanyManagement companyToRead = (CompanyManagement)ois.readObject();
            companyToRead.startAndEndSystem(); // CompanyManagementهذه اهم دالة في النظام والتي يبدأ وينتهي عندها النظام وقد تم شرحها في الكلاس
            
            //عن طريق حذف القديم وتخزين الجديد ..بعد تشغيل النظام وعمل تعديلات عليه سيتم هنا حفظ ما تم تعديله على البيانات السابقة 
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(companyToRead);
            
            // قمت بغلق الكلاسات الخاصة بالكتابة في الملف ليتم حفظ البيانات
            oos.close();
            fos.close();
            
            // قمت بغلق الكلاسات الخاصة بالقراءة من باب انو افضل لسلامة النظام من اي خطأ متوقع
            ois.close();
            fis.close();
            
        }else{// سيتم الدخول هنا فقط في حال كانت اول مرة يتم فيها تشغيل النظام
            
            
            // ليتم حفظ البيانات المحضرة من الملف الى النظام بهذه الطريقة البسيطة CompanyManagementمن نوع الكلاس Objectقمت بإنشاء 
            CompanyManagement companyToRead = new CompanyManagement();
            companyToRead.startAndEndSystem();
            
            //عن طريق حذف القديم وتخزين الجديد ..بعد تشغيل النظام وعمل تعديلات عليه سيتم هنا حفظ ما تم تعديله على البيانات السابقة 
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(companyToRead);

            // قمت بغلق الكلاسات الخاصة بالكتابة في الملف ليتم حفظ البيانات
            oos.close();
            fos.close();
        }
        
    }
}