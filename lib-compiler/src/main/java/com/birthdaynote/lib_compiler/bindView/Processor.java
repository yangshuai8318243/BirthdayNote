package com.birthdaynote.lib_compiler.bindView;

import com.birthdaynote.lib_annotations.bindView.BindView;
import com.birthdaynote.lib_annotations.bindView.Keep;
import com.birthdaynote.lib_annotations.bindView.OnClick;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import sun.reflect.generics.tree.TypeTree;

public class Processor extends AbstractProcessor{

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new TreeSet<>(
                                Arrays.asList(BindView.class.getCanonicalName(),
                                            OnClick.class.getCanonicalName(),
                                            Keep.class.getCanonicalName()
                                            )
                                );
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<TypeElement> typeElements = getTypeElementsByAnnotationType(set, roundEnvironment.getRootElements());

        for (TypeElement typeElement:typeElements) {
            //包名
            String pkgName = mElementUtils.getPackageOf(typeElement).getQualifiedName().toString();
            //类名
            String className = typeElement.getSimpleName().toString();
            //工具自动生成类名
            ClassName autoClassName = ClassName.get(pkgName, NameUtils.getAutoGeneratorTypeName(className));
            //构建自动生成的类
            TypeSpec.Builder builder = TypeSpec.classBuilder(autoClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Keep.class);
            //添加构造方法
            builder.addMethod(
                    MethodSpec.constructorBuilder().
                            addModifiers(Modifier.PUBLIC)
                            .addParameter(autoClassName,NameUtils.Variable.ANDROID_ACTIVITY)
                            .addStatement("$N($N)",NameUtils.Method.BIND_VIEW,NameUtils.Variable.ANDROID_ACTIVITY)
                            .addStatement("$N($N)",NameUtils.Method.SET_ON_CLICK_LISTENER,NameUtils.Variable.ANDROID_ACTIVITY)
                    .build()
            );

            MethodSpec.Builder bindViewBuilde = MethodSpec.methodBuilder(NameUtils.Method.BIND_VIEW)
                    .addModifiers(Modifier.PRIVATE)
                    .returns(TypeName.VOID)
                    .addParameter(autoClassName, NameUtils.Variable.ANDROID_ACTIVITY);

            //添加方法内容
            for (VariableElement variableElement: ElementFilter.fieldsIn(typeElement.getEnclosedElements())) {
                BindView bindView = variableElement.getAnnotation(BindView.class);
                if (bindView != null){
                    bindViewBuilde.addStatement("$N.$N = ($T)$N.findViewById($L)",
                            NameUtils.Variable.ANDROID_ACTIVITY,
                            variableElement.getSimpleName(),
                            variableElement,
                            NameUtils.Variable.ANDROID_ACTIVITY,
                            bindView.value());

                }
            }
            builder.addMethod(bindViewBuilde.build());

        }

        return false;
    }

    private Set<TypeElement> getTypeElementsByAnnotationType(Set<? extends TypeElement> annotations, Set<? extends Element> elements){
        HashSet<TypeElement> result = new HashSet<>();
//        遍历所有的class类
        for (Element element:elements) {
//            匹配 class or interface
            if (element instanceof TypeElement){
                boolean found = false;
                /**
                 * ExecutableElement	表示某个类或接口的方法、构造方法或初始化程序（静态或实例），包括注解类型元素。
                 * PackageElement	表示一个包程序元素。提供对有关包及其成员的信息的访问。
                 * TypeElement	表示一个类或接口程序元素。提供对有关类型及其成员的信息的访问。注意，枚举类型是一种类，而注解类型是一种接口。
                 * TypeParameterElement	表示一般类、接口、方法或构造方法元素的形式类型参数。
                 * VariableElement	表示一个字段、enum 常量、方法或构造方法参数、局部变量或异常参数。
                 */
                //遍历 element 中所有的注解
                for (Element subElement : element.getEnclosedElements()) {
                    for (AnnotationMirror annotationMirror: subElement.getAnnotationMirrors()) {
                        for (TypeElement typeElement:annotations) {
                            //匹配注解
                            if (annotationMirror.getAnnotationType().asElement().equals(element)){
                                result.add((TypeElement) element);
                                found = true;
                                break;
                            }
                        }
                        if (found)break;
                    }
                    if (found)break;
                }

            }
        }
        return result;
    }
}
