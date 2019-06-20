@Retention(value = AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Execute

class Demo {
    fun method1() {
        println("running inside method1")
    }

    @Execute
    fun method2() {
        println("running inside method2")
    }


    fun method3() {
        println("running inside method3")
    }

    @Execute
    fun method4() {
        println("running inside method4")
    }
}


fun main() {
    val demo = Demo()
    val demoClass = demo.javaClass

    val methods = demoClass.methods
    methods.forEach { method ->
        val annotation = method.getAnnotation(Execute::class.java)
        annotation?.let {
            try {
                method.invoke(demo)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}