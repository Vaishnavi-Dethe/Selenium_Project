package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod) {

        // ✅ Always apply retry
        annotation.setRetryAnalyzer(RetryAnalyzer.class);

        // ✅ Debug log – VERY IMPORTANT for verification
        System.out.println("RetryListener applied to test: " + testMethod.getName());
    }
}
