rootProject.name = "kfc-plugins"

include("examples:component-extension")
include("examples:component-extension-multiplatform")
include("examples:multiple-output")

include("examples:web-component-extension")
include("examples:yfiles-web-component")

include("examples:resources:lib-a")
include("examples:resources:lib-b")
include("examples:resources:lib-c")
include("examples:resources:app-d")

include("examples:webpack-plus-ktor:entity")
include("examples:webpack-plus-ktor:server")
include("examples:webpack-plus-ktor:view")
include("examples:webpack-plus-ktor:dev-server")

include("examples:yfiles-optimizer")

includeBuild("gradle-plugin")
