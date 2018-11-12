#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_kmitl_it_project_project_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    //std::string hello = "Hello from C++";
    std::string hello = "";
    return env->NewStringUTF(hello.c_str());
}
