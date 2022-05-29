package attendanceManagementSystem;

import java.io.Serializable;

// 1 // يورث من الإنترفيسAdmin & Manager & Employeeمن خلال جعل الكلاسpolymorphismوظيفة هذا الإنترفيس هي فقط الإستفادة من مفهوم 
// 2 //  على تخزينة على شكل قيم باينري في الملف الباينري الخاص بتخزين بيانات النظامcompilerحتي يكون قادرال Serialiableوأيضا قمنا بجعل هذا الإنترفيس يرث من الإنترفيس
// 3 // لأنهم ورثو هذا من أبوهمSeriabliableلا داعي لجعل الكلاسات الأبناء يرثون من الإنترفيس Serialiableأنه في حال قمت بجعل الأب يرث من الإنترفيس:وايضا ملاحظة
public interface User extends Serializable{
    
}
