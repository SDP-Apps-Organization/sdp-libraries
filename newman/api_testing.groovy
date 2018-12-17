def call(){
  stage "API Testing", {
    run_tests = config.dev_Run_Newman_Tests ?:
          false
    if (run_tests){
      echo "running api tests"
    }
    else{
      echo "skipping running api tests"
    }
  }
}
