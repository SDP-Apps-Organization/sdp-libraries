void call(app_env){
  stage "Unit & Syntax Testing", {
    node {
      def img = ""
      def unit_command = ""
      def syntax_command = ""
      if (fileExists("package.json")){
        img = "node"
        //unit_command = "npm install --only=dev; npm test"
        unit_command = "ls"
        syntax_command = "/bin/bash -c \'walk() { cd \"\$1\"; for file in *; do if [ -d \"\$file\" ] && [ \"\$file\" != \"node_modules\" ]; then walk \"\$1/\$file\"; else if [[ ${file: -3} == \".js\" ]]; then node -c \"\$file\"; fi; fi; cd \"\$1\"; done; }; walk `pwd`\'"
      }
      else if (fileExists("pom.xml")){
        img = "maven"
        unit_command = "mvn test -Dmaven.test.failure.ignore=false"
        syntax_command = "mvn package clean -DskipTests"
      }
      else{
        error "Unable to determine language: Must have a package.json or pom.xml"
      }
      docker.image(img).inside{
       unstash "workspace"
       echo "Running Unit Tests..."
       sh unit_command
       echo "Running Syntax Tests..."
       sh syntax_command
      }
    }
  }
}
