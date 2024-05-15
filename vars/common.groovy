def compile() {
  if (app_lang == 'nodejs') {
    sh 'npm install'
  }
  if (app_lang == 'maven') {
    sh 'mvn package'
  }
}

def codequality() {
  sh 'sonar-scanner -Dsonar.host.url=http://172.31.80.191:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} ${sonar_extra_opts} -Dsonar.qualitygate.wait=true'
}




