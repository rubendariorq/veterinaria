@Library('ceiba-jenkins-library') _
pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }

  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 	disableConcurrentBuilds()
  }

  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK8_Centos' //Verisión preinstalada en la Configuración del Master
  }

  //Aquí comienzan los “items” del Pipeline
  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout scm
      }
    }

    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Clean Tests<------------"
        sh 'chmod +x gradlew'
        sh './gradlew --b ./build.gradle clean'

        echo "------------>Compile & Unit Tests<------------"
        sh 'chmod +x gradlew'
        sh './gradlew --b ./build.gradle test'
      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'

        sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:veterinaria-ruben.rodriguez',
                sonarName:'CeibaADN-Veterinaria[ruben.rodriguez]',
                sonarPathProperties:'./sonar-project.properties')
        }
      }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
        sh './gradlew --b ./build.gradle build -x test'
      }
    }

  post {
    success {
      echo 'This will run only if successful'
      junit 'build/test-results/test/*.xml'
    }
    failure {
      echo 'This will run only if failed'
      mail (
        to: 'ruben.rodriguez@ceiba.com.co',
        subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
        body: "Something is wrong with ${env.BUILD_URL}")
    }
  }
}
