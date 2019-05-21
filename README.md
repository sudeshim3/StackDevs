
```
private val greeting: TextView
get() = findViewById(R.id.greeting)

NOTE: findViewById is called everytime you use it

ALTERNATIVE
private val greeting by lazy {
  findViewById<TextView>(R.id.txt_greet)
}
```
### Dagger   [![Dagger][dagger-svg]][dagger-maven]
### Retrofit [![Retrofit Maven][retrofit-svg]][retofit-maven]
### Gson     [![Gson Maven][gson-svg]][gson-maven]


[dagger-svg]: https://maven-badges.herokuapp.com/maven-central/com.google.dagger/dagger/badge.svg
[dagger-maven]: https://search.maven.org/artifact/com.google.dagger/dagger


[retrofit-svg]:https://maven-badges.herokuapp.com/maven-central/com.squareup.retrofit2/retrofit/badge.svg
[retofit-maven]:https://search.maven.org/artifact/com.squareup.retrofit2/parent

[gson-svg]:https://maven-badges.herokuapp.com/maven-central/com.google.code.gson/gson/badge.svg
[gson-maven]:https://search.maven.org/artifact/com.google.code.gson/gson-parent/


https://maven-badges.herokuapp.com/maven-central/com.google.code.gson/gson/badge.svg

