pipeline {
    agent any
	
	stages{
        stage('Build'){
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.jar'
                }
            }
        }
	}
}
