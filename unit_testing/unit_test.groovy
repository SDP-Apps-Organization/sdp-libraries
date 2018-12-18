void call(app_env){
  stage "Unit Testing", {
    node {
      def img = ""
      def command = ""
      if (fileExists("package.json")){
        img = "node"
        command = "npm install --only=dev; npm test"
      }
      else if (fileExists("pom.xml")){
        img = "maven"
        command = "mvn test -Dmaven.test.failure.ignore=false"
      }
      else{
        error "Unable to determine language: Must have a package.json or pom.xml"
      }

      docker.image(img).inside{
       unstash "workspace"
       sh command
      }
    }
  }
}
