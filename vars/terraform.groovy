def call() {
  pipeline {
    agent any

    parameters {
      string(name: 'ENV', defaultValue: '', description: 'which env')
      string(name: 'ACTION', defaultValue: '', description: 'which action')
    }

    options {
      ansiColor('xterm')
    }

    stages {

      stage('init') {
        steps {
          sh 'terraform init -backend-config=env-${ENV}/state.tfvars'
        }
      }

      stage('apply') {
        steps {
          sh 'terraform ${ACTION} -auto-approve -var-file=env-${ENV}/main.tfvars'
//          sh 'echo'
        }
      }
    }
  }
}