pipeline {
    
  if (isUnix()) --> sh "command"
     else --> bat "command"
    
    agent any
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
} 
