package com.birthdaynote.app;

//@Aspect
public class ActivityHooker {
    public static ActivityRecord sActivityRecord;

    static {
        sActivityRecord = new ActivityRecord();
    }

//    @Insert(value = "onCreate", mayCreateSuper = true)
//    @TargetClass(value = "androidx.appcompat.app.AppCompatActivity", scope = Scope.ALL)
//    protected void onCreate(Bundle savedInstanceState) {
//        sActivityRecord.mOnCreateTime = System.currentTimeMillis();
//        // 调用当前Hook类方法中原先的逻辑
//        Origin.callVoid();
//    }
//
//    @Insert(value = "onWindowFocusChanged", mayCreateSuper = true)
//    @TargetClass(value = "android.app.Activity", scope = Scope.ALL)
//    public void onWindowFocusChanged(boolean hasFocus) {
//        sActivityRecord.mOnWindowsFocusChangedTime = System.currentTimeMillis();
//        Log.i(getClass().getCanonicalName(), " onWindowFocusChanged cost " + (sActivityRecord.mOnWindowsFocusChangedTime - sActivityRecord.mOnCreateTime));
//        Origin.callVoid();
//    }
//
//    @Before("execution(* *..MainActivity+.on**(..))")
//    public void method(JoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className = joinPoint.getThis().getClass().getSimpleName();
//
//        Log.e("TAG", "class:" + className);
//        Log.e("TAG", "method:" + methodSignature.getName());
//    }


    public static class ActivityRecord {

        /**
         * 避免没有仅执行onResume就去统计界面打开速度的情况，如息屏、亮屏等等
         */
        public boolean isNewCreate;

        public long mOnCreateTime;
        public long mOnWindowsFocusChangedTime;
    }
}
