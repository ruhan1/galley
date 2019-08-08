pipeline {
   agent { label 'maven' }
   stages {
      stage('Build') {
         steps {
            sh 'mvn -DskipTests -B clean verify'
         }
      }
   }
}
