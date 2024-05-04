def call() {
  pipeline {
    agent any

    stages {

      stage('init') {
        steps {
          sh 'terraform init -backend-config=env-dev/state.tfvars'
        }
      }

      stage('apply') {
        steps {
          sh 'terraform apply -auto-approve -var-file=env-dev/main.tfvars'
        }
      }
    }
  }
}