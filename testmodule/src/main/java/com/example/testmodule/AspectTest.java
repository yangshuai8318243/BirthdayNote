package com.example.testmodule;

//@Aspect
public class AspectTest {
    final String TAG = AspectTest.class.getSimpleName();

//    @Pointcut("call(* com.example.testmodule.TestLeak.fly(..))")
//    public void callMethod() {
//        Log.e(TAG, "callMethod->");
//    }
//
////    @After("execution(* android.app.Activity.on**(..))")
////    public void onResumeMethod(JoinPoint joinPoint) throws Throwable {
////        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
////    }
//
//    @Before("callMethod()")
//    public void beforeMethodCall(JoinPoint joinPoint) {
//        Log.e(TAG, "getTarget->" + joinPoint.getTarget());
//        Log.e(TAG, "getThis->" + joinPoint.getThis());
//    }
//
//
//    @Before("execution(*callMethod()")
//    public void method(JoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className = joinPoint.getThis().getClass().getSimpleName();
//        Log.e(TAG,"--------------1111111111111-------------------->");
//        Log.e(TAG, "class:" + className);
//        Log.e(TAG, "method:" + methodSignature.getName());
//    }

}
