import org.codehaus.groovy.tools.groovydoc.SimpleGroovyTag

def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts =""
  }

  if (env.TAG_NAME ==~ ".*") {
    env.STAG = "true"
  } else {
    env.STAG = "false"
  }
  node('workstation') {

    try {
      stage('check out code') {
        cleanWs()
        git branch: 'main', url: "https://github.com/purnavr/${component}.git"
      }

      sh 'env'

      if (env.BRANCH_NAME != "main") {
        stage('compile/build') {
          common.compile()
        }
      }

      println STAG
      println BRANCH_NAME

      if (env.STAG != "true" && env.BRANCH_NAME != "main") {
        stage('test cases') {
          common.testcases()
        }
      }

      if (BRANCH_NAME ==~ "PR-.*"){
        stage('code quality') {
          common.codequality()
        }
      }

      if (env.STAG == "true") {
        stage('package') {
          common.prepareArtifacts()
        }
        stage('Artifact Upload') {
          common.testcases()
        }
      }

    } catch (e) {
      mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'friendscreations634@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline Failed", to: 'friendscreations634@gmail.com'
    }

  }
}



//      if (env.TAG_NAME != ".*" && env.BRANCH_NAME != "main") {
//        stage('test cases') {
//          common.testcases()
//        }
//      }
