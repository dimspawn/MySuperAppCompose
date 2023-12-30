import dependencies.MyDependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? {
    return add("implementation", dependencyNotation)
}
fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? {
    return add("testImplementation", dependencyNotation)
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? {
    return add("androidTestImplementation", dependencyNotation)
}

fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? {
    return add("debugImplementation", dependencyNotation)
}

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? {
    return add("kapt", dependencyNotation)
}

fun DependencyHandler.api(dependencyNotation: String): Dependency? {
    return add("api", dependencyNotation)
}

fun DependencyHandler.coreDependencies() {
    api(MyDependencies.core_ktx)
    api(MyDependencies.lifecycle_ktx)

    api(MyDependencies.material_compose)
    api(MyDependencies.activity_compose)
    api(MyDependencies.ui_compose)
    api(MyDependencies.ui_tooling_preview)
    api(MyDependencies.navigation_compose)

    testImplementation(MyDependencies.mockito_kotlin)
    testImplementation(MyDependencies.coroutines_test)

    api(MyDependencies.retrofit)
    api(MyDependencies.retrofit2_converter_gson)
    api(MyDependencies.retrofit2_adapter_rxjava2)
    api(MyDependencies.okhttp3)

    api(MyDependencies.coil)
    api(MyDependencies.room)
    kapt(MyDependencies.room_kapt)

    api(MyDependencies.room_ktx)

    api(MyDependencies.accompanist_pager)
    api(MyDependencies.accompanist_pager_indicator)

    api(MyDependencies.accompanist_system_ui_controller)
}

fun DependencyHandler.mainDependencies() {
    testImplementation(MyDependencies.junit)
    androidTestImplementation(MyDependencies.test_ext_junit)
    androidTestImplementation(MyDependencies.espresso_core)
    androidTestImplementation(MyDependencies.junit_compose)
    debugImplementation(MyDependencies.ui_tooling)
    debugImplementation(MyDependencies.ui_test_manifest)

    //Dagger
    implementation(MyDependencies.dagger)
    kapt(MyDependencies.dagger_kapt)
}