import de.codepitbull.module.example.MyService;

module javatest.dependency.main {
    exports de.codepitbull.module.example;
    provides MyService with de.codepitbull.module.example.MyServiceImpl;
}