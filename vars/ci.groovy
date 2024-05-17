def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts =""
  }
  pipeline {
    agent any

    stages {

      stage('compile/build') {
        when { not { branch 'main' } }
        steps {
          script {
            common.compile()
          }
        }
      }

      stage('test cases') {
        steps {
          script {
            common.testcases()
          }
        }
      }

      stage('code quality') {
        steps {
          script {
            common.codequality()
          }
        }
      }

    }

    post {
      failure {
        mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'friendscreations634@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline Failed", to: 'friendscreations634@gmail.com'
      }
    }
  }
}

